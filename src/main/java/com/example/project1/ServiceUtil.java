package com.example.project1;

public class ServiceUtil {

	public static String getActivationCode()
	{
		StringBuffer activationCode = new StringBuffer();
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";
		
		//	taking 40 alpha numeric characters randomly
		int index;
		for(int i = 0 ; i < 40 ; i++)
		{
			index = (int) (alphaNumericString.length() * Math.random());
			activationCode.append(alphaNumericString.charAt(index));
		}
		
		return activationCode.toString();
	}
}
