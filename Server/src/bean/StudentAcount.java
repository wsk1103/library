package bean;

/**
 * @author wsk
 * @className:StudenAcont
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月10日下午11:00:29
 */
public class StudentAcount implements java.io.Serializable{
	private String num;
	private String password;
	private String question;
	private String answer;
	private int stats;
//	public StudentAcount(String num,String question,int stats){
//		this.num=num;
//		this.question=question;
//		this.stats=stats;
//	}
	public String getNum(){
		return num;
	}
	public void setNum(String num){
		this.num=num;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getQuestion(){
		return question;
	}
	public void setQuestion(String question){
		this.question=question;
	}
	public String getAnswer(){
		return answer;
	}
	public void setAnswer(String answer){
		this.answer=answer;
	}
	public int getStats(){
		return stats;
	}
	public void setStats(int stats){
		this.stats=stats;
	}
}
