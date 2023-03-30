package com.ditheringllama.demo.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ditheringllama.demo.dao.RoleDAO;
import com.ditheringllama.demo.model.Role;
import com.ditheringllama.demo.ui.model.RoleUI;

@Component("RoleDAO")
public class RoleDAOImpl implements RoleDAO{

	@Autowired
	public SessionFactory orbeonWSSessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public Role get(String id) {
		Role role = (Role) orbeonWSSessionFactory.getCurrentSession().get(Role.class, id);
		return role;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Role getByName(String roleName) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Role> queryCriteria = builder.createQuery(Role.class);
		
		Root<Role> from = queryCriteria.from(Role.class);
		queryCriteria.select(from);
		queryCriteria.where(builder.equal(from.get("roleName"), roleName));
		
		Query<Role> query = session.createQuery(queryCriteria);
		return query.uniqueResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> getAllRoles() {
//		Criteria criteria = orbeonWSSessionFactory.getCurrentSession().createCriteria(Role.class);
//		criteria.addOrder(Order.asc("role_name").ignoreCase());
//		return criteria.list();
		
		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Role> queryCriteria = builder.createQuery(Role.class);
		
		Root<Role> from = queryCriteria.from(Role.class);
		queryCriteria.select(from);
		queryCriteria.orderBy(builder.asc(from.get("roleName")));
		
		Query<Role> query = session.createQuery(queryCriteria);
		return query.list();
	}

	@Override
	@Transactional
	public void save(Role role) {
		orbeonWSSessionFactory.getCurrentSession().saveOrUpdate(role);	
		
	}

	@Override
	@Transactional
	public boolean delete(String id) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		Role role = session.get(Role.class, id);
		if( null != role) {
			session.delete(role);
		}else {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateRole(RoleUI roleUI) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		Role role = session.get(Role.class, roleUI.getId());
		//Perform Replace
		role.setRoleDescription(roleUI.getRoleDescription());
		role.setId(roleUI.getId());
		role.setRoleName(roleUI.getRoleName());
		try {
			session.saveOrUpdate(role);
			return true;
		}catch (Exception e) {
			
		}
		return false;
	}

}
