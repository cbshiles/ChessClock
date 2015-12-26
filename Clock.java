package chess_clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class Clock extends JFrame {

    private TimePane aPane, bPane;
    private boolean ona = true; //on aPane = true, on bPane = false
    private Timer tmr;
    private String aName, bName;
    
    public Clock(String plyr1, String plyr2, Duration d){

	aName = plyr1;
	bName = plyr2;
	
	setBounds(100, 100, 900, 300);

	add(aPane = new TimePane(plyr1, true, d));
	add(bPane = new TimePane(plyr2, false, d)); 

	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);

	ActionListener tick = new ActionListener() {
		//periodic update for display, checks if time is up
		long last = 0;

		public void actionPerformed(ActionEvent evt) {
		    boolean over = (ona?aPane:bPane).scratch();
		    if (over) end();
		    last = System.currentTimeMillis();
		}};
	    
	Action flip = new AbstractAction() {
		//action triggered for key event. checks if time is up, switches focus to other player
		public void actionPerformed(ActionEvent e) {
		    if ((ona?aPane:bPane).scratch()) end();
		    else (ona?bPane:aPane).setCurr();
		    ona = !ona;
		}};

	JPanel content = (JPanel) getContentPane();
	content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "flip");
	content.getActionMap().put("flip", flip);

	tmr = new Timer(100, tick); //every 1/10th of a second
	tmr.start(); // and begin	
    }

    private void end(){ //stop the clock, time has run out
	//#redirect this
	tmr.stop();
	System.out.println((ona?aName:bName)+" has run out of time.");
    }
}
