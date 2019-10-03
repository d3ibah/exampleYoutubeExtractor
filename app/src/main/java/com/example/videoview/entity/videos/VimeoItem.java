package com.example.videoview.entity.videos;

import com.google.gson.annotations.SerializedName;

public class VimeoItem{

	@SerializedName("url")
	private String url;

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"VimeoItem{" + 
			"url = '" + url + '\'' + 
			"}";
		}
}