/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Logic.Question;

/**
 * @author suhru
 *
 */
public class QuizWriterGUI implements ActionListener {
	Thread colourcheck;
	ArrayList <Question>list=null;
	ArrayList <JButton>questionbuttons = new ArrayList();
	ArrayList<Integer>responces=new ArrayList();
	ArrayList <Boolean>visited=new ArrayList();
	ArrayList<Boolean> reviewmark=new ArrayList();
	JLabel posmark= new JLabel("Positive Marks"); 
	JLabel negmark= new JLabel("Negative Marks");
	String studentname,image;
	NewJPanel question=null;
	JPanel	questionpanel;
	JFrame quizwrite,settingup;
	String userdetails[]=null,examname=null;
	int timeremaining=10800;
	int index=0;
	JButton clear=new JButton("Clear"),button1=new JButton("open"),button2=new JButton("open"),markforreview=new JButton("Mark for review and next"),next=new JButton("Next"),prev= new JButton("Previous");
	Thread t=null;
	JTextField exam=new JTextField(20),time=new JTextField(20),quizpap=new JTextField(30),studentdat=new JTextField(30);
	JButton submit,submitexam=new JButton("Submit"),calc=new JButton("Calculator");
	QuizWriterGUI(){

	}
	public static void main(String[] args) {
		new QuizWriterGUI().setup();
	}
	public void setup() {
		/*Setup GUI Part*/
		this.settingup=new JFrame("Exam Details");
		this.settingup.setSize(562, 232);
		this.settingup.isResizable();
		JPanel setup=new JPanel();
		JPanel examname=new JPanel(),examtime=new JPanel(),questionfile=new JPanel(),studentpanel=new JPanel();
		// add action listener to the button to capture user 
		// response on buttons 
		button1.addActionListener(this); 
		button2.addActionListener(this);
		examname.add(new JLabel("Name of the exam: "));
		examname.add(exam);
		examtime.add(new JLabel("Exam Time(in min):"));
		examtime.add(time);
		questionfile.add(new JLabel("Question file: "));
		questionfile.add(this.quizpap);
		questionfile.add(button1);
		studentpanel.add(new JLabel("Student Database"));
		studentpanel.add(this.studentdat);
		studentpanel.add(button2);
		setup.add(examname);
		setup.add(examtime);
		setup.add(questionfile);
		setup.add(studentpanel);
		submit=new JButton("submit");
		submit.addActionListener(this);
		//		setup.add(submit);
		setup.add(submit);
		settingup.add(setup);
		settingup.setResizable(false);
		settingup.setVisible(true);
	}
	//Exam is exam name
	public void quizwriter(String examname,String username, String[] user) {
		FileInputStream fin;
		try
		{
			fin = new FileInputStream(quizpap.getText());
			ObjectInputStream ois = new ObjectInputStream(fin);
			list = (ArrayList)ois.readObject();	
		}
		catch(Exception e){}
		this.examname=examname;
		this.userdetails=user;
		ImageIcon icon = new ImageIcon(user[3]);
		Image scaleImage = icon.getImage().getScaledInstance(95, 106,Image.SCALE_DEFAULT);
		icon.setImage(scaleImage);
		//icon=(ImageIcon) scaleImage;
		quizwrite=new JFrame();
		questionpanel=new JPanel(new BorderLayout());
		int n=90;
		JPanel sidepanel= new JPanel();
		sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));
		questionpanel.setLayout(new GridLayout(n/3,4));
		for(int i=0;i<list.size();i++) {
			JButton a=new JButton(String.valueOf(i+1));
			a.setText(String.valueOf(i+1));
			a.setBackground(Color.white);
			a.addActionListener(this);
			responces.add(0);
			questionbuttons.add(a);
			questionpanel.add(a);
			visited.add(false);
			reviewmark.add(false);
		}
		int hrs=timeremaining/3600;
		int min=hrs%3600;
		int sec=min%60;
		JLabel timerem=new JLabel("Time remaining:"+String.valueOf(hrs)+" : "+String.valueOf(min)+" : "+String.valueOf(sec));
		timerem.setFont(timerem.getFont().deriveFont (22.0f));
		sidepanel.add(questionpanel);
		JScrollPane pane=new JScrollPane(questionpanel);
		JScrollPane sidepanelpane = new JScrollPane(sidepanel);
		//		sidepanelpane.setHorizontalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		quizwrite.add(sidepanelpane);
		sidepanel.add(timerem);
		JPanel studentinfopanel=new JPanel();
		JPanel studentinfopanel1=new JPanel();
		studentinfopanel.setLayout(new FlowLayout());
		studentinfopanel.add(new JLabel(icon));
		studentinfopanel1.setLayout(new BoxLayout(studentinfopanel1,BoxLayout.Y_AXIS));
		studentinfopanel1.add(new JLabel("User: "+username));
		studentinfopanel1.add(new JLabel("Name:"+user[1]));
		studentinfopanel.add(studentinfopanel1);
		sidepanel.add(studentinfopanel);
		sidepanel.add(questionpanel);
		sidepanel.add(submitexam);
		//sidepanel.setSize(250,1000);
		quizwrite.add(sidepanel,BorderLayout.EAST);
		JPanel Examtitlepanel=new JPanel();
		JPanel examtitle=new JPanel();
		JLabel examn=new JLabel(examname);
		examtitle.setLayout(new BorderLayout());
		examn.setFont (examn.getFont().deriveFont (18.0f));
		int red = 0;
		int green = 0;
		int blue = 244;
		Color myBlue = new Color(red,green,blue);
		examtitle.setBackground(myBlue);
		examtitle.add(examn,BorderLayout.CENTER);
		examtitle.setBackground(Color.RED);
		JPanel timerpan=new JPanel();
		timerpan.setLayout(new FlowLayout());
		calc.addActionListener(this);
		timerpan.add(calc);
		timerpan.add(this.posmark);
		timerpan.add(this.negmark);
		//timerpan.add(timerem);
		//quizwrite.add(examtitle,BorderLayout.NORTH);
		//quizwrite.add(pane,BorderLayout.WEST);
		Examtitlepanel.add(examtitle);
		Examtitlepanel.add(timerpan);
		//		Examtitlepanel.add(new JLabel("Exam1"));
		quizwrite.add(Examtitlepanel,BorderLayout.NORTH);
		question=new NewJPanel();
		question.jToggleButton1.setVisible(false);
		quizwrite.add(question);
		quizwrite.setVisible(true);
		JPanel downpanel=new JPanel();
		Examtitlepanel.setLayout(new BoxLayout(Examtitlepanel,BoxLayout.Y_AXIS));
		colourcheck= new Thread() {
			public void run() {
				while(true) {
					for(int i=0;i<list.size();i++) {
						Color color=Color.WHITE;
						Color color1=Color.BLACK;
						if(visited.get(i)) {
							if(!reviewmark.get(i)) {
								if(responces.get(i)!=0) {
									color=color.GREEN;
								}
								else {
									color=color.RED;
								}
							}
							else{
								if(responces.get(i)!=0) {
									color=color.PINK;
									color1=color1.GREEN;
								}
								else {
									color=color.PINK;
									color1=color1.RED;
								}
							}								
						}
						questionbuttons.get(i).setBackground(color);
						questionbuttons.get(i).setForeground(color1);
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		colourcheck.start();
		t=new Thread() {
			public void run() {
				while(true) {
					timeremaining-=1;
					//SwingUtilities.updateComponentTreeUI(quizwrite);
					quizwrite.repaint();
					quizwrite.revalidate();
					int hrs=timeremaining/3600;
					int min=(timeremaining%3600)/60;
					int sec=timeremaining%60;
					timerem.setText("Time remaining:"+twodigits(hrs)+" : "+twodigits(min)+" : "+twodigits(sec));
					int warningtime=timeremaining/36;
					if(timeremaining<300)
					{

						if(timeremaining%2==0) {
							timerem.setForeground(Color.RED);
							//questionpanel.setVisible(true);
						}
						else {
							timerem.setForeground(Color.BLACK);
							//questionpanel.setVisible(false);	
						}

					}
					//System.out.println(timeremaining);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(timeremaining==0) {
						QuizWriterGUI.submitexam(quizwrite);
						break;
					}
				}
			}
		};
		t.start();
		//JPanel _2buttons=new JPanel();
		//markforreview
		//_2buttons.add();
		downpanel.setLayout(new FlowLayout());
		downpanel.add(prev);
		downpanel.add(clear);
		JPanel nextandreview=new JPanel();
		nextandreview.setLayout(new FlowLayout());
		//		nextandreview.add(clear);
		nextandreview.add(submitexam);
		nextandreview.add(next);
		nextandreview.add(markforreview);
		prev.addActionListener(this);
		next.addActionListener(this);
		markforreview.addActionListener(this);
		submitexam.addActionListener(this);
		clear.addActionListener(this);
		JPanel downpanel1=new JPanel();
		downpanel1.setLayout(new BorderLayout());
		downpanel1.add(nextandreview,BorderLayout.EAST);
		downpanel1.add(downpanel,BorderLayout.WEST);
		quizwrite.add(downpanel1,BorderLayout.SOUTH);
		quizwrite.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//quizwrite.setWindowDecorationStyle(JRootPane.NONE);
		Question temp=list.get(index);
		question.jTextArea1.setText(temp.question);
		question.jTextArea5.setText(temp.option1);
		question.jTextArea9.setText(temp.option2);
		question.jTextArea6.setText(temp.option3);
		question.jTextArea8.setText(temp.option4);
	}
	public String twodigits(int i) {
		if(i<10)
			return "0"+i;
		else
			return i+"";
	}
	public void actionPerformed(ActionEvent a){
		//		System.out.println(responces);
		JButton button=(JButton)a.getSource();
		if (a.getSource()==button1) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			j.setAcceptAllFileFilterUsed(false); 
			j.setDialogTitle("Select a .txt file"); 
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt"); 
			j.addChoosableFileFilter(restrict); 
			int r = j.showSaveDialog(null); 
			if (r == JFileChooser.APPROVE_OPTION) 

			{ 
				quizpap.setText(j.getSelectedFile().getAbsolutePath()); 
			}  
			else
				quizpap.setText("the user cancelled the operation"); 
		}
		else if (a.getSource()==button2) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			j.setAcceptAllFileFilterUsed(false); 
			j.setDialogTitle("Select a .csv file"); 
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .csv files", "csv"); 
			j.addChoosableFileFilter(restrict); 
			int r = j.showSaveDialog(null); 
			if (r == JFileChooser.APPROVE_OPTION) 

			{ 
				studentdat.setText(j.getSelectedFile().getAbsolutePath()); 
			} 
			else
				studentdat.setText("the user cancelled the operation"); 
		}

		else if(a.getSource()==submit) {
			//this.quizwriter(exam.getText());
			while(true) {
				try{
					System.out.println(this.studentdat.getText());
					this.timeremaining=60*Integer.parseInt(time.getText());
					new StudentLogin(this.studentdat.getText(),this.exam.getText(),this).Login();
					this.settingup.setVisible(false);
					return;
				}catch(Exception e) {return;}
			}

		}
		else if(a.getSource()== calc) {
			calgrid.main(null);
		}
		else if(a.getSource() == prev) {
			//4 3 1 2== 1 2 3 4
			reviewmark.set(index, false);
			int choosenone=choosenoption();
			try {

			}catch(Exception e) {
				return;
			}
			try {
				responces.set(index,choosenone);
			}catch(Exception e){
				responces.add(choosenone);
			}
			if(index>0) {
				visited.set(index,true);
				index--;
				question.buttonGroup1.clearSelection();
				questionchanger();
			}
		}
		else if(a.getSource()== next) {
			reviewmark.set(index, false);
			visited.set(index,true);
			if(index<list.size()-1) {
				nextques();
			}
		}
		else if(a.getSource()== markforreview) {
			reviewmark.set(index, true);
			visited.set(index,true);
			if(index<list.size()-1) {
				nextques();
			}
		}
		else if(a.getSource()== clear)
		{
			responces.set(index, 0);
			question.buttonGroup1.clearSelection();
		}
		else if(a.getSource()== submitexam) {
			responces.set(index,choosenoption());
			int dialogResult = JOptionPane.showConfirmDialog (null, "Would You like to end the test","Warning",JOptionPane.INFORMATION_MESSAGE);
			if(dialogResult == JOptionPane.YES_OPTION){
				// Saving code here
				int score=Question.evaluator(list, responces, this.quizpap.getText(), this.userdetails,examname);
				System.out.println("Score:"+score);
				this.quizwrite.setVisible(false);
				new FinalScore(examname,score);
			}
			else {
				return;
			}
		}
		else if(Integer.parseInt(button.getText())-1<list.size()) {
			reviewmark.set(index, false);
			visited.set(index,true);
			responces.set(index,choosenoption());
			index=Integer.parseInt(button.getText())-1;
			questionchanger();
		}
	}
	final static void submitexam(JFrame a) {
		a.setVisible(false);
	}
	public void nextques() {
		if(index<list.size()) {
			int choosenone=choosenoption();
			try {
				responces.set(index,choosenone);
			}catch(Exception e){
				responces.add(index,choosenone);
			}
			index++;
			questionchanger();
		}
	}
	public void questionchanger() {
		Question temp=list.get(index);
		question.jTextArea1.setText(temp.question);
		question.jTextArea5.setText(temp.option1);
		question.jTextArea9.setText(temp.option2);
		question.jTextArea6.setText(temp.option3);
		question.jTextArea8.setText(temp.option4);
		this.posmark.setText("Marks for correct answer: "+temp.posmark);
		this.negmark.setText("Marks for Wrong answer: -"+temp.negmark);
		int choosentemp=responces.get(index);
		if(choosentemp==1)
			question.jRadioButton4.setSelected(true);
		else if(choosentemp==2)
			question.jRadioButton3.setSelected(true);
		else if(choosentemp==3)
			question.jRadioButton1.setSelected(true);
		else if(choosentemp==4)
			question.jRadioButton2.setSelected(true);
		else 
			question.buttonGroup1.clearSelection();
	}
	public int choosenoption() {
		int choosenone=0;
		if(question.jRadioButton1.isSelected()) {
			choosenone=3;
		}
		else if(question.jRadioButton2.isSelected()) {
			choosenone=4;
		}
		else if(question.jRadioButton3.isSelected()) {
			choosenone=2;
		}
		else if(question.jRadioButton4.isSelected()) {
			choosenone=1;
		}
		return choosenone;
	}
}