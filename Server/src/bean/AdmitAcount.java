package bean;

/**
 * @author wsk
 * @className:AdimtAcont
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月10日下午11:10:11
 */
public class AdmitAcount implements java.io.Serializable{
	private String id;
	private String password;
	private String question;
	private String answer;
	private int stats;
	
//	public AdimtAcount(String id,String password){
//		this.id=id;
//		this.password=password;
//	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
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
