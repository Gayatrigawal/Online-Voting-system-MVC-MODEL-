package in.co.online.vote.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;

import in.co.online.vote.bean.BaseBean;
import in.co.online.vote.bean.PartiesBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.model.VotePartiesModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.DataValidator;
import in.co.online.vote.util.PropertyReader;
import in.co.online.vote.util.ServletUtility;

/**
 * Servlet implementation class PartiesCtl
 */
@WebServlet(name="PartiesCtl",urlPatterns={"/ctl/PartiesCtl"})
@MultipartConfig(maxFileSize = 16177215)
public class PartiesCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(PartiesCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("PartiesCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }

        

        log.debug("PartiesCtl validate method end");
        return pass;
    }

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PartiesCtl populateBean method start");
		PartiesBean bean=new PartiesBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		 Blob blob = null;
	        Part filePart;
	        try {
				filePart = request.getPart("image");
				blob =  medicinePacketUpload(filePart);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        System.out.println("image :" + blob);
		bean.setImage(blob);
		populateDTO(bean, request);
		log.debug("PartiesCtl populateBean method end");
		return bean;
	}
	
	  public Blob medicinePacketUpload(Part part) throws IOException {
			InputStream inputStream = null;
			Blob blob = null;
			inputStream = part.getInputStream();
			byte[] b = new byte[inputStream.available()];
			inputStream.read(b);
			try {
				blob = new SerialBlob(b);
			} catch (SerialException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return blob;
		}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PartiesCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   VotePartiesModel model = new VotePartiesModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				PartiesBean bean;
				try {
					bean = model.findByPK(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("PartiesCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("PartiesCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			VotePartiesModel model=new VotePartiesModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				PartiesBean bean=(PartiesBean)populateBean(request);
					try {
						if(id>0){
						model.Update(bean);
						//ServletUtility.setOpration("Edit", request);
						ServletUtility.setSuccessMessage("Data is successfully Updated", request);
		              //  ServletUtility.setBean(bean, request);

						}else {
							long pk=model.add(bean);
							//bean.setId(id);
							ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						//	ServletUtility.forward(getView(), request, response);
						}
		              
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.forward(OVSView.ERROR_VIEW, request, response);
						return;
					
				} catch (DuplicateRecordException e) {
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Party already exists",
							request);
				}
				
			
		
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("PartiesCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OVSView.PARTY_VIEW;
	}

}
