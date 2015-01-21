package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtVdoRvrtStoreVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	@FieldHint(index = 2, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.REVERTED)
	private Integer Reverted;
	
	public void setReverted(int reverted) {
		Reverted = reverted;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtVdoRvrtStoreVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.RESULT)
		private Integer Result;
		
		@FieldHint(index = 2, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.REVERTED)
		private Integer Reverted;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

		public Integer getReverted() {
			return Reverted;
		}

		public void setReverted(Integer reverted) {
			Reverted = reverted;
		}

	}
}
