package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.book;

/**
 * @author wsk
 * @className:HandleOrder
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月16日下午10:24:00
 */
public class HandleOrder implements Runnable {
	private Socket socket;
	private ObjectOutputStream toClient;
	private DataOutputStream toClientData;
	private DataInputStream fromClient;

	public HandleOrder(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL c = new ContentSQL();
			Statement stat = c.conn.createStatement();
			fromClient = new DataInputStream(socket.getInputStream());
			toClient = new ObjectOutputStream(socket.getOutputStream());
			toClientData = new DataOutputStream(socket.getOutputStream());
			int i = fromClient.readInt();
			String type = fromClient.readUTF();
			String key = fromClient.readUTF();
			String sql = "select * from book where ";
				//热门度
				if (i == 1) {
					if (type.equals("书名")) {
						sql += "bname like '%" + key
								+ "%' order by bacount desc";
					} else if (type.equals("作者"))
						sql += "bauthor like '%" + key
								+ "%' order by bacount desc";
					else if (type.equals("类型"))
						sql += "bsort like '%" + key
								+ "%' order by bacount desc";
					else if (type.equals("译者"))
						sql += "btranslator like '%" + key
								+ "%' order by bacount desc";
					else if (type.equals("出版社"))
						sql += "bpublish like '%" + key
								+ "%' order by bacount desc";
					else if (type.equals("编号"))
						sql += "bno like '%" + key + "%' order by bacount desc";
					else if (type.equals("时间"))
						sql += "bintime like '%" + key + "%' order by bacount desc";
					ResultSet res = stat.executeQuery(sql);
					ArrayList<book> list = new ArrayList<book>();
					while (res.next()) {
						book book = new book();
						book.setNo(res.getString(1));
						book.setName(res.getString(2));
						book.setAuthor(res.getString(3));
						book.setPublis(res.getString(5));
						book.setStock(res.getInt(8));
						book.setTime(res.getDate(7));
						book.setSort(res.getString(6));
						book.setTranslator(res.getString(4));
						book.setAcount(res.getInt(9));
						list.add(book);
					}
					toClient.writeObject(list);
					stat.close();
					res.close();
				}
				//编号
				else if (i==2){
					if (type.equals("书名")) {
						sql += "bname like '%" + key + "%'";
					} else if (type.equals("作者"))
						sql += "bauthor like '%" + key + "%'";
					else if (type.equals("类型"))
						sql += "bsort like '%" + key + "%'";
					else if (type.equals("译者"))
						sql += "btranslator like '%" + key + "%'";
					else if (type.equals("出版社"))
						sql += "bpublish like '%" + key + "%'";
					else if (type.equals("编号"))
						sql += "bno like '%" + key + "%' ";
					else if (type.equals("时间"))
						sql += "bintime like '%" + key + "%' ";
					ResultSet res = stat.executeQuery(sql);
					ArrayList<book> list = new ArrayList<book>();
					while (res.next()) {
						book book = new book();
						book.setNo(res.getString(1));
						book.setName(res.getString(2));
						book.setAuthor(res.getString(3));
						book.setPublis(res.getString(5));
						book.setStock(res.getInt(8));
						book.setTime(res.getDate(7));
						book.setSort(res.getString(6));
						book.setTranslator(res.getString(4));
						book.setAcount(res.getInt(9));
						list.add(book);
					}
					toClient.writeObject(list);
					stat.close();
					res.close();
				}
				//时间
				else if (i==3){
						if (type.equals("书名")) {
							sql += "bname like '%" + key + "%' order by bintime desc";
						} else if (type.equals("作者"))
							sql += "bauthor like '%" + key + "%' order by bintime desc";
						else if (type.equals("类型"))
							sql += "bsort like '%" + key + "%' order by bintime desc";
						else if (type.equals("译者"))
							sql += "btranslator like '%" + key + "%' order by bintime desc";
						else if (type.equals("出版社"))
							sql += "bpublish like '%" + key + "%' order by bintime desc";
						else if (type.equals("编号"))
							sql += "bno like '%" + key + "%' order by bintime desc";
						else if (type.equals("时间"))
							sql += "bintime like '%" + key + "%' order by bintime desc";
						// System.out.println("type");
						ResultSet res = stat.executeQuery(sql);
						ArrayList<book> list = new ArrayList<book>();
						while (res.next()) {
							book book = new book();
							book.setNo(res.getString(1));
							book.setName(res.getString(2));
							book.setAuthor(res.getString(3));
							book.setPublis(res.getString(5));
							book.setStock(res.getInt(8));
							book.setTime(res.getDate(7));
							book.setSort(res.getString(6));
							book.setTranslator(res.getString(4));
							book.setAcount(res.getInt(9));
							list.add(book);
						}
						toClient.writeObject(list);
						stat.close();
						res.close();
					}
			c.conn.close();
			fromClient.close();
			toClientData.flush();
			toClient.flush();
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
