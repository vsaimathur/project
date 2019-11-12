package Logic;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.applet.*;
import javax.swing.*;
public class Question implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static FileOutputStream fos;
	public static int evaluator(ArrayList	<Question>a,ArrayList <Integer>selected,String path,String studentinfo[],String examname) {
		int marks=0;
		path=path.replace("\\", "/");
		String[] pathsplit=path.split("/");
		path="";
		for(int i1=0;i1<pathsplit.length-1;i1++) {
			path=path+pathsplit[i1]+"\\";
		}
		path=path+examname+".csv";
		try
		{
			FileWriter fw = new FileWriter(path,true);
			BufferedWriter writer = new BufferedWriter(fw);
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<a.size();i++)
			{
				if(selected.get(i)!=0) {
					if(a.get(i).correct_option==selected.get(i)) {
						marks+=a.get(i).posmark;
						sb.append(String.valueOf(a.get(i).posmark));
						sb.append(",");
						
					}
					else 
					{
						marks-=java.lang.Math.abs(a.get(i).negmark);
						sb.append(String.valueOf(java.lang.Math.abs(a.get(i).negmark)));
						sb.append(",");
					}
				}else
				{
					sb.append("0");
					sb.append(",");
				}
			}
			System.out.println("done!");
			sb.append(String.valueOf(marks));
			sb.append("\n");
			writer.write(sb.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}
	public Question(String question,String option1,String option2,String option3,String option4,int correct_option,int posmark,int negmark){
		this.question=question;
		this.option1=option1;
		this.option2=option2;
		this.option3=option3;
		this.option4=option4;
		this.correct_option=correct_option;
		this.posmark=posmark;
		this.negmark=negmark;
	}
	public int correct_option;
	public int posmark;
	public int negmark;
	public String question;
	public String option1;
	public String option2;
	public String option3;
	public String option4;
	public JRadioButton jb1 = new JRadioButton();
	public JRadioButton jb2 = new JRadioButton();
	public JRadioButton jb3 = new JRadioButton();
	public JRadioButton jb4 = new JRadioButton();
}
