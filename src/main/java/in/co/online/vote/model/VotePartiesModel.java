package in.co.online.vote.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.vote.bean.PartiesBean;
import in.co.online.vote.bean.VoteBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DatabaseException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.util.JDBCDataSource;

public class VotePartiesModel {

	private static Logger log = Logger.getLogger(VotePartiesModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM V_parties");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public Integer nextVotePK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM V_vote");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public PartiesBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
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
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk : get parameter
	 * @return bean
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public PartiesBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM V_Parties WHERE ID=?");
		PartiesBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
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
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public long add(PartiesBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		PartiesBean duplicataRole = findByName(bean.getName());

		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Party already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO V_Parties VALUES(?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setBlob(7, bean.getImage());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	public List PartyList() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from V_parties");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			PartiesBean bean = new PartiesBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setCreatedBy(rs.getString(3));
			bean.setModifiedBy(rs.getString(4));
			bean.setCreatedDatetime(rs.getTimestamp(5));
			bean.setModifiedDatetime(rs.getTimestamp(6));
			bean.setImage(rs.getBlob(7));
			list.add(bean);
		}
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Role with pagination
	 * 
	 * @return list : List of Role
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from V_parties");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PartiesBean bean = new PartiesBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCreatedBy(rs.getString(3));
				bean.setModifiedBy(rs.getString(4));
				bean.setCreatedDatetime(rs.getTimestamp(5));
				bean.setModifiedDatetime(rs.getTimestamp(6));
				bean.setImage(rs.getBlob(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

	public long addVote(VoteBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		VoteBean duplicataRole = findByVoteId(bean.getVoterId());

		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Vote already exists");
		}
		System.out.println("Bean party Id" + bean.getPartyId());
		PartiesBean pBean = findByPK(bean.getPartyId());
		bean.setPartyName(pBean.getName());
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextVotePK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO V_vote VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getPartyId());
			pstmt.setString(3, bean.getPartyName());
			pstmt.setString(4, bean.getVoterId());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	public VoteBean findByVoteId(String Id) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM V_Vote WHERE voterID=?");
		VoteBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new VoteBean();
				bean.setId(rs.getLong(1));
				bean.setPartyId(rs.getLong(2));
				bean.setPartyName(rs.getString(3));
				bean.setVoterId(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}

	public VoteBean findByVotePk(long Id) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM V_Vote WHERE ID=?");
		VoteBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new VoteBean();
				bean.setId(rs.getLong(1));
				bean.setPartyId(rs.getLong(2));
				bean.setPartyName(rs.getString(3));
				bean.setVoterId(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}

	public List search(VoteBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean     : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * 
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List search(VoteBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM V_Vote WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getPartyId() > 0) {
				sql.append(" AND partyId = " + bean.getPartyId());
			}
			if (bean.getPartyName() != null && bean.getPartyName().length() > 0) {
				sql.append(" AND partyNAME LIKE '" + bean.getPartyName() + "%'");
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
				bean.setPartyId(rs.getLong(2));
				bean.setPartyName(rs.getString(3));
				bean.setVoterId(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	public static long delete(long id) throws Exception {
		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM V_Parties WHERE id=?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	public long Update(PartiesBean bean) {
		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update V_Parties set name=?,createdBy=?,modifiedBy=?,createdDatetime=?,modifiedDatetime=?,image=? where ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getCreatedBy());
			pstmt.setString(3, bean.getModifiedBy());
			pstmt.setTimestamp(4, bean.getCreatedDatetime());
			pstmt.setTimestamp(5, bean.getModifiedDatetime());
			pstmt.setBlob(6, bean.getImage());
			pstmt.setLong(7, bean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	public List Votelist() throws Exception {
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from V_Vote");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			VoteBean bean = new VoteBean();
			bean.setId(rs.getLong(1));
			bean.setPartyId(rs.getLong(2));
			bean.setPartyName(rs.getString(3));
			bean.setVoterId(rs.getString(4));
			bean.setCreatedBy(rs.getString(5));
			bean.setModifiedBy(rs.getString(6));
			bean.setCreatedDatetime(rs.getTimestamp(7));
			bean.setModifiedDatetime(rs.getTimestamp(8));
			list.add(bean);
		}
		return list;
	}

	public List PartyLogolist() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT distinct image FROM V_Parties");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			PartiesBean bean = new PartiesBean();
			bean.setImage(rs.getBlob(1));
			list.add(bean);
		}
		return list;
	}

}
