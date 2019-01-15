package Server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

import bean.book;

public class HandleBook implements Runnable {
	Socket socket;
	DataOutputStream toClient;
	DataInputStream fromClient;

	public HandleBook(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			toClient = new DataOutputStream(socket.getOutputStream());
			fromClient = new DataInputStream(socket.getInputStream());
			ContentSQL con = new ContentSQL();
			int i = fromClient.readInt();
			String sql;
			//删除书籍
			if (i == 1) {
				String bno = fromClient.readUTF();
				sql = "delete from book where bno=?";
				PreparedStatement pre = con.conn.prepareStatement(sql);
				pre.setString(1, bno);
				pre.executeUpdate();
				toClient.writeInt(1);
				pre.close();
			}
			//更新书籍
			else if (i == 2) {
				ObjectInputStream fromClientOb = new ObjectInputStream(
						socket.getInputStream());
				ArrayList<book> list = (ArrayList<book>) fromClientOb
						.readObject();
				book book = new book();
				book = list.get(0);
				String bno = book.getNo();
				String bname = book.getName();
				String bauthor = book.getAuthor();
				String btranslator = book.getTranslator();
				String bsort = book.getSort();
				String bpublish = book.getPublsh();
				String bintime = book.getOrderTime();
				String bstock = "" + book.getStock();
				sql = "begin tran ss update book set bname=? where bno=? "
						+ "if @@error!=0 rollback tran ss else begin  "
						+ "update book set bauthor=? where bno=? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update book set btranslator=? where bno=? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update book set bsort=? where bno=? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update book set bpublish=? where bno=? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update book set bintime=? where bno=? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update book set bstock=? where bno=? end commit tran "
						+ "if @@error!=0 rollback tran ss ";
				PreparedStatement pre = con.conn.prepareStatement(sql);
				pre.setString(1, bname);
				pre.setString(2, bno);
				pre.setString(3, bauthor);
				pre.setString(4, bno);
				pre.setString(5, btranslator);
				pre.setString(6, bno);
				pre.setString(7, bsort);
				pre.setString(8, bno);
				pre.setString(9, bpublish);
				pre.setString(10, bno);
				pre.setString(11, bintime);
				pre.setString(12, bno);
				pre.setString(13, bstock);
				pre.setString(14, bno);
				pre.executeUpdate();
				toClient.writeInt(1);
				pre.close();
			} 
			//增加书籍
			else if (i == 3) {
				ObjectInputStream fromClientOb = new ObjectInputStream(
						socket.getInputStream());
				ArrayList<book> list = (ArrayList<book>) fromClientOb
						.readObject();
				book book = new book();
				book = list.get(0);
				// String bno=book.getNo();
				String bname = book.getName();
				String bauthor = book.getAuthor();
				String btranslator = book.getTranslator();
				String bsort = book.getSort();
				String bpublish = book.getPublsh();
				String bintime = book.getOrderTime();
				String bstock = "" + book.getStock();
				sql = "insert into book(bname,bauthor,bpublish,bstock,bintime,bsort,btranslator) values(?,?,?,?,?,?,?)";
				PreparedStatement pre = con.conn.prepareStatement(sql);
				pre.setString(1, bname);
				pre.setString(2, bauthor);
				pre.setString(3, bpublish);
				pre.setString(4, bstock);
				pre.setString(5, bintime);
				pre.setString(6, bsort);
				pre.setString(7, btranslator);
				pre.executeUpdate();
				toClient.writeInt(1);
				pre.close();
			}
			toClient.flush();
			toClient.close();
			fromClient.close();
			con.conn.close();
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
