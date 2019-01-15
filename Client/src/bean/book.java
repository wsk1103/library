package bean;

import java.util.Date;

/**
 * @author wsk
 * @className:book
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月10日下午11:13:57
 */
public class book implements java.io.Serializable{
	private String no;
	private String name;
	private String author;
	private String publish;
	private int stock;
	private Date time;
	private String sort;
	private String translator;
	private int acount;
	private String orderTime;
	
	public String getNo(){
		return no;
	}
	public void setNo(String no){
		this.no= no;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name= name;
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author= author;
	}
	public String getPublsh(){
		return publish;
	}
	public void setPublis(String publish){
		this.publish= publish;
	}
	public Date getTime(){
		return time;
	}
	public void setTime(Date time){
		this.time= time;
	}
	public String getOrderTime(){
		return orderTime;
	}
	public void setOrderTime(String time){
		this.orderTime=time;
	}
	public String getSort(){
		return sort;
	}
	public void setSort(String sort){
		this.sort= sort;
	}
	public String getTranslator(){
		return translator;
	}
	public void setTranslator(String translator){
		this.translator= translator;
	}
	public int getStock(){
		return stock;
	}
	public void setStock(int stock){
		this.stock=stock;
	}
	public int getAcount(){
		return acount;
	}
	public void setAcount(int i){
		this.acount=i;
	}
}
