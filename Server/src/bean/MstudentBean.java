package bean;

import java.io.Serializable;


/**
 * @author wsk
 * @className:MstudentBean
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月24日下午12:07:28
 */
public class MstudentBean implements Serializable{
	private String sno;
	private String sname;
	private String bno;
	private String bname;
	private String author;
	//private String publish;
	//private int stock;
	//private Date time;
	//private String sort;
	//private String translator;
	private String time;
	private int acount;
	private String orderTime;
	
	public String getSNo(){
		return sno;
	}
	public void setSNo(String no){
		this.sno= no;
	}
	public String getSName(){
		return sname;
	}
	public void setSName(String name){
		this.sname= name;
	}
	public String getBno(){
		return bno;
	}
	public void setBno(String author){
		this.bno= author;
	}
	public String getOrderTime(){
		return orderTime;
	}
	public void setOrderTime(String time){
		this.orderTime=time;
	}
	public String getBname(){
		return bname;
	}
	public void setBname(String sort){
		this.bname= sort;
	}

	public int getAcount(){
		return acount;
	}
	public void setAcount(int i){
		this.acount=i;
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String i){
		this.author=i;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String i){
		this.time=i;
	}
}
