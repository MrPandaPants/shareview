package com.example.shareviewapp;




import com.example.shareviewapp.UsersMapper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.initializeDBOnce();
        
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void gotoRegister(View view) {
    	Intent intent = new Intent(this, RegisterActivity.class);
		 
    	startActivity(intent);
    	this.onDestroy();
    	this.finish();	
    }
    
    public void loginUser(View view) {
    	
    	TextView textView = (TextView) findViewById(R.id.debug);
		
		
    	UsersMapper um = getUsersMapper();
    	User[] users = um.fetchAll();
    	
    	String s = "";
    	for(int i = 0; i < users.length; i++) {
    		s += users[i].getName();
    	}
    	
    	textView.setText(s);
	}
    
	private UsersMapper getUsersMapper() {
		// TODO Auto-generated method stub
		return new UsersMapper(this);
	}
	
	public void initializeDBOnce() {
		UsersMapper usersMapper=  this.getUsersMapper();
		
		usersMapper.createDBFirst();
		
	}
	
	public void close(View view) {
    	finishActivity(this);
    	
    }
    private void finishActivity(MainActivity mainActivity) {
		// TODO Auto-generated method stub
		mainActivity.onDestroy();
		mainActivity.finish();
	}

}
