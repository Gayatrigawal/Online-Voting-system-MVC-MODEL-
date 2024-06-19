package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.online.vote.bean.UserBean;
import in.co.online.vote.bean.VoterApplicationBean;
import in.co.online.vote.exception.DatabaseException;
import in.co.online.vote.model.VoterApplicationModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.ServletUtility;

@WebServlet(name = "VoterApplicationListCtl", urlPatterns = { "/ctl/VoterApplicationListCtl" })
public class VoterApplicationListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public VoterApplicationListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VoterApplicationModel model = new VoterApplicationModel();
		VoterApplicationBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));
		long Vid = DataUtility.getLong(request.getParameter("vid"));
		if (Vid>0){
		try {
			long voterid = 	model.VoterID();
			long i = model.update(voterid, Vid);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		}
		if (id > 0) {
			try {
				model.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}
		List list = null;
        HttpSession session = request.getSession(false);
    	UserBean bean2 = (UserBean) session.getAttribute("user");
    	long roleid = bean2.getRoleId();
    	if (roleid==3) {
    		   try {
    			     list =	model.Voterlist(bean2.getId());
    			     if (list == null || list.size() == 0) {
    						ServletUtility.setErrorMessage("No record found ", request);
    					}
    			     ServletUtility.setList(list, request);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
		}else{
			 try {
			     list =	model.list();
			     if (list == null || list.size() == 0) {
						ServletUtility.setErrorMessage("No record found ", request);
					}
			     ServletUtility.setList(list, request);
			} catch (Exception e) {
				e.printStackTrace();
		}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return OVSView.VOTER_APPLICATION_LIST_VIEW;
	}

}
