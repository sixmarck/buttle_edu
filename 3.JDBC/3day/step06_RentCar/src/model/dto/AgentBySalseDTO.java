package model.dto;

public class AgentBySalseDTO {
	private String agtNo;
	private String carName;
	private int resTotal;
	
	public AgentBySalseDTO() {
		super();
	}

	public AgentBySalseDTO(String agtNo, String carName, int resTotal) {
		super();
		this.agtNo = agtNo;
		this.carName = carName;
		this.resTotal = resTotal;
	}

	public String getAgtNo() {
		return agtNo;
	}

	public void setAgtNo(String agtNo) {
		this.agtNo = agtNo;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
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
		builder.append("AgentBySalseDTO [agtNo=");
		builder.append(agtNo);
		builder.append(", carName=");
		builder.append(carName);
		builder.append(", resTotal=");
		builder.append(resTotal);
		builder.append("]");
		return builder.toString();
	}
}