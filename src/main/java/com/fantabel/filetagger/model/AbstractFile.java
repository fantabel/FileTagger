package com.fantabel.filetagger.model;

import java.io.File;

import org.apache.commons.lang.WordUtils;

public abstract class AbstractFile extends File {

	private static final long serialVersionUID = 1L;
	private boolean isAnalysed;

	public boolean isAnalysed() {
		return isAnalysed;
	}

	public void setAnalysed(boolean isAnalysed) {
		this.isAnalysed = isAnalysed;
	}

	public AbstractFile(String pathname) {
		super(pathname);
		isAnalysed = false;
		// TODO Auto-generated constructor stub
	}

	abstract public void analyse();

	@Override
	public String toString() {
		return WordUtils.capitalizeFully(getName(), new char[] { '.', '-' });
	}

	/*
	 * @Override public String getName() { // return super.getName();
	 * 
	 * if (isDirectory()) { return super.getName(); }
	 * 
	 * return FilenameUtils.getBaseName(super.getName());
	 * 
	 * }
	 */

	public String getExtension() {
		return super.getName();
	}
}
