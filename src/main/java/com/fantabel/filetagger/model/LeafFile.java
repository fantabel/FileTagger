package com.fantabel.filetagger.model;

public class LeafFile extends AbstractFile {

	private static final long serialVersionUID = 1L;
	private Folder parent;

	public LeafFile(String pathname, Folder parent) {
		super(pathname);
		this.parent = parent;

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
		if (!parent.isAnalysed()) {
			parent.analyse();
		}
		System.out.println("analysing file : " + toString());

		setAnalysed(true);

	}

}
