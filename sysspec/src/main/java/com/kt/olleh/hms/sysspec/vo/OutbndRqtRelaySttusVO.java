package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class OutbndRqtRelaySttusVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	@FieldHint(index = 2, length = 4)
	private Integer StateCode;
	
	public void setStateCode(int stateCode) {
		StateCode = stateCode;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtRelaySttusVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

	}
}
