package dao;

import java.util.List;

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
}