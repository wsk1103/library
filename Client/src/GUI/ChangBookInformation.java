package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.*;

import bean.book;

public class ChangBookInformation{
	
	static JFrame frame;
	private JPanel contentPane;
	private JLabel JTno;
	private JTextField JTname;
	private JTextField JTauthor;
	private JTextField JTtranslator;
	private JTextField JTpublish;
	String[] Sort = { "计算机", "艺术", "数学", "文学", "物理", "生物", "化学", "音乐", "历史",
			"地理", "政治", "休闲", "体育", "娱乐", "生活", "经济" ,"动漫","英语","外语"};
	Font font = new Font("楷体", Font.BOLD, 22);
	String[] Syear = new String[2030-1900];
	String[] Smonth = new String[12];
	String[] Sday = new String[31];

	JComboBox CBsort;
	JComboBox CByear;
	JComboBox CBmonth;
	JComboBox CBday;
	String Sno;
	String Sname;
	String Sauthor;
	String Stranslator;
	String Ssort;
	String Spublish;
	String Sstock;
	String Stime;
	private JLabel JLyear;
	private JLabel JLmonth;
	private JLabel JLday;
	private JLabel JLstock;
	private JTextField JTstock;
	private JButton JBdel;

	public ChangBookInformation(String no, String name, String author,
			String translator, String sort, String publish, String stock,
			String time, int ok) {
		frame=new JFrame();
		Admit.information=2;
		Sno = no;
		Sname = name;
		Sauthor = author;
		Stranslator = translator;
		Ssort = sort;
		Spublish = publish;
		Stime = time;
		Sstock = stock;

		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/backgroud.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
//书名
		JLabel JLname = new JLabel("\u4E66\u540D\uFF1A");
		JLname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLname.setFont(font);
		JLname.setBounds(47, 76, 101, 30);
		contentPane.add(JLname);
				JTname = new JTextField(Sname);
				JTname.setFont(font);
				JTname.setColumns(10);
				JTname.setBounds(158, 76, 307, 30);
				contentPane.add(JTname);
//作者
		JLabel JLauthor = new JLabel("\u4F5C\u8005\uFF1A");
		JLauthor.setHorizontalAlignment(SwingConstants.RIGHT);
		JLauthor.setFont(font);
		JLauthor.setBounds(47, 128, 101, 30);
		contentPane.add(JLauthor);
		JTauthor = new JTextField(Sauthor);
		JTauthor.setFont(font);
		JTauthor.setColumns(10);
		JTauthor.setBounds(158, 128, 307, 30);
		contentPane.add(JTauthor);
//译者
		JLabel JLtranslator = new JLabel("\u8BD1\u8005\uFF1A");
		JLtranslator.setHorizontalAlignment(SwingConstants.RIGHT);
		JLtranslator.setFont(font);
		JLtranslator.setBounds(47, 178, 101, 30);
		contentPane.add(JLtranslator);

		JTtranslator = new JTextField(Stranslator);
		JTtranslator.setFont(font);
		JTtranslator.setColumns(10);
		JTtranslator.setBounds(158, 178, 307, 30);
		contentPane.add(JTtranslator);
//分类
		JLabel JLsort = new JLabel("\u7C7B\u578B\uFF1A");
		JLsort.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsort.setFont(font);
		JLsort.setBounds(47, 229, 101, 30);
		contentPane.add(JLsort);
//出版社
		JLabel JLpublish = new JLabel("\u51FA\u7248\u793E\uFF1A");
		JLpublish.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpublish.setFont(font);
		JLpublish.setBounds(47, 279, 101, 30);
		contentPane.add(JLpublish);
		JTpublish = new JTextField(Spublish);
		JTpublish.setFont(font);
		JTpublish.setColumns(10);
		JTpublish.setBounds(158, 279, 307, 30);
		contentPane.add(JTpublish);
//分类-组合框
		CBsort = new JComboBox(Sort);
		int a = 0;
		// System.out.println(Sort.length);
		if (Ssort != null) {
			for (int i = 0; i < Sort.length; i++) {
				if (Ssort.equals(Sort[i])) {
					a = i;
					break;
				}
			}
		}
		CBsort.setSelectedIndex(a);//设置默认值
		CBsort.setBackground(Color.white);
		CBsort.setFont(font);
		CBsort.setBounds(158, 229, 307, 30);
		contentPane.add(CBsort);



//库存
		JLstock = new JLabel("\u5E93\u5B58\uFF1A");
		JLstock.setHorizontalAlignment(SwingConstants.RIGHT);
		JLstock.setFont(font);
		JLstock.setBounds(47, 331, 101, 30);
		contentPane.add(JLstock);
		JTstock = new JTextField(Sstock);
		JTstock.setFont(font);
		JTstock.setColumns(10);
		JTstock.setBounds(158, 331, 307, 30);
		contentPane.add(JTstock);
		///初始化年月日
		int j = 0;
		for (int i = 2030; i > 1900; i--) {
			Syear[j] = "" + i;
			j++;
		}
		for (int i = 0; i < 12; i++) {
			Smonth[i] = "" + (i + 1);
		}
		for (int i = 0; i < 31; i++)
			Sday[i] = "" + (i + 1);
		//年月日组合框
		CByear = new JComboBox(Syear);
		CBmonth = new JComboBox(Smonth);
		CBday = new JComboBox(Sday);

//对应处理预约过期按钮
		if (1 == ok) {
			Calendar cal=Calendar.getInstance();
			String byear=""+cal.get(Calendar.YEAR);
			String bmonth =""+(cal.get(Calendar.MONTH)+1);
			String bday = ""+cal.get(Calendar.DAY_OF_MONTH);

			CByear.setSelectedItem(byear);
			CByear.setFont(font);
			CByear.setBackground(Color.white);
			CByear.setBounds(132, 380, 91, 30);
			contentPane.add(CByear);

			CBmonth.setSelectedItem(bmonth);
			CBmonth.setBackground(Color.white);
			CBmonth.setFont(font);
			CBmonth.setBounds(259, 380, 91, 30);
			contentPane.add(CBmonth);

			CBday.setSelectedItem(bday);
			CBday.setBackground(Color.white);
			CBday.setFont(font);
			CBday.setBounds(390, 380, 91, 30);
			contentPane.add(CBday);
//年标签
			JLyear = new JLabel("\u5E74");
			JLyear.setHorizontalAlignment(SwingConstants.CENTER);
			JLyear.setFont(font);
			JLyear.setBounds(214, 380, 54, 30);
			contentPane.add(JLyear);
//月标签
			JLmonth = new JLabel("\u6708");
			JLmonth.setHorizontalAlignment(SwingConstants.CENTER);
			JLmonth.setFont(font);
			JLmonth.setBounds(341, 380, 54, 30);
			contentPane.add(JLmonth);
//日标签
			JLday = new JLabel("\u65E5");
			JLday.setHorizontalAlignment(SwingConstants.CENTER);
			JLday.setFont(font);
			JLday.setBounds(470, 380, 54, 30);
			contentPane.add(JLday);
			//编号
			JLabel JLno = new JLabel("\u7F16\u53F7\uFF1A");
			JLno.setHorizontalAlignment(SwingConstants.RIGHT);
			JLno.setFont(font);
			JLno.setBounds(47, 26, 101, 30);
			contentPane.add(JLno);
			JTno = new JLabel(Sno);
			JTno.setFont(font);
			JTno.setBounds(158, 26, 307, 30);
			contentPane.add(JTno);
//确定修改按钮
			JButton JBsure = new JButton("\u786E\u5B9A\u4FEE\u6539");
			JBsure.setFont(new Font("楷体", Font.BOLD, 18));
			JBsure.setContentAreaFilled(false);
			JBsure.setBounds(259, 445, 136, 42);
			contentPane.add(JBsure);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int isYES=JOptionPane.showConfirmDialog(null, "确定修改？","确定修改？",JOptionPane.YES_NO_OPTION);
					if (isYES==JOptionPane.YES_OPTION){
					// TODO Auto-generated method stub
					String bno = JTno.getText().trim();
					String bname = JTname.getText().trim();
					String bauthor = JTauthor.getText().trim();
					String btranslator = JTtranslator.getText().trim();
					String bsort = CBsort.getSelectedItem().toString();
					String bpublish = JTpublish.getText().trim();
					//System.out.println(bstock);
					String byear = CByear.getSelectedItem().toString();
					String bmonth = CBmonth.getSelectedItem().toString();
					String bday = CBday.getSelectedItem().toString();
					String btime = byear + "-" + bmonth + "-" + bday;//年月日
					boolean ok = time(byear, bmonth, bday);
					if (JTname.getText().isEmpty()||bname.length() > 50)
						JOptionPane.showMessageDialog(null, "书名为空或者过长，限制25个汉字");
					else if (JTauthor.getText().isEmpty()||bauthor.length() > 50)
						JOptionPane.showMessageDialog(null, "作者名字为空或者过长，限制25个汉字");
					else if (btranslator.length() > 50)
						JOptionPane.showMessageDialog(null, "译者名字过长，限制25个汉字");
					else if (JTpublish.getText().isEmpty()||bpublish.length() > 50)
						JOptionPane.showMessageDialog(null, "出版社为空或者过长，限制25个汉字");
					else if (JTstock.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "输入的库存数量为空，请检查");
					else if (!ok)
						JOptionPane.showMessageDialog(null, "日期错误");
					else {
						try {
						book book = new book();
						book.setNo(bno);
						book.setName(bname);
						book.setAuthor(bauthor);
						book.setTranslator(btranslator);
						book.setSort(bsort);
						book.setPublis(bpublish);
						book.setStock(Integer.parseInt(JTstock.getText().trim()));
						book.setOrderTime(btime);
						ArrayList<book> list = new ArrayList<book>();
						list.add(book);
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							toServer.writeInt(10);//方式11
							toServer.writeInt(2);//自方式2
							ObjectOutputStream toServerOb = new ObjectOutputStream(
									socket.getOutputStream());
							toServerOb.writeObject(list);
							int i = fromServer.readInt();
							if (i == 1) {
								JOptionPane.showMessageDialog(null, "修改成功");
								frame.dispose();
							}
							toServerOb.flush();
							toServerOb.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							// TODO: handle exception
							e.printStackTrace();
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "库存输入格式错误，只能输入数字");
						}
					}
				}}
			});
//删除按钮
			JBdel = new JButton("\u5220\u9664\u8BE5\u4E66\u7C4D");
			JBdel.setFont(new Font("楷体", Font.BOLD, 18));
			JBdel.setContentAreaFilled(false);
			JBdel.setBounds(107, 445, 136, 42);
			contentPane.add(JBdel);
		//年标签	
			JLabel JLtime = new JLabel("\u8FDB\u8D27\u65F6\u95F4:");
			JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
			JLtime.setFont(new Font("楷体", Font.BOLD, 22));
			JLtime.setBounds(0, 380, 122, 30);
			contentPane.add(JLtime);
			JBdel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int isYes = JOptionPane.showConfirmDialog(null, "确定删除?!!!",
							"删除", JOptionPane.YES_NO_OPTION);
					if (isYes == JOptionPane.YES_OPTION) {
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							toServer.writeInt(10);//方式10
							toServer.writeInt(1);//子方式1
							toServer.writeUTF(Sno);
							int i = fromServer.readInt();
							if (i == 1) {
								JOptionPane.showMessageDialog(null, "删除成功");
								frame.dispose();
							}

							toServer.flush();
							toServer.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							// TODO: handle exception
						}
					}
				}
			});
		} 
		//添加书籍
	else 
		if (2 == ok) {
			JButton JBsure = new JButton("确定");
			JBsure.setFont(new Font("楷体", Font.BOLD, 18));
			JBsure.setContentAreaFilled(false);
			JBsure.setBounds(200, 400, 136, 29);
			contentPane.add(JBsure);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int isYES=JOptionPane.showConfirmDialog(null, "确定添加？","确定添加？",JOptionPane.YES_NO_OPTION);
					if (isYES==JOptionPane.YES_OPTION){
					if (JTname.getText().isEmpty()||JTname.getText().length() > 50)
						JOptionPane.showMessageDialog(null, "书名为空或过长，限制25个汉字");
					else if (JTauthor.getText().isEmpty()||JTauthor.getText().length() > 50)
						JOptionPane.showMessageDialog(null, "作者名字为空或过长，限制25个汉字");
					else if (JTtranslator.getText().length() > 50)
						JOptionPane.showMessageDialog(null, "译者名字过长，限制25个汉字");
					else if (JTpublish.getText().isEmpty()||JTpublish.getText().length() > 50)
						JOptionPane.showMessageDialog(null, "出版社为空或过长，限制25个汉字");
					else if (JTstock.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "输入的库存数量为空");
					else {
						try{
						String bname = JTname.getText().trim();
						String bauthor = JTauthor.getText().trim();
						String btranslator = JTtranslator.getText().trim();
						String bsort = CBsort.getSelectedItem().toString();
						String bpublish = JTpublish.getText().trim();
						int bstock = Integer.parseInt(JTstock.getText().trim());
						Calendar cal=Calendar.getInstance();
						String byear=""+cal.get(Calendar.YEAR);
						String bmonth =""+(cal.get(Calendar.MONTH)+1);
						String bday = ""+cal.get(Calendar.DAY_OF_MONTH);
						String btime = byear + "-" + bmonth + "-" + bday;
						book book = new book();
						//book.setNo(bno);
						book.setName(bname);
						book.setAuthor(bauthor);
						book.setTranslator(btranslator);
						book.setSort(bsort);
						book.setPublis(bpublish);
						book.setStock(bstock);
						book.setOrderTime(btime);
						ArrayList<book> list = new ArrayList<book>();
						list.add(book);
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							toServer.writeInt(10);//增加书籍
							toServer.writeInt(3);
							ObjectOutputStream toServerOb = new ObjectOutputStream(
									socket.getOutputStream());
							toServerOb.writeObject(list);
							int i = fromServer.readInt();
							if (i == 1) {
								JOptionPane.showMessageDialog(null, "添加成功");
								frame.dispose();
							}
							toServerOb.flush();
							toServerOb.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							// TODO: handle exception
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "库存输入格式错误，只能输入数字");
						}
					}
				}}
			});
		}
	}

	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("书籍具体信息");
		frame.setBounds(x, y, 550, 540);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
	//判断日期是否输入正确
	public boolean time(String Syear, String Smonth, String Sday) {
		int year = Integer.parseInt(Syear);
		int month = Integer.parseInt(Smonth);
		int day = Integer.parseInt(Sday);
		boolean isYear = false;
		boolean ok = true;
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			isYear = true;

		if (month == 4 || month == 6 || month == 9 || month == 11)
			if (day == 31)
				ok = false;

		if (isYear) {
			if (month == 2)
				if (day > 29)
					ok = false;
		} else if (month == 2)
			if (day > 28)
				ok = false;
		return ok;
	}
}