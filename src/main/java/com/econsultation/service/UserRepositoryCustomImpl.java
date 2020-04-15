package com.econsultation.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.econsultation.controller.UserController;
import com.econsultation.jpa.UserRepositoryCustom;
import com.econsultation.model.User;


public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);
	

    @Override
    public List<User> getActiveUserByRoleIdAndEndDate(long roleId) {  
    	log.info("in findUserByRoleId = " + roleId);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q =cb.createQuery(User.class);
        
        //Using criteria builder you can build your criteria queries.
        Root<User> users = q.from(User.class);
        Predicate userRolePredicate = cb.equal(users.get("roleId"), roleId);
        //Predicate startDatePredicate = cb.equal(users.get("startDate"), startDate);
        Predicate endDatePredicate = cb.equal(users.get("endDate"), null);
        Predicate namePredicate = cb.like(users.get("firstName"), "%Pat2%");
        //Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
        q.where(userRolePredicate,endDatePredicate); // endDatePredicate); startDatePredicate
        
        TypedQuery<User> query = em.createQuery(q);
        return query.getResultList();
        
    }

}