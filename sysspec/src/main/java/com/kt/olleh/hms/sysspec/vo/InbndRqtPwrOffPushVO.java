package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtPwrOffPushVO extends HomeCameraInbndVO {

	private static final Logger logger = LoggerFactory.getLogger(InbndRqtPwrOffPushVO.class);
	
	public InbndRqtPwrOffPushVO() {
		super();
	}

	InbndRqtPwrOffPushVO(byte[] dataBytes) {
		super(dataBytes);
	}

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	@FieldHint(index = 2, length = 14)
	private String DateTime;

	public UUID getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtPwrOffPushVO.Response.class);

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
