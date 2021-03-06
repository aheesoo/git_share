package com.kt.olleh.hms.sysspec;

import java.awt.TextArea;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Control;

import com.kt.olleh.hms.sysspec.annotation.FieldHint;
import com.kt.olleh.hms.sysspec.util.HomeCameraUtil;
import com.kt.olleh.hms.sysspec.util.ParseUtil;
import com.kt.olleh.hms.sysspec.vo.HomeCameraVO;
import com.kt.smcp.gw.ca.util.StringUtil;

/**
 * Hello world!
 * 484F4D454343545600004F000000000206B6E87A0000001000000000000187FF0000000000000001 //keepalive commandid;
 * 484F4D4543435456 00004F00 00000008 6B6C4A40 000043A300000000000187FF0000000000000001 303031323334414142424343 323031353031313531393137303630303130303132333441414242 0001 0004 74657374 323031353031313531393137303630303130303132333441414242 0001 0004 74657374 32303135303131353139313730363030312020202053595354454d 0001 0004 74657374 32303135303131353139313730363030312020202053595354454d 0001 0004 74657374
 * 484F4D4543435456 00004F00 00000008 6B6C4A40 000043A300000000000187FF0000000000000001 303031323334414142424343 323031353031313531393137303630303130303132333441414242 0001 0004 74657374 323031353031313531393137303630303130303132333441414242 0001 0004 74657374 32303135303131353139313730363030312020202053595354454d 0001 0004 74657374 32303135303131353139313730363030312020202053595354454d 0001 0009 746573744576657279 32303135303131353139313730363030312020202053595354454d 0001 0009 746573744576657279
 */
public class Appswt 
{
	private static Display display;

	private static Button buttonGet;
	private static Button buttonInit;
	
	private Text textHex; 
	private Group groupHeader; 
	private Group groupBody;
	
	public Appswt(){
		display = Display.getDefault();
		
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(650, 750);
		shell.setText("System Spec by hexcode");
		
		Group groupProxy = new Group(shell, SWT.NULL);
		groupProxy.setText("System Infomation");
		groupProxy.setLayout(new GridLayout(2, false));
		groupProxy.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label proxyNameLabel = new Label(groupProxy, SWT.NULL);
		proxyNameLabel.setLayoutData(new GridData(85, 0));
		proxyNameLabel.setVisible(false);
		
		Label proxyInputLabel = new Label(groupProxy, SWT.NULL);
		proxyInputLabel.setLayoutData(new GridData(85, 0));
		proxyInputLabel.setVisible(false);	
		
		new Label(groupProxy, SWT.NULL).setText("Hex Code");
		textHex = new Text(groupProxy, SWT.SINGLE | SWT.BORDER);
		textHex.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textHex.setText("");
		
		groupBody = new Group(shell, SWT.NULL);
		groupBody.setText("Detail");
		groupBody.setLayout(new GridLayout(2, false));
		groupBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label bodyNameLabel = new Label(groupBody, SWT.NULL);
		bodyNameLabel.setLayoutData(new GridData(85, 0));
		bodyNameLabel.setVisible(false);
		
		Label bodyInputLabel = new Label(groupBody, SWT.NULL);
		bodyInputLabel.setLayoutData(new GridData(85, 0));
		bodyInputLabel.setVisible(false);
		
		buttonGet = new Button(shell, SWT.PUSH);
		buttonGet.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		buttonGet.setText("Confirm");
		buttonGet.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					if("Confirm".equals(buttonGet.getText())) {
				    	String inputCode = textHex.getText();
				    	getCam(inputCode);
					} 
					
					break;
				}
			}
		});
		
		textHex.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e)
			{
				String string = "";
 
				if(e.keyCode == SWT.CR  || e.keyCode == SWT.KEYPAD_CR)
				{
					String inputCode2 = textHex.getText();
			    	getCam(inputCode2);
				}
			}
		});
		
		buttonInit = new Button(shell, SWT.PUSH);
		buttonInit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		buttonInit.setText("Init");
		buttonInit.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					if("Init".equals(buttonInit.getText())) {
						Control[] controls = groupBody.getChildren();
						for(int i = 0; i < controls.length; i++) {
							if(controls[i].getVisible()) {
								controls[i].dispose();
							}
						}
						textHex.setText("");
						textHex.setFocus();
					} 
					
					break;
				}
			}
		});
		
		shell.open();
		shell.layout();
				
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}
		
		display.dispose();
	}
	
	//public void setCamSpec(List<Field> voList){
	@FieldHint(index = 4, length = 4)
	private Integer	DataSize;

	public void setDataSize(int datasize) {
		DataSize = datasize;
	}

	public Integer getDataSize() {
		return DataSize;
	}
	
	private int bodyOff = 0;
	private int logOff = 0;
	private short logValueLength = 0;
	public void setCamSpec(List<Field> voList, String code, int defInt) throws DecoderException{
		
		if(1 == defInt){
			Control[] controls = groupBody.getChildren();
			for(int i = 0; i < controls.length; i++) {
				if(controls[i].getVisible()) {
					controls[i].dispose();
				}
			}
		}
		
		code = code.trim().replace(" ", "");
		byte[] data = Hex.decodeHex(code.toCharArray());
		
		String name = "";
		String reValue = "";
		
		try{
			
			int offset = 0;
			if(2 == defInt){
				offset = bodyOff;
				bodyOff = 0;
			}
			if(3 != defInt){
				for(int i=0; i<voList.size(); i++){
					Class<?> fieldType = voList.get(i).getType();
					String fieldTypeSimpleName = fieldType.getSimpleName();
					name = voList.get(i).getName();
					Field field = voList.get(i);
					
					FieldHint anno = field.getAnnotation(FieldHint.class);
					int fieldLength;
					
					double version = HomeCameraUtil.extractVersion(data);
					/** if fixed length **/
					if (anno.length() > 0)
						fieldLength = anno.length();
					/** if unfixed length **/
					else
					{
						fieldLength = DataSize + calculateProtocolLength(voList) - offset;
					}
					byte[] extractedData = new byte[fieldLength];
	
					System.arraycopy(data, offset, extractedData, 0, fieldLength);
					offset += fieldLength;
					
					field.setAccessible(true);
					reValue = getTypeValue(fieldType, name, extractedData, 1);
	
					new Label(groupBody, SWT.NULL).setText(name);
					Text textObj = new Text(groupBody, SWT.SINGLE | SWT.BORDER);
					textObj.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
					textObj.setText(reValue);
					textObj.setData("size", 20);
					textObj.setData("type", "char");
				}
				bodyOff = offset;
				logOff = offset;
			}
			
			// 반복 log data print
			if(3 == defInt){
				offset = logOff;
				boolean checkLastData = true;
				
				String textArea = "";
				new Label(groupBody, SWT.NULL).setText("Log");
				Text textLog = new Text(groupBody,  SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				textLog.setLayoutData(new GridData(GridData.FILL_BOTH));

				while(checkLastData){
//					short logValueLength = 0;
					for(int j=0; j<voList.size(); j++){
						
						Class<?> fieldType2 = voList.get(j).getType();
						String fieldTypeSimpleName2 = fieldType2.getSimpleName();
						name = voList.get(j).getName();
						Field field2 = voList.get(j);
						FieldHint anno2 = field2.getAnnotation(FieldHint.class);
						int fieldLength2;
						double version = HomeCameraUtil.extractVersion(data);
						
						
						/** if fixed length **/
						if (anno2.length() > 0)
							fieldLength2 = anno2.length();
						else if(anno2.index() == 5){
							fieldLength2 = logValueLength;
						}
						/** if unfixed length **/
						else {
							fieldLength2 = DataSize + calculateProtocolLength(voList) - offset;
						}
						byte[] extractedData2 = new byte[fieldLength2];
						if(checkData(data, offset,  fieldLength2)) {
							checkLastData = false;
							break;
						}
						System.arraycopy(data, offset, extractedData2, 0, fieldLength2);
						offset += fieldLength2;
						field2.setAccessible(true);
						reValue = getTypeValue(fieldType2, name, extractedData2, 3);
						
						textArea += "<"+name+"> : "+reValue;
						textArea += "\r\n";
					}
					textArea += "\r\n";
					textLog.setText(textArea);					
					logOff = offset;
				}
			}
			
		}catch (Exception e){
			throw new RuntimeException();
		}
	}
	
	private static String bytesToString(byte[] src) {
		StringBuffer sb = new StringBuffer();
		for (byte b : src)
		{
			if (b == 0)
				break;

			sb.append((char) b);
		}
		String result = sb.toString();
		return result;
	}
	private static String trimBytesToString(byte[] src) {
		StringBuffer sb = new StringBuffer();
		for (byte b : src)
		{
			String hexString = "";
			hexString += Integer.toString((b & 0xF0) >> 4, 16);  
    	    hexString += Integer.toString(b & 0x0F, 16);
			if (b == 0)
				break;
			else if("20".equals(hexString)){
				continue;
			}
			sb.append((char) b);
		}
		String result = sb.toString();
		return result;
	}

	private static Integer bytesToInteger(byte[] src) {
		int result = ByteBuffer.wrap(src).getInt();
		return result;
	}
	
	private static Short bytesToShort(byte[] src) {
		short result = ByteBuffer.wrap(src).getShort();
		return result;
	}
	
	private static UUID bytesToUUID(byte[] src) {
		ByteBuffer buffer = ByteBuffer.wrap(src);
		buffer.order(ByteOrder.BIG_ENDIAN);
		long firstLong = buffer.getLong();
		long secondLong = buffer.getLong();
		
		return new UUID(firstLong, secondLong);
	}
	
	protected int calculateProtocolLength(List<Field> fields) {
		int length = 0;

		for (Field f : fields)
		{
			if (f.isAnnotationPresent(FieldHint.class))
			{
				int fieldLength = f.getAnnotation(FieldHint.class).length();
				if (fieldLength < 0)
				{
					length = -1;
					break;
				}
				length += fieldLength;
			}

		}
		return length;
	}
	
	public static String byteArrayToHex(byte[] ba) {
	    if (ba == null || ba.length == 0) {
	        return null;
	    }
	 
	    StringBuffer sb = new StringBuffer(ba.length * 2);
	    String hexNumber;
	    for (int x = 0; x < ba.length; x++) {
	        hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
	 
	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	    }
	    return sb.toString();
	} 
	
	public static Date StringToDate(String time) throws ParseException
    {
        return StringtoDate(time, defaultFormat);
    }

    public static Date StringtoDate(String time, String format) throws ParseException
    {
        SimpleDateFormat nowSimpleDateFormat = new SimpleDateFormat(format);
        return nowSimpleDateFormat.parse(time);
    }

    private static String defaultFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    private static String yyyyMMddHHmmssFormat = "yyyy-MM-dd HH:mm:ss";
    private static String HHmmssFormat = "HH:mm:ss";
	
    public boolean checkData(byte[] data, int off, int fieldLength2){
    	boolean result = false;
    	
    	int dataLength = data.length;
    	int curDataLength = off + fieldLength2;
    	
    	if(dataLength <curDataLength)
    		result = true;
    	
    	return result;
    }
    
	public void getCam(String inputCode){
		try{
    		List<Field> resultHead = ParseUtil.getSysSepc(inputCode, 1);
			List<Field> resultBody = ParseUtil.getSysSepc(inputCode, 2);
			
    		if(resultBody != null){
    			setCamSpec(resultHead, inputCode, 1);	// 1,2,3 -> head,body,log 구분자
    			setCamSpec(resultBody, inputCode, 2);
    			
    			boolean isLogVO = ParseUtil.checkLogVO(inputCode);
        		if(isLogVO){
        			List<Field> resultLog = ParseUtil.getSysSepc(inputCode, 3);
        			setCamSpec(resultLog, inputCode, 3);
        		}
    			
    			groupBody.layout();
    			textHex.setFocus();
    		}
    		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String getTypeValue(Class<?> fieldType, String name, byte[] exData, int defFlag){
		String reValue = "";
//		short logValueLength = 0;
		if (fieldType == String.class)
		{
			String value = bytesToString(exData);
			if("LogType".equals(name)){
				value = trimBytesToString(exData);
			}
			reValue = value;
			System.out.println(" String value : "+value);
		}
		else if (fieldType == Integer.class)
		{
			Integer value = bytesToInteger(exData);
			if(defFlag != 3){
				if("Command".equals(name)){
					reValue = byteArrayToHex(exData);
				}else{
					reValue = value.toString();
				}
			}else{
				reValue = value.toString();
			}
			System.out.println(" int value : "+value);
		}
		else if (fieldType == Short.class)
		{
			short value = bytesToShort(exData);
			logValueLength = value;
			reValue = String.valueOf(value);
			System.out.println(" short value : "+logValueLength);
		}
		else if (fieldType == byte[].class)
		{
			byte[] value = exData;
			reValue = String.valueOf(value.length);
			System.out.println(" byte value : "+value);
		}
		else if (fieldType == UUID.class)
		{
			UUID value = bytesToUUID(exData);
			reValue = value.toString();
			System.out.println(" UUID value : "+value);
		}
		else
			throw new RuntimeException("Setting field of " + name + " is not yet implemented.");
		
		return reValue;
	}
	
    public static void main(String[] args) {
    	new Appswt();
      }
    
}
