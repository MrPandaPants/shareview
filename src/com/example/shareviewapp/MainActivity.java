package com.example.shareviewapp;

import com.example.shareviewapp.UsersMapper;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.Menu;

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
    
	private UsersMapper getUsersMapper() {
		// TODO Auto-generated method stub
		return new UsersMapper(this);
	}
	
	public void initializeDBOnce() {
		UsersMapper usersMapper=  this.getUsersMapper();
		
		usersMapper.createDBFirst();
		
	}
}
