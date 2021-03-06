package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import model.dto.AccidentDTO;
import model.dto.BoardDTO;
import util.DBUtil;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		return instance;
	}
	private static Properties info = DBUtil.getInfo();

	public boolean registerInfoDesk(BoardDTO b) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("boardRegister"));
			pstmt.setString(1, b.getBoaTitle());
			pstmt.setString(2, b.getBoaContent());
			pstmt.setString(3, b.getMemID());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return result;
	}

	public boolean updateInfoDesk(int index, String boaTitle, String boaContent) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("boardUpdate"));
			pstmt.setString(1, boaTitle);
			pstmt.setString(2, boaContent);
			pstmt.setInt(3, index);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return result;
	}

	public boolean deleteInfoDesk(int index) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("boardDelete"));
			pstmt.setInt(1, index);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return result;
	}

	public ArrayList<BoardDTO> allInfoDesk() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BoardDTO> all = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("boardAll"));
			rset = pstmt.executeQuery();

			all = new ArrayList<BoardDTO>();

			while (rset.next()) {
				all.add(new BoardDTO(rset.getInt(1),rset.getDate(2), rset.getString(3), rset
						.getString(4),rset.getString(5)));
			}

		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return all;
	}

	public boolean registerAccident(AccidentDTO a) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("accidentRegister"));
			pstmt.setString(1,	a.getAccContent());
			pstmt.setString(2, a.getMemID());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return result;
	}

	public ArrayList<AccidentDTO> allAccident() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AccidentDTO> all = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("accidentAll"));
			rset = pstmt.executeQuery();
			all = new ArrayList<AccidentDTO>();
			while (rset.next()) {
				all.add(new AccidentDTO(rset.getInt(1),rset.getString(2), rset.getString(3), rset.getString(4)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return all;
	}

	public boolean accidentDelete(int accNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(info.getProperty("accidentDelete"));
			pstmt.setInt(1, accNo);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return result;
	}
}