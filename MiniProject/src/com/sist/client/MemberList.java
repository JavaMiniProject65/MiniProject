package com.sist.client;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

public class MemberList extends JPanel implements ActionListener {
    private final ControllerPanel cp;

    private JLabel titleLabel, pagesCountLabel;
    private JTable table;
    private DefaultTableModel model;
    private JButton previousBtn, nextBtn, editBtn, deleteBtn;
    private TableColumn column;

    private int curpage = 1;
    private int totalpage = 0;
    private final int rowSize = 10;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public MemberList(ControllerPanel cp) {
        this.cp = cp;
        setLayout(null);

        titleLabel = new JLabel("회원 목록", JLabel.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
        titleLabel.setBounds(140, 15, 1230, 60); // 우측 버튼 공간 확보
        add(titleLabel);

        // 우측 상단: 수정/삭제 버튼
        editBtn = new JButton("수정");
        deleteBtn = new JButton("삭제");
        editBtn.setBounds(1270, 25, 120, 40);
        deleteBtn.setBounds(1400, 25, 120, 40);
        add(editBtn);
        add(deleteBtn);

        pagesCountLabel = new JLabel("0 page / 0 pages");

        previousBtn = new JButton("이전");
        nextBtn = new JButton("다음");

        String[] col = {"번호","아이디","이름","성별","우편번호","주소","상세주소","전화","가입일"};
        model = new DefaultTableModel(new String[0][col.length], col) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(52);
        table.getTableHeader().setPreferredSize(
            new Dimension(table.getTableHeader().getPreferredSize().width, 50)
        );
        table.getTableHeader().setBackground(Color.PINK);

        // 컬럼 폭/정렬
        for (int i = 0; i < col.length; i++) {
            DefaultTableCellRenderer rend = new DefaultTableCellRenderer();
            column = table.getColumnModel().getColumn(i);
            switch (i) {
                case 0: column.setPreferredWidth(55);  rend.setHorizontalAlignment(JLabel.CENTER); break;
                case 1: column.setPreferredWidth(140); rend.setHorizontalAlignment(JLabel.CENTER); break;
                case 2: column.setPreferredWidth(100); rend.setHorizontalAlignment(JLabel.CENTER); break;
                case 3: column.setPreferredWidth(60);  rend.setHorizontalAlignment(JLabel.CENTER); break;
                case 4: column.setPreferredWidth(90);  rend.setHorizontalAlignment(JLabel.CENTER); break;
                case 5: column.setPreferredWidth(360); break;
                case 6: column.setPreferredWidth(320); break;
                case 7: column.setPreferredWidth(130); rend.setHorizontalAlignment(JLabel.CENTER); break;
                case 8: column.setPreferredWidth(120); rend.setHorizontalAlignment(JLabel.CENTER); break;
            }
            column.setCellRenderer(rend);
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        sp.setBounds(30, 150, 1490, 570);
        add(sp);

        JPanel btnPanel = new JPanel();
        btnPanel.add(previousBtn);
        btnPanel.add(pagesCountLabel);
        btnPanel.add(nextBtn);
        btnPanel.setBounds(30, 740, 1490, 30);
        add(btnPanel);

        previousBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        print();
    }

    /** 테이블 채우기 */
    public void print() {
        // 초기화
        for (int i = model.getRowCount() - 1; i >= 0; i--) model.removeRow(i);

        MemberDAO dao = MemberDAO.newInstance();

        int count = dao.memberRowCount();
        totalpage = (int) Math.ceil(count / (double) rowSize);

        List<MemberVO> list = dao.memberListData(curpage);

        int number = count - ((curpage * rowSize) - rowSize); // 역순 번호

        for (MemberVO vo : list) {
            String reg = (vo.getRegdate() != null) ? sdf.format(vo.getRegdate()) : "";
            model.addRow(new String[] {
                String.valueOf(number),
                vo.getId(),
                vo.getName(),
                vo.getSex(),
                vo.getPost(),
                vo.getAddr1(),
                vo.getAddr2(),
                vo.getPhone(),
                reg
            });
            number--;
        }

        pagesCountLabel.setText(curpage + " page / " + totalpage + " pages");
    }

    /** 현재 선택된 행의 회원 ID 얻기 */
    private String getSelectedMemberId() {
        int row = table.getSelectedRow();
        if (row < 0) return null; // 선택 없음
        // 모델과 뷰 인덱스 차이 대응(정렬 등 대비)
        int modelRow = table.convertRowIndexToModel(row);
        return (String) model.getValueAt(modelRow, 1); // 1 = 아이디 컬럼
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == previousBtn) {
            if (curpage > 1) {
                curpage--;
                print();
            }
            return;
        }

        if (src == nextBtn) {
            if (curpage < totalpage) {
                curpage++;
                print();
            }
            return;
        }

        if (src == editBtn) {
            String id = getSelectedMemberId();
            if (id == null) {
                JOptionPane.showMessageDialog(this, "수정할 회원을 선택하세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // TODO: 수정 폼으로 전환 (예: cp.card.show(cp, "memberEdit"); cp.memberEdit.load(id);)
            JOptionPane.showMessageDialog(this, "수정 선택: " + id, "정보", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (src == deleteBtn) {
            String id = getSelectedMemberId();
            if (id == null) {
                JOptionPane.showMessageDialog(this, "삭제할 회원을 선택하세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int ok = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?\nID: " + id,
                                                   "확인", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                // TODO: 실제 삭제 로직 호출
                // MemberDAO dao = MemberDAO.newInstance();
                // int res = dao.memberDelete(id);
                // if (res > 0) { print(); } else { ... }
                JOptionPane.showMessageDialog(this, "삭제 선택: " + id, "정보", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
