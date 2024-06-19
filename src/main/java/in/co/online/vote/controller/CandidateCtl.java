package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.vote.bean.BaseBean;
import in.co.online.vote.bean.CandidateBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.model.CandidateModel;
import in.co.online.vote.model.VotePartiesModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.DataValidator;
import in.co.online.vote.util.PropertyReader;
import in.co.online.vote.util.ServletUtility;

@WebServlet(name = "CandidateCtl", urlPatterns = { "/ctl/CandidateCtl" })
public class CandidateCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public CandidateCtl() {
        super();
    }
    
    @Override
   	protected void preload(HttpServletRequest request) {
       	VotePartiesModel model = new VotePartiesModel();
   			try {
   					List partylist = model.PartyList();
   					request.setAttribute("partyName", partylist);
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
                   	
   	}
    
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.password", "Password"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", "Min Age Must be 17 years");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}
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
		CandidateBean bean = new CandidateBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setPartyid(DataUtility.getLong(request.getParameter("partyid")));

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateModel model = new CandidateModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			CandidateBean bean = null;
			try {
				bean = model.findByPK(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setBean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		CandidateModel model = new CandidateModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			CandidateBean bean = (CandidateBean) populateBean(request);
			try {
				if (id>0) {
					model.Update(bean);
					ServletUtility.setSuccessMessage("Candidate Successfully Update", request);
				}else {
				 System.out.println("in try sign up");
				long pk = model.add(bean);
				 System.out.println("register");
				bean.setId(pk);
				request.getSession().setAttribute("CandidateBean", bean);
				ServletUtility.setSuccessMessage("Candidate Successfully Registered", request);
				}
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OVSView.USER_REGISTRATION_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return OVSView.CANDIDATE_VIEW;
	}

}
