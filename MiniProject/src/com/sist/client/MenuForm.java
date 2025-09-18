package com.sist.client;
import javax.swing.*;

import com.sist.commons.ImageChange;

import java.awt.*;
import java.net.URL;
public class MenuForm extends JPanel {
	JButton b1=new JButton("Home");
	JButton b2=new JButton("맛집검색");
	JButton b5=new JButton("게시판");
	JButton b6=new JButton("고객 정보 조회");
	JButton b7=new JButton("마이페이지");
	
	public MenuForm() {
			
		setLayout(new GridLayout(1,5,5,5));
		add(b1);add(b2);
		add(b5);add(b6);add(b7);
	}
}