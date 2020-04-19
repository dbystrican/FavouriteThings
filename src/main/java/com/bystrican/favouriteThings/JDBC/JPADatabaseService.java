package com.bystrican.favouriteThings.JDBC;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;
import java.util.List;
@Alternative
public class JPADatabaseService implements DatabaseInterface{
    @PostgresDB
    @PersistenceContext(unitName = "jta")
    EntityManager entityManager;
    @Override
    public boolean delete(int id) {
        FavouriteThing favouriteThing = this.read(id);
        if(favouriteThing==null) return false;
        favouriteThing = entityManager.merge(favouriteThing);
        entityManager.remove(favouriteThing);
        return true;
    }


    @Override
    public boolean insert(FavouriteThing favouriteThing) {
        entityManager.persist(favouriteThing);
        return true;
    }

    @Override
    public boolean update(int id, FavouriteThing favouriteThing) {
        entityManager.merge(favouriteThing);
        return true;
    }

    @Override
    public FavouriteThing read(int id) {
        return entityManager.find(FavouriteThing.class,id);
    }

    @Override
    public List<FavouriteThing> read() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FavouriteThing> criteria = criteriaBuilder.createQuery(FavouriteThing.class);
        Root<FavouriteThing> member = criteria.from(FavouriteThing.class);
        criteria.select(member).orderBy(criteriaBuilder.asc(member.get("id")));
        return entityManager.createQuery(criteria).getResultList();
    }
}
