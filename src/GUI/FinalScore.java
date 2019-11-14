/**
 * 
 */
package GUI;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.*;
/**
 * @author suhru
 *
 */
public class FinalScore extends JFrame{
	JLabel score,Examname;
	FinalScore(String Examname,int score){
		this.score=new JLabel("Your Score is :"+String.valueOf(score));
		this.Examname=new JLabel(Examname);
		display();
	}
	public static void main(String args[]){
		new FinalScore("HEllo",200);
	}
	void display() {
		JPanel a=new JPanel();
		Examname.setFont (Examname.getFont().deriveFont (24.0f));
		JLabel thanks=new JLabel("Thanks for writing the exam");
		thanks.setFont (thanks.getFont().deriveFont (24.0f));
		score.setFont (score.getFont().deriveFont (24.0f));
		a.setLayout(new BorderLayout());
		add(Examname,BorderLayout.NORTH);
		a.add(thanks,BorderLayout.NORTH);
		a.add(score,BorderLayout.CENTER);
		ImageIcon icon = new ImageIcon("C:\\Users\\suhru\\OneDrive\\Desktop\\Image\\bqf-3029_1_.jpg");
		//Image scaleImage = icon.getImage().getScaledInstance(95, 106,Image.SCALE_DEFAULT);
		//icon.setImage(scaleImage);
		add(new JLabel(icon),BorderLayout.SOUTH);
		add(a,BorderLayout.CENTER);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
}
