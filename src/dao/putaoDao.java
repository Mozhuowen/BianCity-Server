package dao;

import java.util.List;

import domain.putao;
import domain.town;

public interface putaoDao
{
	putao get(Integer id);
	Integer save(putao t);
	void update(putao t);
	boolean checkPutao(int putaoid);
	List<putao> loadMorePutao(town townx,int position);
	Integer getgoods(int putaoid);
}