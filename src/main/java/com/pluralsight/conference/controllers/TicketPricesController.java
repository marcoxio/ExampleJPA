package com.pluralsight.conference.controllers;

import com.pluralsight.conference.models.TicketPrice;
import com.pluralsight.conference.repository.TicketPriceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket_prices")
public class TicketPricesController {
    @Autowired
    private TicketPriceRepository repository;

    @GetMapping
    public List<TicketPrice> list(){
        return repository.list();
    }

    @PostMapping
    public TicketPrice create(@RequestBody final TicketPrice tp){
        return repository.create(tp);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){
        repository.delete(id);
    }

    @PutMapping
    public TicketPrice update(@PathVariable Long id, @RequestBody TicketPrice tp){
        TicketPrice existingTp = repository.find(id);
        BeanUtils.copyProperties(tp, existingTp, "ticket_price_id");
        return repository.update(tp);
    }

}
