package com.fantabel.filetagger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShowPanel extends JPanel {
	
	private JLabel lblShowName;
	private JLabel lblSeasonNumber;
	private JLabel lblEpisodeNumber;
	
	private JTextField txtShowName;
	private JTextField txtSeasonNumber;
	private JTextField txtEpisodeNumber;
	
	private static final long serialVersionUID = 1L;
	
	public ShowPanel() {
		
		this.setLayout(new GridLayout(6, 2));
		
		lblShowName = new JLabel("Show name");
		lblShowName.setOpaque(true);
		lblShowName.setBackground(Color.BLUE);
		lblSeasonNumber = new JLabel("Season number");
		lblEpisodeNumber = new JLabel("Episode number");
		
		txtShowName = new JTextField();
		txtSeasonNumber = new JTextField();
		txtEpisodeNumber = new JTextField();
		
		Dimension d = txtShowName.getPreferredSize();
		d.setSize(200, d.getHeight());
		
		txtShowName.setPreferredSize(d);
		txtSeasonNumber.setPreferredSize(d);
		txtEpisodeNumber.setPreferredSize(d);
		
		this.setPreferredSize(new Dimension(600, 600));
		
		this.add(lblShowName);
		this.add(txtShowName);
		this.add(lblSeasonNumber);
		this.add(txtSeasonNumber);
		this.add(lblEpisodeNumber);
		this.add(txtEpisodeNumber);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		
	}
	
}
