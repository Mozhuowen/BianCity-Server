package dao;

import java.util.List;

import domain.comment;
import domain.putao;

public interface commentDao
{
	comment get(Integer i);
	Integer save(comment c);
	void update(comment c);
	List<comment> loadMoreComments(putao pt,int position);
	Integer getGoods(int commentid);
}