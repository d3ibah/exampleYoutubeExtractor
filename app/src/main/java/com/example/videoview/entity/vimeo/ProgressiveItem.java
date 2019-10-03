package com.example.videoview.entity.vimeo;

import com.google.gson.annotations.SerializedName;

public class ProgressiveItem{

	@SerializedName("profile")
	private int profile;

	@SerializedName("mime")
	private String mime;

	@SerializedName("width")
	private int width;

	@SerializedName("fps")
	private int fps;

	@SerializedName("id")
	private int id;

	@SerializedName("url")
	private String url;

	@SerializedName("quality")
	private String quality;

	@SerializedName("height")
	private int height;

	public void setProfile(int profile){
		this.profile = profile;
	}

	public int getProfile(){
		return profile;
	}

	public void setMime(String mime){
		this.mime = mime;
	}

	public String getMime(){
		return mime;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setFps(int fps){
		this.fps = fps;
	}

	public int getFps(){
		return fps;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setQuality(String quality){
		this.quality = quality;
	}

	public String getQuality(){
		return quality;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"ProgressiveItem{" + 
			"profile = '" + profile + '\'' + 
			",mime = '" + mime + '\'' + 
			",width = '" + width + '\'' + 
			",fps = '" + fps + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			",quality = '" + quality + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}