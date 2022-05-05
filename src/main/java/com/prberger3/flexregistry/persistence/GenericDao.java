package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.UserConnectionId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO: 4/30/2022 javadocs 

public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    public <T>T getById(int id) {

        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;

    }

    // TODO: 5/5/2022 delete if not needed
//    public <T>T getById(Serializable id) {
//
//        Session session = getSession();
//        T entity = (T)session.get(type, id);
//        session.close();
//        return entity;
//
//    }

    public void saveOrUpdate(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();

    }

    public int insert(T entity) {

        int newId = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        newId = (int)session.save(entity);
        transaction.commit();
        session.close();

        return newId;

    }

    // TODO: 5/5/2022 delete if not needed
//    public UserConnectionId insertConnection(T entity) {
//
//        UserConnectionId newId;
//        Session session = getSession();
//        Transaction transaction = session.beginTransaction();
//
//        newId = (UserConnectionId)session.save(entity);
//        transaction.commit();
//        session.close();
//
//        return newId;
//
//    }

    public void delete(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();

    }

    public List<T> getAll() {

        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;

    }

    /**
     * Finds entities by one of its properties.

     * @param propertyName the property name.
     * @param value the value by which to find.
     * @return
     */
    public List<T> findByPropertyEqual(String propertyName, Object value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName),value));

        return session.createQuery(query).getResultList();
    }

    /**
     * Finds entities by one of its properties.

     * @param propertyName the property name.
     * @param value the value by which to find.
     * @return
     */
    public List<T> findByPropertyEqual(String propertyName, boolean value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName),value));

        return session.createQuery(query).getResultList();
    }

    /**
     * Finds entities by multiple properties.
     * Inspired by https://stackoverflow.com/questions/11138118/really-dynamic-jpa-criteriabuilder
     *
     * @param propertyMap property and value pairs
     * @return entities with properties equal to those passed in the map
     */
    public List<T> findByPropertyEqual(Map<String, Object> propertyMap) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Map.Entry entry: propertyMap.entrySet()) {
            predicates.add(builder.equal(root.get((String) entry.getKey()), entry.getValue()));
        }
        query.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        return session.createQuery(query).getResultList();
    }

}
