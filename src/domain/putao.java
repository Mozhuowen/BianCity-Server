package domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class putao
{
	private int putaoid;
	private String title;
	private String content;
	private String cover;
	private Calendar createtime;
	private List<Image> images;
	private town parenttown;
	private Set<comment> comments = new HashSet<comment>();
	private int goods;
	public Set<users> dogoodusers;
	public Set<users> favoriteusers;
	private users owner;
	public void setOwner(users o) {
		this.owner = o;
	}
	public users getOwner() {
		return this.owner;
	}
	public void setFavoriteusers(Set<users> u) {
		this.favoriteusers = u;
	}
	public Set<users> getFavoriteusers() {
		return this.favoriteusers;
	}
	public void setDogoodusers(Set<users> u) {
		this.dogoodusers = u;
	}
	public Set<users> getDogoodusers() {
		return this.dogoodusers;
	}
	public void setGoods(int g){
		this.goods = g;
	}
	public int getGoods(){
		return this.goods;
	}
	public void setComments(Set<comment> com) {
		this.comments = com;
	}
	public Set<comment> getComments() {
		return this.comments;
	}
	public int getPutaoid() {
		return putaoid;
	}
	public void setPutaoid(int putaoid) {
		this.putaoid = putaoid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Calendar getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Calendar createtime) {
		this.createtime = createtime;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public town getParenttown() {
		return parenttown;
	}
	public void setParenttown(town parenttown) {
		this.parenttown = parenttown;
	}
	
}