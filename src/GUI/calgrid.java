package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class calgrid implements ActionListener
{
	
	JButton b0;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,a,s,m,d,sub;
	JTextField t;
	JLabel l1;
	Font f;
	double c,b,res;
	int op;
	public static void main(String args[]) {
		new calgrid().calc();
	}
	public void calc() {
		JFrame frame=new JFrame("Calculator");
		l1 = new JLabel(); 
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		a = new JButton("+");
		s = new JButton("-");
		m = new JButton("*");
		d = new JButton("/");
		f = new Font("Elephant",Font.ITALIC,28);
		sub = new JButton("SUBMIT");
		t = new JTextField(20);
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		a.addActionListener(this);
		s.addActionListener(this);
		m.addActionListener(this);
		d.addActionListener(this);
		sub.addActionListener(this);
		JPanel grid=new JPanel(new GridLayout(3,4));
		grid.add(s);
		grid.add(b7);
		grid.add(b8);
		grid.add(b9);
		grid.add(a);
		grid.add(b4);
		grid.add(b5);
		grid.add(b6);
		grid.add(d);
		grid.add(b1);
		grid.add(b2);
		grid.add(b3);
		JPanel zerosubmit = new JPanel();
		zerosubmit.add(m);
		zerosubmit.add(b0);
		JPanel downpannel=new JPanel();
		downpannel.add(zerosubmit);
		downpannel.add(sub);
		downpannel.setLayout(new GridLayout());
		zerosubmit.setLayout(new GridLayout(1,3));
		JPanel numpan=new JPanel();
		numpan.add(grid);
		numpan.add(downpannel);
		numpan.setLayout(new BoxLayout(numpan,BoxLayout.Y_AXIS));
		frame.add(numpan);
		frame.add(t,BorderLayout.NORTH);
		frame.setSize(250,300);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == b0)
			t.setText(t.getText().concat("0"));
		if(ae.getSource() == b1)
			t.setText(t.getText().concat("1"));
		if(ae.getSource() == b2)
			t.setText(t.getText().concat("2"));
		if(ae.getSource() == b3)
			t.setText(t.getText().concat("3"));
		if(ae.getSource() == b4)
			t.setText(t.getText().concat("4"));
		if(ae.getSource() == b5)
			t.setText(t.getText().concat("5"));
		if(ae.getSource() == b6)
			t.setText(t.getText().concat("6"));
		if(ae.getSource() == b7)
			t.setText(t.getText().concat("7"));
		if(ae.getSource() == b8)
			t.setText(t.getText().concat("8"));
		if(ae.getSource() == b9)
			t.setText(t.getText().concat("9"));
		if(ae.getSource() == a)
		{
			b=Double.parseDouble(t.getText());
			op=1;
			t.setText("");
		}
		JButton somebutton=(JButton) ae.getSource();
		b0.setName("Good");
		System.out.println(somebutton.getName());
		if(ae.getSource() == s)
		{
			b=Double.parseDouble(t.getText());
			op=2;
			t.setText("");
		}
		if(ae.getSource() == m)
		{
			b=Double.parseDouble(t.getText());
			op=3;
			t.setText("");
		}
		if(ae.getSource() == d)
		{
			b=Double.parseDouble(t.getText());
			op=4;
			t.setText("");
		}
		
		if(ae.getSource() == sub)
		{
			c = Double.parseDouble(t.getText());
			switch(op)
			{
				
				case 1:
						res = c+b;
						break;
				case 2:
						res = b-c;
						break;
				case 3:
						res = b*c;
						break;
				case 4:
						res = b/c;
						break;
				default:
					   res=0;
			}
			t.setText(res+"");
		}
	}
}