package com.njmsita.exam.base;

import java.io.Serializable;
import java.util.List;


public interface BaseEbi<T> {

	/**
	 * 保存
	 * @param t    模型数据
	 */
	public void save(T t);


	/**
	 * 获取所有
	 * @return
	 */
	public List<T> getAll();

	/**
	 * 根据uuid获取
	 * @param uuid 	id
	 * @return		具体模型数据
	 */
	public T get(Serializable uuid);

	/**
	 * 根据查询条件获取所有
	 * @param qm		查询条件模型数据
	 * @param pageNum	页码
	 * @param pageCount	页容量
	 * @return			模型数据集合
	 */
	public List<T> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount);

	/**
	 * 统计查询条件下的数据总量
	 * @param qm		查询条件模型数据
	 * @return			数据总量
	 */
	public Integer getCount(BaseQueryModel qm);

	/**
	 * 更新
	 * @param t  		具体模型数据
	 */
    public void update(T t);
}
