package dao;

import java.util.List;

import tools.SortTownAction;
import tools.TownSort;
import domain.town;

public interface townDao
{
	town get(Integer id);
	Integer save(town t);
	void update(town t);
	town getBytownname(String name);
	Integer getGoods(int townid);
	Integer doGoods(int townid,int action);
	List<TownSort> getHotTownByTime();
	List<TownSort> getHotTownByGood();
	List<TownSort> getHotTownByFans();
	List<TownSort> getNearTown(String geohash);
	List<town> getTargetTown(List<SortTownAction.SortData> targetdata);	
	List<town> getTownByTime(int position);
	Long getTownCount();
}