package com.njmsita.exam.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;

public abstract class BaseImpl<T>  extends HibernateDaoSupport implements BaseDao<T> {


	@Resource
	public void mySetSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	private Class<T> entityClass=null;
	public BaseImpl(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass =  (Class)params[0];
	}
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public List<T> getAll() {

		DetachedCriteria dc= DetachedCriteria.forClass(entityClass);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}
	public T get(Serializable uuid) {
		return this.getHibernateTemplate().get(entityClass, uuid);
	}
	public List<T> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount) {
		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		doQbc(dc, qm);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}
	public Integer getCount(BaseQueryModel qm) {

		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		doQbc(dc, qm);
		dc.setProjection(Projections.rowCount());
		List<Long> lists= (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		return lists.get(0).intValue();
	}

	public abstract void doQbc(DetachedCriteria dc,BaseQueryModel qm);
}
