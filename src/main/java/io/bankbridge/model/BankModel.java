package io.bankbridge.model;

import java.util.List;

public class BankModel {
	
	public String bic;
	public String name;
	public String countryCode;
	public String auth;
	//Added to remove Runtime error
	public List<String> products;

}
