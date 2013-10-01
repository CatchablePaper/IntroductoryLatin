/* Title: tabs.java - creation of the tabs for the summative program
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class tabs implements ActionListener {

	//VARIABLE DECLARATION
	//current tab
	static int currentTab = 1;
	static String Lessons = "Lessons";
	static String Review = "Review";
	//current page and lesson
	static int currentPage = 0;
	static int currentLesson = 0;
	static int extraWindowWidth = 100;
	static boolean test = false;
	//stores the user's answer choices for the quiz
	static ArrayList<Integer> userAns = new ArrayList<>();
	static ArrayList<Integer> answers = new ArrayList<>();
	//count the number of buttons
	static int numButtons = 0;
	//login variables, stores username
	static String user = null;
	static String pass = null;
	//default lesson content
	static String lessonContent = "Please select a lesson!";

	//LOGIN METHOD
	//Input: username and password
	//Output: successful login
	public static void login() throws IOException
	{
		//ask for username and password
		user = JOptionPane.showInputDialog("Username: ");
		pass = JOptionPane.showInputDialog("Password: ");
		//checks the user's inputs with the password file
		BufferedReader fh = new BufferedReader(new FileReader("C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/users.txt"));
		String line = null;
		//for each new entry in the user database, have a new element in the arraylist
		ArrayList<String> userDB = new ArrayList<>();
		ArrayList<String> passDB = new ArrayList<>();
		//add to the arraylist for each entry
		while ((line = fh.readLine())!=null)
		{
			String[] thisUser = line.split("!");
			userDB.add(thisUser[0]);
			passDB.add(thisUser[1]);
		}
		//declare whether logged in or not
		boolean loggedIn = false;
		//for each user, check whether the user's username and password match
		for (String theUser : userDB)
		{
			//check for each user
			if (theUser.equals(user))
			{
				//find the user using arraylist's indexOf
				int userList = userDB.indexOf(theUser);
				//check password
				if ((passDB.get(userList)).equals(pass))
				{
					loggedIn = true;
				}
			}
		}
		if (loggedIn)
		{
			//if user is not null it means logged in
			user = user;
		}
		else
		{
			user = null;
		}
	}

	//CREATE AND SHOW GUI
	public static void createAndShowGUI() throws IOException {
		//Create and set up the window.
		Frame[] list = Frame.getFrames();
		//clear out all the frames at the beginning of each GUI generation
		for (int i = 0; i < list.length; i++)
		{
			list[i].dispose();
		}
		//initiate the overarching JFrame
		JFrame frame = new JFrame("Rhetor");
		frame.setBackground(Color.white);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		tabs demo = new tabs();
		demo.addComponentToPane(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	//GET LESSON METHOD
	public String getLesson (int currentPage, int currentLesson)
	{
		//get the current lesson
		String filename = "C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/lesson"+currentLesson+"-"+(currentPage+1)+".txt";
		//read the lesson and return it
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Scanner kb = new Scanner(br);
			lessonContent = "";
			//read each line of html code
			while(kb.hasNextLine())
			{
				lessonContent += kb.nextLine();
			}
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		return lessonContent;
	}

	//ACTION PERFORMED
	//listens to actions of clicking the button by the user
	public void actionPerformed(ActionEvent e) {
		//understand what the user inputted in numerical terms
		int action = Integer.parseInt(e.getActionCommand());
		int i = 0;
		numButtons = 0;
		//switch on the action
		switch (action)
		{
		//1 = Lesson 1
		case 1:
			//set the current lesson and current page
			currentLesson = 1;
			currentPage = 0;
			//get the lesson content
			lessonContent = getLesson(currentPage,currentLesson);
			break;

			//2 = Lesson 2
		case 2:
			//set current lesson
			currentLesson = 2;
			currentPage = 0;
			lessonContent = getLesson(currentPage,currentLesson);
			break;

			//analogous to all the previous cases
		case 3:
			//set current lesson
			currentLesson = 3;
			currentPage = 0;
			lessonContent = getLesson(currentPage,currentLesson);
			break;

			//analogous to all the previous cases
		case 4:
			//set current lesson
			currentLesson = 4;
			currentPage = 0;
			lessonContent = getLesson(currentPage,currentLesson);
			break;

			//analogous to all the previous cases
		case 5:
			//set current lesson
			currentLesson = 5;
			currentPage = 0;
			lessonContent = getLesson(currentPage,currentLesson);
			break;

			//analogous to all the previous cases
		case 6:
			//set current lesson
			currentLesson = 6;
			currentPage = 0;
			lessonContent = getLesson(currentPage,currentLesson);
			break;

			//analogous to all the previous cases
		case 7:
			//set current lesson
			currentPage++;
			lessonContent = getLesson(currentPage,currentLesson);
			break;

			//if they want a quiz
		case 8:
			test = true;
			try {
				goToQuiz();
			} catch (FileNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			currentTab = 2;
			break;

			//returning from quiz
		case 55:
			//return from quiz
			userAns.clear();
			answers.clear();
			currentTab = 1;
			break;

			//user wants to login
		case 69:
			//might get a bad input
			try {
				login();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;

			//user wants to add a user file
		case 88:
			try {
				addUser();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		}

		//and now create the GUI
		try {
			createAndShowGUI();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	//ADD USER METHOD
	public void addUser() throws IOException
	{
		//ask for user input
		String name, pass;
		do
		{
			name = JOptionPane.showInputDialog(null,"New Username: ");
			pass = JOptionPane.showInputDialog(null,"Password: ");
			if ((name.indexOf("!")!=-1)||(pass.indexOf("!")!=-1))
			{
				JOptionPane.showMessageDialog(null,"Please do not use '!' in your username or password.");
			}
		} while ((name.indexOf("!")!=-1)||(pass.indexOf("!")!=-1));
		//read the existing files and appends
		String file = "C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/users.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
		//write the new line of user name into the file
		bw.newLine();
		bw.write(name+"!"+pass);
		bw.close();
	}

	//MAKE BUTTON METHOD (for the Lesson buttons)
	public JButton makeButton (JButton thisButton, JPanel card, boolean buttonWorks)
	{
		//create a button of size 170, 30
		JButton button = thisButton;
		button.setPreferredSize(new Dimension(170,30));
		Dimension size = button.getPreferredSize();
		//depending on the number of lessons, the position of the button will change
		button.setBounds(0+numButtons*170,0,size.width,size.height);
		//adds 1 to the global button counter
		numButtons++;
		//sets font and actionlistener
		button.setFont( new Font( "Dialog", Font.BOLD, 12 ) );
		button.addActionListener(this);
		//if we want the button to work (i.e., that the user has completed all the lessons beforehand
		if (buttonWorks)
		{
			//write text on it
			String but = ""+numButtons;
			if ((thisButton.getText()).equals("Quiz Time!"))
			{
				//name it an action command so that we know which button you pressed
				but = ""+(numButtons+1);
				button.setActionCommand(but);
			}
			//otherwise just make it a button
			else
			{
				button.setActionCommand(but);
			}
		}
		//if the button is not meant to work
		else
		{
			//make it invisible
			button.setForeground(new Color(128,64,0));
			button.setBackground(Color.BLACK);
		}
		return button;
	}

	//GET AN IMAGE SCALED TO THE DESIRED SIZE
	public BufferedImage getScaledImage(Image srcImg, int w, int h){
		//get the image
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		//draw a graphics
		Graphics2D g2 = resizedImg.createGraphics();
		//draw the new image
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		//need memory space so get rid of this graphics
		g2.dispose();
		return resizedImg;
	}

	//TEST UPDATE METHOD (for recording results of tests)
	public void testUpdate(String correct) throws IOException
	{
		//get the results in a list
		String file = "C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/test"+currentLesson+"results.txt";
		ArrayList<String> results = new ArrayList<>();
		String thisLine = "";
		//basically cycle through until you get the result of the user you want
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			Scanner kb = new Scanner(br);
			while(kb.hasNextLine())
			{
				thisLine = kb.nextLine();
				String[] thisUser = thisLine.split("!");
				if (thisUser[0].equals(user))
				{
					//result of the user you want
					results.add(user+"!"+(correct));
				}
				else
				{
					results.add(thisLine);
				}
			}

		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		int i = 1;
		while (i <= results.size())
		{
			bw.write(results.get(i-1));
			bw.newLine();
			i++;
		}
		bw.close();
	}

	//WHAT HAPPENS WHEN A LESSON IS FINISHED
	public void lessonDone() throws IOException
	{
		//first update lesson progress
		String progress = "C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/progress.txt";
		ArrayList<String> progresses = new ArrayList<>();
		String thisLine = "";
		//read the results and update the new one
		try {
			BufferedReader br = new BufferedReader(new FileReader(progress));
			Scanner kb = new Scanner(br);
			while(kb.hasNextLine())
			{
				thisLine = kb.nextLine();
				String[] thisUser = thisLine.split("!");
				int recordedLesson = Integer.parseInt(thisUser[1]);
				//user and recorded lessons match, then update it
				//also guards against the case when the user goes back to a previous lesson
				//by adding only if the user did the last lesson
				if (thisUser[0].equals(user) && recordedLesson < currentLesson)
				{
					progresses.add(user+"!"+(recordedLesson+1));
				}
				//if not this user is not the user we want and he gets passed
				else
				{
					progresses.add(thisLine);
				}
			}

		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//then write the progress arraylist into it
		BufferedWriter bw = new BufferedWriter(new FileWriter(progress));
		int i = 1;
		while (i <= progresses.size())
		{
			bw.write(progresses.get(i-1));
			bw.newLine();
			i++;
		}
		bw.close();
	}

	//PROCESSING THIS LESSON
	public int thisLesson ()
	{
		//don't know what lesson it is beforehand
		int theLesson = -1;
		String progress = "C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/progress.txt";
		try {
			//read in the lesson
			BufferedReader br = new BufferedReader(new FileReader(progress));
			Scanner kb = new Scanner(br);
			while(kb.hasNextLine())
			{
				String line = kb.nextLine();
				String[] thisUser = line.split("!");
				//get lesson progress!!!
				if (thisUser[0].equals(user))
				{
					theLesson = Integer.parseInt(thisUser[1]);
				}
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return theLesson;
	}

	//SHOW RESULTS ON THE RESULTS TAB
	public void showResults(JPanel card3) throws FileNotFoundException
	{
		//display the results in a table
		card3.setLayout(null);
		JLabel ques;
		//read the lessons from file
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/progress.txt"));
		Scanner kb = new Scanner(br);
		String line = kb.nextLine();
		String[] theUser = line.split("!");
		//split according to !
		int lastLesson = Integer.parseInt(theUser[1]);

		//each row
		for (int row = 0; row < 3; row++)
		{
			//if first row, then special treatment
			if (row == 0)
			{
				ques = new JLabel();
			}
			//if second row, special treatment
			else if (row == 1)
			{
				ques = new JLabel("Lesson");
			}
			//if third row, special treatment
			else
			{
				ques = new JLabel("Quiz");

			}
			//make each lesson box 100x20
			ques.setBounds(100,100+row*30,100,20);
			card3.add(ques);
			//for each column, write the relevant data for that user in that column
			for (int col = 0; col < 6; col++)
			{
				BufferedReader b = new BufferedReader(new FileReader("C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/test"+(col+1)+"results.txt"));
				Scanner k = new Scanner(b);
				String[] me = (k.nextLine()).split("!");
				JLabel answ;
				//outputs either whether the lesson gets done
				if (row == 0)
				{
					answ = new JLabel("Lesson "+(col+1));
				}
				//or outputs whether the quiz is completed
				else
				{
					if (row == 1)
					{
						if (col <= (lastLesson-1))
						{
							//green for finished quiz
							answ = new JLabel("<html><font color = green>Completed!</font></html");
						}
						else
						{
							//red for unfinished quiz
							answ = new JLabel("<html><font color = red>Not Done!</font></html>");
						}
					}
					else
					{
						if (col <= (lastLesson-1))
						{
							//green for score
							answ = new JLabel("<html><font color = green>"+me[1]+"/9</font></html>");
						}
						else
						{
							//red for score
							answ = new JLabel("<html><font color = red>Not Done!</html>");
						}
					}

				}
				answ.setBounds(100+(col+1)*150,100+row*30,100,20);
				card3.add(answ);
			}
		}
		//if done all lessons, shoutout!
		if (lastLesson == 6)
		{
			JLabel amazing = new JLabel("<html><h1><font color = blue>Congratulations on completing Rhetor v1.0!</font></h1></html>");
			amazing.setBounds(350,500,700,150);
			card3.add(amazing);
		}
	}

	//GO TO THE QUIZ!
	public void goToQuiz() throws FileNotFoundException
	{
		//print out the quiz on popup windows
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/test"+currentLesson+".txt"));
		Scanner kb = new Scanner(br);
		for (int i = 1; i <= 9; i++)
		{
			String[] line = kb.nextLine().split("!");
			Integer[] choices = {1,2,3,4};
			//all the choices are like multiple choice over integers
			int choice = (int) JOptionPane.showInputDialog(null,line[0]+"\n1. "+line[1]+"\n2. "+line[2]+"\n3. "+line[3]+"\n4. "+line[4],"Question "+i,JOptionPane.PLAIN_MESSAGE,null,choices,line[1]);
			userAns.add(choice);
			answers.add(Integer.parseInt(line[5]));
		}
	}

	//COMPUTE THE NUMBER OF QUESTIONS CORRECT
	public int numCorrect ()
	{
		int numCorrect = 0;
		//for each answer check for whether it is correct
		for (int i = 1; i <= userAns.size(); i++)
		{
			if (userAns.get(i-1)==answers.get(i-1))
			{
				numCorrect++;
			}
		}
		return numCorrect;
	}

	//DRAW COMPONENT
	public void addComponentToPane(Container pane) throws IOException {
		//make the tabs of the pane
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont( new Font( "Cambria", Font.BOLD|Font.ITALIC, 24 ) );
		//tabbedPane.setBackground(Color.white);
		//Lesson Card
		JPanel card1 = new JPanel() {
			public Dimension getPreferredSize() {
				Dimension size = super.getPreferredSize();
				size.width += extraWindowWidth;
				return size;
			}
		};
		card1.setBackground(Color.white);
		card1.setLayout(null);

		//creates button
		JButton button;
		//get the number of lesson it is
		int lessonNum = thisLesson();

		//create buttons
		button = new JButton("Lesson 1: Nouns");
		card1.add(makeButton(button,card1,(lessonNum>=0)));

		//determines if it is allowed to work because user didn't get there yet
		button = new JButton("Lesson 2: Verbs");
		if (lessonNum < 1)
		{
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
		}
		card1.add(makeButton(button,card1,(lessonNum>=1)));

		//determines if it is allowed to work because user didn't get there yet
		button = new JButton("Lesson 3: Adjectives");
		if (lessonNum < 2)
		{
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
		}
		card1.add(makeButton(button,card1, (lessonNum>=2)));

		//determines if it is allowed to work because user didn't get there yet
		button = new JButton("Lesson 4: Subjunctives");
		if (lessonNum < 3)
		{
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
		}
		card1.add(makeButton(button,card1,(lessonNum>=3)));

		//determines if it is allowed to work because user didn't get there yet
		button = new JButton("Lesson 5: Verbals");
		if (lessonNum < 4)
		{
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
		}
		card1.add(makeButton(button,card1,(lessonNum>=4)));

		//determines if it is allowed to work because user didn't get there yet
		button = new JButton("Lesson 6: Pronouns");
		if (lessonNum < 5)
		{
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
		}
		card1.add(makeButton(button,card1,(lessonNum>=5)));

		//if the user is logged in already
		if (user != null)
		{
			//make the next page button
			JButton next = new JButton("Next Page");
			try {
				//get the next lesson
				String filename = "C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/lesson"+currentLesson+"-"+(currentPage+2)+".txt";
				BufferedReader br = new BufferedReader(new FileReader(filename));
				//make the button
				card1.add(makeButton(next,card1,true));
			} catch (FileNotFoundException e2) {
				//quiz time!
				JButton quiz = new JButton("Quiz Time!");
				card1.add(makeButton(quiz,card1,true));
				lessonDone();
			}
		}

		//make the area for the lesson
		JEditorPane area = new JEditorPane();
		area.setEditable(false);
		//html file
		area.setContentType("text/html");
		area.setText(lessonContent);
		//make it scrollable too
		JScrollPane scroll = new JScrollPane(area);
		scroll.setBounds(765,70,1246-760,540);
		//make it visible
		area.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		card1.add(scroll);
		//get a random image to put on the left side just to spice things up
		BufferedImage before = ImageIO.read(new File("C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/"+(int)(Math.random()*5)+".jpg"));
		BufferedImage after = getScaledImage(before,510,650);
		JLabel picLabel = new JLabel(new ImageIcon(before));
		picLabel.setBounds(50,50,510,600);
		card1.add(picLabel);
		//make the tablet background
		BufferedImage aTablet = ImageIO.read(new File("C:/Users/JohnnyTang/Dropbox/Clubs/Classics/summative/tablet.jpg"));
		BufferedImage tablet = getScaledImage(aTablet,1366,700);
		JLabel thisPic = new JLabel(new ImageIcon(tablet));
		thisPic.setBounds(0,0,1366,700);
		card1.add(thisPic);

		//Review Card
		//make it here as opposed to anywhere else because it prevents any cross-panel errors
		GridBagConstraints c = new GridBagConstraints();
		JPanel card2 = new JPanel() {
			public Dimension getPreferredSize() {
				Dimension size = super.getPreferredSize();
				size.width += extraWindowWidth;
				return size;
			}
		};
		card2.setLayout(null);
		//if the lesson has not been selected
		if (currentLesson == 0)
		{
			//if the lesson is not selected, output a reminder message
			JLabel not = new JLabel("Please select a lesson from the Lesson page!");
			not.setBounds(610,30,300,100);
			card2.add(not);
		}
		//otherwise get the number of corrects or incorrects
		String cor = ""+numCorrect();
		JLabel correct = new JLabel("Total Score: "+cor);
		//decipher each user answer choice
		for (int i = 1; i <= userAns.size(); i++)
		{
			String yes = "CORRECT";
			//is it right?
			if (userAns.get(i-1)==answers.get(i-1))
			{
				yes = "CORRECT";
			}
			//is it wrong?
			else
			{
				yes = "WRONG ("+answers.get(i-1)+")";
			}
			JLabel mark;
			//if correct, output green text
			if (yes.equals("CORRECT"))
			{
				mark = new JLabel("<html>Question: "+i+" <font color=green>"+yes+"</font></html>");
			}
			//otherwise, red text
			else
			{
				mark = new JLabel("<html>Question: <font color=red>"+i+" "+yes+"</font></html>");
			}
			mark.setBounds(610,30+30*i,300,100);
			card2.add(mark);
			testUpdate(cor);
		}
		card2.add(correct);
		//go back to the last lesson used
		JButton returnLesson = new JButton("Return to Lesson Page");
		returnLesson.setActionCommand("55");
		returnLesson.addActionListener(this);
		returnLesson.setBounds(610,60+30*10,180,40);
		card2.add(returnLesson);

		//progress update
		JPanel card3 = new JPanel();
		showResults(card3);

		//login pane
		JPanel card4 = new JPanel();

		//add all the cards into the tabbedPane
		tabbedPane.addTab("Home Page", card4);
		tabbedPane.addTab(Lessons, card1);
		tabbedPane.addTab(Review, card2);
		tabbedPane.addTab("Progress Report", card3);

		//if the user is null that means not yet logged in
		if (user == null)
		{
			JButton login = new JButton("Login");
			login.setActionCommand("69");
			login.addActionListener(this);
			card4.add(login);
			//adding users
			JButton addUser = new JButton("Add User");
			addUser.setActionCommand("88");
			addUser.addActionListener(this);
			card4.add(addUser);
		}
		//otherwise logged in, display message and set to currentTab
		else 
		{
			JLabel welcome = new JLabel("Welcome, "+user);
			card4.add(welcome);
			if (!test)
				tabbedPane.setSelectedIndex(1);
			else
				tabbedPane.setSelectedIndex(currentTab);
		}
		pane.add(tabbedPane, BorderLayout.CENTER);
	}
}
