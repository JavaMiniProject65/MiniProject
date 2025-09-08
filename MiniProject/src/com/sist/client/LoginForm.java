package com.sist.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginForm extends JFrame{
	JLabel la1,la2,la3;
	JTextField tf;
	JPasswordField pf;
	JButton b1,b2,b3;
	public LoginForm() {
		// 초기화 / 배치
		setLayout(null);
		la1=new JLabel("ID",JLabel.CENTER);
		la2=new JLabel("PW",JLabel.CENTER);
		la3=new JLabel("LOGIN",JLabel.CENTER);
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("로그인");
		b2=new JButton("회원가입");
		b3=new JButton("취소");
		
		
		la1.setBounds(60, 75, 60, 30);
		tf.setBounds(125, 75, 150, 30);
		add(la1);add(tf);
		la2.setBounds(60, 110, 60, 30);
		pf.setBounds(125, 110, 150, 30);
		add(la2);add(pf);
		la3.setBounds(160, 30, 60, 30);
		//
		add(la3);
		
		JPanel p=new JPanel();
    	p.add(b1);p.add(b2);p.add(b3);
    	p.setBounds(150, 200, 285, 35);
		
		add(p);
		setSize(400, 250);
		setVisible(true);       
		
//		b1.addActionListener(this);
//		b2.addActionListener(this);
	}
}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource()==b1) {
//			dispose();
//			new ClientMainFrame();
//			
//			
//		}
//		if (e.getSource()==b2) {
//			dispose();
//			System.exit(0);
//		}
//	}
//}