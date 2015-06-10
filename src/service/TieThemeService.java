package service;

import java.util.List;

import domain.Image;
import tools.objects.ResponseSimple;

public interface TieThemeService
{	
	ResponseSimple createNew(int townid,int userid,String title,String content,List<Image> images);
}