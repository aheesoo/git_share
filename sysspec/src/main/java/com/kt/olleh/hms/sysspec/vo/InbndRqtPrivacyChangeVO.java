package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtPrivacyChangeVO extends HomeCameraInbndVO {

	private static final Logger logger = LoggerFactory.getLogger(InbndRqtPrivacyChangeVO.class);

	public InbndRqtPrivacyChangeVO() {
		super();
	}

	InbndRqtPrivacyChangeVO(byte[] dataBytes) {
		super(dataBytes);
	}

	@FieldHint(index = 1, length = 16, minVer = 0.82f)
	private UUID DeviceID;

	@FieldHint(index = 2, length = 4, minVer = 0.82f)
	private Integer PrivateMode;

	public UUID getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public Integer getPrivateMode() {
		return PrivateMode;
	}

	public void setPrivateMode(Integer privateMode) {
		PrivateMode = privateMode;
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtPrivacyChangeVO.Response.class);

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
