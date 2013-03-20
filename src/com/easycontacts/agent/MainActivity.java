package com.easycontacts.agent;

import android.os.Bundle;
import android.view.View;
import android.util.Log;

import java.util.UUID;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.content.SharedPreferences;

public class MainActivity extends PreferenceActivity {
	SharedPreferences preferences;
	
//	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.preferences);
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
	
/*	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu, menu);
		menu.setHeaderTitle("Select an Option");
		
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.menu_delete:
				Toast.makeText(MainActivity.this,"Delete Operation is performed!",Toast.LENGTH_SHORT ).show();
				return true;
			case R.id.menu_copy:
				Toast.makeText(MainActivity.this,"Copy Operation is performed!",Toast.LENGTH_SHORT ).show();
				return true;
			case R.id.menu_edit:
				Toast.makeText(MainActivity.this,"Edit Operation is performed!",Toast.LENGTH_SHORT ).show();
				return true;
		}
		return super.onContextItemSelected(item);
	}	
*/}
