package service.Impl;

import dao.VersionDao;
import domain.Version;
import service.versionService;
import tools.NetErrorUtil;
import tools.objects.ModelAppUpdate;
import tools.objects.ResponseVersion;

public class versionServiceImpl implements versionService
{
	private VersionDao version;
	
	@Override
	public ResponseVersion getLastestVersion() {
		ResponseVersion res = new ResponseVersion();
		Version v = version.getLatestVersion();
		if (v != null ) {
			res.setStat(true);
			ModelAppUpdate m = new ModelAppUpdate();
			m.setVersioncode(v.getVersioncode());
			m.setVersionname(v.getVersionname());
			m.setUpdateinfo(v.getUpdateinfo());
			m.setDownloadurl(v.getDownloadurl());
			res.setVersion(m);
		} else {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		
		return res;
	}

	public VersionDao getVersion() {
		return version;
	}

	public void setVersion(VersionDao version) {
		this.version = version;
	}
	
}