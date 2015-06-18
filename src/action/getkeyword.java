package action;

import java.util.List;

import service.SearchService;
import tools.objects.ResKeyWord;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getkeyword extends BaseAction implements Action
{
	private SearchService search;
	private String ptoken;
	private int ptuserid;
	private String keyword;
	public String jsonstr;
	
	@Override
	public String execute() throws Exception {
		ResKeyWord res = search.getKeyWord(keyword);
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

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	
}