package com.sist.client;
import javax.swing.*;
import java.awt.*; // 배치 => 레이아웃
import java.awt.event.*; // 이벤트 처리
public class ClientMainFrame extends JFrame implements ActionListener {
	
	MenuForm menu=new MenuForm();
	ControllerPanel cp=new ControllerPanel();
	LoginForm login=new LoginForm();
	JoinForm join=new JoinForm();

	// has-a => 포함 클래스
	public ClientMainFrame() {
		setLayout(null);
		menu.setBounds(350, 25, 900, 70);
		cp.setBounds(30, 120, 1550, 780);
		add(menu);
		add(cp);
		setSize(1620, 960);
		//setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu.b1.addActionListener(this);
		menu.b2.addActionListener(this);
		menu.b3.addActionListener(this);
		menu.b4.addActionListener(this);
		menu.b5.addActionListener(this);
		menu.b6.addActionListener(this);
		
		login.b1.addActionListener(this); // 로그인
    	login.b2.addActionListener(this); // 회원가입
    	login.b3.addActionListener(this); // 취소
    	
    	join.b1.addActionListener(this); // 회원가입
    	join.b2.addActionListener(this); // 취소
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch(Exception e) {}
		new ClientMainFrame(); 
		// ClientMainFrame c=new ClientMainFrame();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==menu.b1) {
			cp.card.show(cp, "HF");
		}
		if(e.getSource()==menu.b2) {
			cp.card.show(cp, "SF");
			}
		if(e.getSource()==menu.b3) {
			cp.card.show(cp, "RF");
		}
		if(e.getSource()==menu.b4) {
			cp.card.show(cp, "CF");
		}
		if(e.getSource()==menu.b5) {
			cp.card.show(cp, "BF");
		}
		if(e.getSource()==menu.b6) {
			cp.card.show(cp, "CIF");
		}
		if(e.getSource()==login.b1) // 취소버튼
		{
			setVisible(true);
		}
		if(e.getSource()==login.b2)
		{
			login.setVisible(false);
			join.setVisible(true);
		}
		
		if(e.getSource()==login.b3) // 취소버튼
		{
			dispose();
			System.exit(0);
		}
		if(e.getSource()==join.b1)
		{
			
		}
		if(e.getSource()==join.b2)
		{
			login.setVisible(true);
			join.setVisible(false);
		}
	}

}