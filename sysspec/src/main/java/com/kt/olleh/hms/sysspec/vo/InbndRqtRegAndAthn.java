/**
 * 등록 및 인증
 *
 * 
 */

package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtRegAndAthn extends HomeCameraInbndVO {
	
	private static final Logger logger = LoggerFactory.getLogger(InbndRqtRegAndAthn.class);

	public InbndRqtRegAndAthn() {
		super();
	}

	public InbndRqtRegAndAthn(byte[] dataBytes) {
		super(dataBytes);
	}
	
	@FieldHint(index = 1, length = 20)
	private String ModelCode;

	@FieldHint(index = 2, length = 40)
	private String DeviceType;

	@FieldHint(index = 3, length = 30)
	private String MAC;

	@FieldHint(index = 4, length = 11)
	private String CameraSAID;

	@FieldHint(index = 5, length = 40)
	private String Secret;

	@FieldHint(index = 6, length = 4)
	private Integer CCTV_LOC;
	
	@FieldHint(index = 7, length = 4)
	private Integer Reserved1;
	
	@FieldHint(index = 8, length = 4)
	private Integer Reserved2;
	
	public String getModelCode() {
		return ModelCode;
	}

	public void setModelCode(String modelCode) {
		ModelCode = modelCode;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getMacAdr() {
		return MAC;
	}

	public void setMacAdr(String mac) {
		MAC = mac;
	}

	public String getCameraSAID() {
		return CameraSAID;
	}

	public void setCameraSAID(String cameraSAID) {
		CameraSAID = cameraSAID;
	}

	public String getSecret() {
		return Secret;
	}

	public void setSecret(String secret) {
		Secret = secret;
	}

	public Integer getCCTVLoc() {
		return CCTV_LOC;
	}

	public void setCCTVLoc(Integer cctvLoc) {
		CCTV_LOC = cctvLoc;
	}

	public Integer getReserved1() {
		return Reserved1;
	}

	public void setReserved1(Integer reserved1) {
		Reserved1 = reserved1;
	}

	public Integer getReserved2() {
		return Reserved2;
	}

	public void setReserved2(Integer reserved2) {
		Reserved2 = reserved2;
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtRegAndAthn.Response.class);

		public Response() {
			super();
		}

		public Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;

		@FieldHint(index = 2, length = 16)
		private UUID DeviceID;
		
		public void setResult(Integer result) {
			Result = result;
		}

		public void setDeviceID(UUID deviceID) {
			DeviceID = deviceID;
		}
		
		public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
			DeviceID = new UUID(svcTgtSeq, spotDevSeq);
		}

		public String getSessionKey() {
			return this.DeviceID.toString().toUpperCase();
		}
		
	}

}

