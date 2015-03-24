package com.kt.olleh.hms.sysspec.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.RowExtensionHint;

public class OutbndRqtWifiQosRqtVO extends HomeCameraOutbndVO {

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public void setDeviceID(long svcTgtSeq, long spotDevSeq) {
		DeviceID = new UUID(svcTgtSeq, spotDevSeq);
	}

	public static final class Response extends HomeCameraInbndVO {
		private static final Logger logger = LoggerFactory.getLogger(OutbndRqtWifiQosRqtVO.Response.class);

		public Response() {
			super();
		}

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;

		@FieldHint(index = 2, length = 32)
		private String CurrentSSID;

		@FieldHint(index = 3, length = 4)
		private Integer CurrentRSSI;

		@FieldHint(index = 4, length = 4)
		private Integer AdjacentNum;

		@FieldHint(index = 5, length = 4)
		private Integer AdjacentRSSI;

		@FieldHint(index = 6, length = 4)
		private Integer CoChannelNum;

		@FieldHint(index = 7, length = 4)
		private Integer CoChannelRSSI;

		public Integer getResult() {
			return Result;
		}

		public void setResult(Integer result) {
			Result = result;
		}

		public String getCurrentSSID() {
			return CurrentSSID;
		}

		public void setCurrentSSID(String currentSSID) {
			CurrentSSID = currentSSID;
		}

		public Integer getCurrentRSSI() {
			return CurrentRSSI;
		}

		public void setCurrentRSSI(Integer currentRSSI) {
			CurrentRSSI = currentRSSI;
		}

		public Integer getAdjacentNum() {
			return AdjacentNum;
		}

		public void setAdjacentNum(Integer adjacentNum) {
			AdjacentNum = adjacentNum;
		}

		public Integer getAdjacentRSSI() {
			return AdjacentRSSI;
		}

		public void setAdjacentRSSI(Integer adjacentRSSI) {
			AdjacentRSSI = adjacentRSSI;
		}

		public Integer getCoChannelNum() {
			return CoChannelNum;
		}

		public void setCoChannelNum(Integer coChannelNum) {
			CoChannelNum = coChannelNum;
		}

		public Integer getCoChannelRSSI() {
			return CoChannelRSSI;
		}

		public void setCoChannelRSSI(Integer coChannelRSSI) {
			CoChannelRSSI = coChannelRSSI;
		}

	}
}
