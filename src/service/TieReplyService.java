package service;

import tools.objects.community.ResSubmiTieReply;

public interface TieReplyService
{
	ResSubmiTieReply createNew(int parentie,int userid,int bereplyid,String content);
}