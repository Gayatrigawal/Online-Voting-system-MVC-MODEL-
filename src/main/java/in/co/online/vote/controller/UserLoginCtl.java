package in.co.online.vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.vote.bean.BaseBean;
import in.co.online.vote.bean.UserBean;
import in.co.online.vote.bean.VoteBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.model.UserModel;
import in.co.online.vote.model.VotePartiesModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.DataValidator;
import in.co.online.vote.util.PropertyReader;
import in.co.online.vote.util.ServletUtility;

/**
 * Servlet implementation class UserLoginCtl
 */
@WebServlet(name = "UserLoginCtl", urlPatterns = { "/UserLoginCtl" })
public class UserLoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SUBMIT = "Submit";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
	public static String HIT_URI = null;
	
	private  static Logger log = Logger.getLogger(UserLoginCtl.class);

@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserLoginCtl Method validate Started");
		
		boolean pass = true;
		
		String op = request.getParameter("operation");
		
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		
		
		String login = request.getParameter("voterId");
		
		if (DataValidator.isNull(login)) {
			request.setAttribute("voterId", PropertyReader.getValue("error.require", "Voter Id"));
			pass = false;
		
		} 
		log.debug("UserLoginCtl Method validate Ended");
		return pass;
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserLoginCtl Method populateBean Started");

		UserBean bean = new UserBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setVoterId(DataUtility.getString(request.getParameter("voterId")));
		

		log.debug("UserLoginCtl Method PopulatedBean End");

		return bean;
	}

	/**
	 * Display Login form
	 * 
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("Method doGet Started");
		
		
		HttpSession session = request.getSession(true);
		String op = DataUtility.getString(request.getParameter("operation"));
		
		UserModel model = new UserModel();
		
		
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			UserBean userBean;
			try {
				userBean = model.findByPK(id);
				ServletUtility.setBean(userBean, request);
		
			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_LOG_OUT.equals(op)) {
			session = request.getSession(false);
			session.invalidate();
			ServletUtility.setSuccessMessage("You have been logged out successfully", request);
			
			ServletUtility.forward(OVSView.USER_LOGIN_VIEW, request, response);
			return;
		}
		if (session.getAttribute("user") != null) {
			ServletUtility.redirect(OVSView.WELCOME_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("Method doGet end");
	}

	/**
	 * Submit Logic
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		log.debug(" UserLoginCtl Method doPost Started");
		
		HttpSession session = request.getSession(true);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get Model
		UserModel model = new UserModel();
	
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		if (OP_SUBMIT.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				
				VotePartiesModel vModel=new VotePartiesModel();
				VoteBean	vBean=vModel.findByVoteId(bean.getVoterId());
				
				if(vBean==null) {
					
					System.out.println("Voter Id==="+bean.getVoterId());
					bean = model.findByLogin(bean.getVoterId());
				
				if (bean != null) {
					session.setAttribute("user", bean);
					session.setMaxInactiveInterval(10 * 6000);

					long rollId = bean.getRoleId();
					
					
					// save state of session remember URL
					String uri = request.getParameter("uri");
					
					
					if (uri == null || "null".equalsIgnoreCase(uri)) {
						ServletUtility.redirect(OVSView.VOTE_CTL, request, response);
						return;
					} else {
						ServletUtility.redirect(uri, request, response);
					}
					return;
				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid Voter ID", request);
				}
				}else {
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("You Have Already Vote", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				
				return;
			}
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OVSView.USER_REGISTRATION_CTL, request, response);
		return;
		}
		
		
		ServletUtility.forward(getView(), request, response);
		log.debug("UserLoginCtl Method doPost Ended");
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return OVSView.USER_LOGIN_VIEW;
	}

}
