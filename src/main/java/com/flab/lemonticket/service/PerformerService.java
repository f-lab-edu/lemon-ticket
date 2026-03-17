package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.Performer;
import com.flab.lemonticket.repository.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerformerService {
    @Autowired
    private PerformerRepository performerRepository;

    @Transactional
    public Performer addPerformer(Performer performer){
        return performerRepository.save(performer);
    }
}
