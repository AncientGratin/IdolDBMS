package model;

/**
 * 유닛활동 DTO
 * @author JSLHRD
 *
 */
public class UnitActivityDTO {
	private int id;			// 일련번호
	private int idolId;		// 아이돌 일련번호
	private int unitId;		// 유닛 일련번호
	private String joinedDate;	// 가입일
	private String leavedDate;	// 탈퇴일
	
	public UnitActivityDTO(int id, int idolId, int unitId, String joinedDate, String leavedDate) {
		super();
		this.id = id;
		this.idolId = idolId;
		this.unitId = unitId;
		this.joinedDate = joinedDate;
		this.leavedDate = leavedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
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
