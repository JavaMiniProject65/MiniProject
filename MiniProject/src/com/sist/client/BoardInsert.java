package com.sist.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;
public class BoardInsert extends JPanel implements ActionListener{
	JLabel mainTitleLabel, nameLabel,titleTextLabel,contentLabel,pwdLabel;
	JTextField nameTextField, titleTextField;
	JTextArea contentTextArea;
	JPasswordField pwdTextField;
	JButton writingBtn,cancelBtn;
	private ControllerPanel boardListForm;
	
	public BoardInsert(ControllerPanel boardListForm)
	{
		this.boardListForm=boardListForm;
		mainTitleLabel=new JLabel("글쓰기",JLabel.CENTER);
		mainTitleLabel.setFont(new Font("맑은 고딕",Font.BOLD,50));
    	
		nameLabel=new JLabel("이름",JLabel.CENTER);
		nameLabel.setFont(new Font("맑은 고딕",Font.PLAIN,20));
    	
		titleTextLabel=new JLabel("제목",JLabel.CENTER);
		titleTextLabel.setFont(new Font("맑은 고딕",Font.PLAIN,20));
    	
    	contentLabel=new JLabel("내용",JLabel.CENTER);
    	contentLabel.setFont(new Font("맑은 고딕",Font.PLAIN,20));
    	
    	pwdLabel=new JLabel("비밀번호",JLabel.CENTER);
    	pwdLabel.setFont(new Font("맑은 고딕",Font.PLAIN,20));
    	
    	nameTextField=new JTextField();
    	titleTextField=new JTextField();
    	pwdTextField=new JPasswordField();
    	
    	contentTextArea=new JTextArea();
    	JScrollPane contentScrollPanel=new JScrollPane(contentTextArea);
    	
    	writingBtn=new JButton("글쓰기");
    	cancelBtn=new JButton("취소");
    	
    	setLayout(null);
    	
    	int width = 1000;
    	int startX = (1550 - width) / 2;
    	
    	mainTitleLabel.setBounds(startX, 50, width, 60);
        add(mainTitleLabel);
        
        nameLabel.setBounds(startX, 150, 100, 40);
        nameTextField.setBounds(startX + 110, 150, 200, 40);
        add(nameLabel);add(nameTextField);
        
        titleTextLabel.setBounds(startX, 200, 100, 40);
        titleTextField.setBounds(startX + 110, 200, width - 110, 40);
        add(titleTextLabel);add(titleTextField);
        
        contentLabel.setBounds(startX, 250, 100, 40);
        contentScrollPanel.setBounds(startX + 110, 250, width - 110, 350);
        add(contentLabel);add(contentScrollPanel);
        
        pwdLabel.setBounds(startX, 620, 100, 40);
        pwdTextField.setBounds(startX + 110, 620, 200, 40);
        add(pwdLabel);add(pwdTextField);
        
        JPanel btnPanel=new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); 
        writingBtn.setPreferredSize(new Dimension(200, 50)); // 글쓰기 버튼 크기 조절
        cancelBtn.setPreferredSize(new Dimension(200, 50)); // 취소 버튼 크기 조절
        btnPanel.add(writingBtn); btnPanel.add(cancelBtn);
        btnPanel.setBounds(startX, 680, width, 50);
        add(btnPanel);
    	
        writingBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==writingBtn)
		{
			String name=nameTextField.getText();
			if(name.trim().length()<1)
			{
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				nameTextField.requestFocus();
				return;
			}
			
			String subject=titleTextField.getText();
			if(subject.trim().length()<1)
			{
				JOptionPane.showMessageDialog(this, "제목을 입력하세요");
				titleTextField.requestFocus();
				return;
			}
			
			String content=contentTextArea.getText();
			if(content.trim().length()<1)
			{
				JOptionPane.showMessageDialog(this, "내용을 입력하세요");
				contentTextArea.requestFocus();
				return;
			}
			
			String pwd=String.valueOf(pwdTextField.getPassword());
			if(pwd.trim().length()<1)
			{
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				pwdTextField.requestFocus();
				return;
			}
			
			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setContent(content);
			vo.setSubject(subject);
			vo.setPwd(pwd);
			
			BoardDAO dao=BoardDAO.newInstance();
			dao.boardInsert(vo);
			
			boardListForm.card.show(boardListForm, "list");
			boardListForm.bf.print();
			
		}
		else if(e.getSource()==cancelBtn)
		{
			boardListForm.card.show(boardListForm,"list");
		}
	}
}
