package com.sist.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ChatForm extends JPanel {
	JButton sendBtn, infoBtn;		// 채팅 전송, 정보보기 버튼
	JTextPane chatDisplayPane, userListPane;			// 채팅 보여주는 화면, 사용자 목록 화면
	JTextField chatTextField;		// 채팅 입력 텍스트 필드

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

		chatDisplayPane = new JTextPane();
		userListPane = new JTextPane();
		JScrollPane chatScrollPane = new JScrollPane(chatDisplayPane);
		JScrollPane listScrollPane = new JScrollPane(userListPane);
		
		bar = chatScrollPane.getVerticalScrollBar();
		chatScrollPane.setBounds(900, 15, 600, 650); 
		listScrollPane.setBounds(15, 15, 850, 400); 
		
		add(chatScrollPane);
		add(listScrollPane);
		chatDisplayPane.setEditable(false);
		userListPane.setEditable(false);

		chatTextField = new JTextField();
		chatTextField.setBounds(15, 420, 850, 30);
		add(chatTextField);

		sendBtn = new JButton("쪽지 보내기");
		infoBtn = new JButton("정보보기");
		
		JPanel panel = new JPanel();
		panel.setLayout(null); 
		panel.add(sendBtn);
		panel.add(infoBtn);
		
		sendBtn.setBounds(5, 5, 100, 30); // x, y, 가로, 세로
		infoBtn.setBounds(110, 5, 100, 30);

		panel.setBounds(900, 670, 600, 50); // 위치와 크기 재설정
		add(panel);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("채팅");
		frame.setSize(1620, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ChatForm());
		frame.setVisible(true);
	}
}