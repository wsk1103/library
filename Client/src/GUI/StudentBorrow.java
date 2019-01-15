package GUI;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

import bean.book;

public class StudentBorrow extends JPanel {
	private JTable table;
	Object[][] ob=new Object[0][9];
	private TableAction tableaction = new TableAction();
	String[] s={"编号","书名","作者","译者","出版社","类型","借书时间","应还时间","操作"};
	Font font=new Font("楷体", Font.BOLD, 16);
	JScrollPane scrollPane = new JScrollPane();
	public StudentBorrow() {
		setOpaque(false);
		setLayout(null);
		//滑板
		scrollPane.setBounds(0, 52, 993, 434);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);		
		add(scrollPane);
		
	///列表	
		table = new JTable(ob,s);
		table.setRowHeight(28);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		//刷新
		JButton reflush = new JButton("\u5237\u65B0/查询");
		reflush.setFont(new Font("楷体", Font.BOLD, 18));
		reflush.setBounds(814, 10, 125, 32);
		reflush.setContentAreaFilled(false);
		//this.getRootPane().setDefaultButton(reflush);
		reflush.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				HandleBorrow();
			}
		});
		add(reflush);
	}
//初始化列表
	public void HandleBorrow(){
		try {
			Socket socket=new Socket(Login.ip, 8181);
			DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
			toServer.writeInt(8);//借书情况
			toServer.writeUTF(Login.JTnum.getText());
			toServer.writeInt(1);
			ObjectInputStream fromServer=new ObjectInputStream(socket.getInputStream());
			ArrayList<book> list=(ArrayList<book>)fromServer.readObject();
			ob=new Object[list.size()][9];
			if (list.size()!=0){
			int i=0,j=0;
			for (book book:list){
				if (j>8)j=0;
				ob[i][j++]=book.getNo();
				ob[i][j++]=book.getName();
				ob[i][j++]=book.getAuthor();
				ob[i][j++]=book.getTranslator();
				ob[i][j++]=book.getPublsh();
				ob[i][j++]=book.getSort();
				ob[i][j++]=book.getTime();
				ob[i][j++]=book.getOrderTime();
				ob[i][j++]="双击续借";
				i++;
			}
			table=new JTable(new MyTable(ob, s));
			table.setFont(font);
			table.setRowHeight(28);
			table.addMouseListener(tableaction);
			
			}else {
				JOptionPane.showMessageDialog(null, "没有借书");
				ob=new Object[0][9];
				table=new JTable(new MyTable(ob, s));
				table.setRowHeight(28);
				table.setEnabled(false);
			}
			scrollPane.setViewportView(table);
			toServer.flush();
			toServer.close();
			fromServer.close();
			socket.close();
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	class TableAction implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			int Mousercount = e.getClickCount();
			if (Mousercount == 2) {
				int r = table.getSelectedRow();
					String BookNum = table.getValueAt(r, 0).toString();
					String BookName = table.getValueAt(r, 1).toString();
					String BookAuthor = table.getValueAt(r, 2).toString();
					String BookTranslator;
					if (null == ob[r][3]) {
						BookTranslator = "";
					} else
						BookTranslator = table.getValueAt(r, 3).toString();
					String BookSort = table.getValueAt(r, 5).toString();
					String BookPublish = table.getValueAt(r, 4).toString();
					Point p = Student.frame.getLocation();
					String Snum=Login.JTnum.getText();
					int acount=Student.count;
					int x = (int) p.getX()+150;
					int y = (int) p.getY()+150;
					BookInformation bookinformation = new BookInformation(Snum,
							BookNum, BookName, BookAuthor, BookTranslator,
							BookSort, BookPublish,"sstock",acount,3);
					bookinformation.show(x, y);
				}
		}
		
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}
