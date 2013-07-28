/**
 * 
 */
package com.example.shareviewapp;

import java.util.LinkedList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author MrPandaPants
 *
 */
public class UsersMapper extends SQLiteOpenHelper {

        
	public UsersMapper(Context context) {
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
		 
	}
	
	private static final int DATABASE_VERSION = 3;
	
    private static final String USERS_TABLE_NAME = "users";
    
    public static String getPasswordsTableName() {
		return USERS_TABLE_NAME;
	}

	private static final String USERS_TABLE_CREATE = "create table if not exists" +
    		" users ( id integer not null, " +
    		"username varchar(255), password varchar(255), primary key(id));";
    
	private static final String DATABASE_NAME = "shareview.sqlite"; 
	
	
	public void deleteById(Integer id) {
		SQLiteDatabase sq = getWritableDatabase();
		SQLiteStatement cs = sq.compileStatement("delete from users where id = ?;");
		
		cs.bindString(1, id.toString());
		cs.execute();
		cs.close();
	}
	
	public void createDBFirst() {
		SQLiteDatabase sq = getWritableDatabase();
		SQLiteStatement cs = sq.compileStatement(USERS_TABLE_CREATE);
		
		cs.execute();
		cs.close();
	}
	
	public int createPassword(String name, String value) {
		SQLiteDatabase sq = getWritableDatabase();
		SQLiteStatement cs = sq.compileStatement("insert into users values (null, ?, ?);");
		
		cs.bindString(1, name);
		cs.bindString(2, value);
		cs.execute();
		cs.close();
		
		
		return 1;
	}
	
	/**
	 * Get all users inserted in the DB table
	 * 
	 * @return String debugString Fetch all users debug string
	 */
	public String debugSelectAll() {
		
		SQLiteDatabase sq = getReadableDatabase(); 
		Cursor c = sq.rawQuery("select * from users;", null);
	 
		String debugString = "";
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			
			try {
				Integer i = c.getColumnCount();
			
				for (int indx = 0; indx < i; indx++) {
					debugString += " | " + c.getString(indx);
				}
				debugString += "\n";
				c.moveToNext();
			} catch (IllegalStateException e) {
				c.moveToNext();
				
			}
			
		}
		c.close();
		return debugString;
	}
	
	/**
	 * Debug by describing the users table
	 * 
	 * @return String debugString The mySQL describe equivalent of SQLite returned as String
	 */
	public String debugDescribe() {
		SQLiteDatabase sq = getReadableDatabase(); 
		Cursor c = sq.rawQuery("pragma table_info(users);", null);
	 
		String debugString = "";
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			
			try {
				Integer i = c.getColumnCount();
			
				for (int indx = 0; indx < i; indx++) {
					debugString += " | " + c.getString(indx);
				}
				debugString += "\n";
				c.moveToNext();
			} catch (IllegalStateException e) {
				c.moveToNext();
				
			}
			
		}
		c.close();
		return debugString;
		
	}

	public User[] fetchAll() {
		SQLiteDatabase sq = getReadableDatabase(); 
		Cursor c = sq.rawQuery("SELECT * FROM users order by id desc;", null);
	 
		LinkedList<User> lstr = new LinkedList<User>();
		
		c.moveToFirst();
		while (!c.isLast()) {
			 
			lstr.add(new User(Integer.parseInt(c.getString(0)),
					c.getString(1), c.getString(2)));
			 
			c.moveToNext();
		}
		c.close();
		Object[] array = lstr.toArray();
		User[] tt = new User[array.length];
		for (int j = 0; j < array.length; j++) {
			tt[j] = (User) array[j];
			
		}
		return tt;
		
		
		
	}
	
	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
	
		db.execSQL(USERS_TABLE_CREATE);
		
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
