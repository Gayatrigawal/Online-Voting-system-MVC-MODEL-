package in.co.online.vote.bean;

import java.sql.Blob;

public class VoterApplicationBean extends BaseBean{
	
	
	private String firstName;
	
	private String lastName;
	
	private String login;
	
	private String mobileNo;
	
	private long voterID;
	
	private Blob idProof;
	
	private long userid;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getVoterID() {
		return voterID;
	}

	public void setVoterID(long voterID) {
		this.voterID = voterID;
	}

	public Blob getIdProof() {
		return idProof;
	}

	public void setIdProof(Blob idProof) {
		this.idProof = idProof;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
