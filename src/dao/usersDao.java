package dao;

import domain.users;

public interface usersDao
{
	users get(Integer u);
	Integer save(users u);
	void update(users u);
	users getByUsername(String username);
	users getByEmail(String email);
	/**0-town 1-putao 2-comment 3-mess*/
	Boolean checkdoGood(int type,int userid,int id);
	Boolean checkSubscirTown(int userid,int townid);
	Boolean checkFavorite(int userid,int putaoid);
	Integer getFans(int userid);
	Integer getBegoodCount(int userid);
	//检测是否拥有这些内容
	Boolean checkOwnTown(int userid,int townid);
	Boolean checkOwnPutao(int userid,int putaoid);
	Boolean checkOwnComment(int userid,int commentid);
	Boolean checkOwnMess(int userid,int messid);
	Boolean checkIfJoinCommunity(int townid,int userid);
	Boolean checkIfGoodForTieth(int tieid,int userid);
}