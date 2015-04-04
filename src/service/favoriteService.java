package service;

import tools.objects.ResponseFavori;

public interface favoriteService
{
	ResponseFavori getFavorite(int userid,int putaoid);
	ResponseFavori doFavorite(int userid,int putaoid,int action);
}