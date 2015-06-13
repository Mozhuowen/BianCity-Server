package service;

import java.util.List;

import domain.Image;
import tools.objects.ResponseSimple;

public interface TieService
{
	ResponseSimple submitTie(int townid,int tiethid,int userid,String content,List<Image> images);
}