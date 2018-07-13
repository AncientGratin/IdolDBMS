package model;

/**
 * 그룹활동 DTO
 * @author JSLHRD
 *
 */
public class GroupActivityDTO {
	private int id;			// 일련번호
	private int idolId;		// 아이돌 일련번호
	private int groupId;		// 그룹 일련번호
	private String joinedDate;	// 가입일
	private String leavedDate;	// 탈퇴일
	
	public GroupActivityDTO(int id, int idolId, int groupId, String joinedDate, String leavedDate) {
		super();
		this.id = id;
		this.idolId = idolId;
		this.groupId = groupId;
		this.joinedDate = joinedDate;
		this.leavedDate = leavedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getIdolId() {
		return idolId;
	}

	public void setIdolId(int idolId) {
		this.idolId = idolId;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getLeavedDate() {
		return leavedDate;
	}

	public void setLeavedDate(String leavedDate) {
		this.leavedDate = leavedDate;
	}
	
	
}

// 8
