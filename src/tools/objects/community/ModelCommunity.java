package tools.objects.community;

import domain.town;

public class ModelCommunity
{
	private int id;
	private String cover;
	private String name;
	private String address;
	
	public ModelCommunity(town t){
		this.id = t.getTownid();
		this.cover = t.getCover();
		this.name = t.getName();
		this.address = t.getGeo().getCity()+t.getGeo().getFreeaddr();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}