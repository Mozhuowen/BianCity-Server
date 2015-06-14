package dao;

import java.util.List;

import tools.objects.community.ModelTieReply;
import domain.Tie;
import domain.TieReply;

public interface TieReplyDao
{
	TieReply get(Integer id);
	Integer save(TieReply tie);
	void update(TieReply tie);
	List<ModelTieReply> getreplys(Tie tie);
}