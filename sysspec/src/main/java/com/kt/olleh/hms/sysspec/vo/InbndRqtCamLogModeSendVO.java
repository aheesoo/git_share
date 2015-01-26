package com.kt.olleh.hms.sysspec.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class InbndRqtCamLogModeSendVO extends HomeCameraOutbndVO{

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	@FieldHint(index = 2, length = 12)
	private String MAC;

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	public UUID getDeviceID() {
		return DeviceID;
	}
	
	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}
	
	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtCamLogModeSendVO.Response.class);

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
