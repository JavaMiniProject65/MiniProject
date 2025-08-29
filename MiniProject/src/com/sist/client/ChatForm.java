package com.sist.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ChatForm extends JPanel {
	JButton b1, b2;
	JTextPane pane, pane_1;
	JTextField tf;

	DefaultTableModel model1, model2;
	JScrollBar bar;

	public ChatForm() {
		String[] col1 = { "방이름", "인원", "상태" };
		Object[][] row1 = new Object[0][3];

		model1 = new DefaultTableModel(row1, col1) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};


		String[] col2 = { "아이디", "이름", "위치" };
		Object[][] row2 = new Object[0][3];

		model2 = new DefaultTableModel(row2, col2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		setLayout(null);

		pane = new JTextPane();
		pane_1 = new JTextPane();
		JScrollPane js3 = new JScrollPane(pane);
		JScrollPane js4 = new JScrollPane(pane_1);
		
		bar = js3.getVerticalScrollBar();
		js3.setBounds(900, 15, 600, 650); 
		js4.setBounds(15, 15, 850, 400); 
		
		add(js3);
		add(js4);
		pane.setEditable(false);
		pane_1.setEditable(false);

		tf = new JTextField();
		tf.setBounds(15, 420, 850, 30);
		add(tf);

		b1 = new JButton("쪽지 보내기");
		b2 = new JButton("정보보기");
		
		JPanel p = new JPanel();
		p.setLayout(null); 
		p.add(b1);
		p.add(b2);
		
		b1.setBounds(5, 5, 100, 30); // x, y, 가로, 세로
		b2.setBounds(110, 5, 100, 30);

		p.setBounds(900, 670, 600, 50); // 위치와 크기 재설정
		add(p);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("채팅");
		frame.setSize(1620, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ChatForm());
		frame.setVisible(true);
	}
}