package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtUcloudToknUpdVO extends HomeCameraInbndVO {

	private static final Logger logger = LoggerFactory.getLogger(InbndRqtUcloudToknUpdVO.class);

	public InbndRqtUcloudToknUpdVO() {
		super();
	}

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	public UUID getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	InbndRqtUcloudToknUpdVO(byte[] dataBytes) {
		super(dataBytes);
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtUcloudToknUpdVO.Response.class);

		public Response() {
			super();
		}

		public Response(byte[] dataBytes) {
			super(dataBytes);
		}

		/* 요청 결과 */
		@FieldHint(index = 1, length = 4)
		private Integer Result;

		public void setResult(int result) {
			this.Result = result;
		}

		/* ucloud API Key */
		@FieldHint(index = 2, length = 36)
		private String ucloudAPIKey;

		public void setUcloudAPIKey(String apiKey) {
			this.ucloudAPIKey = apiKey;
		}

		/* ucloud API Secret */
		@FieldHint(index = 3, length = 36)
		private String ucloudAPISecret;

		public void setUcloudAPISecret(String apiSecret) {
			this.ucloudAPISecret = apiSecret;
		}

		/* ucloud 인증 Token */
		@FieldHint(index = 4, length = 25)
		private String ucloudToken;

		public void setUcloudToken(String ucloudToken) {
			this.ucloudToken = ucloudToken;
		}

		/* ucloud 인증 비밀번호 */
		@FieldHint(index = 5, length = 45)
		private String ucloudSecret;

		public void setUcloudSecret(String ucloudSecret) {
			this.ucloudSecret = ucloudSecret;
		}

		/* ucloud 저장 경로 */
		@FieldHint(index = 6, length = 50)
		private String ucloudPath;

		public void setUcloudPath(String ucloudPath) {
			this.ucloudPath = ucloudPath;
		}

	}

}
