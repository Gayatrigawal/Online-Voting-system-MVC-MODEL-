package in.co.online.vote.bean;

import java.util.Date;

public class ElectionBean extends BaseBean{
	
	private String electionName;
	
	private Date electionDate;
	
	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	@Override
	public String getKey() {
		return id+"";
	}

	@Override
	public String getValue() {
		return electionName;
	}

}
