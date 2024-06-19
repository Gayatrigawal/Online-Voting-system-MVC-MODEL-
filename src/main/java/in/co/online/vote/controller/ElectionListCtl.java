package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.vote.bean.ElectionBean;
import in.co.online.vote.model.ElectionModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.ServletUtility;

@WebServlet(name = "ElectionListCtl", urlPatterns = { "/ctl/ElectionListCtl" })
public class ElectionListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public ElectionListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");
		ElectionModel model = new ElectionModel();
		ElectionBean bean = null;
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
		return OVSView.ELECTION_LIST_VIEW;
	}

}
