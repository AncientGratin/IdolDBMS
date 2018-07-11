package model;

import java.util.HashMap;
import java.util.Iterator;

import resources.Constants;

/**
 * 아이돌 정보
 * @author JSLHRD
 *
 */
public class IdolDTO {
	private int id;									// 일련번호
	private HashMap<String, String> attrStrings;		// 문자열 속성의 값들을 갖는 해시맵
	private HashMap<String, Integer> attrIntegers;		// 정수 속성의 값들을 갖는 해시맵
	
	/**
	 * 생성자
	 * @param id : 일련번호
	 * @param name : 이름
	 */
	public IdolDTO(String name) {
		attrStrings = new HashMap<String, String>();
		attrIntegers = new HashMap<String, Integer>();
		setName(name);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return attrStrings.get(Constants.IDOL_KEY_NAME);
	}
	
	public int getAge() {
		return attrIntegers.get(Constants.IDOL_KEY_AGE);
	}
	
	public int getHeight() {
		return attrIntegers.get(Constants.IDOL_KEY_HEIGHT);
	}
	
	public int getWeight() {
		return attrIntegers.get(Constants.IDOL_KEY_WEIGHT);
	}
	
	public int getBirthMonth() {
		return attrIntegers.get(Constants.IDOL_KEY_BIRTHMONTH);
	}
	
	public int getBirthDate() {
		return attrIntegers.get(Constants.IDOL_KEY_BIRTHDATE);
	}
	
	public String getBloodType() {
		return attrStrings.get(Constants.IDOL_KEY_BLOODTYPE);
	}
	
	public int getBust() {
		return attrIntegers.get(Constants.IDOL_KEY_BUST);
	}
	
	public int getWaist() {
		return attrIntegers.get(Constants.IDOL_KEY_WAIST);
	}
	
	public int getHip() {
		return attrIntegers.get(Constants.IDOL_KEY_HIP);
	}
	
	public String getHobby() {
		return attrStrings.get(Constants.IDOL_KEY_HOBBY);
	}
	
	public String getSpeciality() {
		return attrStrings.get(Constants.IDOL_KEY_SPECIALITY);
	}
	
	public String getHomeTown() {
		return attrStrings.get(Constants.IDOL_KEY_HOMETOWN);
	}
	
	public String getImageColor() {
		return attrStrings.get(Constants.IDOL_KEY_IMAGECOLOR);
	}
	
	public String getVoiceActor() {
		return attrStrings.get(Constants.IDOL_KEY_VOICEACTOR);
	}
	
	public String getCompany() {
		return attrStrings.get(Constants.IDOL_KEY_COMPANY);
	}
	
	public String getPrimaryHand() {
		return attrStrings.get(Constants.IDOL_KEY_PRIMARYHAND);
	}
	
	public String getLikeFood() {
		return attrStrings.get(Constants.IDOL_KEY_LIKEFOOD);
	}
	
	public String getDislikeFood() {
		return attrStrings.get(Constants.IDOL_KEY_DISLIKEFOOD);
	}
	
	public String getVirtue() {
		return attrStrings.get(Constants.IDOL_KEY_VIRTUE);
	}

	public String getCharm() {
		return attrStrings.get(Constants.IDOL_KEY_CHARM);
	}
	
	public String getDream() {
		return attrStrings.get(Constants.IDOL_KEY_DREAM);
	}
	
	public String getStrongSubject() {
		return attrStrings.get(Constants.IDOL_KEY_STRONGSUBJECT);
	}
	
	public String getWeakSubject() {
		return attrStrings.get(Constants.IDOL_KEY_WEAKSUBJECT);
	}
	
	public String getCook() {
		return attrStrings.get(Constants.IDOL_KEY_COOK);
	}
	
	public String getFirstPerson() {
		return attrStrings.get(Constants.IDOL_KEY_FIRSTPERSON);
	}
	
	public HashMap<String, Integer> getAttrIntegers() {
		return attrIntegers;
	}
	
	public HashMap<String, String> getAttrStrings() {
		return attrStrings;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		attrStrings.put(Constants.IDOL_KEY_NAME, name);
	}
	
	public void setAge(int age) {
		attrIntegers.put(Constants.IDOL_KEY_AGE, age);
	}
	
	public void setHeight(int height) {
		attrIntegers.put(Constants.IDOL_KEY_HEIGHT, height);
	}
	
	public void setWeight(int weight) {
		attrIntegers.put(Constants.IDOL_KEY_WEIGHT, weight);
	}
	
	public void setBirthMonth(int birthMonth) {
		attrIntegers.put(Constants.IDOL_KEY_BIRTHMONTH, birthMonth);
	}
	
	public void setBirthDate(int birthDate) {
		attrIntegers.put(Constants.IDOL_KEY_BIRTHDATE, birthDate);
	}
	
	public void setBloodType(String bloodType) {
		attrStrings.put(Constants.IDOL_KEY_BLOODTYPE, bloodType);
	}
	
	public void setBust(int bust) {
		attrIntegers.put(Constants.IDOL_KEY_BUST, bust);
	}
	
	public void setWaist(int waist) {
		attrIntegers.put(Constants.IDOL_KEY_WAIST, waist);
	}
	
	public void setHip(int hip) {
		attrIntegers.put(Constants.IDOL_KEY_HIP, hip);
	}
	
	public void setHobby(String hobby) {
		attrStrings.put(Constants.IDOL_KEY_HOBBY, hobby);
	}
	
	public void setSpeciality(String speciality) {
		attrStrings.put(Constants.IDOL_KEY_SPECIALITY, speciality);
	}
	
	public void setHometown(String hometown) {
		attrStrings.put(Constants.IDOL_KEY_HOMETOWN, hometown);
	}
	
	public void setImageColor(String imageColor) {
		attrStrings.put(Constants.IDOL_KEY_IMAGECOLOR, imageColor);
	}
	
	public void setVoiceActor(String voiceActor) {
		attrStrings.put(Constants.IDOL_KEY_VOICEACTOR, voiceActor);
	}
	
	public void setCompany(String company) {
		attrStrings.put(Constants.IDOL_KEY_COMPANY, company);
	}
	
	public void setPrimaryHand(String primaryHand) {
		attrStrings.put(Constants.IDOL_KEY_PRIMARYHAND, primaryHand);
	}
	
	public void setLikeFood(String likeFood) {
		attrStrings.put(Constants.IDOL_KEY_LIKEFOOD, likeFood);
	}
	
	public void setDislikeFood(String dislikeFood) {
		attrStrings.put(Constants.IDOL_KEY_DISLIKEFOOD, dislikeFood);
	}
	
	public void setVirtue(String virtue) {
		attrStrings.put(Constants.IDOL_KEY_VIRTUE, virtue);
	}
	
	public void setCharm(String charm) {
		attrStrings.put(Constants.IDOL_KEY_CHARM, charm);
	}
	
	public void setDream(String dream) {
		attrStrings.put(Constants.IDOL_KEY_DREAM, dream);
	}
	
	public void setStrongSubject(String strongSubject) {
		attrStrings.put(Constants.IDOL_KEY_STRONGSUBJECT, strongSubject);
	}
	
	public void setWeakSubject(String weakSubject) {
		attrStrings.put(Constants.IDOL_KEY_WEAKSUBJECT, weakSubject);
	}
	
	public void setCook(String cook) {
		attrStrings.put(Constants.IDOL_KEY_COOK, cook);
	}
	
	public void setFirstPerson(String firstPerson) {
		attrStrings.put(Constants.IDOL_KEY_FIRSTPERSON, firstPerson);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		str += "일련번호 : " + id + "\n";
		str += "이름 : " + attrStrings.get(Constants.IDOL_KEY_NAME) + "\n";
		str += "--------------------------------\n";
		
		Iterator<String> intKeys = attrIntegers.keySet().iterator();
		while(intKeys.hasNext()) {
			String tmpKey = intKeys.next();
			str += tmpKey + " : " + attrIntegers.get(tmpKey) + "\n";
		}
		
		Iterator<String> strKeys = attrStrings.keySet().iterator();
		while(strKeys.hasNext()) {
			String tmpKey = strKeys.next();
			
			// 이름은 이미 처리했으므로 생략
			if(tmpKey.equals(Constants.IDOL_KEY_NAME))
				continue;
			
			str += tmpKey + " : " + attrStrings.get(tmpKey) + "\n";
		}
		
		return str;
	}
	
	
}

// 132