package com.ytrewq.rosLearning.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public abstract class BaseRepository<Entity, T> {
    private final Class<Entity> entityClass;
    @PersistenceContext
    private EntityManager entityManager;

    public BaseRepository(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void create(Entity entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public void delete(Entity entity) {
        entityManager.remove(entity);
    }

    @Transactional
    public Entity update(Entity entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    public Entity findById(Class<Entity> entityClass, int id) {
        return entityManager.find(entityClass, id);
    }

    @Transactional
    public Set<Entity> findAll(Class<Entity> entityClass) {
        String jpqlQuery = "SELECT e FROM %s e".formatted(entityClass.getName());
        return new HashSet<>(entityManager.createQuery(jpqlQuery, entityClass).getResultList());

    }

}