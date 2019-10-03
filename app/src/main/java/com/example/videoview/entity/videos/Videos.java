package com.example.videoview.entity.videos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos{

	//@SerializedName("videos")
	//private Videos videos;

	@SerializedName("youtube")
	private List<YoutubeItem> youtube;

	@SerializedName("vimeo")
	private List<VimeoItem> vimeo;

	@SerializedName("direct")
	private List<DirectItem> direct;

	//public void setVideos(Videos videos){
	//	this.videos = videos;
	//}
	//
	//public Videos getVideos(){
	//	return videos;
	//}

	public void setYoutube(List<YoutubeItem> youtube){
		this.youtube = youtube;
	}

	public List<YoutubeItem> getYoutube(){
		return youtube;
	}

	public void setVimeo(List<VimeoItem> vimeo){
		this.vimeo = vimeo;
	}

	public List<VimeoItem> getVimeo(){
		return vimeo;
	}

	public void setDirect(List<DirectItem> direct){
		this.direct = direct;
	}

	public List<DirectItem> getDirect(){
		return direct;
	}

	@Override
 	public String toString(){
		return 
			"Videos{" +
			",youtube = '" + youtube + '\'' + 
			",vimeo = '" + vimeo + '\'' + 
			",direct = '" + direct + '\'' + 
			"}";
		}
}