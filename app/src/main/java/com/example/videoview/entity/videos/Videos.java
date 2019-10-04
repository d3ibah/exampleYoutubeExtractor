package com.example.videoview.entity.videos;

public class Videos{
	private Youtube youtube;
	private Vimeo vimeo;
	private Direct direct;

	public void setYoutube(Youtube youtube) {
		this.youtube = youtube;
	}

	public Youtube getYoutube() {
		return youtube;
	}

	public void setVimeo(Vimeo vimeo) {
		this.vimeo = vimeo;
	}

	public Vimeo getVimeo() {
		return vimeo;
	}

	public void setDirect(Direct direct) {
		this.direct = direct;
	}

	public Direct getDirect() {
		return direct;
	}

	@Override
 	public String toString(){
		return "Videos{" + "youtube = '" + youtube + '\'' +
			   ",vimeo = '" + vimeo + '\'' +
			   ",direct = '" + direct + '\'' +
			   "}";
		}
}
