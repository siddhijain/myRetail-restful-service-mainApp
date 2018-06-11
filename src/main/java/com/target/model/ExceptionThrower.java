package com.target.model;

public class ExceptionThrower {
	public void throwGeneralException() throws Exception{
		throw new Exception("General exception");
	}
	
	public void throwNullPointerException() throws NullPointerException{
		throw new NullPointerException("Product details not found");
	}
	
	
		
}
