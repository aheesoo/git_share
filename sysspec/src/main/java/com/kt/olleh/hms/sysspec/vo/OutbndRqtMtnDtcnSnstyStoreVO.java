package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtMtnDtcnSnstyStoreVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}
	
	@FieldHint(index = 2, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.M_SENSITIVITY)
	private Integer MSensitivity;
	
	public void setMSensitivity(int mSensitivity) {
		MSensitivity = mSensitivity;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtMtnDtcnSnstyStoreVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.RESULT)
		private Integer Result;
		
		@FieldHint(index = 2, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.M_SENSITIVITY)
		private Integer MSensitivity;

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

	}
}
