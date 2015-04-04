package tools.objects;

import domain.GeoInfo;
import domain.town;

public class ResGeoInfo
{
	private double longitude;
	private double latitude;
	private String address = "";
	private String country = "China";
	private String province = "";
	private String city = "";
	private String district = "";
	private String citycode;
	private float accuracy;
	private String street;
	private String road;
	private String freeaddr;
	private String screenpng;
	
	public ResGeoInfo(GeoInfo geo) {
		this.longitude = geo.getLongitude();
		this.latitude = geo.getLatitude();
		this.address = geo.getAddress();
		this.district = geo.getDistrict();
		this.province = geo.getProvince();
		this.city = geo.getCity();
		this.accuracy = geo.getAccuracy();
		this.street = geo.getStreet();
		this.road = geo.getRoad();
		this.freeaddr = geo.getFreeaddr();
		this.screenpng = geo.getScreenpng();
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getFreeaddr() {
		return freeaddr;
	}

	public void setFreeaddr(String freeaddr) {
		this.freeaddr = freeaddr;
	}

	public String getScreenpng() {
		return screenpng;
	}

	public void setScreenpng(String screenpng) {
		this.screenpng = screenpng;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String toString() {
		return "This is the geoinfo: longitude: "+longitude+" latitude: "+latitude
				+" address: "+address
				+" country: "+country
				+" province: "+province
				+" city: "+city
				+" district: "+district;
	}
	
}