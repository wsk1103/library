package bean;

/**
 * @author wsk
 * @className:Class
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月10日下午11:14:11
 */
public class Class implements java.io.Serializable{
	private String no;
	private String sclass;
	private String time;
	private String department;
	public String getNo(){
		return no;
	}
	public void setNo(String no){
		this.no=no;
	}
	public String getSclass(){
		return sclass;
	}
	public void setSclass(String sclass){
		this.sclass=sclass;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getDepartment(){
		return department;
	}
	public void setDepartment(String department){
		this.department=department;
	}
}
