package service.Impl;

import dao.townDao;
import service.SearchService;
import tools.objects.ResKeyWord;
import tools.objects.ResSearch;

public class SearchServiceImpl implements SearchService
{
	private townDao townx;

	@Override
	public ResKeyWord getKeyWord(String keywork) {
		ResKeyWord res = new ResKeyWord();
		res.setStat(true);
		res.setWords(townx.getNameLike(keywork));
		return res;
	}

	@Override
	public ResSearch searchTown(String towname) {
		ResSearch res = new ResSearch();
		res.setStat(true);
		res.setTowns(townx.search(towname));
		
		return res;
	}

	public townDao getTownx() {
		return townx;
	}

	public void setTownx(townDao townx) {
		this.townx = townx;
	}

}