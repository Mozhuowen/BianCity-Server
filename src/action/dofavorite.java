package action;

import service.favoriteService;
import tools.objects.ResponseFavori;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class dofavorite extends BaseAction implements Action
{
	private favoriteService favorite;
	private String ptoken;
	private int ptuserid;
	private int putaoid;
	/**收藏动作 0-收藏 1-取消收藏*/
	private int action;
	public String jsonstr;

	@Override
	public boolean needInterceptCheck() {
		return false;
	}
	public favoriteService getFavorite() {
		return favorite;
	}

	public void setFavorite(favoriteService favorite) {
		this.favorite = favorite;
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

	public int getPutaoid() {
		return putaoid;
	}

	public void setPutaoid(int putaoid) {
		this.putaoid = putaoid;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	@Override
	public String execute() throws Exception {
		ResponseFavori resobj = favorite.doFavorite(ptuserid, putaoid, action);
		jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}