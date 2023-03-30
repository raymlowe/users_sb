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

import com.ditheringllama.demo.dao.GroupDAO;
import com.ditheringllama.demo.model.Group;
import com.ditheringllama.demo.ui.model.GroupUI;



@Component("GroupDAO")
public class GroupDAOImpl implements GroupDAO{

	@Autowired
	public SessionFactory orbeonWSSessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public Group get(String id) {
		Group group = (Group) orbeonWSSessionFactory.getCurrentSession().get(Group.class, id);
		return group;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Group getByName(String groupName) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Group> queryCriteria = builder.createQuery(Group.class);
		
		Root<Group> from = queryCriteria.from(Group.class);
		queryCriteria.select(from);
		queryCriteria.where(builder.equal(from.get("groupName"), groupName));
		
		Query<Group> query = session.createQuery(queryCriteria);
		return query.uniqueResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Group> getAllGroups() {
		
		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Group> queryCriteria = builder.createQuery(Group.class);
		
		Root<Group> from = queryCriteria.from(Group.class);
		queryCriteria.select(from);
		queryCriteria.orderBy(builder.asc(from.get("groupName")));
		
		Query<Group> query = session.createQuery(queryCriteria);
		return query.list();
	}

	@Override
	@Transactional
	public void save(Group group) {
		orbeonWSSessionFactory.getCurrentSession().saveOrUpdate(group);	
	}

	@Override
	@Transactional
	public boolean delete(final String id) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		Group group = session.get(Group.class, id);
		if(null != group) {
			session.delete(group);
		}else {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateGroup(GroupUI groupUI) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		Group group = session.get(Group.class, groupUI.getId());
		
		//Perform Replace
		group.setGroupDescription(groupUI.getGroupDescription());
		group.setGroupName(groupUI.getGroupName());
		group.setId(groupUI.getId());
		try {
			session.saveOrUpdate(group);
			return true;
		}catch (Exception e) {
			
		}
		return false;
	}
}
