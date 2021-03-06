package model.dto;

public class MemberOneSalsDTO {
	private int memMileage;
	private double disValue;
	
	public MemberOneSalsDTO() {
		super();
	}

	public MemberOneSalsDTO(int memMileage, double disValue) {
		super();
		this.memMileage = memMileage;
		this.disValue = disValue;
	}

	public int getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}

	public double getDisValue() {
		return disValue;
	}

	public void setDisValue(double disValue) {
		this.disValue = disValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberOneSalsDTO [memMileage=");
		builder.append(memMileage);
		builder.append(", disValue=");
		builder.append(disValue);
		builder.append("]");
		return builder.toString();
	}
}