package com.example.videoview.entity;

import com.google.gson.annotations.SerializedName;

public class VimeoConfigResponse{

	@SerializedName("request")
	private Request request;

	@SerializedName("video")
	private Video video;

	public void setRequest(Request request){
		this.request = request;
	}

	public Request getRequest(){
		return request;
	}

	public Video getVideo(){
		return video;
	}

	@Override
 	public String toString(){
		return 
			"Video{" +
			"request = '" + request + '\'' +
			", video = '" + video + '\'' +
			"}";
		}
}