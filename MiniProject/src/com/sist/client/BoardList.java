
package com.sist.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;
public class BoardList extends JPanel implements ActionListener{
    JLabel titleLabel,pagesCountLabel;
    JTable table;
    DefaultTableModel model;
    JButton writeBtn,previousBtn,nextBtn;
    private ControllerPanel bm;
    TableColumn column;
    // 현재페이지 / 총페이지 
    int curpage=1;
    int totalpage=0;
    
    BoardInsert insertPage;
    public BoardList(ControllerPanel bm)
    {
    	this.bm=bm;
    	titleLabel=new JLabel("게시판",JLabel.CENTER);
    	titleLabel.setFont(new Font("맑은 고딕",Font.BOLD,50));
    	pagesCountLabel=new JLabel("0 page / 0 pages");
    	writeBtn=new JButton("새글");
    	previousBtn=new JButton("이전");
    	nextBtn=new JButton("다음");
    	
    	String[] col={"번호","제목","이름","작성일","조회수"};
    	String[][] row=new String[0][5];
    	model=new DefaultTableModel(row,col)
    	{

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
    		
    	};
    	table=new JTable(model);
    	JScrollPane tableScrollPanel=new JScrollPane(table);

    	// 수직 스크롤바 표기 X
    	tableScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    	
    	for(int i=0;i<col.length;i++)
    	{
    		DefaultTableCellRenderer rend=new DefaultTableCellRenderer();
    		column=table.getColumnModel().getColumn(i);
    		if(i==0)
    		{
    			column.setPreferredWidth(80);
    			rend.setHorizontalAlignment(JLabel.CENTER);
    		}
    		else if(i==1)
    		{
    			column.setPreferredWidth(950);
    		}
    		else if(i==2)
    		{
    			column.setPreferredWidth(150);
    			rend.setHorizontalAlignment(JLabel.CENTER);
    		}
    		else if(i==3)
    		{
    			column.setPreferredWidth(200);
    			rend.setHorizontalAlignment(JLabel.CENTER);
    		}
    		else if(i==4)
    		{
    			column.setPreferredWidth(120);
    			rend.setHorizontalAlignment(JLabel.CENTER);
    		}
    		column.setCellRenderer(rend);
    	}
    	table.getTableHeader().setReorderingAllowed(false);
    	table.getTableHeader().setResizingAllowed(false);
    
    	table.setRowHeight(52);		
    	table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getPreferredSize().width, 50));
    	
    	table.getTableHeader().setBackground(Color.pink);
    	
    	setLayout(null);
    	titleLabel.setBounds(10, 15, 1530, 60);
    	add(titleLabel);
    	
    	writeBtn.setBounds(1350, 90, 150, 40);
    	add(writeBtn);
    	
    	tableScrollPanel.setBounds(30, 150, 1490, 570);	
    	add(tableScrollPanel);
    	
    	JPanel btnPanel=new JPanel();
    	btnPanel.add(previousBtn);btnPanel.add(pagesCountLabel);btnPanel.add(nextBtn);
    	btnPanel.setBounds(30, 740, 1490, 30);
    	add(btnPanel);
    	print();
    	
    	writeBtn.addActionListener(this);
    	previousBtn.addActionListener(this);
    	nextBtn.addActionListener(this);
    }
    // 데이터 출력 
    public void print()
    {
    	//1. 테이블을 초기화 
    	for(int i=model.getRowCount()-1;i>=0;i--)
    	{
    		model.removeRow(i);
    	}
    	//2. 데이터베이스값 
    	BoardDAO dao=BoardDAO.newInstance();
    	List<BoardVO> list=dao.boardListData(curpage);
    	int count=dao.boardRowCount();
    	totalpage=(int)(Math.ceil(count/10.0));
    	count=count-((curpage*10)-10); 
    	
    	for(BoardVO vo:list)
    	{
    		String[] data= {
    			String.valueOf(count),
    			vo.getSubject(),
    			vo.getName(),
    			vo.getDbday(),
    			String.valueOf(vo.getHit())
    		};
    		model.addRow(data);
    		count--;
    	}
    	pagesCountLabel.setText(curpage +" page / "+totalpage+" pages");
    	
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==previousBtn)// 이전
		{
			if(curpage>1)
			{
				curpage--;
				print();
			}
		}
		else if(e.getSource()==nextBtn) //다음
		{
			if(curpage<totalpage)
			{
				curpage++;
				print();
			}
		}
		else if(e.getSource()==writeBtn)
		{
			bm.card.show(bm, "insert");	
			bm.bi.resetForm();
		
		}
	}
    
}
