package model.dto;

public class AgentByResDTO {
	private int agtNo ;
	private String memID ;
	private String carName; 
	private String resDate;
	private String resStartDate; 
	private String resEndDate;
	private int resOption;
	private int resTotal;
	
	public AgentByResDTO() {
		super();
	}
	
	public AgentByResDTO(int agtNo, String memID, String carName,
			String resDate, String resStartDate, String resEndDate,
			int resOption, int resTotal) {
		super();
		this.agtNo = agtNo;
		this.memID = memID;
		this.carName = carName;
		this.resDate = resDate;
		this.resStartDate = resStartDate;
		this.resEndDate = resEndDate;
		this.resOption = resOption;
		this.resTotal = resTotal;
	}

	public int getAgtNo() {
		return agtNo;
	}
	
	public void setAgtNo(int agtNo) {
		this.agtNo = agtNo;
	}
	
	public String getMemID() {
		return memID;
	}
	
	public void setMemID(String memID) {
		this.memID = memID;
	}
	
	public String getCarName() {
		return carName;
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	public String getResDate() {
		return resDate;
	}
	
	public void setResDate(String resDate) {
		this.resDate = resDate;
	}
	
	public String getResStartDate() {
		return resStartDate;
	}
	
	public void setResStartDate(String resStartDate) {
		this.resStartDate = resStartDate;
	}
	
	public String getResEndDate() {
		return resEndDate;
	}
	
	public void setResEndDate(String resEndDate) {
		this.resEndDate = resEndDate;
	}
	
	public int getResOption() {
		return resOption;
	}
	
	public void setResOption(int resOption) {
		this.resOption = resOption;
	}
	
	public int getResTotal() {
		return resTotal;
	}
	
	public void setResTotal(int resTotal) {
		this.resTotal = resTotal;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AgentByResDTO [agtNo=");
		builder.append(agtNo);
		builder.append(", memID=");
		builder.append(memID);
		builder.append(", carName=");
		builder.append(carName);
		builder.append(", resDate=");
		builder.append(resDate);
		builder.append(", resStartDate=");
		builder.append(resStartDate);
		builder.append(", resEndDate=");
		builder.append(resEndDate);
		builder.append(", resOption=");
		builder.append(resOption);
		builder.append(", resTotal=");
		builder.append(resTotal);
		builder.append("]");
		return builder.toString();
	}
}