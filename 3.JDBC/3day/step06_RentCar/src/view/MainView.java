package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.dto.AccidentDTO;
import model.dto.BoardDTO;
import model.dto.CarDTO;
import model.dto.CarRentTotalDTO;
import model.dto.MemberDTO;
import model.dto.MemberUpdateGradeDTO;
import model.dto.SessionDTO;
import util.Util;
import controller.AdminController;
import controller.BoardController;
import controller.CarController;
import controller.MemberController;
import controller.MilegeController;

public class MainView {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static SessionDTO session = null;

	public static boolean startForm() throws IOException {
		boolean result = false;
		System.out.print("1. ȸ������\n2. �α���\n3. ����\n���� : ");
		int index = Integer.parseInt(reader.readLine());
		switch (index) {
			case Util.NO1:
				// ȸ������
				memberRegisterForm();
				break;
			case Util.NO2:
				// �α���
				memberLoginForm();
				break;
			case Util.NO3:
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void memberRegisterForm() throws IOException {
		System.out.print("���̵� : ");
		String memID = reader.readLine();
		System.out.print("��й�ȣ : ");
		String memPW = reader.readLine();
		System.out.print("�̸� : ");
		String memName = reader.readLine();
		System.out.print("��ȭ��ȣ : ");
		String memTel = reader.readLine();
		System.out.print("������� : ");
		String memBirthday = reader.readLine();
		System.out.print("��������ȣ : ");
		String memLicense = reader.readLine();
		System.out.print("�ּ� : ");
		String memAddress = reader.readLine();

		MemberController.insertMember(new MemberDTO(memID, memPW, memName,
				memTel, memBirthday, memLicense, memAddress));
	}

	public static void memberLoginForm() throws IOException {
		String id = null;
		String pw = null;
		System.out.print("���̵� : ");
		id = reader.readLine();
		System.out.print("��й�ȣ : ");
		pw = reader.readLine();
		memberLogin(id, pw);
	}

	public static void memberLogin(String id, String pw) throws IOException{
		session = MemberController.memberLogin(id, pw);
		if(session == null){
			System.out.println("���̵� �� �н����尡 �߸��Ǿ����ϴ�.");
		} else {
			switch (session.getDisGrade()) {
				case Util.GRADE_CEO :
					System.out.println(session.getMemName()+"(�����)");
					ceoForm();
					break;
				case Util.GRADE_ADMIN :
					System.out.println(session.getMemName()+"(" + Util.AGENTCODE[session.getAgtNo()-1]+ "�������)");
					adminForm();
					break;
				case Util.GRADE_VVIP :
				case Util.GRADE_VIP :
				case Util.GRADE_NORMAL :
				case Util.GRADE_BAD :
					AdminController.returnCar(session.getMemID());
					System.out.println(session.getMemName()+" ȸ���� ȯ���մϴ�.");
					userForm();
					break;
				default:
					break;
			}
		}
	}

	public static void adminForm() throws IOException {
		while(true) {
			System.out.print("1. ȸ������\n2. ��������\n3. �������\n4. ������Ȳ\n5. �������\n6. �α׾ƿ�\n���� : ");
			if(admin(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean admin(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1:
				// ȸ������
				adminMemberForm();
				break;
			case Util.NO2:
				// ��������
				carForm();
				break;
			case Util.NO3:
				// �������
				adminSalseForm();
				break;
			case Util.NO4:
				// ������Ȳ
				adminRentForm();
				break;
			case Util.NO5:
				// �������
				accidentForm();
				break;
			case Util.NO6:
				// �α׾ƿ�
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void adminMemberForm() throws IOException {
		while (true) {
			System.out.print("1. ȸ����ü����\n2. ȸ����޺�����\n3. ��ް���\n4. ���ư���\n���� : ");
			if(adminMember(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean adminMember(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1 :
				// ȸ����ü����
				AdminController.allMemberList();
				break;
			case Util.NO2 :
				// ȸ����޺�����
				gradeMemberForm();
				break;
			case Util.NO3 :
				// ��ް���
				gradeUpdateForm();
				break;
			case Util.NO4 :
				// ���ư���
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void gradeUpdateForm() throws IOException {
		System.out.println("---------������ ����-----------");
		ArrayList<MemberUpdateGradeDTO> list = AdminController.adminGradeSelectMember(); 
		if(list.size() != 0) {
			gradeUpdate(list);
		}
	}
	
	public static void gradeUpdate(ArrayList<MemberUpdateGradeDTO> list) throws IOException {
		System.out.print("��� ��� ��û �ұ��? [1] ��, [2] �ƴϿ�");
		if(Integer.parseInt(reader.readLine()) == 1) {
			AdminController.adminGradeUpdateMember(list);
		}
	}

	public static void carForm() throws IOException {
		while (true) {
			System.out.print("1. �������\n2. ��������\n3. �����ݳ�����\n4. ���ư���\n���� : ");
			if(car(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean car(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1 :
				// �������
				carRegisterForm();
				break;
			case Util.NO2 :
				// ��������
				carDeleteForm();
				break;
			case Util.NO3 :
				// �����ݳ�����
				returnCarForm();
				break;
			case Util.NO4 :
				// ���ư���
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void carRegisterForm() throws IOException {
		System.out.print("�ڵ����� : ");
		String carName = reader.readLine();
		System.out.print("������ȣ : ");
		String carNum = reader.readLine();
		System.out.print("ž���ο� : ");
		String carPax = reader.readLine();
		System.out.print("�������� ex) [1] ����, [2] ����, [3] ������, [4] SUV, [5] ������ : ");
		String carKind = Util.CARKIND[Integer.parseInt(reader.readLine())-1];
		System.out.print("�⺻�� : ");
		int carPrice = Integer.parseInt(reader.readLine());
		System.out.print("���� : ");
		String carFuel = reader.readLine();
		CarController.insertCar(new CarDTO(session.getAgtNo(),carName,carNum,carPax,carKind,carPrice,carFuel));
	}

	public static void carDeleteForm() throws IOException {
		System.out.print("������ ������ȣ ���� : ");
		int carNo = Integer.parseInt(reader.readLine());
		CarController.deleteCar(carNo);
	}

	public static void returnCarForm() throws IOException {
		if(AdminController.returnCar(session.getAgtNo())) {
			System.out.print("�ݳ��� ������ �����ȣ �Է� : ");
			AdminController.updateStateCar(Integer.parseInt(reader.readLine()));
		}
	}
	
	public static void adminSalseForm() throws IOException {
		while (true) {
			System.out.print("1. ���⸮��Ʈ\n2. ���ư���\n���� : ");
			if(adminSalse(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean adminSalse(int index) throws IOException {
		boolean result = false;
		switch (index) {
		case Util.NO1:
			// ���⸮��Ʈ
			agentBySalse();
			break;
		case Util.NO2:
			// ���ư���
			result = true;
			break;
		default:
			break;
		}
		
		return result;
	}

	public static void adminRentForm() throws IOException {
		while (true) {
			System.out.print("1. ��Ʈ��Ȳ\n2. ���ư���\n���� : ");
			if(adminRent(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean adminRent(int index) throws IOException {
		boolean result = false;
		switch (index) {
		case Util.NO1:
			// ��Ʈ��Ȳ
			agentByRentForm();
			break;
		case Util.NO2:
			// ���ư���
			result = true;
			break;
		default:
			break;
		}
		
		return result;
	}

	public static void accidentForm() throws IOException {
		while (true) {
			System.out.print("1. �������\n2. �������\n3. ���ư���\n���� : ");
			if(accident(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean accident(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1:
				// �������
				accidentRegisterForm();
				break;
			case Util.NO2:
				// �������
				accidentDeleteForm();
				break;
			case Util.NO3:
				// ���ư���
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void accidentRegisterForm() throws IOException {
		System.out.print("����� : ");
		String memID = reader.readLine();
		System.out.print("���� : ");
		String accContent = reader.readLine();
		BoardController.accidentBoard(new AccidentDTO(memID , accContent));
	}

	public static void accidentDeleteForm() throws IOException {
		System.out.print("������ �����ȣ ���� : ");
		int accNo =Integer.parseInt(reader.readLine());
		BoardController.accidentDelete(accNo);
	}

	public static void ceoForm() throws IOException {
		while (true) {
			System.out.print("1. ȸ������\n2. �������\n3. ��Ʈ��Ȳ\n4. �α׾ƿ�\n���� : ");
			if(ceo(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean ceo(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1:
				// ȸ������
				ceoMemberForm();
				break;
			case Util.NO2:
				// �������
				ceoSalseForm();
				break;
			case Util.NO3:
				// ��Ʈ��Ȳ
				ceoRentForm();
				break;
			case Util.NO4:
				// �α׾ƿ�
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void ceoMemberForm() throws IOException {
		while (true) {
			System.out.print("1. ȸ����ü����\n2. ȸ����޺�����\n3. ���ư���\n���� : ");
			if(ceoMember(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean ceoMember(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1 :
				// ȸ����ü����
				AdminController.allMemberList();
				break;
			case Util.NO2 :
				// ȸ����޺�����
				gradeMemberForm();
				break;
			case Util.NO3 :
				// ���ư���
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void gradeMemberForm() throws IOException {
		System.out.print("��޼��� ex) [1] Bad, [2] Normal, [3] VIP, [4] VVIP : ");
		int disGrade = Integer.parseInt(reader.readLine());
		AdminController.allGradeMemberList(Util.GRADECODE[disGrade-1]);
	}

	public static void ceoSalseForm() throws IOException {
		while (true) {
			System.out.print("1. ��ü�븮�����⸮��Ʈ\n2. �븮�������⸮��Ʈ\n3. ���ư���\n���� : ");
			if(ceoSalse(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean ceoSalse(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1 :
				// ��ü�븮�����⸮��Ʈ
				System.out.println("ceo");
				AdminController.allAgentBySalseList();
				break;
			case Util.NO2 :
				// �븮�������⸮��Ʈ
				agentBySalse();
				break;
			case Util.NO3 :
				// ���ư���
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void agentBySalse() throws IOException {
		System.out.print("�븮������ ex) [1] ���, [2] ����, [3] ��õ : ");
		AdminController.agentByOneSalsesList(Integer.parseInt(reader.readLine()));
	}

	public static void ceoRentForm() throws IOException {
		while (true) {
			System.out.print("1. ��ü��Ʈ��Ȳ\n2. �븮������Ʈ��Ȳ\n3. ���ư���\n���� : ");
			if(ceoRent(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean ceoRent(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1 :
				// ��ü��Ʈ��Ȳ
				AdminController.allReservationList();
				break;
			case Util.NO2 :
				// �븮������Ʈ��Ȳ
				agentByRentForm();
				
				break;
			case Util.NO3 :
				result = true;
				// ���ư���
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void agentByRentForm() throws IOException {
		System.out.print("�븮������ ex) [1] ���, [2] ����, [3] ��õ : ");
		AdminController.agentReservationList(Integer.parseInt(reader.readLine()));
	}

	public static void userForm() throws IOException {
		while(true) {
			System.out.print("1. �������\n2. �ǽð� ����\n3. �������\n4. ���ϸ��� ����\n5. ��������\n6. �α׾ƿ�\n���� : ");
			if(user(Integer.parseInt(reader.readLine()))) {
				session = null;
				break;
			}
		}
	}

	public static boolean user(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1:
				// �������
				CarController.rateInfoList();
				break;
			case Util.NO2:
				// �ǽð� ����
				rentalForm();
				break;
			case Util.NO3:
				// �������
				cancellationFrom();
				break;
			case Util.NO4:
				// ���ϸ��� ����
				mileageForm();
				break;
			case Util.NO5:
				// ��������
				boardForm();
				break;
			case Util.NO6:
				// �α׾ƿ�
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void rentalForm() throws IOException {
		System.out.print("�뿩 �Ⱓ �Է� ex) [1] 1��, [2] 2��, [3] 3��, ... : ");
		int day = Integer.parseInt(reader.readLine());
		System.out
				.print("���� �Է� ex) [1] ����, [2] ����, [3] ������, [4] SUV, [5] ������ : ");
		int carKind = Integer.parseInt(reader.readLine());
		System.out.print("���� �Է� ex) [1] ���, [2] ����, [3] ��õ : ");
		int agtNo = Integer.parseInt(reader.readLine());
		CarController.rentCarList(day, Util.CARKIND[carKind-1], agtNo);
		carListForm(day, agtNo);
	}

	public static void carListForm(int day, int agtNo) throws IOException {
		System.out.print("���� ���� : ");
		carOptionForm(Integer.parseInt(reader.readLine()), day, agtNo);
	}

	public static void carOptionForm(int carNo, int day, int agtNo) throws IOException {
		System.out.println("�ɼ� ����");
		System.out.print("�׺���̼� ex) [1] Yes, [2] No : ");
		String naviResult = reader.readLine();
		System.out.print("ī��Ʈ ex) [1] Yes, [2] No : ");
		String sheetResult = reader.readLine();

		int option = 0;
		if(naviResult.equals("1") && sheetResult.equals("1")) {
			option = 3;
		} else if (naviResult.equals("1")) {
			option = 1;
		} else if (sheetResult.equals("1")) {
			option = 2;
		}
		
		reservationFrom(carNo, day, agtNo, option);
	}

	public static void reservationFrom(int carNo, int day, int agtNo, int option) throws IOException {
		CarRentTotalDTO crt = CarController.paymentInformation(session.getMemID(), carNo, day, agtNo, option);
		System.out.print("[1] ����, [2] ���ư���\n���� : ");
		switch (Integer.parseInt(reader.readLine())) {
			case Util.NO1 :
				// ����
				paymentForm(crt);
				break;
			case Util.NO2 :
				// ���ư���
				break;
			default:
				break;
		}
	}

	public static void paymentForm(CarRentTotalDTO crt) throws IOException {
		while(true) {
			System.out.println("�����ݾ� : " + crt.getResTotal());
			System.out.println("���ϸ��� : " + crt.getMemMilage());
			System.out.print("[1] ����, [2] ���ϸ��� ����, [3] ���\n���� : ");
			if(payment(Integer.parseInt(reader.readLine()), crt)) {
				break;
			}
		}
	}
	
	public static boolean payment(int index, CarRentTotalDTO crt)	throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1 :
				// ����
				if(crt.getMemMilage() > crt.getResTotal()) {
					result = CarController.carPayment(session.getMemID(), crt);
				} else {
					System.out.println("���ϸ����� �����ϼ���.");
				}
				break;
			case Util.NO2 :
				//���ϸ��� ����
				mileage(crt);
				break;
			case Util.NO3 :
				//���
				result = true;
				break;
			default:
				break;
		}
		
		return result;
	}

	public static void cancellationFrom() throws IOException {
		System.out.println("���ฮ��Ʈ");
		//���ฮ��Ʈ�� �Ѹ���
		CarController.resInfoList(session.getMemID());
		System.out.print("��ҹ�ȣ �Է� : ");
		cancellation(Integer.parseInt(reader.readLine()));
	}

	public static void cancellation(int index) throws IOException {
		int reMoney = CarController.carCancleReturnMoney(session.getMemID(), index);
		System.out.print("[1] OK, [2] Cancle\n���� : ");
		switch (Integer.parseInt(reader.readLine())) {
			case Util.NO1 :
				// �������
				int carNumber = CarController.getCarNum(index);
				CarController.cancleReservation(index, session.getMemID(), carNumber,reMoney);
				break;
			case Util.NO2 :
				// ���
				break;
			default:
				break;
		}
	}

	public static void mileageForm() throws IOException {
		System.out.println("����ݾ� : "+ MemberController.getMileage(session.getMemID()));
		System.out.print("[1] ����, [2] ���ư���");
		switch (Integer.parseInt(reader.readLine())) {
		case Util.NO1:
			// ����
			mileage();
			break;
		case Util.NO2:
			// ���ư���
			break;
		default:
			break;
		}
	}

	public static void mileage() throws IOException {
		System.out.println("����ݾ� : " + MemberController.getMileage(session.getMemID()));
		System.out.print("���� �� �ݾ� �Է� : ");
		int cash = Integer.parseInt(reader.readLine());
		MilegeController.updateMileage(Util.MILECODE[0] , cash , session.getMemID());
	}
	
	public static CarRentTotalDTO mileage(CarRentTotalDTO crt) throws IOException {
		System.out.println("����ݾ� : " + crt.getMemMilage());
		System.out.println("������ �ݾ� : " + crt.getResTotal());
		System.out.println("�ּ� ���� �ݾ� : " + (crt.getResTotal() - crt.getMemMilage()));
		System.out.print("���� �� �ݾ� �Է� : ");
		int cash = Integer.parseInt(reader.readLine());
		MilegeController.updateMileage(Util.MILECODE[0] , cash , session.getMemID(), crt);
		
		return crt;
	}

	public static void boardForm() throws IOException {
		while (true) {
			System.out.print("1. ���\n2. ����\n3. ����\n4. ���ư���\n���� : ");
			if(board(Integer.parseInt(reader.readLine()))) {
				break;
			}
		}
	}

	public static boolean board(int index) throws IOException {
		boolean result = false;
		switch (index) {
			case Util.NO1:
				registerBoardForm();
				// �Խ��� ���
				break;
			case Util.NO2:
				updateBoardForm();
				// �Խ��� ����
				break;
			case Util.NO3:
				deleteBoardForm();
				// �Խ��� ����
				break;
			case Util.NO4:
				// ���ư���
				result = true;
				break;
			default:
				break;
		}
		return result;
	}

	public static void registerBoardForm() throws IOException {
		System.out.print("���� : ");
		String boaTitle = reader.readLine();
		System.out.print("���� : ");
		String boaContent = reader.readLine();
		BoardController.insertBoard(new BoardDTO(boaTitle, boaContent,session.getMemID()));
	}

	public static void updateBoardForm() throws IOException {
		System.out.print("������ �� ��ȣ �Է� : ");
		int index = Integer.parseInt(reader.readLine());
		System.out.print("���� : ");
		String boaTitle = reader.readLine();
		System.out.print("���� : ");
		String boaContent = reader.readLine();
		BoardController.updateBoard(index, boaTitle, boaContent);
	}

	public static void deleteBoardForm() throws IOException {
		System.out.print("������ �� ��ȣ �Է� : ");
		int index = Integer.parseInt(reader.readLine());
		BoardController.deleteBoard(index);
	}

	public static void main(String[] args) {
		while (true) {
			try {
				if(startForm()) {
					break;
				}
			} catch (Exception e) {
				System.out.println("�ٽ� �Է����ּ���.");
			}
		}
	}
}