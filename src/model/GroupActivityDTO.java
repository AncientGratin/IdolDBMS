package model;

/**
 * 그룹활동 DTO
 * @author JSLHRD
 *
 */
public class GroupActivityDTO {
	private int id;			// 일련번호
	private String groupId;		// 그룹 일련번호
	private String idolId;		// 아이돌 일련번호
	private String joinedDate;	// 가입일
	private String leavedDate;	// 탈퇴일
	
	public GroupActivityDTO(int id, String groupId, String idolId, String joinedDate) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.idolId = idolId;
		this.joinedDate = joinedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getIdolId() {
		return idolId;
	}

	public void setIdolId(String idolId) {
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
