package com.kltn.DTO;

public class EpisodeDTO {
	
	private int episodeId;
	private String episodeName;
	private int episodeNumber;
	private String link;
	private int view;
	private String image; 
	private FilmDTO filmDTO;
	
	public int getEpisodeId() {
		return episodeId;
	}
	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}
	public String getEpisodeName() {
		return episodeName;
	}
	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}
	public int getEpisodeNumber() {
		return episodeNumber;
	}
	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public FilmDTO getFilmDTO() {
		return filmDTO;
	}
	public void setFilmDTO(FilmDTO filmDTO) {
		this.filmDTO = filmDTO;
	}
	public EpisodeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
