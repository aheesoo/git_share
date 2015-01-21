package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtStrgeModeStoreVO extends HomeCameraOutbndVO {

	/* 128 bit, 단말 고유 식별자 */
	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	/* 저장 매체 */
	@FieldHint(index = 2, length = 4)
	private Integer SaveMode;

	public void setSaveMode(int saveMode) {
		SaveMode = saveMode;
	}

	/* ucloud API Key */
	@FieldHint(index = 3, length = 36)
	private String ucloudAPIKey;

	public void setUcloudAPIKey(String key) {
		this.ucloudAPIKey = key;
	}

	/* ucloud API Secret */
	@FieldHint(index = 4, length = 36)
	private String ucloudAPISecret;

	public void setUcloudAPISecret(String secret) {
		this.ucloudAPISecret = secret;
	}

	/* ucloud 인증 Token */
	@FieldHint(index = 5, length = 25)
	private String ucloudToken;

	public void setUcloudToken(String token) {
		this.ucloudToken = token;
	}

	/* ucloud 인증 Secret */
	@FieldHint(index = 6, length = 45)
	private String ucloudSecret;

	public void setUcloudSecret(String secret) {
		this.ucloudSecret = secret;
	}

	/* ucloud 저장 경로 */
	@FieldHint(index = 7, length = 50)
	private String UcloudPath;

	public void setUcloudPath(String ucloudPath) {
		UcloudPath = ucloudPath;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtStrgeModeStoreVO.Response.class);

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
		@RowExtensionHint(rowExtension = RowExtension.SAVE_MODE)
		private Integer SaveMode;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

		public Integer getSaveMode() {
			return SaveMode;
		}

		public void setSaveMode(Integer saveMode) {
			SaveMode = saveMode;
		}

	}
}
