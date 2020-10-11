package com.pluralsight.conference.repository;

import com.pluralsight.conference.models.TicketType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TicketTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TicketType find(String id) {
        return entityManager.find(TicketType.class, id);
    }
}
