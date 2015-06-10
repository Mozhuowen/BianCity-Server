package dao;

import domain.TieReply;

public interface TieReplyDao
{
	TieReply get(Integer id);
	Integer save(TieReply tie);
	void update(TieReply tie);
}