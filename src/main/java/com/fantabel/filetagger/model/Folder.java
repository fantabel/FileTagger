package com.fantabel.filetagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;

public class Folder extends AbstractFile {
	private static final long serialVersionUID = 1L;
	private List<LeafFile> fileList;
	private String show;
	private String commonSuffix;
	
	public String getCommonSuffix() {
		return commonSuffix;
	}
	
	public void setCommonSuffix(String commonSuffix) {
		this.commonSuffix = commonSuffix;
	}
	
	public Folder(String pathname) {
		super(pathname);
		fileList = new ArrayList<LeafFile>();
		commonSuffix = null;
		// TODO Auto-generated constructor stub
	}
	
	public void addFile(LeafFile file) {
		fileList.add(file);
		file.setParentFolder(this);
	}
	
	@Override
	public void analyse() {
		if (isAnalysed()) {
			return;
		}
		
		setAnalysed(true);
		
		for (AbstractFile f : fileList) {
			String filename = f.getName();
			if (commonSuffix == null) {
				commonSuffix = filename;
				continue;
			}
			for (int i = 0; i < commonSuffix.length(); i++) {
				if (filename.endsWith(
				        commonSuffix.substring(i, commonSuffix.length()))) {
					commonSuffix = commonSuffix.substring(i,
					        commonSuffix.length());
					break;
				}
			}
			
			Pattern p = Pattern.compile("(\\.[s|S]\\d{2}([e|E]\\d{2})+\\.)");
			Matcher m = p.matcher(filename);
			int seasonStartIndex = 0;
			int seasonEndIndex = 0;
			if (m.find()) {
				seasonStartIndex = m.start();
				seasonEndIndex = m.end();
			}
			
			setShow(WordUtils
			        .capitalizeFully(filename.substring(0, seasonStartIndex),
			                new char[] { '.', '-' })
			        .replace('.', ' '));
					
		}
		
		fileList.stream().filter(f -> !f.isAnalysed())
		        .forEach(f -> f.analyse());
				
	}
	
	public String getShow() {
		return show;
	}
	
	public void setShow(String show) {
		System.out.println(show);
		this.show = show;
	}
}
