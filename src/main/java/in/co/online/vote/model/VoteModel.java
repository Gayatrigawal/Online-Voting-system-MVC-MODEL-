package in.co.online.vote.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.online.vote.bean.PartiesBean;
import in.co.online.vote.bean.VoteBean;
import in.co.online.vote.bean.VoterApplicationBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DatabaseException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.util.JDBCDataSource;

public class VoteModel {
	
	public Integer nextPK() throws DatabaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM vote");
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
	
	public VoterApplicationBean findByVoteId(long Id,long userid) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM voterapplication WHERE voterid=? and userid=?");
		VoterApplicationBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, Id);
			pstmt.setLong(2, userid);
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
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public long addVote(VoteBean bean) throws Exception {
		Connection conn = null;
		int pk = 0;
		
		
		VoteBean existbean = findByVoteriD(bean.getVoterid(),bean.getUserid());

		if (existbean != null) {
			System.out.println("Vote is already exists");
			throw new DuplicateRecordException("Vote already exists");
		}
		
		if (bean.getVoterid()==0) {
			System.out.println("VoteID 0");
			throw new ApplicationException("VoteID not exists");
		}
		
		VoterApplicationBean idd = findByVoteId(bean.getVoterid(),bean.getUserid());
		
		if (idd == null) {
			System.out.println("VoteID not exists");
			throw new ApplicationException("VoteID not exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO vote VALUES(?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getVoterid());
			pstmt.setString(3, bean.getVoterName());
			pstmt.setString(4, bean.getElecationName());
			pstmt.setLong(5, bean.getUserid());
			pstmt.setString(6, bean.getCandidateName());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public PartiesBean findByName(String name) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM V_Parties WHERE NAME=?");
		PartiesBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PartiesBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCreatedBy(rs.getString(3));
				bean.setModifiedBy(rs.getString(4));
				bean.setCreatedDatetime(rs.getTimestamp(5));
				bean.setModifiedDatetime(rs.getTimestamp(6));
				bean.setImage(rs.getBlob(7));
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public VoteBean findByVoteriD(long voterid,long userid) throws Exception {
		VoteBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM vote WHERE voteid=? and userid=?");
			ps.setLong(1, voterid);
			ps.setLong(2, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new VoteBean();
				bean.setId(rs.getLong(1));
				bean.setVoterid(rs.getLong(2));
				bean.setVoterName(rs.getString(3));
				bean.setElecationName(rs.getString(4));
				bean.setUserid(rs.getLong(5));
				bean.setCandidateName(rs.getString(6));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public List ResultList() throws Exception {
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM vote");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			VoteBean bean = new VoteBean();
			bean.setId(rs.getLong(1));
			bean.setVoterid(rs.getLong(2));
			bean.setVoterName(rs.getString(3));
			bean.setElecationName(rs.getString(4));
			bean.setUserid(rs.getLong(5));
			bean.setCandidateName(rs.getString(6));
			list.add(bean);
		}
		return list;
	}
	
	public List RList() throws Exception {
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT distinct candidateName FROM vote");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			VoteBean bean = new VoteBean();
			bean.setCandidateName(rs.getString(1));
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
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM vote WHERE id=?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	public List search(VoteBean bean, int pageNo, int pageSize) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM vote WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
//			if (bean.getPartyId() > 0) {
//				sql.append(" AND partyId = " + bean.getPartyId());
//			}
			if (bean.getCandidateName() != null && bean.getCandidateName().length() > 0) {
				sql.append(" AND candidateName LIKE '" + bean.getCandidateName() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new VoteBean();
				bean.setId(rs.getLong(1));
				bean.setVoterid(rs.getLong(2));
				bean.setVoterName(rs.getString(3));
				bean.setElecationName(rs.getString(4));
				bean.setUserid(rs.getLong(5));
				bean.setCandidateName(rs.getString(6));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
