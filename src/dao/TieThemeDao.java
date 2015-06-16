package dao;

import java.util.List;

import tools.ModelSort;
import tools.SortAction.SortData;
import tools.objects.community.ModelTieTheme;
import tools.objects.community.ModelTopTie;
import domain.TieTheme;
import domain.town;

public interface TieThemeDao
{
	TieTheme get(Integer id);
	Integer save(TieTheme tie);
	void update(TieTheme tie);
	List<ModelTieTheme> getTopTie(town t);
	List<ModelTieTheme> getTies(town t);
	List<ModelSort> sortByTime(town t);
	List<ModelSort> sortByReply(town t);
	List<ModelSort> sortByGoodCou(town t);
	List<TieTheme> getTargetTie(List<SortData> targetdata);
}