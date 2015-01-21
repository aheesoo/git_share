package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtRelaySessnConnVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;
	
	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}
	
	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}
	
	@FieldHint(index = 2, length = 20)
	@RowExtensionHint(rowExtension = RowExtension.RELAY_IP)
	private String RelayIP;
	
	public void setRelayIP(String relayIP) {
		RelayIP = relayIP;
	}
	
	@FieldHint(index = 3, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.RELAY_PORT)
	private Integer RelayPort;
	
	public void setRelayPort(int relayPort) {
		RelayPort = relayPort;
	}
	
	@FieldHint(index = 4, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.RELAY_DEVICE_ID)
	private Integer RelayDeviceID;
	
	public void setRelayDeviceID(int relayDeviceID) {
		RelayDeviceID = relayDeviceID;
	}
	
	@FieldHint(index = 5, length = 4)
	@RowExtensionHint(rowExtension = RowExtension.RELAY_DEVICE_PW)
	private Integer RelayDevicePW;
	
	public void setRelayDevicePW(int relayDevicePW) {
		RelayDevicePW = relayDevicePW;
	}
	
	@FieldHint(index = 6, length = 16)
	@RowExtensionHint(rowExtension = RowExtension.SECRET_KEY)
	private byte[] secretKey;
	
	public void setSecretKey(byte[] secretKey) {
		this.secretKey = secretKey;
	}
	
	@FieldHint(index = 7, length = 16)
	@RowExtensionHint(rowExtension = RowExtension.IV)
	private byte[] iV;
	
	public void setIV(byte[] iV) {
		this.iV = iV;
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtRelaySessnConnVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		@RowExtensionHint(rowExtension = RowExtension.RESULT)
		private Integer Result;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

	}
	
}
