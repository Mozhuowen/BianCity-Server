package action;

import java.util.List;
import service.putaoService;
import tools.objects.ResponsePutao;
import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import domain.Image;

public class createputao extends BaseAction implements Action
{
	private putaoService putao;
	
	private int townid;
	private int userid;
	private String token;
	private String title;
	private String cover;
	private String content;
	private List<Image> images;
	
	public String jsonstr;

	@Override
	public boolean needInterceptCheck() {
		return true;
	}
	public putaoService getPutao() {
		return putao;
	}

	public void setPutao(putaoService putao) {
		this.putao = putao;
	}

	public int getTownid() {
		return townid;
	}

	public void setTownid(int townid) {
		this.townid = townid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("Create putao! title: "+title+" content: "+content+" cover: "+cover);
		for (int i=0;i<images.size();i++) {
			System.out.println("image name: "+images.get(i));
		}
		ResponsePutao resobj = putao.create(townid, title, cover, content, images);
		jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}