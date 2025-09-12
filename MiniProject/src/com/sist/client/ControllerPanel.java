package com.sist.client;
import javax.swing.*;
import java.awt.*;

public class ControllerPanel extends JPanel {
	HomeForm hf;
	ChatForm cf=new ChatForm();
	BoardList bf;
	BoardInsert bi;
	BoardDetail bDetail;
	BoardUpdate bUpdate;
	
	ReservationForm rf=new ReservationForm();
	SearchForm sf=new SearchForm();
	ClientInfoForm cif=new ClientInfoForm();
	CardLayout card=new CardLayout();
	
	public ControllerPanel() {
		hf=new HomeForm(this);
		bf=new BoardList(this);
		bi=new BoardInsert(this);
		bDetail = new BoardDetail(this);
		bUpdate = new BoardUpdate(this);
		setLayout(card);
		add("HF",hf);
		add("CF",cf);
		add("BF",bf);
		add("RF",rf);
		add("SF",sf);
		add("CIF",cif);
		add("list",bf);
		add("insert",bi);
		add("detail", bDetail);
		add("update", bUpdate);
	}
}