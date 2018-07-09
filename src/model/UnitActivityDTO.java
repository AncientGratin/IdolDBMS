package model;

/**
 * 유닛활동 DTO
 * @author JSLHRD
 *
 */
public class UnitActivityDTO {
	private String id;			// 일련번호
	private String unitId;		// 유닛 일련번호
	private String idolId;		// 아이돌 일련번호
	private String joinedDate;	// 가입일
	private String leavedDate;	// 탈퇴일
	
	public UnitActivityDTO(String id, String unitId, String idolId, String joinedDate) {
		super();
		this.id = id;
		this.unitId = unitId;
		this.idolId = idolId;
		this.joinedDate = joinedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
