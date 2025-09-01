package com.sist.client;
import javax.swing.*;
import java.awt.*;

public class ControllerPanel extends JPanel {
	HomeForm hf=new HomeForm();
	ChatForm cf=new ChatForm();
	BoardList bf=new BoardList();
	ReservationForm rf=new ReservationForm();
	SearchForm sf=new SearchForm();
	ClientInfoForm cif=new ClientInfoForm();
	CardLayout card=new CardLayout();
	
	public ControllerPanel() {
		setLayout(card);
		add("HF",hf);
		add("CF",cf);
		add("BF",bf);
		add("RF",rf);
		add("SF",sf);
		add("CIF",cif);
		
		
		
	}
}