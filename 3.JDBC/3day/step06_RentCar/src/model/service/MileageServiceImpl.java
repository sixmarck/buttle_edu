package model.service;

import java.sql.Connection;
import java.sql.SQLException;

import model.dao.MileageDAO;
import model.dto.CarRentTotalDTO;
import util.DBUtil;

public class MileageServiceImpl  implements MileageServiceIF{
	private static MileageServiceImpl instance = new MileageServiceImpl();
	private static MileageDAO dao = MileageDAO.getInstance();
	public static MileageServiceImpl getInstance(){
		return instance;
	}
	private MileageServiceImpl(){}
	
	@Override
	public boolean mileageCharge(String mileCode, int inputMoney, String memid) throws SQLException {
		Connection con = null;
		boolean result = false;
		
		try{
			con = dao.mileageCharge(mileCode, inputMoney, memid);
			con = dao.mileageUpdate(con, inputMoney, memid);
			con.commit();
			result = true;
		} catch(SQLException e) {
			con.rollback();
		} finally {
			DBUtil.close(con);
		}
		
		return result;
	}

	@Override
	public boolean mileageCharge(String mileCode, int inputMoney, String memid, CarRentTotalDTO crt) throws SQLException {
		Connection con = null;
		boolean result = false;
		
		try{
			con = dao.mileageCharge(mileCode, inputMoney, memid);
			con = dao.mileageUpdate(con, inputMoney, memid);
			con.commit();
			result = true;
		} catch(SQLException e) {
			con.rollback();
		} finally {
			DBUtil.close(con);
		}
		
		return result;
	}
}
