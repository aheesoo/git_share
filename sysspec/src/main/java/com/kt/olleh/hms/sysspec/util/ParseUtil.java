package com.kt.olleh.hms.sysspec.util;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kt.smcp.gw.ca.util.StringUtil;
import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.annotation.FieldHintIndexComparator;
import com.kt.olleh.hms.sysspec.vo.HomeCameraCode.Camera;
import com.kt.olleh.hms.sysspec.vo.HomeCameraCode.GwServer;
import com.kt.olleh.hms.sysspec.vo.HomeCameraVO;
import com.kt.olleh.hms.sysspec.vo.InbndRqtKeepAliveVO;
import com.kt.olleh.hms.sysspec.vo.InbndRqtPwrOffPushVO;
import com.kt.olleh.hms.sysspec.vo.InbndRqtRegAndAthn;
import com.kt.olleh.hms.sysspec.vo.InbndRqtIntrsDtcnPushVO;
import com.kt.olleh.hms.sysspec.vo.InbndRqtSDCardErrPushVO;
import com.kt.olleh.hms.sysspec.vo.InbndRqtUcloudErrPushVO;
import com.kt.olleh.hms.sysspec.vo.InbndRqtUcloudToknUpdVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtAlmContlVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtIntrsDtcnStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtMtnDtcnPushIntvlStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtMtnDtcnSnstyStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtPanTiltContlVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtRecScdulRetvVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtRecScdulStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtRelaySessnConnVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtRelaySttusVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtRestartContlVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtScdulRetvVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtScdulStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtSetupRetvVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtSetupStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtSnstyStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtSoundDtcnSnstyStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtStrgeModeStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtUcloudCpctPushIntvlStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtVcCnvyVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtVdoResolStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtVdoRvrtStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtVdoSendActvStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtWatchScdulRetvVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtWatchScdulStoreVO;
import com.kt.olleh.hms.sysspec.vo.OutbndRqtWifiRecnContlVO;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class ParseUtil {
	
	public static byte[] data;
	
	public static List<Field> getSysSepc(String inputHexCode, int def) throws DecoderException {
		List<Field> result = null;

		inputHexCode = inputHexCode.trim().replaceAll(" ", "");
		byte[] packet = StringUtil.hexToByteArray(inputHexCode);
		int command = HomeCameraUtil.extractCommand(packet);
		double version = HomeCameraUtil.extractVersion(packet);
		Camera.Request cmd = Camera.Request.getRequest(command);
		Camera.Response cmdR = Camera.Response.getResponse(command);
		GwServer.Request gcmd = GwServer.Request.getRequest(command);
		GwServer.Response gcmdR = GwServer.Response.getResponse(command);

		//484F4D454343545600004F000000000206B6E87A0000001000000000000187FF0000000000000001 //keepalive commandid;
	 	//data = Hex.decodeHex(inputHexCode.toCharArray());
		
	 	if (cmd == Camera.Request.REG_AND_ATHN){
			InbndRqtRegAndAthn request = new InbndRqtRegAndAthn();
			
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		}
	 	else if (cmdR == Camera.Response.REG_AND_ATHN){
			InbndRqtRegAndAthn.Response request = new InbndRqtRegAndAthn.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		}
	 	else if (cmd == Camera.Request.KEEPALIVE){ //
			InbndRqtKeepAliveVO request = new InbndRqtKeepAliveVO();
			
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}			
		}
	 	else if (cmdR == Camera.Response.KEEPALIVE){ //
			InbndRqtKeepAliveVO.Response request = new InbndRqtKeepAliveVO.Response();
			
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}			
		}
	 	else if (cmd == Camera.Request.INTRS_DTCN_PUSH){ // 침입감지
	 		InbndRqtIntrsDtcnPushVO request = new InbndRqtIntrsDtcnPushVO();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		} 
	 	else if (cmdR == Camera.Response.INTRS_DTCN_PUSH){ // 침입감지
	 		InbndRqtIntrsDtcnPushVO.Response request = new InbndRqtIntrsDtcnPushVO.Response();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		} 
	 	else if (cmd == Camera.Request.UCLOUD_CPCT_PUSH){ //
	 		InbndRqtUcloudErrPushVO request = new InbndRqtUcloudErrPushVO();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
	 	else if (cmdR == Camera.Response.UCLOUD_CPCT_PUSH){ //
	 		InbndRqtUcloudErrPushVO.Response request = new InbndRqtUcloudErrPushVO.Response();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
	 	else if (cmd == Camera.Request.PWROFF_PUSH){ //전원 꺼짐 알림
	 		InbndRqtPwrOffPushVO request = new InbndRqtPwrOffPushVO();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
	 	else if (cmdR == Camera.Response.PWROFF_PUSH){ //전원 꺼짐 알림
	 		InbndRqtPwrOffPushVO.Response request = new InbndRqtPwrOffPushVO.Response();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
	 	else if (cmd == Camera.Request.UCLOUD_TOKN_UPD){ //ucloud 토큰 갱신
	 		InbndRqtUcloudToknUpdVO request = new InbndRqtUcloudToknUpdVO();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
	 	else if (cmdR == Camera.Response.UCLOUD_TOKN_UPD){ //ucloud 토큰 갱신
	 		InbndRqtUcloudToknUpdVO.Response request = new InbndRqtUcloudToknUpdVO.Response();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
	 	else if (cmd == Camera.Request.SDCARD_OOS_PUSH){ // SDCard 장애 알림
	 		InbndRqtSDCardErrPushVO request = new InbndRqtSDCardErrPushVO();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
	 	else if (cmdR == Camera.Response.SDCARD_OOS_PUSH){ // SDCard 장애 알림
	 		InbndRqtSDCardErrPushVO.Response request = new InbndRqtSDCardErrPushVO.Response();
	 		if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	
		} 
		else if (gcmd == GwServer.Request.GENRL_SETUP_RETV){ // 카메라 설정 정보 조회
			OutbndRqtSetupRetvVO request = new OutbndRqtSetupRetvVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.SETUP_RETV){ // 카메라 설정 정보 조회
			OutbndRqtSetupRetvVO.Response request = new OutbndRqtSetupRetvVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.GENRL_SETUP_STORE){ //카메라 설정 정보 저장
			OutbndRqtSetupStoreVO request = new OutbndRqtSetupStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.SETUP_STORE){ //카메라 설정 정보 저장
			OutbndRqtSetupStoreVO.Response request = new OutbndRqtSetupStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.SCDUL_RETV){//스케줄 조회
			OutbndRqtScdulRetvVO request = new OutbndRqtScdulRetvVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.SCDUL_RETV){//스케줄 조회
			OutbndRqtScdulRetvVO.Response request = new OutbndRqtScdulRetvVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.SCDUL_STORE){//스케줄 저장
			OutbndRqtScdulStoreVO request = new OutbndRqtScdulStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.SCDUL_STORE){//스케줄 저장
			OutbndRqtScdulStoreVO.Response request = new OutbndRqtScdulStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.RESTART_CONTL){// 재시작 제어
			OutbndRqtRestartContlVO request = new OutbndRqtRestartContlVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.RESTART_CONTL){// 재시작 제어
			OutbndRqtRestartContlVO.Response request = new OutbndRqtRestartContlVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.WIFI_RECN_CONTL){// Wi-Fi 재접속 제어
			OutbndRqtWifiRecnContlVO request = new OutbndRqtWifiRecnContlVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.WIFI_RECN_CONTL){// Wi-Fi 재접속 제어
			OutbndRqtWifiRecnContlVO.Response request = new OutbndRqtWifiRecnContlVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.ALM_CONTL){// 경보 제어
			OutbndRqtAlmContlVO request = new OutbndRqtAlmContlVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.ALM_CONTL){// 경보 제어
			OutbndRqtAlmContlVO.Response request = new OutbndRqtAlmContlVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.PAN_TILT_CONTL){// Pan / Tilt 제어
			OutbndRqtPanTiltContlVO request = new OutbndRqtPanTiltContlVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.PAN_TILT_CONTL){// Pan / Tilt 제어
			OutbndRqtPanTiltContlVO.Response request = new OutbndRqtPanTiltContlVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.RELAY_CONN_CONTL){// Relay 접속 제어
			OutbndRqtRelaySessnConnVO request = new OutbndRqtRelaySessnConnVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.RELAY_CONN_CONTL){// Relay 접속 제어
			OutbndRqtRelaySessnConnVO.Response request = new OutbndRqtRelaySessnConnVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.VC_RELAY){// 음성 전달
			OutbndRqtVcCnvyVO request = new OutbndRqtVcCnvyVO();
			if(1 == def){
				result = getField(request.getClass(), version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.VC_RELAY){// 음성 전달
			OutbndRqtVcCnvyVO.Response request = new OutbndRqtVcCnvyVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.VDO_SEND_ACTV_STORE){// 영상 전송 활성화 여부 저장
			OutbndRqtVdoSendActvStoreVO request = new OutbndRqtVdoSendActvStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.VDO_SEND_ACTV_STORE){// 영상 전송 활성화 여부 저장
			OutbndRqtVdoSendActvStoreVO.Response request = new OutbndRqtVdoSendActvStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.VDO_RESOL_STORE){// 해상도 저장
			OutbndRqtVdoResolStoreVO request = new OutbndRqtVdoResolStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.VDO_RESOL_STORE){// 해상도 저장
			OutbndRqtVdoResolStoreVO.Response request = new OutbndRqtVdoResolStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.INTRS_DTCN_STORE){// 침입 감지 설정 정보 저장
			OutbndRqtIntrsDtcnStoreVO request = new OutbndRqtIntrsDtcnStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.INTRS_DTCN_STORE){// 침입 감지 설정 정보 저장
			OutbndRqtIntrsDtcnStoreVO.Response request = new OutbndRqtIntrsDtcnStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.SNSTY_STORE){//  민감도 저장
			OutbndRqtSnstyStoreVO request = new OutbndRqtSnstyStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.SNSTY_STORE){//  민감도 저장
			OutbndRqtSnstyStoreVO.Response request = new OutbndRqtSnstyStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.VDO_RVRT_STORE){//  영상 역전 여부 저장
			OutbndRqtVdoRvrtStoreVO request = new OutbndRqtVdoRvrtStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.VDO_RVRT_STORE){//  영상 역전 여부 저장
			OutbndRqtVdoRvrtStoreVO.Response request = new OutbndRqtVdoRvrtStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.MTN_DTCN_PUSH_INTVL_STORE){// 움직임 감지 PNS 간격 저장
			OutbndRqtMtnDtcnPushIntvlStoreVO request = new OutbndRqtMtnDtcnPushIntvlStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.MTN_DTCN_PUSH_INTVL_STORE){// 움직임 감지 PNS 간격 저장
			OutbndRqtMtnDtcnPushIntvlStoreVO.Response request = new OutbndRqtMtnDtcnPushIntvlStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.UCLOUD_CPCT_PUSH_INTVL_STORE){// ucloud 용량 부족 PNS 간격 저장
			OutbndRqtUcloudCpctPushIntvlStoreVO request = new OutbndRqtUcloudCpctPushIntvlStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.UCLOUD_CPCT_PUSH_INTVL_STORE){// ucloud 용량 부족 PNS 간격 저장
			OutbndRqtUcloudCpctPushIntvlStoreVO.Response request = new OutbndRqtUcloudCpctPushIntvlStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
	 	
		else if (gcmd == GwServer.Request.STRGE_MODE_STORE){// 저장 방식 저장
			OutbndRqtStrgeModeStoreVO request = new OutbndRqtStrgeModeStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.STRGE_MODE_STORE){// 저장 방식 저장
			OutbndRqtStrgeModeStoreVO.Response request = new OutbndRqtStrgeModeStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.WATCH_SCDUL_RETV){// 감시 스케줄 조회
			OutbndRqtWatchScdulRetvVO request = new OutbndRqtWatchScdulRetvVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.WATCH_SCDUL_RETV){// 감시 스케줄 조회
			OutbndRqtWatchScdulRetvVO.Response request = new OutbndRqtWatchScdulRetvVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.WATCH_SCDUL_STORE){// 감시 스케줄 저장
			OutbndRqtWatchScdulStoreVO request = new OutbndRqtWatchScdulStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.WATCH_SCDUL_STORE){// 감시 스케줄 저장
			OutbndRqtWatchScdulStoreVO.Response request = new OutbndRqtWatchScdulStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.REC_SCDUL_RETV){// 녹화 스케줄 조회
			OutbndRqtRecScdulRetvVO request = new OutbndRqtRecScdulRetvVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.REC_SCDUL_RETV){// 녹화 스케줄 조회
			OutbndRqtRecScdulRetvVO.Response request = new OutbndRqtRecScdulRetvVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.REC_SCDUL_STORE){// 녹화 스케줄 저장
			OutbndRqtRecScdulStoreVO request = new OutbndRqtRecScdulStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.REC_SCDUL_STORE){// 녹화 스케줄 저장
			OutbndRqtRecScdulStoreVO.Response request = new OutbndRqtRecScdulStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.MTN_DTCN_SNSTY_STORE){// 움직임 감지 민감도 저장
			OutbndRqtMtnDtcnSnstyStoreVO request = new OutbndRqtMtnDtcnSnstyStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.MTN_DTCN_SNSTY_STORE){// 움직임 감지 민감도 저장
			OutbndRqtMtnDtcnSnstyStoreVO.Response request = new OutbndRqtMtnDtcnSnstyStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.SOUND_DTCN_SNSTY_STORE){// 음향 감지 민감도 저장
			OutbndRqtSoundDtcnSnstyStoreVO request = new OutbndRqtSoundDtcnSnstyStoreVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.SOUND_DTCN_SNSTY_STORE){// 음향 감지 민감도 저장
			OutbndRqtSoundDtcnSnstyStoreVO.Response request = new OutbndRqtSoundDtcnSnstyStoreVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmd == GwServer.Request.RELAY_STTUS_SEND){// 릴레이 상태 전송
			OutbndRqtRelaySttusVO request = new OutbndRqtRelaySttusVO();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		else if (gcmdR == GwServer.Response.RELAY_STTUS_SEND){// 릴레이 상태 전송
			OutbndRqtRelaySttusVO.Response request = new OutbndRqtRelaySttusVO.Response();
			if(1 == def){
				result = getField(HomeCameraVO.class, version);
			}else{
				result = getField(request.getClass(), version);
			}	 		
		}
		
	 	
		return result;
	}
	
	public static List<Field> getField(Class<?> c, double version){
		LinkedList<Field> fieldsList = new LinkedList<>(Arrays.asList(c.getDeclaredFields()));
		Iterator<Field> iterator = fieldsList.iterator();
		
		while (iterator.hasNext())
		{
			Field field = iterator.next();
			if (field.isAnnotationPresent(FieldHint.class))
			{
				FieldHint fieldHint = field.getAnnotation(FieldHint.class);
				if (version >= fieldHint.minVer() && version <= fieldHint.maxVer())
					continue;
			}

			iterator.remove();
		}

		/** sorting fields list by HCFieldHint's index value **/
		Collections.sort(fieldsList, new FieldHintIndexComparator());

		return fieldsList;
	}
	
}
