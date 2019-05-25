package com.login;

public class userValidationService {
	public boolean isValid(String name, String password)
	{
		if(name.equals("adwait")&& password.equals("pass")) return true;
		return false;
	}
}
