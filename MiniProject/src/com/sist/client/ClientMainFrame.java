package com.sist.client;
import javax.swing.*;

import com.sist.commons.ImageChange;
import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;
import com.sist.vo.ZipcodeVO;
import java.util.List;
import java.awt.*; // 배치 => 레이아웃
import java.awt.event.*; // 이벤트 처리
import java.net.URL;
public class ClientMainFrame extends JFrame implements ActionListener {
	
	MenuForm menu=new MenuForm();
	ControllerPanel cp=new ControllerPanel();
	LoginForm login=new LoginForm();
	JoinForm join=new JoinForm();
	
	FoodDetail fd=new FoodDetail(cp);

	PostFind post=new PostFind();
	IdCheck ic=new IdCheck();


	// has-a => 포함 클래스
	public ClientMainFrame() {
		
		setLayout(null);
		menu.setBounds(350, 25, 900, 70);
		cp.setBounds(30, 120, 1550, 780);
		add(menu);
		add(cp);
		setSize(1620, 960);
		
		try {
		    URL url = getClass().getResource("/com/sist/client/logo3.png");
		    if (url == null) {
		        System.err.println("이미지를 찾을 수 없습니다.");
		        return;
		    }
		    Image logoImg = ImageChange.getImage(new ImageIcon(url), 70, 70);
		    JLabel lagoLa = new JLabel(new ImageIcon(logoImg));
		    lagoLa.setBounds(150, 25, 70, 70);
		    add(lagoLa);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		//setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu.b1.addActionListener(this);
		menu.b2.addActionListener(this);
		menu.b4.addActionListener(this);
		menu.b5.addActionListener(this);
		menu.b7.addActionListener(this);

		// ★ 추가: 회원가입 폼의 ID 입력칸을 처음엔 비활성화 + 편집 불가
		join.tf1.setEnabled(false);     // 회색 + 포커스/입력 불가
		join.tf1.setEditable(false);    // 혹시 활성화돼도 편집은 잠금
		
		login.b1.addActionListener(this); // 로그인
    	login.b2.addActionListener(this); // 회원가입
    	login.b3.addActionListener(this); // 취소
    	
    	join.b1.addActionListener(this); // 회원가입
    	join.b2.addActionListener(this); // 취소
    	join.b3.addActionListener(this); // 아이디 중복체크
    	join.b4.addActionListener(this); // 우편번호 검색
    	
    	// 아이디 체크
    	ic.ok.addActionListener(this);
    	ic.check.addActionListener(this);
    	
    	post.btn.addActionListener(this);
    	post.tf.addActionListener(this);
    	
    	post.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==post.table)
				{
					if(e.getClickCount()==2)
					{
						int row=post.table.getSelectedRow();
						String zip=post.model.getValueAt(row,0).toString();
						String addr=post.model.getValueAt(row,1).toString();
						
						join.tf3.setText(zip);
						join.tf4.setText(addr);
						post.setVisible(false);
					}
				}
			}
			
		});
    	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch(Exception e) {}
		new ClientMainFrame(); 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==menu.b1) {
			cp.card.show(cp, "HF");
			cp.bDetail.resetPwdBtn();
			cp.bf.print();
		}
		if(e.getSource()==menu.b2) {
			cp.card.show(cp, "ff");
			cp.bDetail.resetPwdBtn();
			cp.bf.print();
			}
		if(e.getSource()==menu.b4) {
			cp.card.show(cp, "CF");
			cp.bDetail.resetPwdBtn();
			cp.bf.print();
		}
		if(e.getSource()==menu.b5) {
			cp.card.show(cp, "list");
			cp.bDetail.resetPwdBtn();
			cp.bf.print();
		}
		if(e.getSource()==menu.b7) {
			cp.card.show(cp, "MF");
			cp.bDetail.resetPwdBtn();
			cp.mf.print();
		}
		if(e.getSource()==login.b1) // 로그인 버튼
		{
		
			String id=login.tf.getText();
			if(id.trim().length()<1)
			{
				login.tf.requestFocus();
				return;
			}
			String pwd=new String(login.pf.getPassword());
			if(pwd.trim().length()<1)
			{
				login.pf.requestFocus();
				return;
			}
			MemberDAO dao=MemberDAO.newInstance();
			MemberVO vo=dao.isLogin(id, pwd);

			if(vo.getMsg().equals("NOID")) // 아이디 없음
			{
				JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다");
				login.tf.setText("");
				login.pf.setText("");
				login.tf.requestFocus();
			}
			else if(vo.getMsg().equals("NOPWD")) // 비번 틀림
			{
				JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다");
				login.pf.setText("");
				login.pf.requestFocus();
			}
			else
			{
				// 성공
				login.setVisible(false);
				setVisible(true);
				setTitle(vo.getName());
				cp.myId=id;
			}
		}
		if(e.getSource()==login.b2) // 회원가입 버튼
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
			String id=join.tf1.getText();
			if(id.trim().length()<1)
			{
				JOptionPane.showMessageDialog(this, 
						"중복체크를 하세요");
				return;
			}
			String pwd=String.valueOf(join.pf.getPassword());
			String name=join.tf2.getText();
			String sex="";
			if(join.rb1.isSelected())
			{
				sex="남자";
			}
			else
			{
				sex="여자";
			}
			String zip=join.tf3.getText();
			String addr1=join.tf4.getText();
			String addr2=join.tf5.getText();
			String phone=join.tf6.getText();
			
			MemberVO vo=new MemberVO();
			vo.setId(id);
			vo.setAddr1(addr1);
			vo.setAddr2(addr2);
			vo.setPhone(phone);
			vo.setPwd(pwd);
			vo.setPost(zip);
			vo.setSex(sex);
			vo.setName(name);
			
			MemberDAO dao=MemberDAO.newInstance();
			int res=dao.memberJoin(vo);
			if(res==0)
			{
				JOptionPane.showMessageDialog(this, 
						"회원가입에 실패하셨습니다");
			}
			else
			{
				JOptionPane.showMessageDialog(this, 
						"회원가입을 축하합니다");
				join.setVisible(false);
				login.setVisible(true);
			}
		}
		else if(e.getSource()==join.b3)
		{
		    // ★ 추가: 중복확인 누르면 ID입력칸 초기화 + 잠금 유지
		    join.tf1.setText("");
		    join.tf1.setEnabled(false);
		    join.tf1.setEditable(false);

		    ic.tf.setText("");
		    ic.rla.setText("");
		    ic.setVisible(true);

		    // ★ 추가: 다이얼로그의 텍스트필드에 즉시 커서 + 전체선택
		    ic.tf.requestFocusInWindow();
		    ic.tf.selectAll();
		}
		else if(e.getSource()==join.b4)
		{
			for(int i=post.model.getRowCount()-1;i>=0;i--)
			{
				post.model.removeRow(i);
			}
			post.tf.setText("");
			post.setVisible(true);
		}
		if(e.getSource()==join.b2)
		{
			login.setVisible(true);
			join.setVisible(false);
			
		}
		// 아이디 체크
				else if(e.getSource()==ic.check)
				{
					String id=ic.tf.getText();
					// request.getParameter()
					if(id.trim().length()<1)
					{
						JOptionPane.showMessageDialog(this, "ID를 입력하세요");
						// 웹 => alert()
						ic.tf.requestFocus();
						return;
					}
					MemberDAO dao=MemberDAO.newInstance();
					int count=dao.memberIdCheck(id.trim());
					if(count==0) // 아이디가 없음
					{
						ic.rla.setBackground(Color.blue);
						ic.rla.setText(id+"는(은) 사용 가능한 아이디 입니다");
						ic.ok.setVisible(true);
						
					}
					else // 아이디 있음
					{
						ic.rla.setBackground(Color.red);
						ic.rla.setText(id+"는(은) 이미 사용중인 아이디 입니다");
						ic.ok.setVisible(false);
					}
				}
				// 결과
				else if(e.getSource()==ic.ok)
				{
				    String id=ic.tf.getText();
				    join.tf1.setText(id);
				    join.tf1.setEnabled(true);      // 회색 해제
				    join.tf1.setEditable(false);    // 편집은 금지(검증 후 변조 방지)

				    // ★ 선택: 다음 입력 흐름 좋게 - 비밀번호 칸으로 포커스 넘기기
				    if (join.pf != null) {
				        join.pf.requestFocusInWindow();
				    }

				    ic.setVisible(false);
				}
				// 우편번호 검색
				else if(e.getSource()==post.btn||e.getSource()==post.tf)
				{
					String fd=post.tf.getText();
					if(fd.trim().length()<1)
					{
						post.tf.requestFocus();
						return;
					}
					MemberDAO dao=MemberDAO.newInstance();
					int count=dao.postFindCount(fd);
					if(count==0) // 검색 결과 없음
					{
						JOptionPane.showMessageDialog(this, "검색 결과가 없습니다");
						post.tf.setText("");
						post.tf.requestFocus();
					}
					else // 검색 결과 있음
					{
						List<ZipcodeVO> list=dao.postFind(fd);
						for(ZipcodeVO vo:list)
						{
							String[] data= {vo.getZipcode(),vo.getAddress()};
							post.model.addRow(data);
						}
					}
				}
		
	}

}
