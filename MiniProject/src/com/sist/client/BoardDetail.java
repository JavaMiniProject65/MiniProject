package com.sist.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;
public class BoardDetail extends JPanel implements ActionListener{
	JLabel la1, la2, la3, la4, la5, la6;
	JLabel la_p1, la_p2, la_p3, la_p4, la_p5;
	JTextArea ta;
	JButton b1, b2, b3;
	ControllerPanel bm;
	
	// 삭제
	JLabel la7;
	JPasswordField pf;
	JButton b4;
	JPanel pan = new JPanel();
	
	boolean bCheck = false;
	
	public BoardDetail(ControllerPanel bm)
	{
		this.bm = bm;
		
		la1 = new JLabel("내용보기", JLabel.CENTER);
		la2 = new JLabel("번호", JLabel.CENTER);
		la3 = new JLabel("작성일", JLabel.CENTER);
		la4 = new JLabel("이름", JLabel.CENTER);
		la5 = new JLabel("조회수", JLabel.CENTER);
		la6 = new JLabel("제목", JLabel.CENTER);
		
		la_p1 = new JLabel("", JLabel.CENTER);
		la_p2 = new JLabel("", JLabel.CENTER);
		la_p3 = new JLabel("", JLabel.CENTER);
		la_p4 = new JLabel("", JLabel.CENTER);
		la_p5 = new JLabel("", JLabel.CENTER);
		
		ta = new JTextArea();
		JScrollPane js = new JScrollPane(ta);
		ta.setEnabled(false);
		b1 = new JButton("수정");
		b2 = new JButton("삭제");
		b3 = new JButton("목록");
		b1.setPreferredSize(new Dimension(100, 40));
		b2.setPreferredSize(new Dimension(100, 40));
		b3.setPreferredSize(new Dimension(100, 40));
		
		
		la7 = new JLabel("비밀번호", JLabel.CENTER);
		pf = new JPasswordField(7);
		b4 = new JButton("삭제");
		b4 = new JButton("삭제");
		b4.setPreferredSize(new Dimension(100, 40));
		
		pan.add(la7); pan.add(pf); pan.add(b4);
		
		// 배치
		setLayout(null);
		
		int panelWidth = 1550;
		int componentWidth = 1200;
		int xOffset = (panelWidth - componentWidth) / 2;
		
		la1.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		la1.setBounds(xOffset, 50, componentWidth, 50);
		add(la1);
		
		Font mainFont = new Font("맑은 고딕", Font.PLAIN, 15); // 나머지 라벨 폰트
		Font mainBoldFont = new Font("맑은 고딕", Font.BOLD, 15); // 나머지 라벨 폰트
		
		la2.setFont(mainBoldFont);
		la2.setBounds(xOffset + 50, 120, 100, 30);
		la_p1.setFont(mainFont);
		la_p1.setBounds(xOffset + 160, 120, 200, 30);
		add(la2); add(la_p1);
		
		la3.setFont(mainBoldFont);
		la3.setBounds(xOffset + 400, 120, 100, 30);
		la_p2.setFont(mainFont);
		la_p2.setBounds(xOffset + 510, 120, 250, 30);
		add(la3); add(la_p2);
		
		la4.setFont(mainBoldFont);
		la4.setBounds(xOffset + 50, 160, 100, 30);
		la_p3.setFont(mainFont);
		la_p3.setBounds(xOffset + 160, 160, 200, 30);
		add(la4); add(la_p3);
		
		la5.setFont(mainBoldFont);
		la5.setBounds(xOffset + 400, 160, 100, 30);
		la_p4.setFont(mainFont);
		la_p4.setBounds(xOffset + 510, 160, 250, 30);
		add(la5); add(la_p4);
		
		la6.setFont(mainBoldFont);
		la6.setBounds(xOffset + 50, 200, 100, 30);
		la_p5.setFont(mainFont);
		la_p5.setBounds(xOffset + 160, 200, 600, 30);
		add(la6); add(la_p5);
		
		ta.setFont(mainFont);
		js.setBounds(xOffset + 50, 250, 1100, 350);
		add(js);
		
		la7.setFont(mainBoldFont);
		
		JPanel p = new JPanel();
		p.add(b1); p.add(b2); p.add(b3);
		p.setBounds(xOffset + 450, 620, 320, 50);
		add(p);
		
		pan.setBounds(xOffset + 450, 670, 320, 50);
		add(pan);
		
		pan.setVisible(false);
		
		b1.addActionListener(this);	// 수정
		b2.addActionListener(this);	// 삭제
		b3.addActionListener(this);	// 목록
		b4.addActionListener(this);	// 삭제
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b2)
		{
			bCheck = !bCheck;
			if(bCheck == true)
			{
				b2.setText("취소");
				pf.setText("");
				pf.requestFocus();
				pan.setVisible(true);
			}
			else
			{
				b2.setText("삭제");
				pf.setText("");
				pan.setVisible(false);
			}
		}
		else if(e.getSource() == b3)
		{
			bm.card.show(bm, "list");
			pf.setText("");
			pan.setVisible(false);
			bCheck = true;
			b2.setText("삭제");
			bm.bf.print();
		}
		else if(e.getSource() == b4)
		{
			String pwd = String.valueOf(pf.getPassword());
			if(pwd.trim().length() < 1)
			{
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
				pf.requestFocus();
				return;
			}
			
			String no = la_p1.getText();
			BoardDAO dao = BoardDAO.newInstance();
			boolean bCheck = dao.boardDelete(Integer.parseInt(no), pwd);
			if(bCheck == true)
			{
				bm.card.show(bm, "list");
				
				bm.bf.print();
			}
			else	// 비밀번호가 틀린 상태 
			{
				JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다.");
				pf.setText("");
				pf.requestFocus();
			}
		}
		else if(e.getSource() == b1)	// 수정
		{
			String no = la_p1.getText();
			pf.setText("");
			bCheck = false;
			pan.setVisible(false);
			b2.setText("삭제");
			bm.card.show(bm, "update");
			bm.bUpdate.pf.setText("");
			bm.bUpdate.print(Integer.parseInt(no));
		}
	}
	public void print(int no)
	{
		BoardDAO dao = BoardDAO.newInstance();
		BoardVO vo = dao.boardDetailData(no);
		la_p1.setText(String.valueOf(vo.getNo()));
		la_p2.setText(vo.getDbday());
		la_p3.setText(vo.getName());
		la_p4.setText(String.valueOf(vo.getHit()));
		la_p5.setText(vo.getSubject());
		ta.setText(vo.getContent());
		
	}
}
