package SyntaxAnalysis;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Show extends JFrame {

	private JPanel contentPane;
	JTextArea WritePlace ;
	JScrollPane scrollPane=new JScrollPane();
	JTextArea Process ;
	private JTextField ShowPlace;
	JLabel jlabel;
	private JButton save;
	FileReader fr;
	FileWriter fw;
	JFileChooser fc;
	File file;
	Analysis fuct;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				}catch(Exception e) {
					System.out.println(e);
				}
				try {
					Show frame = new Show();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Show() {
		
		fuct = new Analysis();
		setTitle("C语言表达式的语法分析");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("所要分析的C语言表达式");
		lblNewLabel.setFont(new Font("宋体",  Font.BOLD, 16));
		lblNewLabel.setBounds(34, 50, 232, 15);
		contentPane.add(lblNewLabel);
		
		WritePlace = new JTextArea();
		WritePlace.setBounds(34, 70, 345, 200);
		contentPane.add(WritePlace);

		JButton file_choose  = new JButton("导入文件");
		file_choose.setFont(new Font("宋体", Font.BOLD, 14));
		file_choose.setBounds(160,280, 93, 33);
		contentPane.add(file_choose);

		JButton act = new JButton("语法分析");
		act.setFont(new Font("宋体", Font.BOLD, 14));
		act.setBounds(34, 280, 93, 33);
		contentPane.add(act);

		JButton restart = new JButton("重置");
		restart.setFont(new Font("宋体", Font.BOLD, 14));
		restart.setBounds(286,280, 93, 33);
		contentPane.add(restart);

		JButton display = new JButton("文法展示");
//		display.setFont(new Font("宋体", Font.BOLD, 14));
//		display.setBounds(630,12, 93, 33);
//		contentPane.add(display);
		
		scrollPane.setSize(427, 537);
		scrollPane.setLocation(400, 70);
		
		contentPane.add(scrollPane);
		
		Process = new JTextArea();
		Process.setEditable(false);
		scrollPane.setViewportView(Process);

		ShowPlace = new JTextField();
		ShowPlace.setBackground(Color.WHITE);
		ShowPlace.setEditable(false);
		ShowPlace.setBounds(500, 15, 327, 30);
		contentPane.add(ShowPlace);
		ShowPlace.setColumns(10);

		JLabel label = new JLabel("语法分析过程");
		label.setFont(new Font("宋体", Font.BOLD, 16));
		label.setBounds(400, 50, 232, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("计算结果");
		label_1.setFont(new Font("宋体", Font.BOLD, 16));
		label_1.setBounds(400, 21, 232, 15);
		contentPane.add(label_1);
		

		 
		save = new JButton("保存分析");	   
	    save.setFont(new Font("宋体", Font.BOLD, 14));
	    save.setBounds(34, 432, 100, 33);
	    //contentPane.add(save);
		act.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=WritePlace.getText().toString().trim();
				try {
					//设置计算结果和分析过程
					String[] result = fuct.analysis(str);
					ShowPlace.setText(result[1]);
					Process.setText(result[0]);
				} catch (Exception e1) {
					ShowPlace.setText("错误！！！");
					Process.setText("错误！！！");
				}
				
			}
		});
		file_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc=new JFileChooser();
				fc.showOpenDialog(null);
				file=fc.getSelectedFile();
				BufferedReader bre = null;
				try {
					bre = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String t=null;
				StringBuffer sbu=new StringBuffer();
				try {
					t=bre.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 while(t!=null)
				 {
					 sbu.append(t+"\n");
					 try {
						t=bre.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 try {
					bre.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				WritePlace.setText(sbu.toString());
			}
		});
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WritePlace.setText("");
				ShowPlace.setText("");
				Process.setText("");
			}
		});
		save.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		fc=new JFileChooser();
					fc.showSaveDialog(null);
					file=fc.getSelectedFile();
					try {
						fw=new FileWriter(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String str;
					str=Process.getText();
					String ss=str.replaceAll("\n", "\r\n");
					try {
						fw.write(ss);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    try {
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

		    	}
		    });
		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowPlace.setText("");
				Process.setText(fuct.grammer_display);
			}
		});
	    jlabel = new JLabel();
	    jlabel.setFont(new Font("宋体", Font.PLAIN, 17));
	    jlabel.setBackground(Color.WHITE);
	    jlabel.setLocation(new Point(0, 0));
	    contentPane.add(jlabel);
	    jlabel.setSize(this.getWidth(),this.getHeight());
	}
}