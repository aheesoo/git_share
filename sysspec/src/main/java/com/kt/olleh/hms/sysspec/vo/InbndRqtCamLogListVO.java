package com.kt.olleh.hms.sysspec.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class InbndRqtCamLogListVO {
	
	@FieldHint(index = 1, length = 17)
	private String DataTime = "";
	
	@FieldHint(index = 2, length = 10)
	private String LogType = "";
	
	@FieldHint(index = 3, length = 2)
	private Short LogCode = 0;
	
	@FieldHint(index = 4, length = 2)
	private Short Length= 0;
	
	@FieldHint(index = 5)
	private String value = "";
	
	List<InbndRqtCamLogListVO> outbndRqtCamLogListVO = new LinkedList<InbndRqtCamLogListVO>();

	public List<InbndRqtCamLogListVO> getOutbndRqtCamLogListVO() {
		return outbndRqtCamLogListVO;
	}

	public void setOutbndRqtCamLogListVO(
			List<InbndRqtCamLogListVO> outbndRqtCamLogListVO) {
		this.outbndRqtCamLogListVO = outbndRqtCamLogListVO;
	}

	public String getDataTime() {
		return DataTime;
	}

	public void setDataTime(String dataTime) {
		DataTime = dataTime;
	}

	public String getLogType() {
		return LogType;
	}

	public void setLogType(String logType) {
		LogType = logType;
	}

	public Short getLogCode() {
		return LogCode;
	}

	public void setLogCode(Short logCode) {
		LogCode = logCode;
	}

	public Short getLength() {
		return Length;
	}

	public void setLength(Short length) {
		Length = length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
