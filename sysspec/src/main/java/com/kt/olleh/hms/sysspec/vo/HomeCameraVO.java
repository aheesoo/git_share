package com.kt.olleh.hms.sysspec.vo;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.FieldHintIndexComparator;
import com.kt.olleh.hms.sysspec.util.HomeCameraUtil;
import com.kt.smcp.gw.ca.util.StringUtil;

/**
 * @author Lee Sung-Bum (thececil@gmail.com)
 *
 *         2014. 10. 27.
 */
public abstract class HomeCameraVO {

	private static final Logger	logger	= LoggerFactory.getLogger(HomeCameraVO.class);

	/** Header Definition **/
	@FieldHint(index = 0, length = 8)
	private String				Name;

	public void setName(String name) {
		Name = name;
	}

	public String getName() {
		return Name;
	}

	@FieldHint(index = 1, length = 4)
	private Integer	Version;

	public void setVersion(int version) {
		Version = version;
	}

	public Integer getVersion() {
		return Version;
	}

	public Integer getMajorVer() {
		return (Version >> 16) & 0xFF;
	}

	public Integer getMinorVer() {
		return (Version >> 8) & 0xFF;
	}

	public double getFullVersion() {
		return getMajorVer() + (getMinorVer() * 0.01);
	}

	@FieldHint(index = 2, length = 4)
	private Integer	Command;

	public void setCommand(int command) {
		Command = command;
	}

	public Integer getCommand() {
		return Command;
	}

	@FieldHint(index = 3, length = 4)
	private Integer	CommandID;

	public void setCommandID(int commandId) {
		CommandID = commandId;
	}

	public Integer getCommandID() {
		return CommandID;
	}

	@FieldHint(index = 4, length = 4)
	private Integer	DataSize;

	public void setDataSize(int datasize) {
		DataSize = datasize;
	}

	public Integer getDataSize() {
		return DataSize;
	}

	public HomeCameraVO() {
	}

	protected int parseHeader(byte[] dataBytes) {
		double version = HomeCameraUtil.extractVersion(dataBytes);
		if (dataBytes.length < headerLength(version))
		{
			throw new RuntimeException("Received packet is shorter than header length.");
		}

		logger.debug("################## Start Parsing Header ##################");
		int offset = parse(dataBytes, HomeCameraVO.class);
		logger.debug("################## End Parsing Header ###################");

		return offset;
	}

	protected int parseBody(byte[] dataBytes, int offset) {
		double version = this.getFullVersion(); // Get Protocol Version from
												// Packet
		int bodyLength = bodyLength(version); // Calculate Protocol Length from
												// Protocol Version
		logger.debug("# Data Length : " + (dataBytes.length - offset));
		logger.debug("# Body Length : " + bodyLength);
		if (dataBytes.length - offset < bodyLength)
		{
			throw new RuntimeException("The length of packet in body is not same as body length. [ Length of Packet Body : " + (dataBytes.length - offset) + " ], [ Length of Predefined : "
					+ bodyLength + " ] ");
		}

		logger.debug("################## Start Parsing Body ##################");
		parse(dataBytes, this.getClass(), offset, version);
		logger.debug("################## End Parsing Body ###################");
		return offset;
	}

	private int parse(byte[] dataBytes, Class<? extends HomeCameraVO> c) {
		double version = HomeCameraUtil.extractVersion(dataBytes);
		return parse(dataBytes, c, 0, version);
	}

	private int parse(byte[] dataBytes, Class<? extends HomeCameraVO> c, int offset, double version) {

		if (null == dataBytes)
		{
			throw new RuntimeException("Null packet received.");
		}

		List<Field> fields = getTargetFields(c, version);

		/** loop for parsing annotated fields using reflection **/
		for (Field field : fields)
		{
			FieldHint anno = field.getAnnotation(FieldHint.class);

			int fieldLength;

			/** if fixed length **/
			if (anno.length() > 0)
				fieldLength = anno.length();

			/** if unfixed length **/
			else
			{
				fieldLength = DataSize + headerLength(version) - offset;
			}

			byte[] extractedData = new byte[fieldLength];

			// logger.debug("# Parsing " + field.getName() + " [ " + fieldLength
			// + " bytes ] from offset " + offset + " ");
			System.arraycopy(dataBytes, offset, extractedData, 0, fieldLength);

			setField(field, this, c, extractedData);

			offset += fieldLength;

			if (anno.length() < 0)
				break;
		}

		return offset;

	}

	protected int headerLength(double version) {
		return calculateProtocolLength(HomeCameraVO.class, version);
	}

	protected int bodyLength(double version) {
		int length = calculateProtocolLength(this.getClass(), version);
		if (null != DataSize && length < 0)
			length = DataSize;
		// if (null != DataSize)
		// length = DataSize;
		return length;
	}

	protected static <T> void setField(Field field, T targetObj, Class<?> targetClass, byte[] data) {
		Class<?> fieldType = field.getType();
		String fieldTypeSimpleName = fieldType.getSimpleName();
		String fieldName = field.getName();
		String targetClassName = targetClass.getName();

		field.setAccessible(true);

		try
		{
			if (fieldType == String.class)
			{
				String value = bytesToString(data);
				field.set(targetObj, value);
				logger.debug("# " + field.getName() + " : " + value);
			}
			else if (fieldType == Integer.class)
			{
				Integer value = bytesToInteger(data);
				field.set(targetObj, bytesToInteger(data));
				logger.debug("# " + field.getName() + " : " + value);
			}
			else if (fieldType == byte[].class)
			{
				byte[] value = data;
				field.set(targetObj, value);
				logger.debug("# " + field.getName() + " : " + ((value.length <= 50) ? StringUtil.byteArrayToHex(value) : value.length + " bytes of data."));
			}
			else if (fieldType == UUID.class)
			{
				UUID value = bytesToUUID(data);
				field.set(targetObj, value);
				logger.debug("# " + field.getName() + " : " + value.toString());
			}
			else
				throw new RuntimeException("Setting field of " + fieldTypeSimpleName + " is not yet implemented.");
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			throw new RuntimeException("Failed to set " + fieldTypeSimpleName + " \"" + fieldName + "\" of Class \"" + targetClassName + "\".");
		}

	}

	/**
	 * @param c
	 * @param version
	 * @return
	 */
	protected int calculateProtocolLength(Class<? extends HomeCameraVO> c, double version) {
		int length = 0;
		List<Field> fields = getTargetFields(c, version);

		for (Field f : fields)
		{
			if (f.isAnnotationPresent(FieldHint.class))
			{
				int fieldLength = f.getAnnotation(FieldHint.class).length();
				if (fieldLength < 0)
				{
					length = -1;
					break;
				}
				length += fieldLength;
			}

		}
		return length;
	}

	private static String bytesToString(byte[] src) {
		StringBuffer sb = new StringBuffer();
		for (byte b : src)
		{
			if (b == 0)
				break;

			sb.append((char) b);
		}
		String result = sb.toString();
		return result;
	}

	private static Integer bytesToInteger(byte[] src) {
		int result = ByteBuffer.wrap(src).getInt();
		return result;
	}

	private static UUID bytesToUUID(byte[] src) {
		ByteBuffer buffer = ByteBuffer.wrap(src);
		buffer.order(ByteOrder.BIG_ENDIAN);
		long firstLong = buffer.getLong();
		long secondLong = buffer.getLong();

		return new UUID(firstLong, secondLong);
	}

	public void setCmdToRes() {
		if ((this.Command & 0x80000000) != 0x00000000)
			throw new RuntimeException("Not a request.");

		this.Command = this.Command | 0x80000000;
	}

	protected LinkedList<Field> getTargetFields(Class<? extends HomeCameraVO> c, double version) {
		LinkedList<Field> fieldsList = new LinkedList<>(Arrays.asList(c.getDeclaredFields()));

		Iterator<Field> iterator = fieldsList.iterator();
		while (iterator.hasNext())
		{
			Field field = iterator.next();
			if (field.isAnnotationPresent(FieldHint.class))
			{
				FieldHint fieldHint = field.getAnnotation(FieldHint.class);
				if (version >= fieldHint.minVer() && version <= fieldHint.maxVer())
					continue;
			}

			iterator.remove();
		}

		/** sorting fields list by HCFieldHint's index value **/
		Collections.sort(fieldsList, new FieldHintIndexComparator());

		return fieldsList;

	}

}
