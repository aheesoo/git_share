package com.kt.olleh.hms.sysspec.vo;

import java.util.HashMap;

public class HomeCameraCode {

	public static final class Header {
		public static final String Name = "HOMECCTV";
		public static final Integer Version = 0x00000001;
	}

	public static abstract class GwServer {
		public enum Request {
			/** 카메라 설정 정보 조회 */
			GENRL_SETUP_RETV(0x00800001),
			/** 카메라 설정 정보 저장 */
			GENRL_SETUP_STORE(0x00800002),
			/** 스케줄 조회 */
			SCDUL_RETV(0x00800003),
			/** 스케줄 저장 */
			SCDUL_STORE(0x00800004),
			/** 재시작 제어 */
			RESTART_CONTL(0x00800005),
			/** Wi-Fi 재접속 제어 */
			WIFI_RECN_CONTL(0x00800006),
			/** 경보 제어 */
			ALM_CONTL(0x00800007),
			/** Pan / Tilt 제어 */
			PAN_TILT_CONTL(0x00800008),
			/** Relay 접속 제어 */
			RELAY_CONN_CONTL(0x00800009),
			/** 음성 전달 */
			VC_RELAY(0x0080000A),
			/** 영상 전송 활성화 여부 저장 */
			VDO_SEND_ACTV_STORE(0x0080000B),
			/** 해상도 저장 */
			VDO_RESOL_STORE(0x0080000C),
			/** 침입 감지 설정 정보 저장 */
			INTRS_DTCN_STORE(0x0080000D),
			/** 민감도 저장 */
			SNSTY_STORE(0x0080000E),
			/** 영상 역전 여부 저장 */
			VDO_RVRT_STORE(0x0080000F),
			/** 움직임 감지 PNS 간격 저장 */
			MTN_DTCN_PUSH_INTVL_STORE(0x00800011),
			/** ucloud 용량 부족 PNS 간격 저장 */
			UCLOUD_CPCT_PUSH_INTVL_STORE(0x00800012),
			/** 저장 방식 저장 */
			STRGE_MODE_STORE(0x00800013),
			/** 감시 스케줄 조회 */
			WATCH_SCDUL_RETV(0x00800014),
			/** 감시 스케줄 저장 */
			WATCH_SCDUL_STORE(0x00800015),
			/** 녹화 스케줄 조회 */
			REC_SCDUL_RETV(0x00800016),
			/** 녹화 스케줄 저장 */
			REC_SCDUL_STORE(0x00800017),
			/** 움직임 감지 민감도 저장 */
			MTN_DTCN_SNSTY_STORE(0x00800018),
			/** 음향 감지 민감도 저장 */
			SOUND_DTCN_SNSTY_STORE(0x00800019),
			/** 릴레이 상태 전송 */
			RELAY_STTUS_SEND(0x0080001A),
			/** 카메라 상태 체크*/
			CAM_STTUS_CHK(0x0080001B),
			/** 단말 관리 설정 조회*/
			CAM_LOGMODE_RETV(0x0080001C),
			/** 단말 로그 실시간 요청*/
			CAM_LOGMODE_STORE(0x0080001E);
			

			private final int value;

			Request(final int value) {
				this.value = value;
			}

			public boolean equals(int obj) {
				return value == obj;
			}

			public int getValue() {
				return value;
			}

			@Override
			public String toString() {
				return Integer.toHexString(value);
			}

			public Response getResponse() {
				int response = this.value | 0x80000000;
				return Response.getResponse(response);
			}

			private static final HashMap<Integer, Request> map = new HashMap<Integer, Request>();
			static {
				for (Request it : values()) {
					map.put(it.getValue(), it);
				}
			}

			public static Request getRequest(int value) {
				return map.get(value);
			}

			public static Request getRequest(String code) {
				return map.get(Integer.parseInt(code, 16));
			}

		}

		public static enum Response {

			/** 카메라 설정 정보 조회 */
			SETUP_RETV(0x80800001),
			/** 카메라 설정 정보 저장 */
			SETUP_STORE(0x80800002),
			/** 스케줄 조회 */
			SCDUL_RETV(0x80800003),
			/** 스케줄 저장 */
			SCDUL_STORE(0x80800004),
			/** 재시작 제어 */
			RESTART_CONTL(0x80800005),
			/** Wi-Fi 재접속 제어 */
			WIFI_RECN_CONTL(0x80800006),
			/** 경보 제어 */
			ALM_CONTL(0x80800007),
			/** Pan / Tilt 제어 */
			PAN_TILT_CONTL(0x80800008),
			/** Relay 접속 제어 */
			RELAY_CONN_CONTL(0x80800009),
			/** 음성 전달 */
			VC_RELAY(0x8080000A),
			/** 영상 전송 활성화 여부 저장 */
			VDO_SEND_ACTV_STORE(0x8080000B),
			/** 해상도 저장 */
			VDO_RESOL_STORE(0x8080000C),
			/** 침입 감지 설정 정보 저장 */
			INTRS_DTCN_STORE(0x8080000D),
			/** 민감도 저장 */
			SNSTY_STORE(0x8080000E),
			/** 영상 역전 여부 저장 */
			VDO_RVRT_STORE(0x8080000F),
			/** 움직임 감지 PNS 간격 저장 */
			MTN_DTCN_PUSH_INTVL_STORE(0x80800011),
			/** ucloud 용량 부족 PNS 간격 저장 */
			UCLOUD_CPCT_PUSH_INTVL_STORE(0x80800012),
			/** 저장 방식 저장 */
			STRGE_MODE_STORE(0x80800013),
			/** 감시 스케줄 조회 */
			WATCH_SCDUL_RETV(0x80800014),
			/** 감시 스케줄 저장 */
			WATCH_SCDUL_STORE(0x80800015),
			/** 녹화 스케줄 조회 */
			REC_SCDUL_RETV(0x80800016),
			/** 녹화 스케줄 저장 */
			REC_SCDUL_STORE(0x80800017),
			/** 움직임 감지 민감도 저장 */
			MTN_DTCN_SNSTY_STORE(0x80800018),
			/** 음향 감지 민감도 저장 */
			SOUND_DTCN_SNSTY_STORE(0x80800019),
			/** 릴레이 상태 전송 */
			RELAY_STTUS_SEND(0x8080001A),
			/** 카메라 상태 체크*/
			CAM_STTUS_CHK(0x8080001B),
			/** 단말 관리 설정 조회*/
			CAM_LOGMODE_RETV(0x8080001C),
			/** 단말 로그 실시간 요청*/
			CAM_LOGMODE_STORE(0x8080001E);

			private final int value;

			Response(final int value) {
				this.value = value;
			}

			public boolean equals(int obj) {
				return value == obj;
			}

			public int getValue() {
				return value;
			}

			@Override
			public String toString() {
				return Integer.toHexString(value);
			}

			public Request getRequest() {
				int request = this.value & 0x0FFFFFFF;
				return Request.getRequest(request);
			}

			private static final HashMap<Integer, Response> map = new HashMap<Integer, Response>();
			static {
				for (Response it : values()) {
					map.put(it.getValue(), it);
				}
			}

			public static Response getResponse(int value) {
				return map.get(value);
			}

			public static Response getResponse(String code) {
				return map.get(Integer.parseInt(code, 16));
			}
		}
	}

	public static final class Camera {
		public enum Request {
			/** 카메라 인증 및 등록 */
			REG_AND_ATHN(0x00000001),
			/** Keep Alive */
			KEEPALIVE(0x00000002),
			/** 침입 감지 알림 */
			INTRS_DTCN_PUSH(0x00000003),
			/** ucloud 용량 부족 알림 */
			UCLOUD_CPCT_PUSH(0x00000004),
			/** 전원 꺼짐 알림 */
			PWROFF_PUSH(0x00000005),
			/** ucloud 토큰 갱신 */
			UCLOUD_TOKN_UPD(0x00000006),
			/** SDCard 장애 알림 */
			SDCARD_OOS_PUSH(0x00000007),
			/** 단말로그 전송 */
			CAM_LOG_SEND(0x00000008);

			private final int value;

			Request(final int value) {
				this.value = value;
			}

			public boolean equals(int obj) {
				return value == obj;
			}

			public int getValue() {
				return value;
			}

			@Override
			public String toString() {
				return Integer.toHexString(value);
			}

			public Response getResponse() {
				int response = this.value | 0x80000000;
				return Response.getResponse(response);
			}

			private static final HashMap<Integer, Request> map = new HashMap<Integer, Request>();
			static {
				for (Request it : values()) {
					map.put(it.getValue(), it);
				}
			}

			public static Request getRequest(int value) {
				return map.get(value);
			}

			public static Request getRequest(String code) {
				return map.get(Integer.parseInt(code, 16));
			}
		}

		public static enum Response {
			/** 카메라 인증 및 등록 */
			REG_AND_ATHN(0x80000001),
			/** Keep Alive */
			KEEPALIVE(0x80000002),
			/** 침입 감지 알림 */
			INTRS_DTCN_PUSH(0x80000003),
			/** ucloud 용량 부족 알림 */
			UCLOUD_CPCT_PUSH(0x80000004),
			/** 전원 꺼짐 알림 */
			PWROFF_PUSH(0x80000005),
			/** ucloud 토큰 갱신 */
			UCLOUD_TOKN_UPD(0x80000006),
			/** SDCard 장애 알림 */
			SDCARD_OOS_PUSH(0x80000007),
			/** 단말로그 전송 */
			CAM_LOG_SEND(0x80000008);

			private final int value;

			Response(final int value) {
				this.value = value;
			}

			public boolean equals(int obj) {
				return value == obj;
			}

			public int getValue() {
				return value;
			}

			@Override
			public String toString() {
				return Integer.toHexString(value);
			}

			public Request getRequest() {
				int request = this.value & 0x0FFFFFFF;
				return Request.getRequest(request);
			}

			private static final HashMap<Integer, Response> map = new HashMap<Integer, Response>();
			static {
				for (Response it : values()) {
					map.put(it.getValue(), it);
				}
			}

			public static Response getResponse(int value) {
				return map.get(value);
			}

			public static Response getResponse(String code) {
				return map.get(Integer.parseInt(code, 16));
			}
		}
	}

}
