package service;

import tools.objects.ResKeyWord;
import tools.objects.ResSearch;

public interface SearchService
{
	ResKeyWord getKeyWord(String keywork);
	ResSearch searchTown(String towname);
}