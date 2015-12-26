package chess_clock;

import java.io.*;
import java.time.*;
import java.util.*;

//have a play chess script which open up chess data in emacs, and waits till thats done, then run the java executable

public class Driver{
    public static void main(String[] args) throws Exception{
	Properties props = new Properties();
	props.load(new FileInputStream("chess.data"));
	String plyr1 = props.getProperty("Player1");
	String plyr2 = props.getProperty("Player2");
	Duration d = Duration.ofMinutes(Integer.parseInt(props.getProperty("Length")));
	new Clock(plyr1, plyr2, d);
	System.out.println(d);
    }
}
