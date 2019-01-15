package bean;

/**
 * @author wsk
 * @className:StudentBrrow
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月10日下午11:14:28
 */
public class StudentBrrow implements java.io.Serializable{
	private String no;
	private String num;
	private String time;
	public String getNo(){
		return no;
	}
	public void setNo(String no){
		this.no=no;
	}
	public String getNum(){
		return num;
	}
	public void setNum(String num){
		this.num=num;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time=time;
	}
}
