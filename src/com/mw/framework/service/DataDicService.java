package com.mw.framework.service;

import java.util.List;

import com.mw.framework.domain.SysDataDic;

public interface DataDicService {

	public boolean save(SysDataDic dd);
	public boolean deleteById(Integer id);
	public List<SysDataDic> getDD(Integer id);
}
