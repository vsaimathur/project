/**
 * 
 */
package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author suhru
 *
 */
public class StudentLogin implements ActionListener{
	private FileReader fis=null;
	private BufferedReader br=null;
	String examname=null;
	QuizWriterGUI quiz=null;
	JButton submit=null;
	JFrame login=null;
	private String path;
	StudentLogin(String path,String examname,QuizWriterGUI quiz){
		this.examname=examname;
		this.quiz=quiz;
		try {
			this.path=path;
			this.fis=new FileReader(path);
			this.br=new BufferedReader(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	JTextField username=new JTextField(20);
	JPasswordField password=new JPasswordField(20);
	public void Login(){
		this.login=new JFrame();
		JPanel loginpanel=new JPanel();
		loginpanel.setLayout(new FlowLayout());
		JPanel usernamepanel=new JPanel();
		usernamepanel.setLayout(new FlowLayout());
		JPanel passwordpanel=new JPanel();
		passwordpanel.setLayout(new FlowLayout());
		usernamepanel.add(new JLabel("Username or Rollno"));
		usernamepanel.add(this.username);
		passwordpanel.add(new JLabel("Password:"));
		passwordpanel.add(this.password);
		this.submit=new JButton("submit");
		this.submit.addActionListener(this);
		loginpanel.add(usernamepanel);
		loginpanel.add(passwordpanel);
		loginpanel.add(submit);
		login.add(loginpanel);
		login.setVisible(true);
		login.setSize(500, 500);
	}
	private String[] validator(String username,char[] cs){
		String userdetails=null;
		try {
			userdetails = this.br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i=0;
		while(true) {
			try {
				this.fis=new FileReader(path);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.br=new BufferedReader(fis);
			while(userdetails!=null)
				try {
					String[] a=userdetails.split(",");
					System.out.println(a[0]+","+a[1]+","+a[2]+" "+a[3]);
					if(a[0].equals(username) && a[2].equals(new String(cs))){
						System.out.println("IF conition");
						return a;
					}
					userdetails=this.br.readLine();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			return null;
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource()==this.submit)
		{
			String[] user=this.validator(this.username.getText(), this.password.getPassword());
			if(user!=null) {
				quiz.quizwriter(this.examname,this.username.getText(),user);
				this.login.setVisible(false);
			}
		}
	}
}
