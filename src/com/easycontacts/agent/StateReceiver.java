package com.easycontacts.agent;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.UUID;

public class StateReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    Bundle extras = intent.getExtras();
    if (extras != null) {
      String state = extras.getString(TelephonyManager.EXTRA_STATE);
      Log.w("DEBUG", state);
      if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
        String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        
        Log.w("DEBUG", phoneNumber);

        AsyncHttpClient client = new AsyncHttpClient();

        String serverKey = "ede1e57a-790a-11e1-9a59-78e400edfc41";
        String myNumber = "380352405277";
        String myAgentKey = "SIP/1117";
		
        UUID uuid = java.util.UUID.randomUUID();
        
		RequestParams params = new RequestParams();
		params.put("c", phoneNumber);
		params.put("e", myNumber);
		params.put("a", myAgentKey);
		params.put("i", uuid.toString());
		
		client.post("http://easy-contacts.com:8888/popup/" + serverKey, params, 
				new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
            	Log.w("DEBUG", response);
            }
        });
      }
    }
  }
} 