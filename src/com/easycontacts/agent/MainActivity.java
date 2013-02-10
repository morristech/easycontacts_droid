package com.easycontacts.agent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import java.util.UUID;
import com.loopj.android.http.*;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

public class MainActivity extends PreferenceActivity {
	SharedPreferences preferences;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.preferences);
	    setContentView(R.layout.main);
	  }

	public void doRequest(View view) {
		AsyncHttpClient client = new AsyncHttpClient();

        UUID uuid = java.util.UUID.randomUUID();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);	
        
		RequestParams params = new RequestParams();
		params.put("c", "4309133");
		params.put("e", preferences.getString("Channel", ""));
		params.put("a", preferences.getString("AgentKey", ""));
		params.put("i", uuid.toString());
		
		client.post("http://easy-contacts.com:8888/popup/" + preferences.getString("ServerKey", ""), params, 
				new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
            	Log.w("DEBUG", response);
            }
        });
	}
}
