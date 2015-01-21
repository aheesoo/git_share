package com.kt.olleh.hms.sysspec.vo;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.smcp.gw.ca.util.ConvertUtil;
import com.kt.smcp.gw.ca.util.StringUtil;

import com.kt.olleh.hms.sysspec.util.HomeCameraUtil;

public abstract class HomeCameraOutbndVO extends HomeCameraVO {

	private static final Logger	logger	= LoggerFactory.getLogger(HomeCameraOutbndVO.class);

	public HomeCameraOutbndVO() {
		super();
	}

	public HomeCameraOutbndVO(byte[] dataBytes) {
		super();
		double version = HomeCameraUtil.extractVersion(dataBytes);
		parseHeader(dataBytes);
		resetDataSize(version);
	}

	public void parsePacket(byte[] dataBytes) {
		double version = HomeCameraUtil.extractVersion(dataBytes);
		parseHeader(dataBytes);
		resetDataSize(version);
	}

	protected void resetDataSize(double version) {
		int length = calculateProtocolLength(this.getClass(), version);

		// length < 0 : There is a field with dynamic length.
		if (length < 0)
			length = calculateDataLength(version);

		setDataSize(length);
	}

	protected int calculateDataLength(double version) {
		int length = 0;

		List<Field> fields = getTargetFields(this.getClass(), version);

		for (Field f : fields)
		{
			int fieldLength = f.getAnnotation(FieldHint.class).length();
			if (fieldLength < 0)
			{
				try
				{
					f.setAccessible(true);
					Object obj = f.get(this);
					if (null == obj)
						fieldLength = 0;
					else
					{
						byte[] barray = (byte[]) obj;
						fieldLength = barray.length;
					}
				} catch (IllegalArgumentException | IllegalAccessException e)
				{
					throw new RuntimeException("The byte[] is not set yet.");
				}
			}
			length += fieldLength;
		}
		return length;
	}

	protected byte[] getMappedBytes(byte[] dataBytes, Class<? extends HomeCameraVO> c) {
		double version = this.getFullVersion();
		List<Field> fields = getTargetFields(c, version);

		int offset = 0;

		for (Field field : fields)
		{
			logger.debug("# Converting Field : " + field.getName());
			FieldHint anno = field.getAnnotation(FieldHint.class);

			int fieldLength;

			fieldLength = anno.length();

			Class<?> fieldType = field.getType();

			field.setAccessible(true);

			try
			{
				if (fieldType == String.class)
				{
					stringToBytes(field, dataBytes, offset);
				}
				else if (fieldType == Integer.class)
				{
					intToBytes(field, dataBytes, offset, fieldLength);
				}
				else if (fieldType == Long.class)
				{
					longToBytes(field, dataBytes, offset, fieldLength);
				}
				else if (fieldType == UUID.class)
				{
					uuidToBytes(field, dataBytes, offset, fieldLength);
				}
				else if (fieldType == byte[].class)
				{
					bytesToBytes(field, dataBytes, offset, fieldLength);
				}

			} catch (IllegalArgumentException | IllegalAccessException e)
			{
				throw new RuntimeException("Failed to get " + fieldType.getSimpleName() + " \"" + field.getName() + "\" of Class \"" + c.getName() + "\".", e);
			}

			offset += fieldLength;

			if (anno.length() < 0)
				break;
		}

		return dataBytes;
	}

	public byte[] getHeaderPacket() {
		double version = getFullVersion();
		resetDataSize(version);
		byte[] headerPacket = new byte[this.headerLength(version)];
		logger.debug("################## Start Creating Header ##################");
		logger.debug("# Getting header packet of length : " + this.headerLength(this.getFullVersion()));
		this.getMappedBytes(headerPacket, HomeCameraVO.class);
		logger.debug("# Header Packet [ " + headerPacket.length + " bytes ] : " + StringUtil.byteArrayToHex(headerPacket));
		logger.debug("################## End Creating Header ##################");
		return headerPacket;
	}

	public byte[] getBodyPacket() {
		byte[] bodyPacket = new byte[this.bodyLength(this.getFullVersion())];
		logger.debug("################## Start Creating Body ##################");
		logger.debug("# Getting body packet of length : " + this.bodyLength(this.getFullVersion()));
		this.getMappedBytes(bodyPacket, this.getClass());
		logger.debug("# Body Packet [ " + bodyPacket.length + " bytes ] : " + ((bodyPacket.length <= 150) ? StringUtil.byteArrayToHex(bodyPacket) : bodyPacket.length + " bytes of data."));
		logger.debug("################## End Creating Body ##################");
		return bodyPacket;
	}

	public byte[] getFullPacket() {
		byte[] headerPacket = getHeaderPacket();
		byte[] bodyPacket = getBodyPacket();

		byte[] fullPacket = new byte[headerPacket.length + bodyPacket.length];
		System.arraycopy(headerPacket, 0, fullPacket, 0, headerPacket.length);
		System.arraycopy(bodyPacket, 0, fullPacket, headerPacket.length, bodyPacket.length);

		return fullPacket;
	}

	private void stringToBytes(Field field, byte[] dataBytes, int offset) throws IllegalArgumentException, IllegalAccessException {
		Object obj = field.get(this);
		if (null != obj)
		{
			String value = (String) obj;
			byte[] bytes = value.getBytes();
			logger.debug("# Converting String \"" + value + "\" to Byte[] \"" + StringUtil.byteArrayToHex(bytes) + "\".");
			System.arraycopy(bytes, 0, dataBytes, offset, bytes.length);
		}
	}

	private void intToBytes(Field field, byte[] dataBytes, int offset, int fieldLength) throws IllegalArgumentException, IllegalAccessException {
		Object obj = field.get(this);
		if (null != obj)
		{
			Integer value = (int) obj;
			byte[] bytes = ConvertUtil.intTobytes(value);
			logger.debug("# Converting Integer \"" + value + "\" to Byte[] \"" + StringUtil.byteArrayToHex(bytes) + "\".");
			System.arraycopy(bytes, 0, dataBytes, offset, fieldLength);
		}
	}

	private void longToBytes(Field field, byte[] dataBytes, int offset, int fieldLength) throws IllegalArgumentException, IllegalAccessException {
		Object obj = field.get(this);
		if (null != obj)
		{
			Long value = (long) obj;
			byte[] bytes = ConvertUtil.longTobytes(value);
			logger.debug("# Converting Long \"" + value + "\" to Byte[] \"" + StringUtil.byteArrayToHex(bytes) + "\".");
			System.arraycopy(bytes, 0, dataBytes, offset, fieldLength);
		}
	}

	private void uuidToBytes(Field field, byte[] dataBytes, int offset, int fieldLength) throws IllegalArgumentException, IllegalAccessException {
		Object obj = field.get(this);
		if (null != obj)
		{
			UUID value = (UUID) obj;
			String uuid = value.toString().toUpperCase();
			byte[] bytes = new byte[fieldLength];
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			buffer.order(ByteOrder.BIG_ENDIAN);
			buffer.putLong(value.getMostSignificantBits());
			buffer.putLong(value.getLeastSignificantBits());
			logger.debug("# Converting UUID \"" + uuid + "\" to Byte[] \"" + StringUtil.byteArrayToHex(buffer.array()) + "\".");
			System.arraycopy(buffer.array(), 0, dataBytes, offset, fieldLength);
		}
	}

	private void bytesToBytes(Field field, byte[] dataBytes, int offset, int fieldLength) throws IllegalArgumentException, IllegalAccessException {
		Object obj = field.get(this);
		if (null != obj)
		{
			fieldLength = (fieldLength >= 0) ? fieldLength : bodyLength(this.getFullVersion()) - offset;
			byte[] value = (byte[]) obj;
			logger.debug("# Converting Byte[] \"" + StringUtil.byteArrayToHex(value) + "\" to Byte[] \"" + StringUtil.byteArrayToHex(value) + "\".");
			System.arraycopy(value, 0, dataBytes, offset, fieldLength);
		}
	}

}
