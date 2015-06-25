package com.demo.basedemo.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsMessage;

import com.demo.basedemo.utils.NetUtil;
import com.demo.basedemo.utils.ToastUtil;

public class MainReceiver extends BroadcastReceiver {
	
	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private boolean incomingFlag = false;
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)){
			int state=intent.getIntExtra("state", -1);
			if(state==0){//耳机未插入
				ToastUtil.toast(context, "耳机被拔出");
			}else if(state==1){//耳机插入
				ToastUtil.toast(context, "耳机已插入");
			}
		}else if(intent.getAction().equals(Intent.ACTION_MEDIA_BAD_REMOVAL)){//扩展介质（扩展卡）已经从 SD 卡插槽拔出，但是挂载点 (mount point) 还没解除 (unmount)
		}else if(intent.getAction().equals(Intent.ACTION_MEDIA_EJECT)){//用户想要移除扩展介质（拔掉扩展卡）
		}else if(intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)){//扩展介质被插入，而且已经被挂载
		}else if(intent.getAction().equals(Intent.ACTION_MEDIA_REMOVED)){//扩展介质被移除
		}else if(intent.getAction().equals(Intent.ACTION_MEDIA_SHARED)){//扩展介质的挂载被解除 (unmount)，因为它已经作为 USB 大容量存储被共享
		}else if(intent.getAction().equals(Intent.ACTION_MEDIA_UNMOUNTED)){//扩展介质存在，但是还没有被挂载 (mount)
			
			
		}else if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){//插上外部电源时发出的广播
			ToastUtil.toast(context, "USB接口已连接");
		}else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){//已断开外部电源连接时发出的广播
			ToastUtil.toast(context, "USB接口已拔出");
		}else if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){//充电状态，或者电池的电量发生变化
		}else if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){//表示电池电量低
			ToastUtil.toast(context, "电池快没电了，赶紧去充电吧！");
		}else if(intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)){//表示电池电量充足，即从电池电量低变化到饱满时会发出广播
			ToastUtil.toast(context, "电池马上充满，请记得拔掉充电器");
			
			
		}else if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){//拨打电话
			incomingFlag=false;
			String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			ToastUtil.toast(context, "正在拨打电话"+phoneNumber);
		}else if(intent.getAction().equals(SMS_RECEIVED_ACTION)){//收到短信
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] myOBJpdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
                for (int i = 0; i < messages.length; i++) {
                	messages[i] = SmsMessage.createFromPdu((byte[]) myOBJpdus[i]);
                }
                for (SmsMessage currentMessage : messages) {
                	String incomingNumber=currentMessage.getDisplayOriginatingAddress();
                	String messageText=currentMessage.getDisplayMessageBody();
                	ToastUtil.toast(context, incomingNumber+"发来的消息\n"+messageText);
                }
            }
			
		}else if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){//网络发生变化
			if(NetUtil.isWifi(context)){
				ToastUtil.toast(context, "WIFI网络已连接");
			}else if(NetUtil.isMobile(context)){
				ToastUtil.toast(context, "手机网络已连接");
			}else if(NetUtil.getNetType(context)==-1){
				ToastUtil.toast(context, "哎呀，没有网络了");
			}
		}
		
		TelephonyManager tManager  = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
		switch (tManager.getCallState()) {
	        case TelephonyManager.CALL_STATE_RINGING:
	        	incomingFlag=true;
	            String incomingNumber = intent.getStringExtra("incoming_number");
	            ToastUtil.toast(context, incomingNumber+"来电");
	            break;
	        case TelephonyManager.CALL_STATE_OFFHOOK://摘机（正在通话中）
	            if (incomingFlag) {
	            }
	            break;
	        case TelephonyManager.CALL_STATE_IDLE://空闲 
	            if (incomingFlag) {
	            }
	            break;
        }
		
	}

}
