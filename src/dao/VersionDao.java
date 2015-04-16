package dao;

import domain.Version;

public interface VersionDao
{
	Version get(Integer id);
	Integer save(Version v);
	void update(Version v);
	Version getLatestVersion();
}