package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class OutbndRqtSetupRetvVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtSetupRetvVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;
		
		@FieldHint(index = 2, length = 4)
		private Integer Status;
		
		@FieldHint(index = 3, length = 4)
		private Integer Resolution;
		
		@FieldHint(index = 4, length = 4)
		private Integer Detection;
		
		@FieldHint(index = 5, length = 4)
		private Integer DetectionMode;
		
		@FieldHint(index = 6, length = 4)
		private Integer SDCard;
		
		@FieldHint(index = 7, length = 4)
		private Integer Reverted;
		
		@FieldHint(index = 8, length = 4)
		private Integer MovingPNS;
		
		@FieldHint(index = 9, length = 4)
		private Integer StoragePNS;
		
		@FieldHint(index = 10, length = 4)
		private Integer SaveMode;
		
		@FieldHint(index = 11, length = 4)
		private Integer MSensitivity;
		
		@FieldHint(index = 12, length = 4)
		private Integer VSensitivity;
		
		@FieldHint(index = 13, length = 4)
		private Integer Scheduled;
		
		@FieldHint(index = 14, length = 10)
		private String firmwareVersion;
		
		@FieldHint(index = 15, length = 32)
		private String APName;
		
		@FieldHint(index = 16, length = 10)
		private String APPower;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
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
			return firmwareVersion;
		}

		public void setFirmwareVersion(String version) {
			firmwareVersion = version;
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
		
	}
}
