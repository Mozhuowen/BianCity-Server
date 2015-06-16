package service;

import java.util.List;

import tools.objects.ResponseSimple;
import tools.objects.community.ResCommunityHeader;
import tools.objects.community.ResCommunityTie;
import tools.objects.community.ResCommunityTieTh;

public interface CommunityService
{
	ResCommunityHeader getCommunityHeader(int townid,int userid);
	ResCommunityTieTh getCommunityTieTh(int townid,List<Integer> Rejectids);
	ResponseSimple joinCommunity(int townid,int userid);
	ResCommunityTie getCommunityTie(int zhulouid,int position);
	ResponseSimple toTop(int titthid,int userid);
}