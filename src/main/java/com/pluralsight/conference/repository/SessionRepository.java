package com.pluralsight.conference.repository;

import com.pluralsight.conference.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {
    @Autowired SessionJpaRepository jpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Session create(Session session) {
        return jpaRepository.saveAndFlush(session);
    }

    public Session update(Session session) {
        return jpaRepository.saveAndFlush(session);
    }

    public void delete(Long id) {
         jpaRepository.getOne(id);
    }

    public Session find(Long id) {
        return jpaRepository.getOne(id);
    }

    public List<Session> list() {
        return jpaRepository.findAll();
    }

    public List<Session> getSessionsThatHaveName(String name) {
        return jpaRepository.findBysessionNameContains(name);

    }


}
