package action;

import java.util.List;

import service.TieThemeService;
import service.Impl.TieThemeServiceImpl;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import domain.Image;

public class submitieth extends BaseAction implements Action
{
	private TieThemeService tieth;
	private String ptoken;
	private int ptuserid;
	private String title;
	private String content;
	private int communityid;	//社区id
	private List<Image> images;
	
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResponseSimple res = tieth.createNew(communityid, ptuserid, title, content, images);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}

	@Override
	public boolean needInterceptCheck() {
		return true;
	}
	public TieThemeService getTieth() {
		return tieth;
	}


	public void setTieth(TieThemeService tieth) {
		this.tieth = tieth;
	}


	public String getPtoken() {
		return ptoken;
	}


	public void setPtoken(String ptoken) {
		this.ptoken = ptoken;
	}


	public int getPtuserid() {
		return ptuserid;
	}


	public void setPtuserid(int ptuserid) {
		this.ptuserid = ptuserid;
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


	public int getCommunityid() {
		return communityid;
	}


	public void setCommunityid(int communityid) {
		this.communityid = communityid;
	}


	public List<Image> getImages() {
		return images;
	}


	public void setImages(List<Image> images) {
		this.images = images;
	}
	
}