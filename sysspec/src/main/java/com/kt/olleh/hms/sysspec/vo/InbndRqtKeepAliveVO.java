/**
 * keep-alive
 *
 * 
 */
package com.kt.olleh.hms.sysspec.vo;


import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtKeepAliveVO extends HomeCameraInbndVO {

	private static final Logger logger = LoggerFactory.getLogger(InbndRqtKeepAliveVO.class);


	public InbndRqtKeepAliveVO() {
		super();
	}

	public InbndRqtKeepAliveVO(byte[] dataBytes) {
		super(dataBytes);
	}

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	public UUID getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtKeepAliveVO.Response.class);

		public Response() {
			super();
		}

		public Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;

		public void setResult(int result) {
			this.Result = result;
		}

	}
}
