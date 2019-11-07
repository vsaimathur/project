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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * @author suhru
 *
 */
public class QuizWriterGUI implements ActionListener {
	JFrame quizwrite,settingup;
	int timeremaining=10800;
	JButton button1=new JButton("open"),button2=new JButton("open"),markforreview=new JButton("Mark for review and next"),next=new JButton("Next"),prev= new JButton("Previous");
	Thread t=null;
	JTextField exam=new JTextField(20),time=new JTextField(20),quizpap=new JTextField(50),studentdat=new JTextField(50);
	JButton submit,submitexam=new JButton("Submit"),calc=new JButton("Calculator");
	QuizWriterGUI(){

	}
	public static void main(String[] args) {
		new QuizWriterGUI().setup();
	}
	public void setup() {
		/*Setup GUI Part*/
		this.settingup=new JFrame();
		JPanel setup=new JPanel();
        JPanel examname=new JPanel(),examtime=new JPanel(),questionfile=new JPanel(),studentpanel=new JPanel();
        // add action listener to the button to capture user 
        // response on buttons 
        button1.addActionListener(this); 
        button2.addActionListener(this);
        
		setup.setLayout(new BoxLayout(setup,BoxLayout.Y_AXIS));
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
		setup.add(submit);
		settingup.add(setup);
		settingup.setVisible(true);
		setup.setPreferredSize(new Dimension(1000,1000));
	}
	//Exam is exam name
	public void quizwriter(String examname,String username) {
		quizwrite=new JFrame();
		JPanel	questionpanel=new JPanel(new BorderLayout());
		int n=90;
		JPanel sidepanel= new JPanel();
		sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));
		questionpanel.setLayout(new GridLayout(n/3,4));
		
		for(int i=0;i<12;i++) {
			JButton a=new JButton(String.valueOf(i+1));
			a.setBackground(Color.white);
			questionpanel.add(a);
		}
		int hrs=timeremaining/3600;
		int min=hrs%3600;
		int sec=min%60;
		JLabel timerem=new JLabel("Time remaining:"+String.valueOf(hrs)+" : "+String.valueOf(min)+" : "+String.valueOf(sec));
		timerem.setFont(timerem.getFont().deriveFont (22.0f));
		sidepanel.add(questionpanel);
		JScrollPane pane=new JScrollPane(questionpanel);
		sidepanel.add(timerem);
		sidepanel.add(questionpanel);
		sidepanel.add(submitexam);
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
		//timerpan.add(timerem);
		//quizwrite.add(examtitle,BorderLayout.NORTH);
		//quizwrite.add(pane,BorderLayout.WEST);
		Examtitlepanel.setLayout(new BoxLayout(Examtitlepanel,BoxLayout.Y_AXIS));
		Examtitlepanel.add(examtitle);
		Examtitlepanel.add(timerpan);
		quizwrite.add(Examtitlepanel,BorderLayout.NORTH);
		JPanel question=new JPanel();
		question.setBackground(Color.WHITE);
		JLabel questionq=new JLabel("Q1)Mathur is the biggest idiot in the world?True or False.");
		question.setLayout(new BoxLayout(question,BoxLayout.Y_AXIS));
		questionq.setFont (examn.getFont().deriveFont (26.0f));
		question.add(questionq);
		JRadioButton a=new JRadioButton("Option 1");
		JRadioButton b=new JRadioButton("Option 2");
		JRadioButton c=new JRadioButton("Option 3");
		JRadioButton d=new JRadioButton("Option 4");
		ButtonGroup options=new ButtonGroup();
		options.add(a);
		options.add(b);
		options.add(c);
		options.add(d);
		a.setFont(a.getFont().deriveFont(26.0f));
		b.setFont(b.getFont().deriveFont(26.0f));
		c.setFont(c.getFont().deriveFont(26.0f));
		d.setFont(d.getFont().deriveFont(26.0f));
		question.add(a);
		question.add(b);
		question.add(c);
		question.add(d);
		examn.setFont (examn.getFont().deriveFont (18.0f));
		quizwrite.add(question);
		quizwrite.setVisible(true);
		JPanel downpanel=new JPanel();
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
					timerem.setText("Time remaining:"+String.valueOf(hrs)+" : "+String.valueOf(min)+" : "+String.valueOf(sec));
					int warningtime=timeremaining/36;
					if(timeremaining<=300)
					{
						if(timeremaining%2==0)
							timerem.setForeground(Color.RED);
						else
							timerem.setForeground(Color.BLACK);
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
		downpanel.setLayout(new BorderLayout());
		downpanel.add(prev);
		JPanel nextandreview=new JPanel();
		nextandreview.setLayout(new FlowLayout());
		nextandreview.add(markforreview);
		nextandreview.add(next);
		downpanel.add(nextandreview,BorderLayout.EAST);
		downpanel.add(prev,BorderLayout.WEST);
		quizwrite.add(downpanel,BorderLayout.SOUTH);
		quizwrite.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//quizwrite.setWindowDecorationStyle(JRootPane.NONE);

	}
	public void actionPerformed(ActionEvent a) {
		if (a.getSource()==button1) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			  
            // resctrict the user to selec files of all types 
            j.setAcceptAllFileFilterUsed(false); 
  
            // set a title for the dialog 
            j.setDialogTitle("Select a .txt file"); 
  
            // only allow files of .txt extension 
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt"); 
            j.addChoosableFileFilter(restrict); 
  
            // invoke the showsSaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null); 
  
            // if the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION) 
  
            { 
                // set the label to the path of the selected file 
                quizpap.setText(j.getSelectedFile().getAbsolutePath()); 
            } 
            // if the user cancelled the operation 
            else
                quizpap.setText("the user cancelled the operation"); 
		}
		else if (a.getSource()==button2) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			  
            // resctrict the user to selec files of all types 
            j.setAcceptAllFileFilterUsed(false); 
  
            // set a title for the dialog 
            j.setDialogTitle("Select a .csv file"); 
  
            // only allow files of .txt extension 
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .csv files", "csv"); 
            j.addChoosableFileFilter(restrict); 
  
            // invoke the showsSaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null); 
  
            // if the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION) 
  
            { 
                // set the label to the path of the selected file 
                studentdat.setText(j.getSelectedFile().getAbsolutePath()); 
            } 
            // if the user cancelled the operation 
            else
                studentdat.setText("the user cancelled the operation"); 
		}

		else if(a.getSource()==submit) {
			//this.quizwriter(exam.getText());
			new StudentLogin(this.studentdat.getText(),this.exam.getText(),this).Login();
			this.settingup.setVisible(false);
		}
		else if(a.getSource()== calc) {
			calgrid.main(null);
		}
	}
	final static void submitexam(JFrame a) {
		a.setVisible(false);
	}
}
