package com.kt.olleh.hms.sysspec.vo;

public abstract class HomeCameraInbndVO extends HomeCameraVO {

	HomeCameraInbndVO() {
		super();
	}
	
	HomeCameraInbndVO(byte[] dataBytes) {
		super();
		parsePacket(dataBytes);
	}

	public void parsePacket(byte[] dataBytes) {
		int bodyOffset = parseHeader(dataBytes);
		parseBody(dataBytes, bodyOffset);
	}
}
