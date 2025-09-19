package com.sist.client;
import javax.swing.*;
import java.awt.*;

public class ControllerPanel extends JPanel {
	HomeForm hf;
	ChatForm cf=new ChatForm();
	BoardList bf;
	BoardInsert bi;
	FoodFind ff;
	FoodDetail fd;
	BoardDetail bDetail;
	BoardUpdate bUpdate;
	NaverNewsFind news;
	MemberList ml;
	String myId;
	MyPageForm mf;
	
	
	ReservationForm rf=new ReservationForm();
	ClientInfoForm cif=new ClientInfoForm();
	CardLayout card=new CardLayout();
	
	public ControllerPanel() {
		hf=new HomeForm(this);
		bf=new BoardList(this);
		bi=new BoardInsert(this);
		ff=new FoodFind(this);
		fd=new FoodDetail(this);
		ml=new MemberList(this);

		news=new NaverNewsFind(this);
		mf = new MyPageForm(this);			
		bDetail = new BoardDetail(this);
		bUpdate = new BoardUpdate(this);
		
		setLayout(card);
		add("HF",hf);
		add("CF",cf);
		add("BF",bf);
		add("CIF",cif);
		add("list",bf);
		add("insert",bi);
		add("ff",ff);
		add("FD",fd);
		add("MF",mf);
		add("detail", bDetail);
		add("update", bUpdate);
		add("NEWS",news);
		add("ML",ml);
	}
}
