package model;

/**
 * 유닛 DTO
 * @author JSLHRD
 *
 */
public class UnitDTO {
	private int id;		// 일련번호
	private String name;	// 유닛명
	private String company;	// 소속사
	
	public UnitDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		String str = "";
		
		str += "일련번호 : " + id + "\n";
		str += "유닛명 : " + name + "\n";
		str += "소속 : " + company + "\n";
		
		return str;
	}
	
	
}

// 12