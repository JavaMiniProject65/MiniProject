package com.sist.client;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.*;
public class SearchForm extends JFrame {
	JTextField tf;
	JButton b1,b2;
	JTable table;
	DefaultTableModel model;
	public SearchForm() {
		tf=new JTextField(13);
		b1=new JButton("검색");;
		String[] col= {"번호","업체명","주소","음식종류","평점"};
		String[][] row=new String[0][2];
		model=new DefaultTableModel(row,col) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub 
				return false;
			}
			
		};
		table=new JTable(model);
		JScrollPane js=new JScrollPane(table);
		
		JPanel p=new JPanel();
		p.add(tf);p.add(b1);
		
		add("North",p);
		add("Center",js);
		setSize(450, 350);
	}
}
