package com.sist.client;
import javax.swing.*;
import java.awt.*; // 배치 => 레이아웃
import java.awt.event.*; // 이벤트 처리
public class ClientMainFrame extends JFrame implements ActionListener {
	
	MenuForm menu=new MenuForm();
	ControllerPanel cp=new ControllerPanel();
	// has-a => 포함 클래스
	public ClientMainFrame() {
		setLayout(null);
		menu.setBounds(350, 25, 900, 70);
		cp.setBounds(30, 120, 1550, 780);
		add(menu);
		add(cp);
		setSize(1620, 960);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu.b1.addActionListener(this);
		menu.b2.addActionListener(this);
		menu.b3.addActionListener(this);
		menu.b4.addActionListener(this);
		menu.b5.addActionListener(this);
		menu.b6.addActionListener(this);
		menu.b7.addActionListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch(Exception e) {}
		new ClientMainFrame(); // 생성자 호출
		// ClientMainFrame c=new ClientMainFrame();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==menu.b1) {
			cp.card.show(cp, "HF");
		}
		if(e.getSource()==menu.b2) {
			cp.card.show(cp, "CF");
			}
		if(e.getSource()==menu.b3) {
			cp.card.show(cp, "BF");
		}
		if(e.getSource()==menu.b4) {
			cp.card.show(cp, "RF");
		}
		if(e.getSource()==menu.b5) {
			cp.card.show(cp, "LF");
		}
		if(e.getSource()==menu.b6) {
			cp.card.show(cp, "SF");
		}
		if(e.getSource()==menu.b7) {
			cp.card.show(cp, "CIF");
		}
	}

}
