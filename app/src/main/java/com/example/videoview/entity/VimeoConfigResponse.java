package com.example.videoview.entity;

import com.google.gson.annotations.SerializedName;

public class VimeoConfigResponse{

	@SerializedName("request")
	private Request request;

	public void setRequest(Request request){
		this.request = request;
	}

	public Request getRequest(){
		return request;
	}

	@Override
 	public String toString(){
		return 
			"VimeoConfigResponse{" + 
			"request = '" + request + '\'' + 
			"}";
		}
}