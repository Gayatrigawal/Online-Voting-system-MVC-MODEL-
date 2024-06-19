package in.co.online.vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.vote.bean.BaseBean;
import in.co.online.vote.bean.PartiesBean;
import in.co.online.vote.bean.VoteBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.model.VotePartiesModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.DataValidator;
import in.co.online.vote.util.PropertyReader;
import in.co.online.vote.util.ServletUtility;

/**
 * Servlet implementation class VoteCtl
 */
@WebServlet(name="VoteCtl",urlPatterns={"/ctl/VoteCtl"})
public class VoteCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(VoteCtl.class);
	
	
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("VoteCtl preload method start");
		VotePartiesModel model = new VotePartiesModel();
		try {
			List l = model.list();
			request.setAttribute("partiesList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("VoteCtl preload method end");
	}
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("VoteCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("voterId"))) {
            request.setAttribute("voterId",
                    PropertyReader.getValue("error.require", "Voter Id"));
            pass = false;
        }
        
        if ("-----Select-----".equalsIgnoreCase(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Parties Name"));
			pass = false;
		}

        

        log.debug("VoteCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("VoteCtl populateBean method start");
		VoteBean bean=new VoteBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setVoterId(DataUtility.getString(request.getParameter("voterId")));
		bean.setPartyId(DataUtility.getLong(request.getParameter("name")));
		populateDTO(bean, request);
		log.debug("VoteCtl populateBean method end");
		return bean;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("VoteCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   VotePartiesModel model = new VotePartiesModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				VoteBean bean;
				try {
					bean = model.findByVotePk(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("VoteCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("VoteCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			VotePartiesModel model=new VotePartiesModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				VoteBean bean=(VoteBean)populateBean(request);
					try {
						if(id>0){
							
						/*model.update(bean);*/
						ServletUtility.setOpration("Edit", request);
						ServletUtility.setSuccessMessage("Data is successfully Updated", request);
		                ServletUtility.setBean(bean, request);

						}else {
							long pk=model.addVote(bean);
							//bean.setId(id);
							ServletUtility.setSuccessMessage("You have vote successfully ", request);
							ServletUtility.forward(getView(), request, response);
						}
		              
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.forward(OVSView.ERROR_VIEW, request, response);
						return;
					
				} catch (DuplicateRecordException e) {
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Vote already exists",
							request);
				}
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("VoteCtl doPost method end");
	}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OVSView.VOTE_VIEW;
	}

}
