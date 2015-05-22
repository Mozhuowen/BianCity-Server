package service.Impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import dao.putaoDao;
import dao.townDao;
import domain.putao;
import domain.town;
import domain.Image;
import domain.users;
import service.putaoService;
import tools.NetErrorUtil;
import tools.objects.PackagePutao;
import tools.objects.ResponsePutao;

public class putaoServiceImpl implements putaoService
{
	private townDao townx;
	private putaoDao putaox;
	
	public void setPutaox(putaoDao p) {
		this.putaox = p;
	}
	public putaoDao getPutaox() {
		return this.putaox;
	}
	public void setTownx(townDao t) {
		this.townx = t;
	}
	public townDao getTownx() {
		return this.townx;
	}

	@Override
	public ResponsePutao create(int townid,String title, String cover, String content,
			List<Image> images) {
		//无需再做检查直接创建
		ResponsePutao res = new ResponsePutao();
		town t = townx.get(townid);
		users u = t.getOwner();
		putao p = new putao();
		p.setParenttown(t);
		p.setTitle(title);
		p.setCover(cover);
		p.setContent(content);
		p.setCreatetime(Calendar.getInstance());
		p.setImages(images);
		//处理每张图片的parent-putao
		for (int i=0;i<images.size();i++) {
			images.get(i).setP(p);
		}
		p.setOwner(u);
		if (putaox.save(p)>0) {			
			res.setStat(true);
			List<PackagePutao> putaolist = new ArrayList<PackagePutao>();
			List<putao> putaos = new ArrayList<putao>(t.getPutao());
			for (int i=0;i<putaos.size();i++) {
				PackagePutao pt = new PackagePutao();
				pt.setTownid(townid);
				pt.setPutaoid(putaos.get(i).getPutaoid());
				pt.setTitle(putaos.get(i).getTitle());
				pt.setCover(putaos.get(i).getCover());
				pt.setContent(putaos.get(i).getContent());
				pt.setUsercover(t.getOwner().getCover());
				pt.setUsername(t.getOwner().getName());
				pt.setCreatetime(getFormatDate(putaos.get(i).getCreatetime()));
				pt.setUserid(t.getOwner().getUsersid());
				//处理包含的图片
				List <String> imagenames = new ArrayList<String>();
				List<Image> is = putaos.get(i).getImages();
				for (int j=0;j<is.size();j++) {
					imagenames.add(is.get(j).getImagename());
				}
				pt.setImagenames(imagenames);
				putaolist.add(pt);
			}
			res.setPutao(putaolist);
		} else {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return res;
	}
	public String getFormatDate(Calendar time) {
		String datestr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(time.getTime());
		return datestr;
	}
	@Override
	public ResponsePutao getPutao(int townid,int positino) {
		ResponsePutao res = new ResponsePutao();
		town t = townx.get(townid);
		res.setStat(true);
		List<PackagePutao> putaolist = new ArrayList<PackagePutao>();
		List<putao> putaos = new ArrayList<putao>(putaox.loadMorePutao(t, positino));
		for (int i=0;i<putaos.size();i++) {
			PackagePutao pt = new PackagePutao();
			pt.setTownid(townid);
			pt.setPutaoid(putaos.get(i).getPutaoid());
			pt.setTitle(putaos.get(i).getTitle());
			pt.setCover(putaos.get(i).getCover());
			pt.setContent(putaos.get(i).getContent());
			pt.setUsercover(t.getOwner().getCover());
			pt.setUsername(t.getOwner().getName());
			pt.setCreatetime(getFormatDate(putaos.get(i).getCreatetime()));
			pt.setGoods(putaos.get(i).getGoods());
			pt.setUserid(t.getOwner().getUsersid());
			//处理包含的图片
			List <String> imagenames = new ArrayList<String>();
			List<Image> is = putaos.get(i).getImages();
			for (int j=0;j<is.size();j++) {
				imagenames.add(is.get(j).getImagename());
			}
			pt.setImagenames(imagenames);
			putaolist.add(pt);
		}
		res.setPutao(putaolist);
		return res;
	}
	@Override
	public List<putao> getStoryByTime(int position) {
		return this.putaox.getStoryByTime(position);
	}
	@Override
	public Long getStoryCount() {
		return this.putaox.getStoryCount();
	}

}