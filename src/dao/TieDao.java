package dao;

import java.util.List;

import tools.objects.community.ModelTie;
import domain.Tie;
import domain.TieTheme;

public interface TieDao
{
	Tie get(Integer id);
	Integer save(Tie tie);
	void update(Tie tie);
	List<ModelTie> getTies(TieTheme tieth);
}