package com.easycontacts.agent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import java.util.UUID;
import com.loopj.android.http.*;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	  }
	
	public void doRequest(View view) {
		AsyncHttpClient client = new AsyncHttpClient();

        UUID uuid = java.util.UUID.randomUUID();

		RequestParams params = new RequestParams();
		params.put("c", "4309133");
		params.put("e", "380352405277");
		params.put("a", "SIP/1117");
		params.put("i", uuid.toString());
		
		client.post("http://easy-contacts.com:8888/popup/ede1e57a-790a-11e1-9a59-78e400edfc41", params, 
				new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
            	Log.w("DEBUG", response);
            }
        });
	}
}
