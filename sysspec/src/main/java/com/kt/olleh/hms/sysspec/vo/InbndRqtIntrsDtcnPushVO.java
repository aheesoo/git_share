/**
 * 침입 및 갑지
 *
 * 
 */
package com.kt.olleh.hms.sysspec.vo;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terracotta.agent.repkg.de.schlichtherle.io.FileOutputStream;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;

public final class InbndRqtIntrsDtcnPushVO extends HomeCameraInbndVO {

	private static final Logger logger = LoggerFactory.getLogger(InbndRqtIntrsDtcnPushVO.class);

	public InbndRqtIntrsDtcnPushVO() {
		super();
	}

	public InbndRqtIntrsDtcnPushVO(byte[] dataBytes) {
		super(dataBytes);
	}

	@FieldHint(index = 1, length = 16)
	private UUID DeviceID;

	@FieldHint(index = 2, length = 14)
	private String DateTime;

	@FieldHint(index = 3, length = 4)
	private Integer DetectionMode;

	@FieldHint(index = 4, length = -1)
	private byte[] Picture;

	public UUID getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(UUID deviceID) {
		DeviceID = deviceID;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public Integer getDetectionMode() {
		return DetectionMode;
	}

	public void setDetectionMode(Integer detectionMode) {
		DetectionMode = detectionMode;
	}

	public byte[] getPicture() {
		return Picture;
	}

	public void setPicture(byte[] picture) {
		Picture = picture;
	}

	public static final class Response extends HomeCameraOutbndVO {
		private static final Logger logger = LoggerFactory.getLogger(InbndRqtIntrsDtcnPushVO.Response.class);

		Response(byte[] dataBytes) {
			super(dataBytes);
		}

		public Response() {
			super();
		}

		@FieldHint(index = 1, length = 4)
		private Integer Result;

		public void setResult(int result) {
			Result = result;
		}

	}


}
