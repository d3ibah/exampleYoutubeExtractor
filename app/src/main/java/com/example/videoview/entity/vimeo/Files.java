package com.example.videoview.entity.vimeo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Files{

	@SerializedName("progressive")
	private List<ProgressiveItem> progressive;

	public void setProgressive(List<ProgressiveItem> progressive){
		this.progressive = progressive;
	}

	public List<ProgressiveItem> getProgressive(){
		return progressive;
	}

	@Override
 	public String toString(){
		return 
			"Files{" + 
			"progressive = '" + progressive + '\'' + 
			"}";
		}
}