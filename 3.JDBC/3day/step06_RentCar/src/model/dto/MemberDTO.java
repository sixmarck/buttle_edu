package model.dto;

import java.util.Date;

public class MemberDTO {
	private String memID;
	private String memPW;
	private String memName;
	private String memTel;
	private String memBirthday;
	private String memLicense;
	private String memAddress;
	private int memMileage;
	private String disGrade;
	private Date memDate;

	public MemberDTO() {
		super();
	}

	public MemberDTO(String memID, String memPW, String memName, String memTel,
			String memBirthday, String memLicense, String memAddress) {
		super();
		this.memID = memID;
		this.memPW = memPW;
		this.memName = memName;
		this.memTel = memTel;
		this.memBirthday = memBirthday;
		this.memLicense = memLicense;
		this.memAddress = memAddress;
	}
	
	public MemberDTO(String memID, String memName, String memTel,
			String memBirthday, String memLicense, String memAddress,
			int memMileage, String disGrade) {
		super();
		this.memID = memID;
		this.memName = memName;
		this.memTel = memTel;
		this.memBirthday = memBirthday;
		this.memLicense = memLicense;
		this.memAddress = memAddress;
		this.memMileage = memMileage;
		this.disGrade = disGrade;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMemPW() {
		return memPW;
	}

	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemBirthday() {
		return memBirthday;
	}

	public void setMemBirthday(String memBirthday) {
		this.memBirthday = memBirthday;
	}

	public String getMemLicense() {
		return memLicense;
	}

	public void setMemLicense(String memLicense) {
		this.memLicense = memLicense;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public int getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}

	public String getDisGrade() {
		return disGrade;
	}

	public void setDisGrade(String disGrade) {
		this.disGrade = disGrade;
	}

	public Date getMemDate() {
		return memDate;
	}

	public void setMemDate(Date memDate) {
		this.memDate = memDate;
	}

	@Override
	public String toString() {
		return "Member [memID=" + memID + ", memPW=" + memPW + ", memName="
				+ memName + ", memTel=" + memTel + ", memBirthday="
				+ memBirthday + ", memLicense=" + memLicense + ", memAddress="
				+ memAddress + ", memMileage=" + memMileage + ", disGrade="
				+ disGrade + ", memDate=" + memDate + "]";
	}
}