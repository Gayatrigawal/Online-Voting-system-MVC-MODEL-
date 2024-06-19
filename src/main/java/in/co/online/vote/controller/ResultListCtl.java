package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.vote.bean.ElectionBean;
import in.co.online.vote.bean.VoteBean;
import in.co.online.vote.model.ElectionModel;
import in.co.online.vote.model.VoteModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.ServletUtility;


@WebServlet(name = "ResultListCtl", urlPatterns = { "/resultListCtl" })
public class ResultListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public ResultListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");
		VoteModel model = new VoteModel();
		VoteBean bean = null;
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
			list = model.RList();
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
		return OVSView.RESULT_LIST_VIEW;
	}

}
