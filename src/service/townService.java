package service;

import java.util.List;

import domain.GeoInfo;
import domain.town;
import tools.objects.ResponseHotTown;
import tools.objects.ResponseTown;


public interface townService
{
	public ResponseTown apply(int userid,String townname,String descri,String cover,GeoInfo geoinfo);
	public boolean checkUserid(int id);
	public boolean checkTownname(String townname);
	public ResponseHotTown getHotTown(List<Integer> reject);
	public ResponseHotTown getNearTwon(GeoInfo geo,List<Integer> reject);
	public List<town> getTownByTime(int position);
	public Long getTownCount();
}