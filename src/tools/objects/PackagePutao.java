package tools.objects;

import java.util.ArrayList;
import java.util.List;

import tools.TimeUtil;
import domain.Image;
import domain.putao;

public class PackagePutao
{
	private int townid;
	private int putaoid;
	private String title;
	private String content;
	private String cover;
	private String usercover;
	private String username;
	private String createtime;
	private List<String> imagenames;
	private int goods;
	private int userid;
	
	public static PackagePutao build(putao p) {
		PackagePutao pt = new PackagePutao();
		pt.setTownid(p.getParenttown().getTownid());
		pt.setPutaoid(p.getPutaoid());
		pt.setTitle(p.getTitle());
		pt.setCover(p.getCover());
		pt.setContent(p.getContent());
		pt.setUsercover(p.getParenttown().getOwner().getCover());
		pt.setUsername(p.getParenttown().getOwner().getName());
		pt.setCreatetime(TimeUtil.getFormatDate(p.getCreatetime()));
		pt.setUserid(p.getParenttown().getOwner().getUsersid());
		//处理包含的图片
		List <String> imagenames = new ArrayList<String>();
		List<Image> is = p.getImages();
		for (int j=0;j<is.size();j++) {
			imagenames.add(is.get(j).getImagename());
		}
		pt.setImagenames(imagenames);
		return pt;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setGoods(int g) {
		this.goods = g;
	}
	public int getGoods(){
		return this.goods;
	}
	public void setUsercover(String u) {
		this.usercover = u;
	}
	public String getUsercover() {
		return this.usercover;
	}
	public void setCreatetime(String c) {
		this.createtime = c;
	}
	public String getCreatetime() {
		return this.createtime;
	}
	public void setUsername(String u) {
		this.username = u;
	}
	public String getUsername() {
		return this.username;
	}
	public int getTownid() {
		return townid;
	}
	public void setTownid(int townid) {
		this.townid = townid;
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
	public List<String> getImagenames() {
		return imagenames;
	}
	public void setImagenames(List<String> images) {
		this.imagenames = images;
	}
	
}