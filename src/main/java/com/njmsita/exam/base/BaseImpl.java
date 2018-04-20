package com.njmsita.exam.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> entityClass=null;
	public BaseImpl(){
		Type genType = getClass().getGenericSuperclass();   
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();   
		entityClass =  (Class)params[0];  
	}
	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	public Serializable save(T t) {
		if (t!=null){
			return this.getCurrentSession().save(t);
		}
		return null;
	}
	public void update(T t) {
		if (t!=null){
			this.getCurrentSession().update(t);
		}
	}
	public void delete(T t) {
		if (t!=null){
			this.getCurrentSession().delete(t);
		}
	}

	public List<T> getAll() {
		Criteria criteria = this.getCurrentSession().createCriteria(entityClass);
		return criteria.list();
	}
	public T get(Serializable uuid) {
		return (T) this.getCurrentSession().get(entityClass,uuid);
	}
	public List<T> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount) {
		Criteria criteria = this.getCurrentSession().createCriteria(entityClass);
		doQbc(criteria, qm);
		criteria.setFirstResult((pageNum-1)*pageCount);
		criteria.setMaxResults(pageCount);
		return criteria.list();

	}
	public Integer getCount(BaseQueryModel qm) {
		Criteria criteria = this.getCurrentSession().createCriteria(entityClass);
		doQbc(criteria, qm);
		criteria.setProjection(Projections.rowCount());
		return (Integer) criteria.list().get(0);
	}

	public abstract void doQbc(Criteria dc,BaseQueryModel qm);
}
