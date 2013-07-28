package com.example.shareviewapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;

/**
 * 
 * @author MrPandaPants
 *
 */
public class User {

	private int Id;
	private String Name;
	private String Password;
	
	public User(int Id, String name, String password) {
		this.setId(Id);
		this.setName(name);
		this.setPassword(password);
	}
	
	public boolean authenticate(UsersMapper usersMapper) {
		
		User me = usersMapper.findByUsername((String) this.getName());
		
		return me.getPassword() == this.getPassword();
		
		
		
	}
	
	public static CharSequence encryptPassword(String password) {
		
		String tempHash = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			String password1 = (String) password;
			
			md.update(password1.getBytes());
			
			tempHash = md.digest().toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempHash;

	}

	void setId(int id2) {
		// TODO Auto-generated method stub
		this.Id = id2;
	} 

	void setName(String i) {
		// TODO Auto-generated method stub
		this.Name = i;
	}
	
	void setPassword(String i) {
		this.Password = i;
	}

	public CharSequence getPassword() {
		// TODO Auto-generated method stub
		return this.Password;
	}
	
	public CharSequence getName() {
		return this.Name;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.Id ;
	}

	 
}
