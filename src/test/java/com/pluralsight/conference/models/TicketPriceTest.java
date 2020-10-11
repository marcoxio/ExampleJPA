package com.pluralsight.conference.models;

import com.pluralsight.conference.repository.PricingCategoryRepository;
import com.pluralsight.conference.repository.TicketPriceJpaRepository;
import com.pluralsight.conference.repository.TicketTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketPriceTest {
    @Autowired
    private TicketPriceJpaRepository repository;

    @Autowired
    private PricingCategoryRepository pcRepository;

    @Autowired
    private TicketTypeRepository ttRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testFind() throws Exception {
        TicketPrice ticket = repository.getOne(1L);
        assertNotNull(ticket);
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        TicketPrice tp = new TicketPrice();
        tp.setBasePrice(BigDecimal.valueOf(200, 2));
        tp.setPricingCategory(pcRepository.find("E"));
        tp.setTicketType(ttRepository.find("C"));
        tp = repository.saveAndFlush(tp);

        entityManager.clear();

        TicketPrice otherTp = repository.getOne(tp.getTicketPriceId());
        assertEquals(BigDecimal.valueOf(200, 2), otherTp.getBasePrice());

        repository.deleteById(otherTp.getTicketPriceId());
    }

    @Test
    public void testQueryAnnotation() throws Exception {
        List<TicketPrice> tickets = repository.getTicketsUnderPriceWithWorkshops(BigDecimal.valueOf(1000));
        assertTrue(tickets.size() > 0);
    }

    @Test
    public void testNamedQuery() throws Exception {
        List<TicketPrice> tickets = repository.namedFindTicketsByPricingCategoryName("Regular");
        assertTrue(tickets.size() > 0);
    }

    @Test
    public void testNamedNativeQuery() throws Exception {
        List<TicketPrice> tickets = repository.nativeFindTicketsByCategoryWithWorkshop("Regular");
        assertTrue(tickets.size() > 0);
    }

}
