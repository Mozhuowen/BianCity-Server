package service;

import java.util.List;

import domain.Image;
import tools.objects.ResponsePutao;

public interface putaoService
{
	ResponsePutao create(int townid,String title,String cover,String content,List<Image>iamges);
	ResponsePutao getPutao(int townid,int position);
	
}