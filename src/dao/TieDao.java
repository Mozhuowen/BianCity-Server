package dao;

import domain.Tie;

public interface TieDao
{
	Tie get(Integer id);
	Integer save(Tie tie);
	void update(Tie tie);
}