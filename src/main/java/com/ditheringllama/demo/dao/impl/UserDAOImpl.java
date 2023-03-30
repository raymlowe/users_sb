package com.ditheringllama.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ditheringllama.demo.dao.UserDAO;
import com.ditheringllama.demo.model.User;
import com.ditheringllama.demo.ui.model.UserUI;



@Component("UserDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	public SessionFactory orbeonWSSessionFactory;

	@Override
	@Transactional(readOnly = true)
	public User get(String id) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> queryCriteria = builder.createQuery(User.class);
		
		Root<User> from = queryCriteria.from(User.class);
		queryCriteria.select(from);
		queryCriteria.where(builder.equal(from.get("id"), id));
		
		Query<User> query = session.createQuery(queryCriteria);
		return query.uniqueResult();
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUser(String userId) {

		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> queryCriteria = builder.createQuery(User.class);
		Root<User> root = queryCriteria.from(User.class);
		queryCriteria.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (null != userId) {
			predicates.add(builder.equal(root.get("userId"), userId));
		}
		queryCriteria.where(predicates.toArray(new Predicate[predicates.size()]));
		Query<User> query = session.createQuery(queryCriteria);
		return query.getSingleResult();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
//		String sql = "SELECT * FROM app_auth_user";
//		NativeQuery query = orbeonWSSessionFactory.getCurrentSession().createNativeQuery(sql);
//		List<User> users = query.getResultList();
//		return users;
		
		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> queryCriteria = builder.createQuery(User.class);
		Root<User> root = queryCriteria.from(User.class);
		queryCriteria.select(root);
		
		Query<User> query = session.createQuery(queryCriteria);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<User> getGroupUsers(String groupId) {
//		CriteriaBuilder criteria = orbeonWSSessionFactory.getCurrentSession().createCriteria(User.class);
//		criteria.createCriteria("userGroup").add(Restrictions.isNotNull("group")).createCriteria("group")
//				.add(Restrictions.eq("id", groupId));
//		return criteria.list();
		
		Session session = orbeonWSSessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> queryCriteria = builder.createQuery(User.class);
		Root<User> root = queryCriteria.from(User.class);
		queryCriteria.select(root);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(groupId != null) {
			predicates.add(builder.equal(root.get("userGroup").get("group_id"), groupId));
		}
		queryCriteria.where(predicates.toArray(new Predicate[predicates.size()]));
		Query<User> query = session.createQuery(queryCriteria);
		return query.getResultList();
		
		
//		String sql = "SELECT user_id FROM app_auth_user_group where group_id = "+groupId;
//		NativeQuery query = orbeonWSSessionFactory.getCurrentSession().createNativeQuery(sql);
//		List<String> userIds = query.getResultList();
		
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		orbeonWSSessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}

	@Override
	@Transactional
	public boolean delete(String id) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		if(null != user) {
			session.delete(user);
		}else {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateUser(UserUI userUI) {
		Session session = orbeonWSSessionFactory.getCurrentSession();
		User user = session.get(User.class, userUI.getId());
		
		//Perform Replace
		user.setId(userUI.getId());
		user.setUserId(userUI.getUserId());
		user.setEmail(userUI.getEmail());
		user.setFirstname(userUI.getFirstName());
		user.setIsactive(userUI.getIsActive());
		user.setLastname(userUI.getLastName());
		user.setPhone(userUI.getPhone());
		user.setUsertype(userUI.getUserType());
		try {
			session.saveOrUpdate(user);
			return true;
		}catch (Exception e) {
			
		}
		return false;
	}

}
