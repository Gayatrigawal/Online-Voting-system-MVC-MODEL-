package in.co.online.vote.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.online.vote.bean.CandidateBean;
import in.co.online.vote.bean.PartiesBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DatabaseException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.util.JDBCDataSource;

public class CandidateModel {
	
	public Integer nextPK() throws DatabaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM candidate");
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
		return pk + 1;
	}
	
	public long add(CandidateBean bean) throws ApplicationException, DuplicateRecordException {
		System.out.println("ADD MEthdo");
		Connection conn = null;
		int pk = 0;

		CandidateBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		
		VotePartiesModel vpmodel = new VotePartiesModel();
		PartiesBean pbean =  vpmodel.findByPK(bean.getPartyid());
		String partyName = pbean.getName();

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO candidate VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getGender());
			pstmt.setLong(9, bean.getPartyid());
			pstmt.setString(10, partyName);
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

	public CandidateBean findByLogin(String login) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM candidate WHERE login=?");
		CandidateBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CandidateBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setGender(rs.getString(8));
				bean.setPartyid(rs.getLong(9));
				bean.setPartyName(rs.getString(10));
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
	
	public CandidateBean findByPK(long pk) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM candidate WHERE ID=?");
		CandidateBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CandidateBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setGender(rs.getString(8));
				bean.setPartyid(rs.getLong(9));
				bean.setPartyName(rs.getString(10));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public List list() throws Exception {
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from candidate");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			CandidateBean bean = new CandidateBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setMobileNo(rs.getString(7));
			bean.setGender(rs.getString(8));
			bean.setPartyid(rs.getLong(9));
			bean.setPartyName(rs.getString(10));
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
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM candidate WHERE id=?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	public long Update(CandidateBean bean) throws ApplicationException {
		System.out.println("in model update method");
		int pk = 0;
		VotePartiesModel vpmodel = new VotePartiesModel();
		PartiesBean pbean =  vpmodel.findByPK(bean.getPartyid());
		String partyName = pbean.getName();
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update candidate set firstName=?,lastName=?,login=?,password=?,dob=?,mobileNo=?,gender=?,partyid=?,partyName=? where ID=?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getGender());
			pstmt.setLong(8, bean.getPartyid());
			pstmt.setString(9, partyName);
			pstmt.setLong(10, bean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}


}
