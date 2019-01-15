package Server;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.MstudentBean;
import bean.book;

/**
 * @author wsk
 * @className:HandleStuent
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月26日下午12:08:28
 */
//显示学生信息，数据信息
public class HandleStuent implements Runnable{
	private Socket socket;
	private DataInputStream fromClient;
	private ObjectOutputStream toClientOb;
	public HandleStuent(Socket socket){
		this.socket=socket;
	}
	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL con = new ContentSQL();
			fromClient = new DataInputStream(socket.getInputStream());
			toClientOb = new ObjectOutputStream(socket.getOutputStream());
			String sno=fromClient.readUTF();
			String bno=fromClient.readUTF();
			String sql="select * from StudentInformation,Class,xi "
					+ "where sno='"+sno+"' and "
					+ "StudentInformation.no=Class.no and xi.cno=class.cno ";					
			Statement stat=con.conn.createStatement();
			//学生信息
			ResultSet res=stat.executeQuery(sql);
			ArrayList<MstudentBean> list=new ArrayList<MstudentBean>();
			if (res.next()){
				MstudentBean ms=new MstudentBean();
				ms.setSNo(res.getString(1));//sno
				ms.setSName(res.getString(2));//name
				ms.setBno(res.getString(4));//ssex
				ms.setAuthor(res.getString(7));//sphone
				ms.setBname(res.getString(9));//cname
				ms.setTime(res.getString(12));//xiname
				ms.setOrderTime(res.getString(14));//yuanname
				list.add(ms);
				toClientOb.writeObject(list);
			}
			//书籍信息
			sql="select * from book where bno='"+bno+"'";
			res=stat.executeQuery(sql);
			ArrayList<book> listbook=new ArrayList<book>();
			if (res.next()){
				book book=new book();
				book.setNo(res.getString(1));
				book.setName(res.getString(2));
				book.setAuthor(res.getString(3));
				listbook.add(book);
				toClientOb.writeObject(listbook);
			}
			
			stat.close();
			res.close();
			toClientOb.flush();
			toClientOb.close();
			fromClient.close();
			con.conn.close();
			socket.close();
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
