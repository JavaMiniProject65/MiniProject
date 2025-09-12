package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

public class JoinForm extends JFrame implements ActionListener {
    JLabel[] las = new JLabel[8];
    String[] title = {"ID","비밀번호","이름","성별","우편번호","주소","상세주소","전화"};
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    JPasswordField pf;
    JRadioButton rb1, rb2;
    JButton b1, b2, b3;
    
    MemberDAO dao = new MemberDAO(); // DB연결 담당

    public JoinForm() {
        setLayout(null);
        for (int i = 0; i < las.length; i++) {
            las[i] = new JLabel(title[i]);
            las[i].setBounds(10, 15 + (i * 35), 65, 30);
            add(las[i]);
        }

        tf1 = new JTextField(); // ID
        tf2 = new JTextField(); // 이름
        tf3 = new JTextField(); // 우편번호
        tf4 = new JTextField(); // 주소
        tf5 = new JTextField(); // 상세주소
        tf6 = new JTextField(); // 전화

        b3 = new JButton("중복체크");
        tf1.setBounds(80, 15, 150, 30);
        b3.setBounds(245, 15, 100, 30);
        add(tf1); add(b3);

        pf = new JPasswordField();
        pf.setBounds(80, 50, 150, 30);
        add(pf);

        tf2.setBounds(80, 85, 150, 30);
        add(tf2);

        rb1 = new JRadioButton("남자");
        rb2 = new JRadioButton("여자");
        ButtonGroup bg = new ButtonGroup();
        rb1.setBounds(80, 120, 70, 30);
        rb2.setBounds(155, 120, 70, 30);
        add(rb1); add(rb2);
        bg.add(rb1); bg.add(rb2);

        tf3.setBounds(80, 155, 150, 30);
        add(tf3);

        tf4.setBounds(80, 190, 300, 30);
        add(tf4);

        tf5.setBounds(80, 225, 300, 30);
        add(tf5);

        tf6.setBounds(80, 260, 150, 30);
        add(tf6);

        b1 = new JButton("회원가입");
        b2 = new JButton("취소");
        JPanel p = new JPanel();
        p.add(b1); p.add(b2);
        p.setBounds(10, 310, 370, 35);
        add(p);

        rb1.setSelected(true);

        // 버튼 이벤트 등록
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setSize(450, 385);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b3) { // ID 중복체크
            String id = tf1.getText().trim();
            if (id.length() < 1) {
                JOptionPane.showMessageDialog(this, "ID를 입력하세요.");
                return;
            }
            boolean exists = dao.isExistId(id);
            if (exists) {
                JOptionPane.showMessageDialog(this, "이미 사용중인 ID입니다.");
            } else {
                JOptionPane.showMessageDialog(this, "사용 가능한 ID입니다.");
            }
        } else if (e.getSource() == b1) { // 회원가입
            try {
                MemberVO vo = new MemberVO();
                vo.setId(tf1.getText().trim());
                vo.setPwd(new String(pf.getPassword()));
                vo.setName(tf2.getText().trim());
                vo.setSex(rb1.isSelected() ? "남자" : "여자");
                vo.setPost(tf3.getText().trim());
                vo.setAddr1(tf4.getText().trim());
                vo.setAddr2(tf5.getText().trim());
                vo.setTel(tf6.getText().trim());

                dao.memberJoin(vo); // DB 저장
                JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "회원가입 중 오류 발생");
            }
        } else if (e.getSource() == b2) { // 취소
            dispose();
        }
    }

    public static void main(String[] args) {
        new JoinForm();
    }
}
