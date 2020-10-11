package com.pluralsight.conference.repository;

import com.pluralsight.conference.models.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PricingCategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PricingCategory find(String id) {
        return entityManager.find(PricingCategory.class, id);
    }
}
