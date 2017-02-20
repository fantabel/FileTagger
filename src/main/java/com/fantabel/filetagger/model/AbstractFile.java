package com.fantabel.filetagger.model;

import java.io.File;

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
		return getName();
	}
}
