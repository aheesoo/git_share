package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class OutbndRqtRecScdulRetvVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtRecScdulRetvVO.Response.class);

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
		private String TimeRec;
		
		@FieldHint(index = 4, length = 4)
		private Integer ModeRec;
		
		@FieldHint(index = 5, length = 4)
		private Integer DurationRec;
		
		@FieldHint(index = 6, length = 4)
		private Integer DayRec;

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

		public String getTimeRec() {
			return TimeRec;
		}

		public void setTimeRec(String timeRec) {
			TimeRec = timeRec;
		}

		public Integer getModeRec() {
			return ModeRec;
		}

		public void setModeRec(Integer modeRec) {
			ModeRec = modeRec;
		}

		public Integer getDurationRec() {
			return DurationRec;
		}

		public void setDurationRec(Integer durationRec) {
			DurationRec = durationRec;
		}

		public Integer getDayRec() {
			return DayRec;
		}

		public void setDayRec(Integer dayRec) {
			DayRec = dayRec;
		}
		
	}
}