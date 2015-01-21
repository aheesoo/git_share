package com.kt.olleh.hms.sysspec;

import java.awt.TextArea;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
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
 *484F4D454343545600004F000000000206B6E87A0000001000000000000187FF0000000000000001 //keepalive commandid;
 */
public class Appswt 
{
	private static Display display;

	private static Button buttonGet;
	
	private Text textHex; 
	private Group groupHeader; 
	private Group groupBody;
	
	public Appswt(){
		display = Display.getDefault();
		
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(650, 700);
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
			    		System.out.println("inputcode : "+inputCode);
				    	try{
				    		List<Field> resultHead = ParseUtil.getSysSepc(inputCode, 1);
			    			List<Field> resultBody = ParseUtil.getSysSepc(inputCode, 2);
			    			//Map result = ParseUtil.getSysSepc(inputCode, 2);
				    		if(resultBody != null){
				    			setCamSpec(resultHead, inputCode, 1);
				    			setCamSpec(resultBody, inputCode, 2);
				    			groupBody.layout();
				    			//setCamSpec(result);
				    			break;
				    		}else{
				    			
				    			break;
				    		}
			    		} catch (Exception e){
			    			e.printStackTrace();
			    		}
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
				
				if (fieldType == String.class)
				{
					String value = bytesToString(extractedData);
					reValue = value;
					System.out.println(" String value : "+value);
				}
				else if (fieldType == Integer.class)
				{
					Integer value = bytesToInteger(extractedData);
					reValue = value.toString();
					System.out.println(" int value : "+value);
				}
				else if (fieldType == byte[].class)
				{
					byte[] value = extractedData;
					reValue = String.valueOf(value.length);
					System.out.println(" byte value : "+value);
				}
				else if (fieldType == UUID.class)
				{
					UUID value = bytesToUUID(extractedData);
					reValue = value.toString();
					System.out.println(" UUID value : "+value);
				}
				else
					throw new RuntimeException("Setting field of " + fieldTypeSimpleName + " is not yet implemented.");

				new Label(groupBody, SWT.NULL).setText(name);
				Text textObj = new Text(groupBody, SWT.SINGLE | SWT.BORDER);
				textObj.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				textObj.setText(reValue);
				textObj.setData("size", 20);
				textObj.setData("type", "char");
			}
			bodyOff = offset;
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

	private static Integer bytesToInteger(byte[] src) {
		int result = ByteBuffer.wrap(src).getInt();
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
    
    public static void main(String[] args) {
    	new Appswt();
      }
    
}
