package action;

import java.util.List;

import service.TieService;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import domain.Image;

public class submitie extends BaseAction implements Action
{
	private TieService tie;
	private String ptoken;
	private int ptuserid;
	private String content;
	private int communityid;	//社区id
	private int zhulouid;		//主楼（第一楼）帖子id
	private List<Image> images;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResponseSimple res = tie.submitTie(communityid, zhulouid, ptuserid, content, images);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}

	public TieService getTie() {
		return tie;
	}

	public void setTie(TieService tie) {
		this.tie = tie;
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

	public int getZhulouid() {
		return zhulouid;
	}

	public void setZhulouid(int zhulouid) {
		this.zhulouid = zhulouid;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
}