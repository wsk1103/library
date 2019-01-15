package bean;

/**
 * @author wsk
 * @className:StudentInformation
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月10日下午10:48:44
 */
public class StudentInformation implements java.io.Serializable{
	private String num;
	private String user;
	private String no;
	private String sex;
	private String birthday;
	private String id;
	private String phone;
	
	public String getNum(){
		return this.num;
	}
	public void setNum(String num){
		this.num=num;
	}
	public String getUser(){
		return this.user;
	}
	public void setUser(String user){
		this.user=user;
	}
	public String getNo(){
		return this.no;
	}
	public void setNo(String no){
		this.no=no;
	}
	public String getSex(){
		return this.sex;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public String getBirthday(){
		return this.birthday;
	}
	public void setBirthday(String birthday){
		this.birthday=birthday;
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}

}
