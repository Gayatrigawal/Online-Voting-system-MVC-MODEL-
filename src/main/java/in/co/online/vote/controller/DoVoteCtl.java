package in.co.online.vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.online.vote.bean.BaseBean;
import in.co.online.vote.bean.UserBean;
import in.co.online.vote.bean.VoteBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.model.VoteModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.DataValidator;
import in.co.online.vote.util.PropertyReader;
import in.co.online.vote.util.ServletUtility;

@WebServlet(name = "DoVoteCtl", urlPatterns = { "/doVote" })
public class DoVoteCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public DoVoteCtl() {
		super();
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("voterId"))) {
			request.setAttribute("voterId", PropertyReader.getValue("error.require", "Voter Id"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		VoteBean bean = new VoteBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setVoterid(DataUtility.getLong(request.getParameter("voterId")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean) session.getAttribute("user");
		long userId = existBean.getId();
		bean.setUserid(userId);
		bean.setVoterName(existBean.getFirstName());
		bean.setCandidateName(DataUtility.getString(request.getParameter("candidateName")));
		populateDTO(bean, request);
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VoteBean bean = new VoteBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCandidateName(DataUtility.getString(request.getParameter("candidateName")));
		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		VoteModel model = new VoteModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			VoteBean bean = (VoteBean) populateBean(request);
			bean.setCandidateName(DataUtility.getString(request.getParameter("candidateName")));
			try {
				long pk = model.addVote(bean);
				ServletUtility.setSuccessMessage("Thanks for vote your Candidate", request);
			} catch (ApplicationException e) {
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} catch (Exception e) {
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return OVSView.DO_VOTE_VIEW;
	}

}
