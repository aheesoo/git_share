package com.kt.olleh.hms.sysspec.vo;


import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeCameraScdulVO {

	private static final Logger logger = LoggerFactory.getLogger(HomeCameraScdulVO.class);
	private int day = 0x00000000;
	private String startTime = null;
	private String finishTime = null;
	private int duration = 0;

	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
		calcDuration();
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
		calcDuration();
	}

	public int getDuration() {
		if (startTime == null || finishTime == null)
			throw new RuntimeException("Both of Start Time and Finish Time must to be set to get Duration.");

		return duration;
	}

	public void setDuration(int duration) {
		if (this.startTime == null)
			throw new RuntimeException("Start Time must to be set first to set Duration.");

		DateTimeFormatter formatter = DateTimeFormat.forPattern("HHmm");
		DateTime startTime = formatter.parseDateTime(this.startTime);
		this.finishTime = formatter.print(startTime.plusMinutes(duration));
	}

	private void calcDuration() {

		if (null == this.startTime || null == this.finishTime)
			return;

		DateTimeFormatter formatter = DateTimeFormat.forPattern("HHmm");
		DateTime startTime = formatter.parseDateTime(this.startTime);
		DateTime finishTime = formatter.parseDateTime(this.finishTime);
		this.duration = Minutes.minutesBetween(startTime, finishTime).getMinutes();

		logger.debug("Calc Duration : " + this.duration);

		if (this.duration < 0) {
			this.duration = 1440 + this.duration;
//			this.duration = Minutes.minutesBetween(finishTime, startTime).getMinutes();
		}

	}
}
