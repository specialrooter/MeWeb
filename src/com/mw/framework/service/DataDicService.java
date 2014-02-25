package com.mw.framework.service;

import java.util.List;

import com.mw.framework.domain.DataDic;

public interface DataDicService {

	public boolean save(DataDic dd);
	public boolean deleteById(Integer id);
	public List<DataDic> getDD(Integer id);
}
