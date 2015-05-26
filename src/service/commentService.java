package service;

import tools.objects.ResponseComment;

public interface commentService
{
	ResponseComment submitComment(int townid,int putaoid,int userid,String content,int replyid);
	ResponseComment getComment(int putaoid,int userid);
	ResponseComment loadMoreComments(int putaoid,int position,int userid);
}