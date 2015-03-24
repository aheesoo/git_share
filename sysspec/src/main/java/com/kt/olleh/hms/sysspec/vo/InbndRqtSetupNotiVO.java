package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtSetupNotiVO extends HomeCameraInbndVO {

	private static final Logger logger = LoggerFactory.getLogger(InbndRqtSetupNotiVO.class);

	public InbndRqtSetupNotiVO() {
		super();
	}

	InbndRqtSetupNotiVO(byte[] dataBytes) {
		super(dataBytes);
	}

	@FieldHint(index = 1, length = 16, minVer = 0.82f)
	private UUID DeviceID;

	@FieldHint(index = 2, length = 4, minVer = 0.82f)
	private Integer Status;

	@FieldHint(index = 3, length = 4, minVer = 0.82f)
	private Integer Resolution;

	@FieldHint(index = 4, length = 4, minVer = 0.82f)
	private Integer Detection;

	@FieldHint(index = 5, length = 4, minVer = 0.82f)
	private Integer DetectionMode;

	@FieldHint(index = 6, length = 4, minVer = 0.82f)
	private Integer SDCard;

	@FieldHint(index = 7, length = 4, minVer = 0.82f)
	private Integer Reverted;

	@FieldHint(index = 8, length = 4, minVer = 0.82f)
	private Integer MovingPNS;

	@FieldHint(index = 9, length = 4, minVer = 0.82f)
	private Integer StoragePNS;

	@FieldHint(index = 10, length = 4, minVer = 0.82f)
	private Integer SaveMode;

	@FieldHint(index = 11, length = 4, minVer = 0.82f)
	private Integer MSensitivity;

	@FieldHint(index = 12, length = 4, minVer = 0.82f)
	private Integer VSensitivity;

	@FieldHint(index = 13, length = 4, minVer = 0.82f)
	private Integer Scheduled;

	@FieldHint(index = 14, length = 10, minVer = 0.82f)
	private String FirmwareVersion;

	@FieldHint(index = 15, length = 32, minVer = 0.82f)
	private String APName;

	@FieldHint(index = 16, length = 10, minVer = 0.82f)
	private String APPower;

	@FieldHint(index = 17, length = 4, minVer = 0.82f)
	private String TimeFrom;

	@FieldHint(index = 18, length = 4, minVer = 0.82f)
	private String TimeTo;

	@FieldHint(index = 19, length = 4, minVer = 0.82f)
	private Integer Day;

	@FieldHint(index = 20, length = 4, minVer = 0.82f)
	private String RecFrom;

	@FieldHint(index = 21, length = 4, minVer = 0.82f)
	private Integer ModeRec;

	@FieldHint(index = 22, length = 4, minVer = 0.82f)
	private String RecTo;

	@FieldHint(index = 23, length = 4, minVer = 0.82f)
	private Integer Period;

	@FieldHint(index = 24, length = 4, minVer = 0.82f)
	private Integer DayRec;

	public UUID getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Integer getResolution() {
		return Resolution;
	}

	public void setResolution(Integer resolution) {
		Resolution = resolution;
	}

	public Integer getDetection() {
		return Detection;
	}

	public void setDetection(Integer detection) {
		Detection = detection;
	}

	public Integer getDetectionMode() {
		return DetectionMode;
	}

	public void setDetectionMode(Integer detectionMode) {
		DetectionMode = detectionMode;
	}

	public Integer getSDCard() {
		return SDCard;
	}

	public void setSDCard(Integer sDCard) {
		SDCard = sDCard;
	}

	public Integer getReverted() {
		return Reverted;
	}

	public void setReverted(Integer reverted) {
		Reverted = reverted;
	}

	public Integer getMovingPNS() {
		return MovingPNS;
	}

	public void setMovingPNS(Integer movingPNS) {
		MovingPNS = movingPNS;
	}

	public Integer getStoragePNS() {
		return StoragePNS;
	}

	public void setStoragePNS(Integer storagePNS) {
		StoragePNS = storagePNS;
	}

	public Integer getSaveMode() {
		return SaveMode;
	}

	public void setSaveMode(Integer saveMode) {
		SaveMode = saveMode;
	}

	public Integer getMSensitivity() {
		return MSensitivity;
	}

	public void setMSensitivity(Integer mSensitivity) {
		MSensitivity = mSensitivity;
	}

	public Integer getVSensitivity() {
		return VSensitivity;
	}

	public void setVSensitivity(Integer vSensitivity) {
		VSensitivity = vSensitivity;
	}

	public Integer getScheduled() {
		return Scheduled;
	}

	public void setScheduled(Integer scheduled) {
		Scheduled = scheduled;
	}

	public String getFirmwareVersion() {
		return FirmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		FirmwareVersion = firmwareVersion;
	}

	public String getAPName() {
		return APName;
	}

	public void setAPName(String aPName) {
		APName = aPName;
	}

	public String getAPPower() {
		return APPower;
	}

	public void setAPPower(String aPPower) {
		APPower = aPPower;
	}

	public String getTimeFrom() {
		return TimeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		TimeFrom = timeFrom;
	}

	public String getTimeTo() {
		return TimeTo;
	}

	public void setTimeTo(String timeTo) {
		TimeTo = timeTo;
	}

	public Integer getDay() {
		return Day;
	}

	public void setDay(Integer day) {
		Day = day;
	}

	public String getRecFrom() {
		return RecFrom;
	}

	public void setRecFrom(String recFrom) {
		RecFrom = recFrom;
	}

	public Integer getModeRec() {
		return ModeRec;
	}

	public void setModeRec(Integer modeRec) {
		ModeRec = modeRec;
	}

	public String getRecTo() {
		return RecTo;
	}

	public void setRecTo(String recTo) {
		RecTo = recTo;
	}

	public Integer getPeriod() {
		return Period;
	}

	public void setPeriod(Integer period) {
		Period = period;
	}

	public Integer getDayRec() {
		return DayRec;
	}

	public void setDayRec(Integer dayRec) {
		DayRec = dayRec;
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtSetupNotiVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;

		public void setResult(Integer result) {
			Result = result;
		}

	}

}
