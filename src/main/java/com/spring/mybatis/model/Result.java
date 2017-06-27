package com.spring.mybatis.model;

public class Result {
    private boolean flag;
    
    public Result(boolean fl ,String me){
    	this.flag=fl;
    	this.message=me;
    	
    }
    
    public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
}
