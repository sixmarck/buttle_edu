package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import model.dto.MemberDTO;
import model.dto.MemberOneSalsDTO;
import model.dto.SessionDTO;
import util.DBUtil;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {}	
	public static MemberDAO getInstance() {
		return instance;
	}
	private static Properties info = DBUtil.getInfo();
	
	public boolean insertMember(MemberDTO m) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("insertMember"));
			pstmt.setString(1, m.getMemID());
			pstmt.setString(2, m.getMemPW());
			pstmt.setString(3, m.getMemName());
			pstmt.setString(4, m.getMemTel());
			pstmt.setString(5, m.getMemBirthday());
			pstmt.setString(6, m.getMemLicense());
			pstmt.setString(7, m.getMemAddress());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return result;
	}
	
	public SessionDTO memberLogin(String memID, String memPW) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SessionDTO session = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("memberLogin"));
			pstmt.setString(1, memID);
			pstmt.setString(2, memPW);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				session = new SessionDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getInt(4),rset.getInt(5));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return session;
	}
	
	public int getMemberMileage(String memID, Connection con) throws SQLException {
		int money = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try{
			pstmt = con.prepareStatement(info.getProperty("getMemberMileage"));
			pstmt.setString(1, memID);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				money = rset.getInt(1);
			}
		} finally {
			DBUtil.close(pstmt, rset);
		}
		
		return money;
	}
	
	public MemberOneSalsDTO selectMemberOneSals(String memID) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MemberOneSalsDTO m = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("selectMemberOneSals"));
			pstmt.setString(1, memID);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new MemberOneSalsDTO(rset.getInt(1), rset.getDouble(2));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return m;
	}
	public int getMileage(String memID) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("money"));
			pstmt.setString(1, memID);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return result;
	}
}
