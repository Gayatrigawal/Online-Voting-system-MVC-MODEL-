package in.co.online.vote.bean;

public class VoteBean extends BaseBean {
	
	
	private long partyId;
	private String partyName;
	private String voterId;
	private long userid;
	private String voterName;
	private long voterid;
	private String elecationName;
	private String candidateName;


	
	

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	
	public long getVoterid() {
		return voterid;
	}

	public void setVoterid(long voterid) {
		this.voterid = voterid;
	}
	

	public String getElecationName() {
		return elecationName;
	}

	public void setElecationName(String elecationName) {
		this.elecationName = elecationName;
	}
	

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
