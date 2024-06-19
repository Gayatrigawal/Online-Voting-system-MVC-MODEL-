package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.vote.bean.CandidateBean;
import in.co.online.vote.bean.UserBean;
import in.co.online.vote.model.CandidateModel;
import in.co.online.vote.model.UserModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.ServletUtility;


@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
public class UserListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public UserListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");
		UserModel model = new UserModel();
		UserBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			try {
				model.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}
		List list = null;
		try {
			list = model.Userlist();
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.setList(list, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return OVSView.USER_LIST_VIEW;
	}

}
