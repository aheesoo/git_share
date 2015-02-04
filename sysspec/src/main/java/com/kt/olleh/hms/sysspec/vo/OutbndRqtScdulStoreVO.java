package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class OutbndRqtScdulStoreVO extends HomeCameraOutbndVO {

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
	
	@FieldHint(index = 7, length = 4)
	private String TimeRec;
	
	public void setTimeRec(String timeRec) {
		TimeRec = timeRec;
	}
	
	@FieldHint(index = 8, length = 4)
	private Integer ModeRec;
	
	public void setModeRec(int modeRec) {
		ModeRec = modeRec;
	}
	
	/*@FieldHint(index = 9, length = 4)
	private Integer DurationRec;*/
	
	@FieldHint(index = 9, length = 4)
	private String TimeEnd;
	
	public void setTimeEnd(String timeEnd) {
		TimeEnd = timeEnd;
	}
	
	@FieldHint(index = 10, length = 4)
	private int Period;
	
	public void setPeriod(int period) {
		Period = period;
	}
	
	@FieldHint(index = 11, length = 4)
	private Integer DayRec;
	
	public void setDayRec(int dayRec) {
		DayRec = dayRec;
	}
	
	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtScdulStoreVO.Response.class);

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
		
		@FieldHint(index = 7, length = 4)
		private String TimeRec;
		
		@FieldHint(index = 8, length = 4)
		private Integer ModeRec;
		
		/*@FieldHint(index = 9, length = 4)
		private Integer DurationRec;*/
		
		@FieldHint(index = 9, length = 4)
		private String TimeEnd;
		
		@FieldHint(index = 10, length = 4)
		private int Period;
		
		@FieldHint(index = 10, length = 4)
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

		public String getTimeEnd() {
			return TimeEnd;
		}

		public void setTimeEnd(String timeEnd) {
			TimeEnd = timeEnd;
		}

		public int getPeriod() {
			return Period;
		}

		public void setPeriod(int period) {
			Period = period;
		}

		public Integer getDayRec() {
			return DayRec;
		}

		public void setDayRec(Integer dayRec) {
			DayRec = dayRec;
		}
		
	}
}
