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
	String myId;
	
	ReservationForm rf=new ReservationForm();
	ClientInfoForm cif=new ClientInfoForm();
	CardLayout card=new CardLayout();
	
	public ControllerPanel() {
		hf=new HomeForm(this);
		bf=new BoardList(this);
		bi=new BoardInsert(this);
		ff=new FoodFind(this);
		fd=new FoodDetail(this);
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
		add("detail", bDetail);
		add("update", bUpdate);
	}
}
