package service;

import tools.objects.ResponseSimple;
import tools.objects.community.ResCommunityHeader;
import tools.objects.community.ResCommunityTieTh;

public interface CommunityService
{
	ResCommunityHeader getCommunityHeader(int townid,int userid);
	ResCommunityTieTh getCommunityTieTh(int townid);
	ResponseSimple joinCommunity(int townid,int userid);
}