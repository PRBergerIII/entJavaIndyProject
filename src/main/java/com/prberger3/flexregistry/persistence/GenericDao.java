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

/**
 * This class is dynamically typed so it can act as a DAO for
 * any object in the persistence model.
 *
 * @param <T> the type parameter
 */
public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Instantiates a new session
     *
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Gets all entities of a type.
     *
     * @return the all
     */
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
     * Gets entities by id.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the by id
     */
    public <T>T getById(int id) {

        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;

    }

    /**
     * Gets entities by composite id.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the by id
     */
    public <T>T getById(Serializable id) {

        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;

    }

    /**
     * Finds entities by one of its properties
     *
     * @param propertyName the property name
     * @param value        the value by which to find
     * @return list
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
     * Finds entities by one of its properties
     *
     * @param propertyName the property name
     * @param value        the value by which to find
     * @return list
     */
    public List<T> findByPropertyLike(String propertyName, String value) {
        value = "%" + value + "%";
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.like(root.get(propertyName), value));

        return session.createQuery(query).getResultList();
    }

    /**
     * Save or update.
     *
     * @param entity the entity
     */
    public void saveOrUpdate(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();

    }

    /**
     * Insert int.
     *
     * @param entity the entity
     * @return the int
     */
    public int insert(T entity) {

        int newId = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        newId = (int)session.save(entity);
        transaction.commit();
        session.close();

        return newId;

    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    public void delete(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();

    }

}
