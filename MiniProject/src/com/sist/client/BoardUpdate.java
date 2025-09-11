package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;


public class BoardUpdate extends JPanel implements ActionListener{
	JLabel la1, la2, la3, la4, la5;
	JTextField tf1, tf2;
	JTextArea ta;
	JPasswordField pf;
	JButton b1, b2;
	
	private ControllerPanel bm;
	int no = 0;
	
	public BoardUpdate(ControllerPanel bm)
	{
		this.bm = bm;
		la1 = new JLabel("수정하기", JLabel.CENTER);
		la1.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		
		la2 = new JLabel("이름", JLabel.CENTER);
		la3 = new JLabel("제목", JLabel.CENTER);
		la4 = new JLabel("내용", JLabel.CENTER);
		la5 = new JLabel("비밀번호", JLabel.CENTER);
		
		tf1 = new JTextField();
		tf2 = new JTextField();
		pf = new JPasswordField();
		
		ta = new JTextArea();
		JScrollPane js = new JScrollPane(ta);
		
		b1 = new JButton("수정");
		b2 = new JButton("취소");
		
		b1.setPreferredSize(new Dimension(100, 40));
		b2.setPreferredSize(new Dimension(100, 40));
		
		Font mainLabelFont = new Font("맑은 고딕", Font.BOLD, 15);
		Font mainFont = new Font("맑은 고딕", Font.PLAIN, 15);
		
		la2.setFont(mainLabelFont);
		la3.setFont(mainLabelFont);
		la4.setFont(mainLabelFont);
		la5.setFont(mainLabelFont);
		tf1.setFont(mainFont);
		tf2.setFont(mainFont);
		ta.setFont(mainFont);
		pf.setFont(mainFont);
		
		
		setLayout(null);
		int panelWidth = 1550;
		int componentWidth = 1000;
		int xOffset = (panelWidth - componentWidth) / 2;

		la1.setBounds(xOffset, 50, componentWidth, 50);
		add(la1);
		
		la2.setBounds(xOffset + 50, 120, 100, 30); 
		tf1.setBounds(xOffset + 160, 120, 200, 30);
		add(la2); add(tf1);
		
		la3.setBounds(xOffset + 50, 160, 100, 30); 
		tf2.setBounds(xOffset + 160, 160, 800, 30);
		add(la3); add(tf2);
		
		la4.setBounds(xOffset + 50, 200, 100, 30); 
		js.setBounds(xOffset + 160, 200, 800, 400);
		add(la4); add(js);
		
		la5.setBounds(xOffset + 50, 620, 100, 30);
		pf.setBounds(xOffset + 160, 620, 200, 30);
		add(la5); add(pf);
		
		JPanel p = new JPanel();
		p.add(b1); p.add(b2);
		p.setBounds(xOffset + 160, 670, 250, 50); 
		add(p);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1)
		{
			String name = tf1.getText();
			if(name.trim().length() < 1)
			{
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				tf1.requestFocus();
				return;
			}
			
			String subject = tf2.getText();
			if(subject.trim().length() < 1)
			{
				JOptionPane.showMessageDialog(this, "제목을 입력하세요");
				tf2.requestFocus();
				return;
			}
			
			String content = ta.getText();
			if(content.trim().length() < 1)
			{
				JOptionPane.showMessageDialog(this, "내용을 입력하세요");
				ta.requestFocus();
				return;
			}
			
			String pwd = String.valueOf(pf.getPassword());
			if(pwd.trim().length() < 1)
			{
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				pf.requestFocus();
				return;
			}
			
			BoardVO vo = new BoardVO();
			vo.setName(name);
			vo.setContent(content);
			vo.setSubject(subject);
			vo.setPwd(pwd);
			vo.setNo(no);
			BoardDAO dao = BoardDAO.newInstance();
			boolean bCheck = dao.boardUpdate(vo);
			if(bCheck == false)
			{
				JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다");
				pf.setText("");
				pf.requestFocus();
			}
			else
			{
				bm.card.show(bm, "detail");
				bm.bDetail.print(no);
			}
			
			bm.card.show(bm, "detail");
			bm.bDetail.print(no);
			
		}
		else if(e.getSource() == b2)
		{
			bm.card.show(bm, "detail");
			bm.bDetail.print(no);
		}
		
	}
	public void print(int no)
	{
		this.no = no;
		BoardDAO dao = BoardDAO.newInstance();
		BoardVO vo = dao.boardUpdateData(no);
		tf1.setText(vo.getName());
		tf2.setText(vo.getSubject());
		ta.setText(vo.getContent());
	}
}
