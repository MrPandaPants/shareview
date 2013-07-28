package com.example.shareviewapp;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	
	public void register(View view) {
		EditText et = (EditText) this.findViewById(R.id.editText1);
		String email = et.getText().toString();
		
		EditText editTextPassword  = (EditText) this.findViewById(R.id.editText2);
		String password = editTextPassword.getText().toString();
		
		EditText editTextPwConfirm = (EditText) this.findViewById(R.id.editText3);
		String pwConfirm = editTextPwConfirm.getText().toString();

    	TextView textView = (TextView) findViewById(R.id.activity_register_debug);
    	
		if (0 == pwConfirm.compareTo(password)) {
			UsersMapper um = getUsersMapper();
			um.createUser(email, password);	
		} else {
			textView.setText("Please make sure that the fields are correct!");
		}
	}

    
	private UsersMapper getUsersMapper() {
		// TODO Auto-generated method stub
		return new UsersMapper(this);
	}
	
}
