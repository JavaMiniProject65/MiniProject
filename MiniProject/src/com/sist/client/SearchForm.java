package com.sist.client;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.*;
public class SearchForm extends JPanel {
	JTextField tf;
	JButton b1,b2;
	JTable table;
	DefaultTableModel model;
	public SearchForm() {
		tf=new JTextField(13);
		b1=new JButton("검색");;
		String[] col= {"번호","업체명","주소","음식종류","평점"};
		String[][] row=new String[0][5];
		model=new DefaultTableModel(row,col) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub 
				return false;
			}
			
		};
		setLayout(null);
		
		
		JPanel p=new JPanel();
		tf.setBounds(0, 0, 200, 30);
		b1.setBounds(210, 0, 80, 30);
		add(tf);
        add(b1);
        
		table=new JTable(model);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);   // 번호
        table.getColumnModel().getColumn(1).setPreferredWidth(250);  // 업체명
        table.getColumnModel().getColumn(2).setPreferredWidth(830);  // 주소 
        table.getColumnModel().getColumn(3).setPreferredWidth(180);  // 음식종류
        table.getColumnModel().getColumn(4).setPreferredWidth(80);   // 평점

		JScrollPane js=new JScrollPane(table);
		js.setBounds(0, 50, 1550, 730);
		add(js);
	}
}