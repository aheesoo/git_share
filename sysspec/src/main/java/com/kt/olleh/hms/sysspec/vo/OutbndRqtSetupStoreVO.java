package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtSetupStoreVO extends HomeCameraOutbndVO {

	/* 128 bit, 단말 고유 식별자 */
	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	/*
	 * 영상 송출
	 * 활성화 : 0x01
	 * 비활성화 : 0x02
	 *
	 * */
	@FieldHint(index = 2, length = 4)
	private Integer Status;

	public void setStatus(int status) {
		Status = status;
	}

	/*
	 * 영상 해상도
	 *
	 * 0x01 : HD
	 * 0x02 : VGA
	 * 0x03 : QVGA
	 *
	 *  */
	@FieldHint(index = 3, length = 4)
	private Integer Resolution;

	public void setResolution(int resolution) {
		Resolution = resolution;
	}

	/*
	 * 침입 감지
	 * 활성화 : 0x01
	 * 비활성화 : 0x00
	 *
	 * */
	@FieldHint(index = 4, length = 4)
	private Integer Detection;

	public void setDetection(int detection) {
		Detection = detection;
	}

	/*
	 * 침입 감지 모드
	 *
	 * Detection이 0x01일때만 해석
	 *
	 * 영상 감지 모드 : 0x01
	 * 음향 감지 모드 : 0x02
	 * 영상/음향 동시 감지 모드 : 0x03
	 *
	 *  */
	@FieldHint(index = 5, length = 4)
	private Integer DetectionMode;

	public void setDetectionMode(int detectionMode) {
		DetectionMode = detectionMode;
	}

	/*
	 * 움직임 감지 민감도
	 *
	 * 0x01 ~ 0x03
	 *
	 *  */
	@FieldHint(index = 6, length = 4)
	private Integer MSensitivity;

	public void setMSensitivity(int mSensitivity) {
		MSensitivity = mSensitivity;
	}

	/*
	 * 음향 감지 민감도
	 *
	 * 0x01 ~ 0x03
	 *
	 *  */
	@FieldHint(index = 7, length = 4)
	private Integer VSensitivity;

	public void setVSensitivity(int vSensitivity) {
		VSensitivity = vSensitivity;
	}

	/*
	 * 영상 역전 여부
	 *
	 * 정상 송출 : 0x00
	 * 역전 송출 : 0x01
	 *
	 *  */
	@FieldHint(index = 8, length = 4)
	private Integer Reverted;

	public void setReverted(int reverted) {
		Reverted = reverted;
	}

	/*
	 * 움직임 감지 PNS 최소 간격 (분 단위)
	 *
	 * 기본 10분, 최소 1분, 최대 1440분
	 *
	 *  */
	@FieldHint(index = 9, length = 4)
	private Integer MovingPNS;

	public void setMovingPNS(int movingPNS) {
		MovingPNS = movingPNS;
	}

	/*
	 * ucloud 용량 부족 PNS 최소 간격 (분 단위)
	 *
	 * 기본 10분, 최소 1분, 최대 1440분
	 *
	 *  */
	@FieldHint(index = 10, length = 4)
	private Integer StoragePNS;

	public void setStoragePNS(int storagePNS) {
		StoragePNS = storagePNS;
	}

	/*
	 * 저장 매체
	 *
	 * SDCard : 0x01
	 * ucloud : 0x02
	 * 저장하지 않음 : 0x00
	 *
	 *  */
	@FieldHint(index = 11, length = 4)
	private Integer SaveMode;

	public void setSaveMode(int saveMode) {
		SaveMode = saveMode;
	}

	/*
	 * ucloud API Key
	 *
	 * SaveMode가 0x02인 경우 ucloud 접속을 위한 API 키
	 *
	 *  */
	@FieldHint(index = 12, length = 36)
	private String ucloudAPIKey;

	public void setUcloudApiKey(String key) {
		this.ucloudAPIKey = key;
	}

	/*
	 * ucloud API Secret
	 *
	 * SaveMode가 0x02인 경우 ucloud 접속을 위한 API 비밀번호
	 *
	 *  */
	@FieldHint(index = 13, length = 36)
	private String ucloudAPISecret;

	public void setUcloudApiSecret(String secret) {
		this.ucloudAPISecret = secret;
	}

	/*
	 * ucloud 인증 Token
	 *
	 * SaveMode가 0ㅌ02인 경우 ucloud 접속을 위한 인증 토큰
	 *
	 *  */
	@FieldHint(index = 14, length = 25)
	private String ucloudToken;

	public void setUcloudToken(String ucloudToken) {
		this.ucloudToken = ucloudToken;
	}

	/*
	 * ucloud 인증 Secret
	 *
	 * SaveMode가 0x02인 경우 ucloud 접속을 위한 인증 비밀번호
	 *
	 *  */
	@FieldHint(index = 15, length = 45)
	private String ucloudSecret;

	public void setUcloudSecret(String secret) {
		this.ucloudSecret = secret;
	}

	/* ucloud 저장 경로 */
	@FieldHint(index = 16, length = 50)
	private String UcloudPath;

	public void setUcloudPath(String ucloudPath) {
		UcloudPath = ucloudPath;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtSetupStoreVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;

		@FieldHint(index = 2, length = 4)
		private Integer Status;

		@FieldHint(index = 3, length = 4)
		private Integer Resolution;

		@FieldHint(index = 4, length = 4)
		private Integer Detection;

		@FieldHint(index = 5, length = 4)
		private Integer DetectionMode;

		@FieldHint(index = 6, length = 4)
		private Integer MSensitivity;

		@FieldHint(index = 7, length = 4)
		private Integer VSensitivity;

		@FieldHint(index = 8, length = 4)
		private Integer Reverted;

		@FieldHint(index = 9, length = 4)
		private Integer MovingPNS;

		@FieldHint(index = 10, length = 4)
		private Integer StoragePNS;

		@FieldHint(index = 11, length = 4)
		private Integer SaveMode;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

		public Integer getStatus() {
			return Status;
		}

		public void setStatus(Integer status) {
			Status = status;
		}

		public Integer getResolution() {
			return Resolution;
		}

		public void setResolution(Integer resolution) {
			Resolution = resolution;
		}

		public Integer getDetection() {
			return Detection;
		}

		public void setDetection(Integer detection) {
			Detection = detection;
		}

		public Integer getDetectionMode() {
			return DetectionMode;
		}

		public void setDetectionMode(Integer detectionMode) {
			DetectionMode = detectionMode;
		}

		public Integer getMSensitivity() {
			return MSensitivity;
		}

		public void setMSensitivity(Integer mSensitivity) {
			MSensitivity = mSensitivity;
		}

		public Integer getVSensitivity() {
			return VSensitivity;
		}

		public void setVSensitivity(Integer vSensitivity) {
			VSensitivity = vSensitivity;
		}

		public Integer getReverted() {
			return Reverted;
		}

		public void setReverted(Integer reverted) {
			Reverted = reverted;
		}

		public Integer getMovingPNS() {
			return MovingPNS;
		}

		public void setMovingPNS(Integer movingPNS) {
			MovingPNS = movingPNS;
		}

		public Integer getStoragePNS() {
			return StoragePNS;
		}

		public void setStoragePNS(Integer storagePNS) {
			StoragePNS = storagePNS;
		}

		public Integer getSaveMode() {
			return SaveMode;
		}

		public void setSaveMode(Integer saveMode) {
			SaveMode = saveMode;
		}

	}

}
