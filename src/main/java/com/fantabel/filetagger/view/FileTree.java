package com.fantabel.filetagger.view;

/*
* Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
* All rights reserved. Software written by Ian F. Darwin and others.
* $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
* 1. Redistributions of source code must retain the above copyright
*    notice, this list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright
*    notice, this list of conditions and the following disclaimer in the
*    documentation and/or other materials provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
* TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
* BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
* 
* Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
* cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
* pioneering role in inventing and promulgating (and standardizing) the Java 
* language and environment is gratefully acknowledged.
* 
* The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
* inventing predecessor languages C and C++ is also gratefully acknowledged.
*/

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.fantabel.filetagger.model.AbstractFile;
import com.fantabel.filetagger.model.Folder;
import com.fantabel.filetagger.model.LeafFile;

/**
 * Display a file system in a JTree view
 * 
 * @version $Id: FileTree.java,v 1.9 2004/02/23 03:39:22 ian Exp $
 * @author Ian Darwin
 */
public class FileTree extends JPanel {
	private static final long serialVersionUID = 1L;

	/** Construct a FileTree */
	public FileTree(File dir) {
		setLayout(new BorderLayout());

		// Make a tree list with all the nodes, and make it a JTree
		JTree tree = new JTree(addNodes(null, dir));
		((AbstractFile) ((DefaultMutableTreeNode) tree.getModel().getRoot()).getUserObject()).analyse();
		// Add a listener
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				AbstractFile currentFile = (AbstractFile) node.getUserObject();
				currentFile.analyse();
			}
		});

		// Lastly, put the JTree into a JScrollPane.
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.getViewport().add(tree);

		add(BorderLayout.CENTER, scrollpane);
	}

	public static boolean contains(String filename, String pattern) {
		return filename.toLowerCase().contains(pattern.toLowerCase());
	}

	public static boolean filenameContainsExcludedPatterns(final String fileName) {
		String[] excluded = { "MACOSX", ".nfo", ".DS_Store", ".db", ".xml", ".SFV", ".txt", "#0001", ".par", ".pdf",
				".ini", ".TNC" };
		List<String> list = Arrays.asList(excluded);
		return list.stream().anyMatch(s -> contains(fileName, s));
		// return fileName.contains("MACOSX") || fileName.contains(".nfo") ||
		// fileName.contains(".DS_Store");
	}

	/** Add nodes from under "dir" into curTop. Highly recursive. */
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		String curPath = dir.getPath();
		Folder currentFolder = new Folder(dir.getPath());
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(currentFolder);

		if (curTop != null) { // should only be null at root
			curTop.add(curDir);
		}

		List<String> ol = new ArrayList<String>();
		String[] tmp = dir.list();

		for (String s : tmp) {
			if (!filenameContainsExcludedPatterns(s)) {
				ol.add(s);
			}
		}

		ol = ol.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

		Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);

		File f;
		Vector<String> files = new Vector<String>();

		// Make two passes, one for Dirs and one for Files. This is #1.
		for (int i = 0; i < ol.size(); i++) {
			String thisObject = ol.get(i);
			String newPath;

			if (curPath.equals(".")) {
				newPath = thisObject;
			} else {
				newPath = curPath + File.separator + thisObject;
			}

			if ((f = new File(newPath)).isDirectory()) {
				addNodes(curDir, f);

			} else {
				files.addElement(thisObject);
			}
		}

		// Pass two: for files.
		for (int fnum = 0; fnum < files.size(); fnum++) {
			System.out.println(curPath + File.separator + files.elementAt(fnum));
			LeafFile currentFile = new LeafFile(curPath + File.separator + files.elementAt(fnum), currentFolder);
			currentFolder.addFile(currentFile);
			curDir.add(new DefaultMutableTreeNode(currentFile));
		}

		return curDir;
	}
	/*
	 * @Override public Dimension getMinimumSize() { return new Dimension(200,
	 * 400); }
	 * 
	 * @Override public Dimension getPreferredSize() { return new Dimension(500,
	 * 1000); }
	 */
}
