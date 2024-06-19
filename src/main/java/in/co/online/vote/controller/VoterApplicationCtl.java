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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import in.co.online.vote.bean.BaseBean;
import in.co.online.vote.bean.UserBean;
import in.co.online.vote.bean.VoterApplicationBean;
import in.co.online.vote.exception.DatabaseException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.model.VoterApplicationModel;
import in.co.online.vote.util.DataUtility;
import in.co.online.vote.util.ServletUtility;

@WebServlet(name = "VoterApplicationCtl", urlPatterns = { "/ctl/VoterApplicationCtl" })
@MultipartConfig(maxFileSize = 16177215)
public class VoterApplicationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public VoterApplicationCtl() {
        super();
    }
    
    @Override
	protected BaseBean populateBean(HttpServletRequest request) {
		VoterApplicationBean bean = new VoterApplicationBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		long userId = existBean.getId();
		
		bean.setUserid(userId);
		
		bean.setFirstName(existBean.getFirstName());

		bean.setLastName(existBean.getLastName());

		bean.setLogin(existBean.getLogin());

		bean.setMobileNo(existBean.getMobileNo());
		 Blob blob = null;
	        Part filePart;
	        try {
				filePart = request.getPart("image");
				blob =  medicinePacketUpload(filePart);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        System.out.println("image :" + blob);
		bean.setIdProof(blob);
        bean.setVoterID(0);
		populateDTO(bean, request);

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=DataUtility.getString(request.getParameter("operation"));
		VoterApplicationModel model=new VoterApplicationModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			VoterApplicationBean bean=(VoterApplicationBean)populateBean(request);
					try {
						long pk=model.add(bean);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
					} catch (DuplicateRecordException e) {
						ServletUtility.setErrorMessage(e.getMessage(), request);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return OVSView.VOTER_APPLICATION_VIEW;
	}

}
