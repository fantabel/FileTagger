package com.fantabel.filetagger.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		lblShowName = new JLabel("Show name");
		lblShowName.setOpaque(true);
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
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(lblShowName, c);
		
		c.gridx = 1;
		c.gridy = 0;
		
		this.add(txtShowName, c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(lblSeasonNumber, c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(txtSeasonNumber, c);
		c.gridx = 0;
		c.gridy = 2;
		this.add(lblEpisodeNumber, c);
		c.gridx = 1;
		c.gridy = 2;
		this.add(txtEpisodeNumber, c);
		
	}
	
}
