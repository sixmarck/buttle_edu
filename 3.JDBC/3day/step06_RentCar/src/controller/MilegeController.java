package controller;

import java.sql.SQLException;

import model.dto.CarRentTotalDTO;
import model.service.MileageServiceImpl;
import view.EndView;

public class MilegeController {

	public static void updateMileage(String mileCode, int inputMoney,	String memid) {
		try {
			if(MileageServiceImpl.getInstance().mileageCharge(mileCode, inputMoney, memid)) {
				EndView.msg(inputMoney + "���� �����Ǿ����ϴ�.");
			} else {
				EndView.errorMsg("���� ����, ��õ� �ϼ���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static CarRentTotalDTO updateMileage(String mileCode, int inputMoney, String memid, CarRentTotalDTO crt) {
		try {
			if(MileageServiceImpl.getInstance().mileageCharge(mileCode, inputMoney, memid, crt)) {
				EndView.msg(inputMoney + "���� �����Ǿ����ϴ�.");
				crt.setMemMilage(crt.getMemMilage() + inputMoney);
			} else {
				EndView.errorMsg("���� ����, ��õ� �ϼ���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return crt;
	}
}