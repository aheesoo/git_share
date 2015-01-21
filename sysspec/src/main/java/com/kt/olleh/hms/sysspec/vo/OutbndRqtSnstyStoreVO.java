package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class OutbndRqtSnstyStoreVO extends HomeCameraOutbndVO {

	private static final Logger logger = LoggerFactory.getLogger(OutbndRqtSnstyStoreVO.class);

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	@FieldHint(index = 2, length = 4)
	private Integer MSensitivity;
	
	public void setMSensitivity(int mSensitivity) {
		MSensitivity = mSensitivity;
	}
	
	@FieldHint(index = 3, length = 4)
	private Integer VSensitivity;
	
	public void setVSensitivity(int vSensitivity) {
		VSensitivity = vSensitivity;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtSnstyStoreVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;
		
		@FieldHint(index = 2, length = 4)
		private Integer MSensitivity;
		
		@FieldHint(index = 3, length = 4)
		private Integer VSensitivity;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
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

	}

}
