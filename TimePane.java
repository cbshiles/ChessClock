package chess_clock;

import java.time.*;
import javax.swing.*;
import java.awt.*;

public class TimePane extends JPanel{
    //pane for black or white time keeping, main component(s) of this clock

    private Duration duration;
    private long curr;
    private JLabel clck;

    private Color colate(int x){return new Color(x,x,x);}
    
    public TimePane(String name, boolean black, Duration d){
	duration = d;
	if (black) curr = System.currentTimeMillis();
	setBackground(colate(black?255:0));
	JLabel l = new JLabel(name);
	Color c = colate(black?55:200);
	l.setForeground(c);
	l.setFont(new Font(l.getFont().getName(), l.getFont().getStyle(), 20));
	add(l);

	clck = new JLabel(left());
	clck.setFont(new Font(l.getFont().getName(), l.getFont().getStyle(), 20));
	clck.setForeground(c);
	add(clck);
    }

    void setCurr(){
	curr = System.currentTimeMillis();
    }

    private String left(){
	long ms = duration.toMillis();
	long mins, absMs = Math.abs(ms);
	String positive = String.format(
					"%d:%02d.%02d",
					mins = absMs / 60000,
					(absMs / 1000) - mins*60,
					absMs % 100);
	return ms < 0 ? "-" + positive : positive;
    }
    
    public boolean scratch(){
	//returns whether the game is over or not & update clock
	long tmp = System.currentTimeMillis();
	duration = duration.minusMillis(tmp -  curr);
	curr = tmp;
	clck.setText(left());
	return duration.isNegative();
	}
}
