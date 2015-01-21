package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public class OutbndRqtVcCnvyVO extends HomeCameraOutbndVO {

	private static final Logger logger = LoggerFactory.getLogger(OutbndRqtVcCnvyVO.class);

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	// 가변 길이 필드의 경우 length를 0보다 작게 Set
	@FieldHint(index = 2, length = -1)
	private byte[] Voice;
	
	public void setVoice(byte[] voice) {
		if (null != Voice) {
			setDataSize(getDataSize() - Voice.length);
		}
		
		resetDataSize(this.getFullVersion());
		
		Voice = voice;
		setDataSize(getDataSize() + voice.length);
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtVcCnvyVO.Response.class);

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
