package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.vote.bean.CandidateBean;
import in.co.online.vote.model.CandidateModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.ServletUtility;


@WebServlet(name = "CandidateListCtl", urlPatterns = { "/ctl/CandidateListCtl" })
public class CandidateListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public CandidateListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");
		CandidateModel model = new CandidateModel();
		CandidateBean bean = new CandidateBean();
		String elecationName = DataUtility.getString(request.getParameter("elecationName"));
		System.out.println("elecationName :"+elecationName);
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
			list = model.list();
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
		return OVSView.CANDIDATE_LIST_VIEW;
	}

}
