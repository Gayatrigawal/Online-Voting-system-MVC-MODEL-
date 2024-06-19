package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.vote.model.VotePartiesModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.ServletUtility;

@WebServlet(name="PartiesListCtl",urlPatterns={"/ctl/PartiesListCtl"})
public class PartiesListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public PartiesListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VotePartiesModel model = new VotePartiesModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id>0) {
			try {
				model.delete(id);
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List list = null;
		try {
			list = model.list();
			 if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
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
		return OVSView.PARTIES_LIST_VIEW;
	}

}
