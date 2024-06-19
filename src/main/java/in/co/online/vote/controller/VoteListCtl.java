package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.vote.bean.VoteBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.model.VotePartiesModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.PropertyReader;
import in.co.online.vote.util.ServletUtility;

/**
 * Servlet implementation class VoteListCtl
 */
@WebServlet(name = "VoteListCtl", urlPatterns = { "/ctl/VoteListCtl" })
public class VoteListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(VoteListCtl.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteListCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("VoteListCtl doGet method start");
		
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

//		VotePartiesModel model = new VotePartiesModel();
//		long id = DataUtility.getLong(request.getParameter("id"));
//		if (id>0) {
//			try {
//				//model.delete(id);
//				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		List list = null;
//		try {
//			list = model.Votelist();
//			 if (list == null || list.size() == 0) {
//					ServletUtility.setErrorMessage("No record found ", request);
//				}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ServletUtility.setList(list, request);
//		ServletUtility.forward(getView(), request, response);
		
		
//		try {
//			 VoteBean bean=new VoteBean();
//			//bean.setPartyId(1L);
//			List	list = model.search(bean, pageNo, pageSize);
//			bean.setPartyId(2L);
//			List list2 = model.search(bean, pageNo, pageSize);
//			bean.setPartyId(3L);
//			List list3 = model.search(bean, pageNo, pageSize);
 //    		request.setAttribute("list",list.size());
//			request.setAttribute("list2",list2.size());
//			request.setAttribute("list3",list3.size());
//			ServletUtility.setList(list, request);
			ServletUtility.forward(getView(), request, response);
//
//		} catch (ApplicationException e) {
//			ServletUtility.handleException(e, request, response);
//			e.printStackTrace();
//			return;
//		}
//		log.debug("VoteListCtl doGet method end");
	}

	

	@Override
	protected String getView() {
		return OVSView.VOTE_LIST_VIEW;
	}

}
