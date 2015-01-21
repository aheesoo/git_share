package com.kt.olleh.hms.sysspec.adaptor;

import java.util.HashMap;

public class HomeCameraAdaptorCode
{
	public static enum Header
	{
		/** 이름 */
		NAME("01"),
		/** 버전 */
		VERSION("02"),
		/** 명령어코드 */
		COMMAND("03"),
		/** 명령어아이디 */
		COMMAND_ID("04"),
		/** 바디사이즈 */
		DATA_SIZE("05");

		private final String value;

		private Header(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, Header> map = new HashMap<String, Header>();
		static {
			for (Header it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static Header fromString(String value) {
			return map.get(value);
		}
	}

	/**
	 * 명령어
	 * @since	: 2014. 11. 8.
	 * @author	: CBJ
	 * <PRE>
	 * Revision History
	 * ----------------------------------------------------
	 * 2014. 11. 8. CBJ: 최초작성
	 * ----------------------------------------------------
	 * </PRE>
	 */
	public static enum Command
	{
		//TODO 모든명령어 넣을 것
		/** 이름 */
		C00000001(0x00000001),
		;
		private final Integer value;

		private Command(Integer value) {
			this.value = value;
		}

		public boolean equals(Integer obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public Integer getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value.toString();
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, Command> map = new HashMap<String, Command>();
		static {
			for (Command it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static Command fromInteger(Integer value) {
			return map.get(value);
		}
	}

	/**
	 * <PRE>
	 *  ClassName SpotDevApdAtrib HomeCameraAdaptorCode
	 * </PRE>
	 * @brief: 현장장치추가속성
	 * @version 1.0
	 * @date 2014. 10. 15. 오후 8:48:05
	 * @author CBJ
	 */
	public static enum SpotDevApdAtrib
	{
		/** 장치모델 */
		DEV_MODEL("DEV_MODEL"),
		/** 장치유형 */
		DEV_TYPE("DEV_TYPE"),
		/** 맥어드레스 */
		MAC_ADR("MAC_ADR"),
		/** CEMS 비밀키 */
		CEMS_SCRT_KEY("CEMS_SCRT_KEY"),
		/** CCTV 설치 위치 */
		CCTV_EQP_LO("CCTV_EQP_LO"),
		/** DEV_UU_ID */
		DEV_UU_ID("DEV_UU_ID"),
		;

		private final String value;

		private SpotDevApdAtrib(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, SpotDevApdAtrib> map = new HashMap<String, SpotDevApdAtrib>();
		static {
			for (SpotDevApdAtrib it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static SpotDevApdAtrib fromString(String value) {
			return map.get(value);
		}

	}


	public static enum SessionValue
	{
		/** 현장장치정보 */
		SPOT_DEV("SPOT_DEV"),
		/** 세션키 */
		SESSION_KEY("SESSION_KEY"),
		;

		private final String value;

		private SessionValue(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, SessionValue> map = new HashMap<String, SessionValue>();
		static {
			for (SessionValue it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static SessionValue fromString(String value) {
			return map.get(value);
		}
	}

	/**
	 * 상태태그
	 * @since	: 2014. 11. 12.
	 * @author	: CBJ
	 * <PRE>
	 * Revision History
	 * ----------------------------------------------------
	 * 2014. 11. 12. CBJ: 최초작성
	 * ----------------------------------------------------
	 * </PRE>
	 */
	public static enum SttusTag
	{
		/** 릴레이상태 */
		RELAY_STTUS("20000004"),
		;

		private final String value;

		private SttusTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, SttusTag> map = new HashMap<String, SttusTag>();
		static {
			for (SttusTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static SttusTag fromString(String value) {
			return map.get(value);
		}
	}

	/**
	 * 제어태그
	 * @since	: 2014. 11. 8.
	 * @author	: CBJ
	 * <PRE>
	 * Revision History
	 * ----------------------------------------------------
	 * 2014. 11. 8. CBJ: 최초작성
	 * ----------------------------------------------------
	 * </PRE>
	 */
	public static enum ContlTag
	{
		/** 재시작 */
		RESTART("30000002"),
		/** Wi-Fi 재접속 제어 */
		WIFI_RESTART("30000003"),
		/** 경고음 발생 */
		ALM("30000004"),
		/** 팬틸트제어 */
		PAN_TILT("30000005"),
		/** 릴레이 세션 접속 제어 */
		RELAY_SESSN_CONN("30000010")
		;

		private final String value;

		private ContlTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, ContlTag> map = new HashMap<String, ContlTag>();
		static {
			for (ContlTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static ContlTag fromString(String value) {
			return map.get(value);
		}
	}

	/**
	 *
	 * @since	: 2014. 11. 8.
	 * @author	: CBJ
	 * <PRE>
	 * Revision History
	 * ----------------------------------------------------
	 * 2014. 11. 8. CBJ: 최초작성
	 * ----------------------------------------------------
	 * </PRE>
	 */
	public static enum CmdTag
	{
		/** 일반설정조회 */
		GENRL_SETUP_RETV("31000002"),
		/** 스케줄설정조회 */
		SCDUL_RETV("31000003"),
		/** 감시스케줄조회 */
		WATCH_SCDUL_RETV("31000004"),
		/** 녹화스케줄조회 */
		REC_SCDUL_RETV("31000005"),
		/** ucloud 토큰 갱신  */
		UCLOUD_TOKN_RETV("31000006")
		;

		private final String value;

		private CmdTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, CmdTag> map = new HashMap<String, CmdTag>();
		static {
			for (CmdTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static CmdTag fromString(String value) {
			return map.get(value);
		}
	}

	public static enum BinTag
	{
		/** 음성 데이터 */
		VC_DATA("50000001"),
		;

		private final String value;

		private BinTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, BinTag> map = new HashMap<String, BinTag>();
		static {
			for (BinTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static BinTag fromString(String value) {
			return map.get(value);
		}
	}

	public static enum GenlSetupTag
	{
		/** 영상 송출 활성화 여부 - API : 20*/
		VDO_SEND_ACTVA_YN("80000001"),
		/** 해상도 옵션 API : 21*/
		VDO_RESOL("80000002"),
		/** 침입 감지 활성화 여부 API : ??*/
		INTRS_DTCN_ACTVA_YN("80000003"), //Open API에서 80000004로 변경해서 옴.
		/** 침입 감지 모드 API : 22*/
		INTRS_DTCN_MODE("80000004"),
		/** 움직임 감지 민감도 API : 32*/
		MTN_DTCN_SNSTY("80000005"),
		/** 음향 감지 민감도 API : 33*/
		SOUND_DTCN_SNSTY("80000006"),
		/** 영상 송출 역전 여부 API : 24*/
		VDO_RVRT("80000007"),
		/** 움직임 감지 PNS 알림 간격 API : 25*/
		MTN_DTCN_PUSH_INTVL("80000008"),
		/** ucloud 용량 부족 PNS 알림 간격 API : 26*/
		UCLOUD_CPCT_PUSH_INTVL("80000009"),
		/** 저장 매체 모드 API : 27*/
		STRGE_MODE("80000010"),
		/** SDCard 장착 상태 API : ?? */
		SDCARD_EQUIP_STATS("80000011"),
		/** 예약 녹화 스케줄 활성화 여부 API : ??*/
		RSRV_REC_SCDUL_ACTVA_YN("80000012"),
		/** 감시 스케줄 활성화 여부 API : ??*/
		WATCH_SCDUL_ACTVA_YN("80000013"),
		/** 예약 녹화 모드 API : ?? */
		RSRV_REC_MODE("80000014"),
		/** Wi-Fi SSID API : ??*/
		WIFI_SSID("80000015"),
		/** Wi-Fi 신호 세기 API : ??*/
		WIFI_SIGNL_STRTH("80000016"),
		/** 홈카메라 설정 API : 6*/
		GENRL_SETUP("80000017"),
		/** 민감도 설정 API : 23*/
		DTCN_SNSTY("80000018"),
		/** Ucloud API Key : 17 */
		UCLOUD_API_KEY("80000024"),
		/** Ucloud API Secret : 17 */
		UCLOUD_API_SECRET("80000025"),
		/** Ucloud Token : 17 */
		UCLOUD_TOKEN("80000026"),
		/** Ucloud Token : 17 */
		UCLOUD_SECRET("80000027"),
		/** Ucloud Path : 17 */
		UCLOUD_PATH("80000028"),
		;

		private final String value;

		private GenlSetupTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, GenlSetupTag> map = new HashMap<String, GenlSetupTag>();
		static {
			for (GenlSetupTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static GenlSetupTag fromString(String value) {
			return map.get(value);
		}
	}

	public static enum MsrTag
	{
		/** 홈모니터링 이벤트 코드 */
		CAMERA_EVENT("10001097"),
		/** ucloud 남은 공간 */
		UCLOUD_SPARE_STORAGE("10001096"),
		/** ucloud 이벤트 타입 */
		UCLOUD_EVENT_TYPE("10001113")
		;

		private final String value;

		private MsrTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, MsrTag> map = new HashMap<String, MsrTag>();
		static {
			for (MsrTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static MsrTag fromString(String value) {
			return map.get(value);
		}
	}

	public static enum StrTag
	{
		/** 홈모니터링 이벤트 코드 */
		DEVICE_ID("60000001"),
		IMAGE_FILE_URL("60000002"),
		ERROR_MSG("60000003"),
		DEVICE_NAME("60000005"),
		DEV_UU_ID("60000006"),
		;

		private final String value;

		private StrTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, StrTag> map = new HashMap<String, StrTag>();
		static {
			for (StrTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static StrTag fromString(String value) {
			return map.get(value);
		}
	}

	public static enum ScdulSetupTag
	{
		/** 감시 스케줄 설정 */
		WATCH_SCDUL_SETUP("81000001"),
		/** 녹화 스케줄 설정 */
		REC_SCDUL_SETUP("81000002"),
		/** 스케줄 설정 */
		GENRL_SCDUL_SETUP("81000003")
		;

		private final String value;

		private ScdulSetupTag(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, ScdulSetupTag> map = new HashMap<String, ScdulSetupTag>();
		static {
			for (ScdulSetupTag it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static ScdulSetupTag fromString(String value) {
			return map.get(value);
		}
	}

	public static enum RowExtension
	{
		STATUS("status"),
		RESOLUTION("resolution"),
		DETECTION("detection"),
		DETECTION_MODE("detectionMode"),
		M_SENSITIVITY("mSensitivity"),
		V_SENSITIVITY("vSensitivity"),
		REVERTED("reverted"),
		MOVING_PNS("movingPNS"),
		STORAGE_PNS("storagePNS"),
		SAVE_MODE("saveMode"),
		UCLOUD_APIKEY("ucloudAPIKey"),
		UCLOUD_APISECRET("ucloudAPISecret"),
		UCLOUD_SECRET("ucloudSecret"),
		UCLOUD_TOKEN("ucloudToken"),
		UCLOUD_PATH("ucloudPath"),
		SCHEDULED("scheduled"),
		TIME_FROM("timeFrom"),
		TIME_TO("timeTo"),
		DAY("day"),
		TIME_REC("timeRec"),
		MODE_REC("modeRec"),
		DURATION_REC("durationRec"),
		DAY_REC("dayRec"),
		PAN("pan"),
		P_ANGLE("pAngle"),
		P_DIRECTION("pDirection"),
		TILT("tilt"),
		T_ANGLE("tAngle"),
		T_DIRECTION("tDirection"),
		RELAY_IP("relayIp"),
		RELAY_PORT("relayPort"),
		RELAY_DEVICE_ID("relayDeviceId"),
		RELAY_DEVICE_PW("relayDevicePw"),
		SECRET_KEY("secretKey"),
		IV("iV"),
		DATETIME("dateTime"),
		VOICE("voice"),
		RESULT("result"),
		STATE_CODE("stateCode"),
		ITG_SETUP("itgSetup"),
		FIRMWARE_VERSION("firmwareVersion"),
		AP_NAME("apName"),
		AP_POWER("apPower")
		;

		private final String value;

		private RowExtension(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, RowExtension> map = new HashMap<String, RowExtension>();
		static {
			for (RowExtension it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static RowExtension fromString(String value) {
			return map.get(value);
		}

	}

	public static enum TransacObject
	{
		COMMNAD_ID("COMMNAD_ID"),
		;

		private final String value;

		private TransacObject(String value) {
			this.value = value;
		}

		public boolean equals(String obj) {
			return value.equals(obj);
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, TransacObject> map = new HashMap<String, TransacObject>();
		static {
			for (TransacObject it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static TransacObject fromString(String value) {
			return map.get(value);
		}
	}

	public static enum HeaderExtension
	{
//		/** 수신객체 */
//		RCV_PACKT_VO("RCV_PACKT_VO"),
//		/** TCP 헤더 */
//		TCP_HEADER("TCP_HEADER"),
		/** 세션이벤트 */
		SESSION_EVENT("SESSION_EVENT"),
		;

		private final String value;

        private HeaderExtension(String value) {
                this.value = value;
        }

        public boolean equals(String obj)
    	{
        	return value.equals(obj);
    	}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}


		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<String, HeaderExtension> map = new HashMap<String, HeaderExtension>();
		static {
			for(HeaderExtension it : values()) {
				map.put(it.toString(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static HeaderExtension fromString(String value) {
			return map.get(value);
		}

	}




}
