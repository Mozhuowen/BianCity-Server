package action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import service.SearchService;
import tools.objects.ResSearch;

public class searchtown extends BaseAction implements Action
{
	private SearchService search;
	private String ptoken;
	private int ptuserid;
	private String keyword;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResSearch res = search.searchTown(keyword);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}

	public SearchService getSearch() {
		return search;
	}

	public void setSearch(SearchService search) {
		this.search = search;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
}