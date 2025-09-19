package com.sist.client;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.List;

import com.sist.commons.ImageChange;
import com.sist.dao.FoodDAO;
import com.sist.vo.JjimVO;

public class MyPageForm extends JPanel {
    JLabel la1;
    JTable table;
    DefaultTableModel model;
    ControllerPanel cp;
    TableColumn column;

    public MyPageForm(ControllerPanel cp) {
        this.cp = cp;

        String[] col = {"번호", "", "업체명"};
        Object[][] row = new Object[0][3];

        la1=new JLabel("찜 목록",JLabel.CENTER);
        la1.setFont(new Font("맑은 고딕",Font.BOLD,25));
        // FoodFind와 동일한 모델 정책: 편집 불가 + 이미지 컬럼 클래스 지정
        model = new DefaultTableModel(row, col) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }

            // FoodFind는 getValueAt(0,…)을 쓰지만 안전하게 타입을 직접 지정
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return ImageIcon.class; // 썸네일
                return String.class;
            }
        };

        table = new JTable(model);
        JScrollPane js = new JScrollPane(table);

        setLayout(null);
        la1.setBounds(670, 30, 200, 30);
        add(la1);

        // FoodFind와 동일한 테이블 영역/스타일
        js.setBounds(300, 100, 950, 600);
        add(js);

        // === FoodFind와 동일한 스타일 세팅 ===
        for (int i = 0; i < col.length; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(590);
            }
        }

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(60);
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 15)); // 셀 글자 크기

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        table.setShowVerticalLines(false);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        // ===================================

        // 더블클릭 삭제 로직 유지
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == table && e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row < 0) return;
                    String jno = model.getValueAt(row, 0).toString();
                    FoodDAO dao = FoodDAO.newInstance();
                    int res = JOptionPane.showConfirmDialog(
                            cp, "삭제할까요?", "삭제",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (res == JOptionPane.YES_OPTION) {
                        dao.jjimCancel(Integer.parseInt(jno));
                        print();
                    }
                }
            }
        });
    }

    public void print() {
        // 초기화
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        FoodDAO dao = FoodDAO.newInstance();
        List<JjimVO> list = dao.jjimListData(cp.myId);

        try {
            for (JjimVO vo : list) {
                URL url = new URL(vo.getPoster());
                // FoodFind와 동일한 썸네일 크기
                Image img = ImageChange.getImage(new ImageIcon(url), 80, 70);
                Object[] d = {
                        String.valueOf(vo.getJno()),
                        new ImageIcon(img),
                        vo.getName()
                };
                model.addRow(d);
            }
        } catch (Exception ex) {
            // 필요시 로그
        }
    }
}
