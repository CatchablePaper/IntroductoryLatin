/* Title: summative.java - a comprehensive learning tool for teaching the user the basics of Latin
 * Author: Johnny Tang
 * Course: ICS4U
 * Teacher: Mr. Berry
 * Date: June 5, 2013
 * Input: (1) username and password input
 * 		  (2) lesson selection using buttons
 * 		  (3) quiz answers input using dropdown menu
 * Output: (1) lesson content
 * 		   (2) randomly generated image
 * 		   (3) quiz questions
 * 		   (4) progress and results
 */

//do all the imports
import java.awt.*;
import javax.imageio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.swing.*;

//overall class of summative
public class summative {

	//main method
	public static void main(String[] args) {
		//catch all the exceptions beforehand
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		//run the program
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//make the create and show GUI, catch the IOException
				try {
					tabs.createAndShowGUI();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
