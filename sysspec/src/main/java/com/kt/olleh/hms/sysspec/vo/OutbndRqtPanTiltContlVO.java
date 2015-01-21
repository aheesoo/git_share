package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtPanTiltContlVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}
	
	@FieldHint(index = 2, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.PAN)
	private Integer Pan;
	
	public void setPan(int pan) {
		Pan = pan;
	}
	
	@FieldHint(index = 3, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.P_ANGLE)
	private Integer PAngle;
	
	public void setPAngle(int pAngle) {
		PAngle = pAngle;
	}
	
	@FieldHint(index = 4, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.P_DIRECTION)
	private Integer PDirection;
	
	public void setPDirection(int pDirection) {
		PDirection = pDirection;
	}
	
	@FieldHint(index= 5, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.TILT)
	private Integer Tilt;
	
	public void setTilt(int tilt) {
		Tilt = tilt;
	}
	
	@FieldHint(index = 6, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.T_ANGLE)
	private Integer TAngle;
	
	public void setTAngle(int tAngle) {
		TAngle = tAngle;
	}
	
	@FieldHint(index = 7, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.T_DIRECTION)
	private Integer TDirection;
	
	public void setTDirection(int tDirection) {
		TDirection = tDirection;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtPanTiltContlVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.RESULT)
		private Integer Result;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

	}
}
