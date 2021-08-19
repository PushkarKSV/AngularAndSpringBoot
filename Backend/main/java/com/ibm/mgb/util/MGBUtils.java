package com.ibm.mgb.util;

public class MGBUtils {

	public static String getMandant(String mandantName) {
		String mandantId = "";
		if(mandantName.equals("Bond EAA"))
			mandantId = mandantId + "BND";
		else if(mandantName.equals("Bonds CBB"))
			mandantId = mandantId + "BNC";
		else if(mandantName.equals("Bond PAG"))
			mandantId = mandantId + "BNG";
		else if(mandantName.equals("Derivate EAA"))
			mandantId = mandantId + "DVD";
		else if(mandantName.equals("Derivative CBB"))
			mandantId = mandantId + "DVC";
		else if(mandantName.equals("Derivative PAG"))
			mandantId = mandantId + "DVG";
		else if(mandantName.equals("MoneyMarket EAA"))
			mandantId = mandantId + "MMK";
		else if(mandantName.equals("MoneyMarket CBB"))
			mandantId = mandantId + "MMC";
		else if(mandantName.equals("MoneyMarket PAG"))
			mandantId = mandantId + "MMG";
		else if(mandantName.equals("Repo EAA"))
			mandantId = mandantId + "REP";
		else if(mandantName.equals("Repo CBB"))
			mandantId = mandantId + "REC";
		else if(mandantName.equals("Repo PAG"))
			mandantId = mandantId + "REG";
		System.out.println("Product: " + mandantId);
		return mandantId;
	}
}
