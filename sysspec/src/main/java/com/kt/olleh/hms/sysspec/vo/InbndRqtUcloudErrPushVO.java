package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtUcloudErrPushVO extends HomeCameraInbndVO {

	private static final Logger logger = LoggerFactory.getLogger(InbndRqtUcloudErrPushVO.class);
	
	public InbndRqtUcloudErrPushVO() {
		super();
	}

	InbndRqtUcloudErrPushVO(byte[] dataBytes) {
		super(dataBytes);
	}

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	@FieldHint(index = 2, length = 14)
	private String DateTime;

	@FieldHint(index = 3, length = 4)
	private Integer EventType;

	@FieldHint(index = 4, length = 8)
	private Integer SpareStorage;

	@FieldHint(index = 5, length = 40, maxVer = 0.78)
	private String ErrorMsg;
	
	@FieldHint(index = 6, length = 4, minVer = 0.79)
	private Integer ErrorCode;

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

	public Integer getEventType() {
		return EventType;
	}

	public void setEventType(Integer eventType) {
		EventType = eventType;
	}

	public Integer getSpareStorage() {
		return SpareStorage;
	}

	public void setSpareStorage(Integer spareStorage) {
		SpareStorage = spareStorage;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	
	public Integer getErrorCode() {
		return ErrorCode;
	}
	
	public void setErrorCode(Integer errorCode) {
		ErrorCode = errorCode;
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtUcloudErrPushVO.Response.class);

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
