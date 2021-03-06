package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.CarDAO;
import model.dto.CarDTO;
import model.dto.CarRateDTO;
import model.dto.CarRentTotalDTO;
import model.dto.CarResDTO;
import model.service.CarServiceImpl;
import util.Util;
import view.EndView;

public class CarController {
	private static CarServiceImpl service = CarServiceImpl.getInstance();

	public static void getAllCar() {
		try {
			ArrayList<CarDTO> car = service.carAll();
			if (car.size() != 0) {
				EndView.successMsgAll2(car);
			} else {
				EndView.msg("차량정보가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.errorMsg("모든 차량정보 검색 실패, 다시 시도해 주세요.");
		}
	}

	public static void insertCar(CarDTO car) {
		try {
			if (service.insertCar(car)) {
				EndView.msg("차량등록이 완료되었습니다.");
			} else {
				EndView.msg("입력이 되지 않았습니다. 새로 입력해주세요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.errorMsg("중복된 차량번호 등의 문제 발생, 새로운 데이터로 입력시도하세요.");
		}
	}

	public static void deleteCar(int carNo) {
		try {
			if (service.deleteCar(carNo) != 0) {
				EndView.msg("삭제가 성공되었습니다.");
			} else {
				EndView.msg("삭제 실패! 다시 시도해주세요");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.errorMsg("해당 차량 정보가 없습니다.");
		}
	}

	public static void cancleReservation(int resNo, String memID, int carNo, int reMoney) {
		try {
			if (service.cancleReservation(resNo, memID, carNo, reMoney)) {
				EndView.msg("예약번호" + resNo + "번이 취소되었습니다.");
			} else {
				EndView.errorMsg("예약 취소 실패, 재시도 하십시오.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void resInfoList(String id){
		try {
			ArrayList<CarResDTO> resInfoAll = service.resInfoList(id); 
			if (resInfoAll.size()!=0) {
				EndView.successMsgAll4(resInfoAll);
			} else {
				EndView.msg("예약정보 리스트가 없습니다.");
			}				
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.errorMsg("예약정보 검색 실패, 다시 시도해 주세요.");
		}
	}
	
	public static void rateInfoList() {
		try {
			ArrayList<CarRateDTO> rateInfoAll = service.rateInfoList();
			if (rateInfoAll.size()!=0) {
				EndView.successMsgAll3(rateInfoAll);
			} else {
				EndView.msg("요금정보 리스트가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.errorMsg("요금정보 검색 실패, 다시 시도해 주세요.");
		}
	}

	public static void rentCarList(int day, String carKind, int agtNo) {
		try {
			ArrayList<CarDTO> list = service.rentCarList(day, carKind, agtNo);
			if (list.size() != 0) {
				EndView.msg("-------- " + Util.AGENTCODE[agtNo - 1]+ " 대리점의 예약 가능 차량 리스트 --------");
				EndView.rentCarPrint(list);
			} else {
				EndView.msg("예약가능한 차량이 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static CarRentTotalDTO paymentInformation(String memID, int carNo, int day, int agtNo, int option) {
		CarRentTotalDTO crt = null;
		
		try {
			crt = service.paymentInformation(memID, carNo, day, agtNo, option);
			if (crt != null) {
				EndView.paymentInfoPrint(crt);
			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return crt;
	}

	public static boolean carPayment(String memID, CarRentTotalDTO crt) {
		boolean result = false;
		
		try {
			if (service.carPayment(memID, crt)) {
				EndView.msg("결제성공");
				result = true;
			} else {
				EndView.msg("결제실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static int carCancleReturnMoney(String memID, int resNo) {
		int ReturnMoney = 0;
		
		try {
			ReturnMoney = service.carCancleReturnMoney(memID,	resNo);
			if (ReturnMoney != 0) {
				EndView.successMsgAll5(ReturnMoney);
			} else {
				EndView.msg("정보 없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.errorMsg("예약정보에 따른 반환 금액 검색 실패, 다시 시도해 주세요.");
		}
		
		return ReturnMoney;
	}

	public static int getCarNum(int resNo){
		int carNum = 0;
		
		try {
			carNum = CarDAO.getCarNum(resNo);
			if (carNum != 0) {

			} else {
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.errorMsg("예약번호에 따른 차량번호 검색 실패.");
		}
		
		return carNum;
	}
}