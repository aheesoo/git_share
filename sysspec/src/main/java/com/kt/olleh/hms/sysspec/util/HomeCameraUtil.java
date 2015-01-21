package com.kt.olleh.hms.sysspec.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.vo.HomeCameraScdulVO;
import com.kt.smcp.gw.ca.domn.row.SclgData;
import com.kt.smcp.gw.ca.domn.row.SclgSetupData;
import com.kt.smcp.gw.ca.domn.row.SclgTimeData;
import com.kt.smcp.gw.ca.util.ConvertUtil;

public class HomeCameraUtil {


	private static final Logger logger = LoggerFactory.getLogger(HomeCameraUtil.class);

	static final int VERSION_OFFSET = 8;
	static final int VERSION_LENGTH = 4;
	static final int COMMAND_OFFSET = 12;
	static final int COMMAND_LENGTH = 4;
	static final int DEVICEID_OFFSET = 24;
	static final int DEVICEID_LENGTH = 16;

	private HomeCameraUtil() {

	}

	public static int extractCommand(byte[] packet) {

		if (packet.length < HomeCameraUtil.COMMAND_OFFSET + HomeCameraUtil.COMMAND_LENGTH)
			throw new RuntimeException("The byte array is too short to include command.");

		byte[] commandCode = new byte[HomeCameraUtil.COMMAND_LENGTH];
		System.arraycopy(packet, HomeCameraUtil.COMMAND_OFFSET, commandCode, 0, HomeCameraUtil.COMMAND_LENGTH);
		return ConvertUtil.bytesToint(commandCode);

	}

	public static UUID extractDeviceId(byte[] packet) {
		if (packet.length < DEVICEID_OFFSET + DEVICEID_LENGTH)
			throw new RuntimeException("The byte array is too short to include device id.");

		ByteBuffer buffer = ByteBuffer.wrap(packet);
		buffer.position(DEVICEID_OFFSET);

		return new UUID(buffer.getLong(), buffer.getLong());
	}

	/**
	 * @param packet
	 * @return	Protocol Version
	 */
	public static double extractVersion(byte[] packet) {
		if (packet.length < HomeCameraUtil.VERSION_OFFSET + HomeCameraUtil.VERSION_LENGTH)
			throw new RuntimeException("The byte array is too short to include version.");

		byte[] version = new byte[HomeCameraUtil.VERSION_LENGTH];
		System.arraycopy(packet, HomeCameraUtil.VERSION_OFFSET, version, 0, HomeCameraUtil.VERSION_LENGTH);

		int value =  ConvertUtil.bytesToint(version);

		double majorVersion = (value >> 16 & 0xFF);
		double minorVersion = (value >> 8 & 0xFF);
		double fullVersion = majorVersion + minorVersion * 0.01;

		logger.debug("# Protocol Ver. : " + fullVersion);

		return fullVersion;
	}

	public static SclgSetupData revertSimpleSchedule(String timeFrom, String timeTo, final int day) {
		SclgSetupData sclgSetupData = new SclgSetupData();
		List<SclgData> sclgDatas = sclgSetupData.getSclgDatas();

		int tmpDay = day;

		for (int i = 0; i < 7; i++)
		{
			tmpDay = day >>> i;
			if ((tmpDay & 0x00000001) == 0x00000001)
			{
				SclgData sclgData = new SclgData();
				sclgData.setDowCd(Integer.toString(i + 1));
				SclgTimeData sclgTimeData = new SclgTimeData();
				sclgTimeData.setStTime(timeFrom);
				sclgTimeData.setFnsTime(timeTo);
				sclgData.getSclgTimeDatas().add(sclgTimeData);
				sclgDatas.add(sclgData);
			}
		}

		return sclgSetupData;
	}

	public SclgSetupData revertSchedule(String timeFrom, String timeTo, final int day) {
		SclgSetupData sclgSetupData = new SclgSetupData();
		List<SclgData> sclgDatas = sclgSetupData.getSclgDatas();

		int intTimeFrom;
		int intTimeTo;
		try
		{
			intTimeFrom = Integer.parseInt(timeFrom);
		} catch (NumberFormatException e)
		{
			intTimeFrom = 0;
		}

		try
		{
			intTimeTo = Integer.parseInt(timeTo);
		} catch (NumberFormatException e)
		{
			intTimeTo = 2400;
		}

		int tmpDay = day;
		boolean pDay = false; // 전일 스케줄 여부

		for (int i = 0; i < 7; i++)
		{
			tmpDay = day >>> i;
			if ((tmpDay & 0x00000001) == 0x00000001)
			{
				SclgData sclgData = new SclgData();
				sclgData.setDowCd(Integer.toString(i + 1));
				if (intTimeFrom <= intTimeTo)
				{
					SclgTimeData sclgTimeData = new SclgTimeData();
					sclgTimeData.setStTime(timeFrom);
					sclgTimeData.setFnsTime(timeTo);
					sclgData.getSclgTimeDatas().add(sclgTimeData);
				}
				else
				{
					if (i == 0 && (((tmpDay >>> 6) & 0x00000001) == 0x00000001))
						pDay = true;

					if (pDay && (intTimeFrom > intTimeTo))
					{
						SclgTimeData sclgTimeDataFirst = new SclgTimeData();
						sclgTimeDataFirst.setStTime("0000");
						sclgTimeDataFirst.setFnsTime(timeTo);
						sclgData.getSclgTimeDatas().add(sclgTimeDataFirst);
					}
					SclgTimeData sclgTimeDataSecond = new SclgTimeData();
					sclgTimeDataSecond.setStTime(timeFrom);
					sclgTimeDataSecond.setFnsTime("2400");
					sclgData.getSclgTimeDatas().add(sclgTimeDataSecond);
					pDay = true;
				}
				sclgDatas.add(sclgData);
			}
			else {
				if (pDay)
				{
					SclgData sclgData = new SclgData();
					sclgData.setDowCd(Integer.toString(i + 1));
					SclgTimeData sclgTimeDataFirst = new SclgTimeData();
					sclgTimeDataFirst.setStTime("0000");
					sclgTimeDataFirst.setFnsTime(timeTo);
					sclgData.getSclgTimeDatas().add(sclgTimeDataFirst);
					sclgDatas.add(sclgData);
					pDay = false;
				}
			}
		}

		return sclgSetupData;
	}

	public static int getDayOfWeek(String dayOfWeek) {
		if (null == dayOfWeek)
			return 0x00000000;
		else if (dayOfWeek.equals("0"))
			return 0x0000007F;
		else if (dayOfWeek.equals("1"))
			return 0x00000001;
		else if (dayOfWeek.equals("2"))
			return 0x00000002;
		else if (dayOfWeek.equals("3"))
			return 0x00000004;
		else if (dayOfWeek.equals("4"))
			return 0x00000008;
		else if (dayOfWeek.equals("5"))
			return 0x00000010;
		else if (dayOfWeek.equals("6"))
			return 0x00000020;
		else if (dayOfWeek.equals("7"))
			return 0x00000040;

		throw new RuntimeException("Unsupported day of week.");
	}

	public static HomeCameraScdulVO getSchedule(SclgSetupData sclgSetupData) {
		HomeCameraScdulVO homeCameraScdulVO = new HomeCameraScdulVO();

		List<SclgData> sclgDatas = sclgSetupData.getSclgDatas();

		int daysOfWeek = 0x00000000;
		String calcStartTime = null;
		String calcFinishTime = null;

		for (SclgData sclgData : sclgDatas)
		{
			List<SclgTimeData> sclgTimeDatas = sclgData.getSclgTimeDatas();

			daysOfWeek |= getDayOfWeek(sclgData.getDowCd());

			for (SclgTimeData sclgTimeData : sclgTimeDatas)
			{
				String startTime = sclgTimeData.getStTime();
				String finishTime = sclgTimeData.getFnsTime();

				if (startTime == null || finishTime == null)
				{
					throw new RuntimeException("Both of Start Time, Finish Time in Schedule cannot be null.");
				}

				if (calcStartTime == null)
				{
					// Meet First Schedule
					calcStartTime = startTime;
					calcFinishTime = finishTime;
				}
				else
				{
					try
					{
						int intStartTime = Integer.parseInt(startTime);
						int intFinishTime = Integer.parseInt(finishTime);
						int intCalcStartTime = Integer.parseInt(calcStartTime);
						int intCalcFinishTime = Integer.parseInt(calcFinishTime);

						if (intFinishTime == intCalcFinishTime && intStartTime == intCalcStartTime)
						{
							continue;
						}
						else if (intFinishTime <= intCalcStartTime)
						{
							intCalcFinishTime = intFinishTime;
						}
						else if (intStartTime >= intCalcFinishTime)
						{
							intCalcStartTime = intStartTime;
						}
						else
						{
							throw new RuntimeException("Cannot set a schedule of two periods.");
						}
					} catch (NumberFormatException e)
					{
						throw new RuntimeException("Not a time format.");
					}
				}
			}
		}

		homeCameraScdulVO.setDay(daysOfWeek);
		homeCameraScdulVO.setStartTime(calcStartTime);
		homeCameraScdulVO.setFinishTime(calcFinishTime);
		return homeCameraScdulVO;
	}

	public static String getImageFilePath(String deviceid, String rootPath, String urlRootPath, byte[] imageData) {
		String filePath = null;
		String url = com.kt.smcp.framework.util.PropUtil.getValues("properties/hiotserver.properties", "hiotserver.urlpath");
		DateTimeFormatter directoryFormat = DateTimeFormat.forPattern("yyyyMMdd");
		DateTimeFormatter fileFormat = DateTimeFormat.forPattern("yyyyMMddHHmmss");
		String root = com.kt.smcp.framework.util.PropUtil.getValues("properties/hiotserver.properties", "hiotserver.naspath");
		LocalDateTime localDateTime = new LocalDateTime();
		String separator = FileSystems.getDefault().getSeparator();
		String directory = root + directoryFormat.print(localDateTime);

		url += "detect?p=" + directoryFormat.print(localDateTime);
		url += "&u=" + deviceid + "_" +fileFormat.print(localDateTime);

		try
		{
			HomeCameraUtil.makeDirectory(directory);
			filePath = directory + separator + deviceid + "_" + fileFormat.print(localDateTime) + ".jpg";

			File file = new File(filePath);

			if (file.exists())
				throw new RuntimeException("File exists : " + filePath);

			HomeCameraUtil.savePictureAsJpeg(filePath, imageData);
			logger.debug("Saving image file to " + filePath + ".");

		} catch (SecurityException e)
		{
			throw new RuntimeException("Security problem occurred creating directory : " + directory);
		} catch (IOException e) {
			logger.error("Failed to save a jpeg image file", e);
		}

		return url;

	}

	public static void savePictureAsJpeg(String path, byte[] imageData) throws IOException {
		InputStream in = new ByteArrayInputStream(imageData);
		BufferedImage bufferedImage = ImageIO.read(in);
		OutputStream outputStream = new FileOutputStream(new File(path));
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		ImageIO.write(bufferedImage, "jpg", bufferedOutputStream);
		bufferedOutputStream.close();
	}

	private static void makeDirectory(String directory) {
		File file = new File(directory);

		if (!file.exists())
		{
			file.mkdir();
		}
	}
	
	 public static int bytesToint(byte value[])
	    {
	        return ByteBuffer.wrap(value).getInt();
	    }

	    public static int bytesToint(byte value[], int index)
	    {
	        byte arrValue[] = new byte[4];
	        System.arraycopy(value, index, arrValue, 0, 4);
	        return bytesToint(arrValue);
	    }


}
