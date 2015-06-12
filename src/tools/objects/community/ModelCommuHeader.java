package tools.objects.community;

import java.util.List;
/**
 * 社区列表头模型
 * @author awen
 *
 */
public class ModelCommuHeader
{
	private int communityid;	//社区id
	private int adminid;		//管理员id
	private String communityname;	//社区名
	private String cover;		//封面照片
	private int tiecount;		//帖子总数
	private int memberscount;	//成员数量
	private boolean hasjoin;	//当前用户是否已经加入该社区
	private long createtime;	//社区创建时间
	private List<ModelTopTie> tops;	//置顶贴子
	public int getCommunityid() {
		return communityid;
	}
	public void setCommunityid(int communityid) {
		this.communityid = communityid;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getTiecount() {
		return tiecount;
	}
	public void setTiecount(int tiecount) {
		this.tiecount = tiecount;
	}
	public int getMemberscount() {
		return memberscount;
	}
	public void setMemberscount(int memberscount) {
		this.memberscount = memberscount;
	}
	public boolean isHasjoin() {
		return hasjoin;
	}
	public void setHasjoin(boolean hasjoin) {
		this.hasjoin = hasjoin;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	public List<ModelTopTie> getTops() {
		return tops;
	}
	public void setTops(List<ModelTopTie> tops) {
		this.tops = tops;
	}
	public String getCommunityname() {
		return communityname;
	}
	public void setCommunityname(String communityname) {
		this.communityname = communityname;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
}