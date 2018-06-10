package com.onekey.takeaway;

import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onekey.common.DataCallback;
import com.onekey.common.LogUtils;
import com.onekey.takeaway.R;

public class LoginActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	LogUtils.d("ll1 onCreate");

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		final EditText editText1 = (EditText)findViewById(R.id.et1);
		final EditText editText2 = (EditText)findViewById(R.id.et2);
		TextView tvLogin = (TextView)findViewById(R.id.login);
		tvLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (editText1.getText().toString().isEmpty() || 
						editText2.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
					return;
				}
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
			}
				
		});
	}
	

}
