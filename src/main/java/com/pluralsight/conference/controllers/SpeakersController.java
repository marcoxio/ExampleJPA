package com.pluralsight.conference.controllers;


import com.pluralsight.conference.models.Speaker;
import com.pluralsight.conference.repository.SpeakerJpaRepository;
import com.pluralsight.conference.repository.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    @Autowired
    private SpeakerRepository repository;

    @GetMapping
    public List<Speaker> list() {
        return repository.list();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return repository.find(id);
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker) {

        return repository.create(speaker);

    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        repository.delete(id);
    }

    @PutMapping
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = repository.find(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return repository.update(speaker);
    }



}
