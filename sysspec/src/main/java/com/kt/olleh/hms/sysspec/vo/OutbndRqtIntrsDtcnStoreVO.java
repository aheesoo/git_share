package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtIntrsDtcnStoreVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}
	
	@FieldHint(index = 2, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.DETECTION)
	private Integer Detection;
	
	public void setDetection(int detection) {
		Detection = detection;
	}
	
	@FieldHint(index = 3, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.DETECTION_MODE)
	private Integer DetectionMode;
	
	public void setDetectionMode(int detectionMode) {
		DetectionMode = detectionMode;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtIntrsDtcnStoreVO.Response.class);

		public Response() {
			super();
			logger.debug("Response");
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.RESULT)
		private Integer Result;
		
		@FieldHint(index = 2, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.DETECTION)
		private Integer Detection;
		
		@FieldHint(index = 2, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.DETECTION_MODE)
		private Integer DetectionMode;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
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

	}
}
