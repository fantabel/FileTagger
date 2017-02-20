package com.fantabel.filetagger.model;

import java.util.ArrayList;
import java.util.List;

public class Folder extends AbstractFile {
	private static final long serialVersionUID = 1L;
	private List<LeafFile> fileList;

	public Folder(String pathname) {
		super(pathname);
		fileList = new ArrayList<LeafFile>();
		// TODO Auto-generated constructor stub
	}

	public addFile(LeafFile file) {
		fileList.add(file);
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void analyse() {
		if (isAnalysed()) {
			return;
		}
		System.out.println("analysing folder : " + toString());

		setAnalysed(true);

	}
}
