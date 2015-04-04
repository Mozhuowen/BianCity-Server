package dao;

import java.util.List;

import domain.MessBoard;
import domain.town;

public interface MessBoardDao
{
	MessBoard get(Integer id);
	Integer save(MessBoard mess);
	void update(MessBoard mess);
	List<MessBoard> loadMoreMess(town townx,int position);
	Integer getGoods(int messboardid);
}