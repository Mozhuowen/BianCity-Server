package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortAction
{
	public static final int SORTTIME = 0;	//发表时间
	public static final int SORTGOOD = 1;	//点赞
	public static final int SORTREPLY = 2;	//回复数量
	private List<ModelSort>[] data;
	private Map<Integer,Double> sortown = new HashMap<Integer,Double>();
	
	public SortAction(List<ModelSort>... t) {
		this.data = t;
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
		if (data.length==0)
			return;
		//对每个集合权重计分
		for (int i=0;i<data.length;i++) {			
			List<ModelSort> t = data[i];
			if (t.size() == 0)
				continue;
			int type = t.get(0).getType();
			double s = 0;
			for (int j=0;j<t.size();j++) {	//处理集合每个元素
				if (type == SORTTIME) {
					s = 1000 - j;					
				} else if (type == SORTGOOD) {
					s = (1000 - j)*0.5 + t.get(j).getData()*0.9;
				} else if (type == SORTREPLY) {
					s = (1000 - j)*0.5 + t.get(j).getData()*10;
				}
				if (sortown.get(t.get(j).getId()) != null) {
					s = sortown.get(t.get(j).getId()) + s;
				}
				sortown.put(t.get(j).getId(), s);
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