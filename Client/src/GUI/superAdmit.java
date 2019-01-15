package GUI;

import javax.swing.*;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bean.MstudentBean;

public class superAdmit extends JPanel {
	private JTextField JTfind;//���� --key
	private JTable table;
	private JScrollPane scrollPane ;
	JComboBox<Object> comboBox;
	ButtonGroup  bg ;
	JRadioButton RBadmit;//����Ա��ѡ��
	JRadioButton RBstudent;//ѧ����ѡ��
	Object[][] ob=new Object[0][0];
	private Font font2 = new Font("����", Font.BOLD, 17);
	private TableAction tableaction = new TableAction();//���ڱ�����
	String[] student={"ѧ��","����","�Ա�","�༶","����ϵ","����Ժ","�ֻ�����","����"};
	String[] Sadmit={"����","����","�Ա�","�ֻ�����","���֤����","e-mail","����"};
	String[] Sid={"ѧ��/����","����"};

	public superAdmit() {
		
		setOpaque(false);
		setLayout(null);
		//���ҷ�ʽ
		JLabel label = new JLabel("\u67E5\u8BE2\u65B9\u5F0F:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("����", Font.BOLD, 18));
		label.setBounds(23, 10, 98, 27);
		add(label);
		//���ҷ�ʽ����Ͽ�
		comboBox = new JComboBox<Object>(Sid);
		comboBox.setOpaque(true);
		comboBox.setFont(new Font("����", Font.BOLD, 18));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(118, 10, 127, 26);
		add(comboBox);
		//����--key
		JTfind = new JTextField();
		JTfind.setFont(new Font("����", Font.BOLD, 18));
		JTfind.setColumns(10);
		JTfind.setBounds(255, 10, 171, 27);
		add(JTfind);
		//��ݵ�ѡ��
		bg = new ButtonGroup (); //������ 
		//����Ա
		RBadmit = new JRadioButton("\u7BA1\u7406\u5458");
		RBadmit.setFont(new Font("����", Font.BOLD, 20));
		RBadmit.setBounds(432, 12, 121, 23);
		bg.add(RBadmit);
		RBadmit.setSelected(true);
		RBadmit.setOpaque(false);
		add(RBadmit);
		//ѧ��
		 RBstudent = new JRadioButton("\u5B66\u751F");
		RBstudent.setFont(new Font("����", Font.BOLD, 20));
		RBstudent.setBounds(555, 12, 121, 23);
		bg.add(RBstudent);
		RBstudent.setOpaque(false);
		add(RBstudent);
		//���Ұ�ť
		JButton JBfind = new JButton("\u67E5\u8BE2");
		JBfind.setToolTipText("\u652F\u6301\u6A21\u7CCA\u67E5\u8BE2");
		JBfind.setFont(new Font("����", Font.BOLD, 20));
		JBfind.setContentAreaFilled(false);
		Admit.frame.getRootPane().setDefaultButton(JBfind);
		JBfind.setBounds(682, 9, 121, 28);
		add(JBfind);
		//getRootPane().setDefaultButton(JBfind);
		JBfind.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket=new Socket(Login.ip, 8181);
					DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
					toServer.writeInt(13);
					toServer.writeInt(1);
					toServer.writeUTF(comboBox.getSelectedItem().toString());
					toServer.writeUTF(JTfind.getText());
					if (RBstudent.isSelected()){
						toServer.writeInt(1);
						ObjectInputStream fromServer=new ObjectInputStream(socket.getInputStream());
						ArrayList<MstudentBean> list=(ArrayList<MstudentBean>)fromServer.readObject();
						ob=new Object[list.size()][8];
						int i=0,j=0;
						if (list.size()!=0){
							for (MstudentBean ms:list){
								if (j>7)
									j=0;
								ob[i][j++]=ms.getSNo();
								ob[i][j++]=ms.getSName();
								ob[i][j++]=ms.getBno();
								ob[i][j++]=ms.getBname();
								ob[i][j++]=ms.getAuthor();
								ob[i][j++]=ms.getOrderTime();
								ob[i][j++]=ms.getTime();
								ob[i][j++]="˫���鿴";
								i++;
							}
						}else {
							JOptionPane.showMessageDialog(null, "δ��ѯ�����");
							ob = new Object[0][0];
						}
						table = new JTable(new MyTable(ob, student));
					}
					else if (RBadmit.isSelected()){
						toServer.writeInt(2);
						ObjectInputStream fromServer=new ObjectInputStream(socket.getInputStream());
						ArrayList<MstudentBean> list=(ArrayList<MstudentBean>)fromServer.readObject();
						ob=new Object[list.size()][7];
						int i=0,j=0;
						if (list.size()!=0){
							for (MstudentBean ms:list){
								if (j>6)
									j=0;
								ob[i][j++]=ms.getSNo();
								ob[i][j++]=ms.getSName();
								ob[i][j++]=ms.getBno();
								ob[i][j++]=ms.getTime();
								ob[i][j++]=ms.getAuthor();
								ob[i][j++]=ms.getOrderTime();
								ob[i][j++]="˫���޸�/�鿴";
								i++;
							}
						}else {
							JOptionPane.showMessageDialog(null, "δ��ѯ�����");
							ob = new Object[0][0];
						}
						table = new JTable(new MyTable(ob, Sadmit));
					}

					table.setFont(font2);
					table.addMouseListener(tableaction);//���������
					table.setRowHeight(28);
					scrollPane.setViewportView(table);//������ʾ
					socket.close();
				} catch (IOException e) {
					// TODO: handle exception
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//���ѧ��/����Ա��ť
		JButton JBadd = new JButton("\u6DFB\u52A0");
		JBadd.setToolTipText("\u652F\u6301\u6A21\u7CCA\u67E5\u8BE2");
		JBadd.setFont(new Font("����", Font.BOLD, 20));
		JBadd.setContentAreaFilled(false);
		JBadd.setBounds(819, 9, 121, 28);
		add(JBadd);
		JBadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (RBstudent.isSelected()){
					Information infor=new Information(null, null, null, null, null,null, null,1);
					int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
					int y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
					infor.show(x, y);
				}else if (RBadmit.isSelected()){
					Information infor=new Information(null, null, null, null, null,null,null, 2);
					int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
					int y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
					infor.show(x, y);
				}
			}
		});
		//����
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 50, 994, 439);
		scrollPane.setOpaque(false);//����͸����������ķ���һ����
		scrollPane.getViewport().setOpaque(false);	
		add(scrollPane);
		//���
		table =new JTable(new MyTable(ob, student));
		table.setRowHeight(28);
		table.setFont(new Font("����", Font.BOLD, 18));
		scrollPane.setViewportView(table);
	}
	//�������������2�£��������黭��
	class TableAction implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			int Mousercount = e.getClickCount();
			if (Mousercount == 2) {
				int r = table.getSelectedRow();
				if (table.getValueAt(r, 6).toString().equals("˫���޸�/�鿴")){
					String no = table.getValueAt(r, 0).toString();
					String name = table.getValueAt(r, 1).toString();
					String sex = table.getValueAt(r, 2).toString();
					String email;
					if (null == ob[r][5]) {
						email = "";
					} else
						email = table.getValueAt(r, 5).toString();
					String phone = table.getValueAt(r, 3).toString();
					String id = table.getValueAt(r, 4).toString();
					Information information = new Information(no,name,sex,phone,id,email,null,4);
					int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
					int y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
					//int y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
					information.show(x, y);
				}
				else if (table.getValueAt(r, 7).toString().equals("˫���鿴")){
					String no = table.getValueAt(r, 0).toString();
					String name = table.getValueAt(r, 1).toString();
					String sex = table.getValueAt(r, 2).toString();

					String Sclass= table.getValueAt(r, 3).toString();
					String xi = table.getValueAt(r, 4).toString();
					String yuan = table.getValueAt(r, 5).toString();
					String phone=table.getValueAt(r, 6).toString();
					Information information = new Information(no,name,sex,Sclass,xi,yuan,phone,3);
					int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
					int y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
					information.show(x, y);
				}
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
