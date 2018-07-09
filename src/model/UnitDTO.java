package model;

/**
 * 유닛 DTO
 * @author JSLHRD
 *
 */
public class UnitDTO {
	private String id;		// 일련번호
	private String name;	// 유닛명
	private String company;	// 소속사
	
	public UnitDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}

// 6