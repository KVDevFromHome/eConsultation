package com.econsultation.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.econsultation.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> //,UserRepositoryCustom 
//extends CrudRepository<User, Long> 
{

	// implemented custom method UserRepositoryCustomImpl
	// List<User> findByRoleId(long roleId);
//	@Query("SELECT u FROM User u WHERE u.roleId = ?1 and u.firstName like '%sau%'")
//	public List<User> getActiveUserByRoleId(long roleId);
	
//	@Query("SELECT u FROM User u WHERE u.getUserRole.roleId = ?1 ")//and u.startDate = '04/05/2020'")
//	public List<User> getActiveUserByRoleId(long roleId);
	
	@Query("SELECT u FROM User u WHERE u.userRole.roleId = ?1 and u.endDate is null")
	public List<User> getActiveUserByRoleIdAndEndDate(long roleId, Date endDate);
	
	public User findByUserId(long userId);
	
	public User findByUserName(String userName);
}

/*
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

//	public User findByUserId(long userId) {
//		return em.find(User.class, userId);
//	}

		public User save(User user) {
			logger.info("in save = " + user);
			try {
				if (user.getUserId() == null) {
					em.persist(user);
				} else {
					em.merge(user);
				}
			} catch (Exception ex) {
				logger.info(ex.getMessage());
			}

			return user;
		}

		public User findByUserId(long userId) {

			logger.info("in findByUserId = " + userId);
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> q = cb.createQuery(User.class);

			// Using criteria builder you can build your criteria queries.
			Root<User> users = q.from(User.class);
			Predicate userIdPredicate = cb.equal(users.get("userId"), userId);
			//Predicate namePredicate = cb.like(users.get("firstName"), "%sau%");
			// Predicate endDatePredicate = cb.equal(users.get("endDate"), null);
			q.where(userIdPredicate);// , endDatePredicate);

			TypedQuery<User> query = em.createQuery(q);
			return query.getResultList().get(0);
		}

		public List<User> getActiveUserByRoleId(long roleId) {
			logger.info("in findUserByRoleId = " + roleId);
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> q = cb.createQuery(User.class);

			// Using criteria builder you can build your criteria queries.
			Root<User> users = q.from(User.class);
			Predicate userRolePredicate = cb.equal(users.get("roleId"), roleId);
			//Predicate namePredicate = cb.like(users.get("firstName"), "%sau%");
			Predicate endDatePredicate = cb.equal(users.get("endDate"), null);
			// Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
			q.where(userRolePredicate, endDatePredicate); //namePredicate);// , 

			TypedQuery<User> query = em.createQuery(q);
			return query.getResultList();

		}

	}
*/
