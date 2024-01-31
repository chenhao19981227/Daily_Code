package reconstruction.chapter7.inline_class;

class TelephoneNumber {
	//.....
	private String areaCode;
	private String number;

	public String getAreaCode() {
		return areaCode;
	}

	public String getTelephoneNumber() {
		return areaCode+"-"+number;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}