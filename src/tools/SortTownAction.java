package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortTownAction
{
	public static final int SORTTIME = 0;	//发表时间
	public static final int SORTGOOD = 1;	//点赞
	public static final int SORTFANS = 2;	//粉丝
	public static final int SORTNEAR = 3;	//附近小镇
	private List<TownSort>[] towns;
	private Map<Integer,Double> sortown = new HashMap<Integer,Double>();
	
	public SortTownAction(List<TownSort>... t) {
		this.towns = t;
	}
	/**排序*/
	public List<SortData> sort() {
		preSort();
		//map转list
		List<SortData> sortlist = new ArrayList<SortData>();
		Set<Integer> set = sortown.keySet();
		for (Iterator<Integer> it = set.iterator();it.hasNext();) {
			Integer i = it.next();
			SortData s = new SortData(i,sortown.get(i));
			sortlist.add(s);
		}
		//排序，注意需要降序!
		Collections.sort(sortlist, new Comparator<SortData>(){
			@Override
			public int compare(SortData o1, SortData o2) {
				SortData s1 = (SortData)o1;
				SortData s2 = (SortData)o2;
				if (s1.getScore() < s2.getScore())
					return 1;
				else if (s1.getScore() == s2.getScore())
					return 0;
				else
					return -1;
			}
			
		});
		return sortlist;
	}
	/**对每个town 计分*/
	private void preSort() {
		if (towns.length==0)
			return;
		//对每个集合权重计分
		for (int i=0;i<towns.length;i++) {			
			List<TownSort> t = towns[i];
			if (t.size() == 0)
				continue;
			int type = t.get(0).getType();
			double s = 0;
			for (int j=0;j<t.size();j++) {	//处理集合每个元素
				if (type == SORTTIME) {
					s = 200 - j;					
				} else if (type == SORTGOOD) {
					s = (200 - j)*0.5 + t.get(j).getData()*0.5;
				} else if (type == SORTFANS) {
					s = (200 - j)*0.5 + t.get(j).getData()*0.8;
				} else if (type == SORTNEAR) {
					s = t.get(j).getData();
				}
				if (sortown.get(t.get(j).getTownid()) != null) {
					s = sortown.get(t.get(j).getTownid()) + s;
				}
				sortown.put(t.get(j).getTownid(), s);
			}
		}
	}
	
	public static class SortData
	{
		private int townid;
		private double score;		
		public SortData(int t,double s) {
			this.townid = t;
			this.score = s;
		}
		public int getTownid() {
			return townid;
		}
		public void setTownid(int townid) {
			this.townid = townid;
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
	}
}