package in.co.online.vote.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.online.vote.bean.CandidateBean;
import in.co.online.vote.bean.VoterApplicationBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DatabaseException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.util.JDBCDataSource;

public class VoterApplicationModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM voterapplication");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public VoterApplicationBean findByLogin(String login) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM voterapplication WHERE login=?");
		VoterApplicationBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new VoterApplicationBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setMobileNo(rs.getString(5));
				bean.setUserid(rs.getLong(6));
				bean.setIdProof(rs.getBlob(7));
	            bean.setVoterID(rs.getLong(8));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public long add(VoterApplicationBean bean) throws Exception {
		System.out.println("ADD MEthdo");
		Connection conn = null;
		int pk = 0;

		VoterApplicationBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Name already exists");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO voterapplication VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setLong(6, bean.getUserid());
			pstmt.setBlob(7, bean.getIdProof());
			pstmt.setLong(8, bean.getVoterID());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
		e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}
	
	public List list() throws Exception {
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from voterapplication");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			VoterApplicationBean bean = new VoterApplicationBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setMobileNo(rs.getString(5));
			bean.setUserid(rs.getLong(6));
			bean.setIdProof(rs.getBlob(7));
            bean.setVoterID(rs.getLong(8));
			list.add(bean);
		}
		return list;
	}
	
	public List Voterlist(long userid) throws Exception {
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from voterapplication where userid=?");
		pstmt.setLong(1, userid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			VoterApplicationBean bean = new VoterApplicationBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setMobileNo(rs.getString(5));
			bean.setUserid(rs.getLong(6));
			bean.setIdProof(rs.getBlob(7));
			bean.setVoterID(rs.getLong(8));
			list.add(bean);
		}
		return list;
	}
	
	public static long delete(long id) throws Exception {
		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM voterapplication WHERE id=?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}
	
	public long update(long voterid, long Rid) {
		System.out.println("in model 22222 update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("update voterapplication set voterid='" + voterid + "' where id=?");
			ps.setLong(1, Rid);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}
	
	public long VoterID() throws DatabaseException {
		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(voterid) FROM voterapplication");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		if(pk>0) {
		return pk + 1;
		}else {
		return 10201;
		}
	}

}
