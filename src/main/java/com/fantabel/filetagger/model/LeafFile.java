package com.fantabel.filetagger.model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;

public class LeafFile extends AbstractFile {

	private static final long serialVersionUID = 1L;
	private Folder parent;
	private int seasonNumber;
	private int[] episodeNumber;
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
		episodeNumber = null;
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

		Pattern p = Pattern.compile("[.]s(?<season>\\d{1,4})e(?<episodes>\\d{1,3}([e-]\\d{1,3})*)[.]");
		Matcher m = p.matcher(getName());
		int pos = 0;
		if (m.find()) {
			setSeasonNumber(m.group("season"));
			setEpisodeNumber(m.group("episodes").split("[e-]"));
			pos = m.end(2);

			System.out.println(getName() + "  " + pos + " " + parent.getCommonSuffix());
			// TODO correct this part with the getname
			setEpisodeName(
					WordUtils.capitalizeFully(getName().substring(pos + 1, getName().indexOf(parent.getCommonSuffix())),
							new char[] { '.', '-' }).replace('.', ' '));

			System.out.println(getEpisodeName());

			setAnalysed(true);
		} else {
			System.out.println("Can't analyse : " + getName());
		}

	}

	public void setParentFolder(Folder folder) {
		this.parent = folder;

	}

	public Folder getParentFolder() {
		return parent;
	}

	public String getSeason() {
		return "S" + String.format("%02d", seasonNumber);
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(String seasonNumber) {
		if (seasonNumber != null) {
			setSeasonNumber(Integer.valueOf(seasonNumber));
		}
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public String getEpisode() {
		StringBuilder sb = new StringBuilder();
		for (int i : episodeNumber) {
			sb.append("E");
			sb.append(String.format("%02d", i));
		}
		return sb.toString();
	}

	public int[] getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(String... episodeNumber) {
		/*
		 * if (episodeNumber != null) { this.episodeNumber = new
		 * int[episodeNumber.length]; int i = 0; for (String s : episodeNumber)
		 * { this.episodeNumber[i++] = Integer.valueOf(s); } }
		 */
		setEpisodeNumber(Arrays.stream(episodeNumber).mapToInt(Integer::parseInt).toArray());
	}

	public void setEpisodeNumber(String episodeNumber) {
		setEpisodeNumber(Integer.valueOf(episodeNumber));
	}

	public void setEpisodeNumber(int episodeNumber) {
		setEpisodeNumber(new int[] { episodeNumber });

	}

	public void setEpisodeNumber(int[] episodeNumber) {
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
			return getShowName() + " " + getSeason() + " " + getEpisode() + " " + getEpisodeName();
		}

		return super.getName();

	}

}
