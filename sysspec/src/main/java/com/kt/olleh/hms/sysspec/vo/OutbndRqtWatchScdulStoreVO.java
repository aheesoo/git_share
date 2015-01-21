package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class OutbndRqtWatchScdulStoreVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	@FieldHint(index = 2, length = 4)
	private Integer Scheduled;
	
	public void setScheduled(int scheduled) {
		Scheduled = scheduled;
	}
	
	@FieldHint(index = 3, length = 4)
	private Integer DetectionMode;
	
	public void setDetectionMode(int detectionMode) {
		DetectionMode = detectionMode;
	}
	
	@FieldHint(index = 4, length = 4)
	private String TimeFrom;
	
	public void setTimeFrom(String timeFrom) {
		TimeFrom = timeFrom;
	}
	
	@FieldHint(index = 5, length = 4)
	private String TimeTo;
	
	public void setTimeTo(String timeTo) {
		TimeTo = timeTo;
	}
	
	@FieldHint(index = 6, length = 4)
	private Integer Day;
	
	public void setDay(int day) {
		Day = day;
	}
	
	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtWatchScdulStoreVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;
		
		@FieldHint(index = 2, length = 4)
		private Integer Scheduled;

		@FieldHint(index = 3, length = 4)
		private Integer DetectionMode;
		
		@FieldHint(index = 4, length = 4)
		private String TimeFrom;
		
		@FieldHint(index = 5, length = 4)
		private String TimeTo;
		
		@FieldHint(index = 6, length = 4)
		private Integer Day;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

		public Integer getScheduled() {
			return Scheduled;
		}

		public void setScheduled(Integer scheduled) {
			Scheduled = scheduled;
		}

		public Integer getDetectionMode() {
			return DetectionMode;
		}

		public void setDetectionMode(Integer detectionMode) {
			DetectionMode = detectionMode;
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
		
	}
}
