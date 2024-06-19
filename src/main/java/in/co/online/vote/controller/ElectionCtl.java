package in.co.online.vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.vote.bean.BaseBean;
import in.co.online.vote.bean.ElectionBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.model.ElectionModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.DataValidator;
import in.co.online.vote.util.PropertyReader;
import in.co.online.vote.util.ServletUtility;

@WebServlet(name = "ElectionCtl", urlPatterns = { "/ctl/ElectionCtl" })
public class ElectionCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public ElectionCtl() {
        super();
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		String date = request.getParameter("date");

		if (DataValidator.isNull(request.getParameter("electionName"))) {
			request.setAttribute("electionName", PropertyReader.getValue("error.require", "Election Name"));
			pass = false;
		}

		if (DataValidator.isNull(date)) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		} 
		return pass;
	}
    
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		ElectionBean bean = new ElectionBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setElectionName(DataUtility.getString(request.getParameter("electionName")));

		bean.setElectionDate(DataUtility.getDate(request.getParameter("date")));

		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ElectionModel model = new ElectionModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			ElectionBean bean = null;
			try {
				bean = model.findByPk(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setBean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method");
		System.out.println("Do Post");
		ElectionModel model = new ElectionModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		ElectionBean bean = new ElectionBean();
		bean = (ElectionBean) populateBean(request);
		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (ElectionBean) populateBean(request);
			try {
				if (id>0) {
					model.Update(bean);
					ServletUtility.setSuccessMessage("Election Updated Successfully", request);
				}else {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Election Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
				return;
				}				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	}

	@Override
	protected String getView() {
		return OVSView.ELECTION_VIEW;
	}

}
