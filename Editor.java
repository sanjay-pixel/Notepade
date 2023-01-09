import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

class Editor  implements ActionListener
{
	Frame f,f1,Find,Find_replace;	MenuBar mb;	Menu m1,m2,m3;
	MenuItem nw,opn,ext,find,find_r,sve,sve_as; TextArea t;
	String str,str1,name;	boolean saveFile;
	FileDialog fd,fd1,fd2;		int ch,sIndex,lIndex;
	TextField t1,t2,t3;	Pattern p;	Matcher m;	 int num,num1;
	public Editor()
	{
		f=new Frame();	f.setSize(600,500); t=new TextArea();
		f1=new Frame();
		f1.setSize(300,200);

		fd=new FileDialog(f1,"Open",FileDialog.LOAD);
		fd1=new FileDialog(f1,"Save",FileDialog.SAVE);
		fd2=new FileDialog(f1,"Save As",FileDialog.SAVE);

		mb=new MenuBar();
		m1=new Menu("File");
		m2=new Menu("Edit");
		
		nw=new MenuItem("New");
		opn=new MenuItem("Open");
		sve=new MenuItem("Save");
		sve_as=new MenuItem("Save As");
		ext=new MenuItem("Exit");
		find=new MenuItem("Find");
		find_r=new MenuItem("Find and Replace");
		nw.addActionListener(this);
		opn.addActionListener(this);
		sve.addActionListener(this);
		sve_as.addActionListener(this);
		ext.addActionListener(this);
		find.addActionListener(this);
		find_r.addActionListener(this);

		WindowCloser wc=new WindowCloser();
		f.addWindowListener(wc);

		m2.add(find);	m2.add(find_r);
		m1.add(nw);	m1.add(opn);	m1.add(sve);	m1.add(sve_as);	
		m1.addSeparator();	m1.add(ext);
		mb.add(m1);	mb.add(m2);
		f.setMenuBar(mb);	f.add(t);
		f.setVisible(true);
	}
		


	public void actionPerformed(ActionEvent e) 
	{
		str=e.getActionCommand();

		if(str.equals("New"))
		{
			t.setText("");
			saveFile=false;
		}
		if(str.equals("Open"))
		{
			fd.setVisible(true);
			name=fd.getFile();
			File fi=new File(name);
			try
			{
			FileInputStream fis=new FileInputStream(fi);
			DataInputStream dis=new DataInputStream(fis);
			t.setText("");
			while((str1=dis.readLine())!=null)
			 {
				t.appendText(str1+"\n");
			 }
				dis.close();
			}
			catch(IOException e2)
			{
				t.setText(e2.getMessage());
			}
		}
		if(str.equals("Save"))
		{
			if(saveFile==false)
			{
				fd1.setVisible(true);
			  	name=fd1.getFile();
				saveFile=true;
			}
			File fo=new File(name);
			try
			{
				FileOutputStream fos=new FileOutputStream(fo);
				DataOutputStream dos=new DataOutputStream(fos);
				str1=t.getText();
				dos.writeBytes(str1);
				dos.close();
			}
			catch(IOException e2)
			{
				t.setText(e2.getMessage());
			}		
		}
		if(str.equals("Save As"))
		{
			fd2.setVisible(true);
			name=fd2.getFile();
			File fo=new File(name);
			try
			{
				FileOutputStream fos=new FileOutputStream(fo);
				DataOutputStream dos=new DataOutputStream(fos);
				str1=t.getText();
				dos.writeBytes(str1);
				dos.close();
			}
			catch(IOException e2)
			{
				t.setText(e2.getMessage());
			}			
		}
		if(str.equals("Exit"))
		{
			f.dispose();
		}


		if(str.equals("Find"))
		{
			num=0; num1=0;
			Find=new Frame("Find");
			Find_replace=new Frame("Find and Replace");
			
			//For the Frame
			Find.setSize(300,150);
			Find.setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.weightx=1.0;	gbc.weighty=1.0;
			Label l1=new Label("Find");
				Find.add(l1,gbc);
			t1=new TextField(15);
				gbc.gridy=1;
				Find.add(t1,gbc);
			Button b1=new Button("Find Next");
				gbc.gridy=2;
				Find.add(b1,gbc);
			Button b2=new Button("Close");
				gbc.gridx=3;
				Find.add(b2,gbc);
			Find.setVisible(true);
				Find_replace.dispose();

			WindowCloser wc=new WindowCloser();
			Find.addWindowListener(wc);
			//For the Frame			
			b1.addActionListener(this);	
			b2.addActionListener(this);

		}
		if(str.equals("Find and Replace"))
		{
			num=0; num1=0;
			Find=new Frame("Find");
			Find_replace=new Frame("Find and Replace");

			//For The Frame
			Find_replace.setSize(300,150);
			t2=new TextField(15);
			t3=new TextField(15);
			Find_replace.setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
		gbc.weightx=1.0;	gbc.weighty=1.0;
			Label l1=new Label("Find");
				Find_replace.add(l1,gbc);
			t1=new TextField(15);
				gbc.gridy=1;
				Find_replace.add(t1,gbc);

			Label l2=new Label("Replace");
				gbc.gridy=2;
				Find_replace.add(l2,gbc);
			t2=new TextField(15);
				gbc.gridy=3;
				Find_replace.add(t2,gbc);

			Button b1=new Button("Find Next");
				gbc.gridy=4;
				Find_replace.add(b1,gbc);

			Button b2=new Button("Replace");
				gbc.gridx=1;
				Find_replace.add(b2,gbc);
			Button b3=new Button("Replace All");
				gbc.gridx=2;
				Find_replace.add(b3,gbc);

			Button b4=new Button("Close");
				gbc.gridx=3;
				Find_replace.add(b4,gbc);
			Find_replace.setVisible(true);
			Find.dispose();
		
			WindowCloser wc=new WindowCloser();	
			Find_replace.addWindowListener(wc);

			//For the Frame
	
			b2.addActionListener(this);
			b1.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);

			
		}
		
	
		if(str.equals("Find Next"))
		{

			p=Pattern.compile(t1.getText());
			m=p.matcher(t.getText());
			if(m.find(num1))
			{
				num=m.start();
				num1=m.end();
				t.requestFocus();
				t.select(m.start(),m.end());

			}
		}
		if(str.equals("Replace All"))
		{
			str1=t2.getText();
			 p=Pattern.compile(t1.getText());
			 m=p.matcher(t.getText());
			while(m.find())
			{
				t.replaceText(str1,m.start(),m.end());
			}		
		}
		if(str.equals("Replace"))
		{
			str1=t2.getText();
			p=Pattern.compile(t1.getText());
			m=p.matcher(t.getText());
			if(m.find(num))
			{
				t.replaceText(str1,m.start(),m.end());
				t.requestFocus();
				t.select(m.start(),m.end());

			}
		}
		if(str.equals("Close"))
		{
				Find.dispose();
				Find_replace.dispose();
		}
	}

	public static void main(String args[]) 
	{
		Editor E=new Editor();
	}

}
