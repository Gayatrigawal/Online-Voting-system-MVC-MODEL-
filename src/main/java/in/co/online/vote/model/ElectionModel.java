package in.co.online.vote.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.online.vote.bean.CandidateBean;
import in.co.online.vote.bean.ElectionBean;
import in.co.online.vote.bean.PartiesBean;
import in.co.online.vote.exception.ApplicationException;
import in.co.online.vote.exception.DuplicateRecordException;
import in.co.online.vote.util.JDBCDataSource;

public class ElectionModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM election");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public ElectionBean findByPk(long pk) throws Exception {
		ElectionBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM election WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new ElectionBean();
				bean.setId(rs.getLong(1));
				bean.setElectionName(rs.getString(2));
				bean.setElectionDate(rs.getDate(3));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public ElectionBean findByName(String name) throws Exception {
		ElectionBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM election WHERE name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new ElectionBean();
				bean.setId(rs.getLong(1));
				bean.setElectionName(rs.getString(2));
				bean.setElectionDate(rs.getDate(3));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public long add(ElectionBean bean) throws Exception {
		System.out.println("ADD MEthdo");
		Connection conn = null;
		int pk = 0;

		ElectionBean existbean = findByName(bean.getElectionName());

		if (existbean != null) {
			throw new DuplicateRecordException("Name already exists");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO election VALUES(?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getElectionName());
			pstmt.setDate(3, new java.sql.Date(bean.getElectionDate().getTime()));
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
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from election");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ElectionBean bean = new ElectionBean();
			bean.setId(rs.getLong(1));
			bean.setElectionName(rs.getString(2));
			bean.setElectionDate(rs.getDate(3));
			list.add(bean);
		}
		return list;
	}

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from election where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public long Update(ElectionBean bean) throws ApplicationException {
		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update election set name=?,date=? where ID=?");
			pstmt.setString(1, bean.getElectionName());
			pstmt.setDate(2, new java.sql.Date(bean.getElectionDate().getTime()));
			pstmt.setLong(3, bean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}


	

}
