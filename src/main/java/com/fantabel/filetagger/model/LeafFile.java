package com.fantabel.filetagger.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeafFile extends AbstractFile {
	
	private static final long serialVersionUID = 1L;
	private Folder parent;
	private String seasonNumber;
	private String episodeNumber;
	private String showName;
	private String episodeName;
	
	public String getEpisodeName() {
		return episodeName;
	}
	
	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}
	
	public LeafFile(String pathname, Folder parent) {
		super(pathname);
		setParentFolder(parent);
		setSeasonNumber(null);
		setEpisodeNumber(null);
		setShowName(null);
		setEpisodeName(null);
		
	}
	
	@Override
	public void analyse() {
		if (isAnalysed()) {
			return;
		}
		if (!parent.isAnalysed()) {
			parent.analyse();
		}
		
		setShowName(parent.getShow());
		
		Pattern p = Pattern.compile("[s|S](\\d{2})[e|E](\\d{2})");
		Matcher m = p.matcher(getName());
		
		if (m.find()) {
			setSeasonNumber(m.group(1));
			setEpisodeNumber(m.group(2));
			System.out.println();
		}
		
		setAnalysed(true);
		
	}
	
	public void setParentFolder(Folder folder) {
		this.parent = folder;
		
	}
	
	public Folder getParentFolder() {
		return parent;
	}
	
	public String getSeason() {
		return seasonNumber;
	}
	
	public void setSeasonNumber(String seasonNumber) {
		this.seasonNumber = seasonNumber;
	}
	
	public String getEpisodeNumber() {
		return episodeNumber;
	}
	
	public void setEpisodeNumber(String episodeNumber) {
		this.episodeNumber = episodeNumber;
	}
	
	public String getShowName() {
		return showName;
	}
	
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	@Override
	public String toString() {
		if (isAnalysed()) {
			return getShowName() + ".S" + getSeason() + "E" + getEpisodeNumber()
			        + "." + getEpisodeName();
		}
		
		return super.getName();
		
	}
	
}
