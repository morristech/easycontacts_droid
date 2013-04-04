package com.easycontacts.agent;

import java.util.UUID;
import java.util.Calendar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;


public class StateReceiver extends BroadcastReceiver {
	
  @Override
  public void onReceive(Context context, Intent intent) {
    Bundle extras = intent.getExtras();
    if (extras != null) {
      String state = extras.getString(TelephonyManager.EXTRA_STATE);
      Log.w("DEBUG", state);
      if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);	
    	  
    	String sendingMode = preferences.getString("sendingMode", "1");
    	
    	if (sendingMode.equals("1")) {
    		return;
    	}  

    	if (sendingMode.equals("3")) {
    		int dow = Calendar.getInstance()
    			.get(Calendar.DAY_OF_WEEK);
	    	if (dow == 0 || dow == 6) {
	    		return;
	    	}  
    	}  
    	  
        String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        String serverKey = preferences.getString("ServerKey", "");
        String myNumber = preferences.getString("Channel", "");
        String myAgentKey = preferences.getString("AgentKey", "");		

        UUID uuid = java.util.UUID.randomUUID();
        
        Log.w("DEBUG", ">>>>>>>>>>>" + phoneNumber);
        Log.w("DEBUG", ">>>>>>>>>>>" + serverKey);
        Log.w("DEBUG", ">>>>>>>>>>>" + myNumber);
        Log.w("DEBUG", ">>>>>>>>>>>" + myAgentKey);
        Log.w("DEBUG", ">>>>>>>>>>>" + uuid.toString());

        AsyncHttpClient client = new AsyncHttpClient();
        
		RequestParams params = new RequestParams();
		params.put("c", phoneNumber);
		params.put("e", myNumber);
		params.put("a", myAgentKey);
		params.put("i", uuid.toString());
		
		client.post("http://easy-contacts.com:8888/popup/" + serverKey, 
				params, 
				new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
            	Log.w("DEBUG", ">>>>>>>>>>>" + response);
            }
        });
      }
    }
  }
} 