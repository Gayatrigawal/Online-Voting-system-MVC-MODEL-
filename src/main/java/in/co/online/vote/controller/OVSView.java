package in.co.online.vote.controller;

public interface OVSView {	
	public String APP_CONTEXT = "/OnlineVotingSystem";
	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";
	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String PARTY_VIEW = PAGE_FOLDER + "/PartiesView.jsp";
	public String VOTE_VIEW = PAGE_FOLDER + "/VoteView.jsp";
	public String VOTE_LIST_VIEW = PAGE_FOLDER + "/VoteListView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/WelcomeView.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String USER_LOGIN_VIEW = PAGE_FOLDER + "/UserLoginView.jsp";
	public String ERROR_CTL = "/ctl/ErrorCtl";
	public String PARTY_CTL = APP_CONTEXT + "/ctl/PartiesCtl";
	public String VOTE_CTL = APP_CONTEXT + "/ctl/VoteCtl";
	public String VOTE_LIST_CTL = APP_CONTEXT + "/ctl/VoteListCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";
	public String USER_LOGIN_CTL = APP_CONTEXT + "/UserLoginCtl";
	public String IMAGE_CTL = APP_CONTEXT + "/image";
	
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	
	public String PARTIES_LIST_CTL = APP_CONTEXT + "/ctl/PartiesListCtl";
	public String PARTIES_LIST_VIEW = PAGE_FOLDER + "/PartiesListView.jsp";
	
	public String CANDIDATE_CTL = APP_CONTEXT + "/ctl/CandidateCtl";
	public String CANDIDATE_VIEW = PAGE_FOLDER + "/CandidateView.jsp";
	
	public String CANDIDATE_LIST_CTL = APP_CONTEXT + "/ctl/CandidateListCtl";
	public String CANDIDATE_LIST_VIEW = PAGE_FOLDER + "/CandidateListView.jsp";
	
	public String ELECTION_CTL = APP_CONTEXT + "/ctl/ElectionCtl";
	public String ELECTION_VIEW = PAGE_FOLDER + "/ElectionView.jsp";
	
	public String ELECTION_LIST_CTL = APP_CONTEXT + "/ctl/ElectionListCtl";
	public String ELECTION_LIST_VIEW = PAGE_FOLDER + "/ElectionListView.jsp";
	
	public String VOTER_APPLICATION_CTL = APP_CONTEXT + "/ctl/VoterApplicationCtl";
	public String VOTER_APPLICATION_VIEW = PAGE_FOLDER + "/VoterApplicationView.jsp";
	
	public String VOTER_APPLICATION_LIST_CTL = APP_CONTEXT + "/ctl/VoterApplicationListCtl";
	public String VOTER_APPLICATION_LIST_VIEW = PAGE_FOLDER + "/VoterApplicationListView.jsp";
	
	public String ID_PROOF_CTL = APP_CONTEXT + "/idProof";
	
	public String DO_VOTE_CTL = APP_CONTEXT + "/doVote";
	public String DO_VOTE_VIEW = PAGE_FOLDER + "/DoVoteView.jsp";
	
	public String RESULT_LIST_CTL = APP_CONTEXT + "/resultListCtl";
	public String RESULT_LIST_VIEW = PAGE_FOLDER + "/ResultListView.jsp";


}
