/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.friendlygreenhouse.application;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.friendlygreenhouse.application.LocalDatabase.SessionManager;
import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

public class MainActivity extends Activity {

	CallAPIhelper helper;
	private Button btnLogin;
	private EditText inputEmail;
	private EditText inputPassword;
	private ProgressDialog pDialog;
	private SessionManager session;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		inputEmail = (EditText) findViewById(R.id.email);
		inputPassword = (EditText) findViewById(R.id.password);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		helper=new CallAPIhelper();

		// Progress dialog
		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);

		// Session manager
		session = new SessionManager(getApplicationContext());

		// Check if user is already logged in or not
		if (session.isLoggedIn()) {
			// User is already logged in. Take him to main activity
			AppConfig.setUserID(session.getUserID());
			Intent intent = new Intent(MainActivity.this, BoardActivity.class);
			startActivity(intent);
			finish();
		}

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String account = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();

				// Check for empty data in the form
				if (account.trim().length() > 0 && password.trim().length() > 0) {
					// login user
					checkLogin(account, password);
				} else {
					// Prompt user to enter credentials
					Toast.makeText(getApplicationContext(),
							"Please enter the credentials!", Toast.LENGTH_LONG)
							.show();
				}
			}

		});


	}

	/**
	 * function to verify login details in mysql db
	 * */
	private void checkLogin(final String account, final String password) {
		// Tag used to cancel the request
		pDialog.setMessage("Logging in ...");
		showDialog();
		helper.getUserID(getBaseContext(),account,password);
		if(AppConfig.getUserID()!=null){
			hideDialog();
			session.setLogin(true);

			// Launch main activity
			Intent intent = new Intent(MainActivity.this,
					BoardActivity.class);
			startActivity(intent);
			finish();
		}else {
			hideDialog();
			Toast.makeText(getApplicationContext(),
					"請再嘗試登入", Toast.LENGTH_LONG).show();
		}
	}

	private void showDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}
}
