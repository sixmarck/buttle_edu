package model.dto;

public class MemberUpdateGradeDTO {
	private String memID;
	private String updateGrade;

	public MemberUpdateGradeDTO() {
		super();
	}

	public MemberUpdateGradeDTO(String memID, String updateGrade) {
		super();
		this.memID = memID;
		this.updateGrade = updateGrade;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getUpdateGrade() {
		return updateGrade;
	}

	public void setUpdateGrade(String updateGrade) {
		this.updateGrade = updateGrade;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberUpdateGradeDTO [memID=");
		builder.append(memID);
		builder.append(", updateGrade=");
		builder.append(updateGrade);
		builder.append("]");
		return builder.toString();
	}
}