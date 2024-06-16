package com.flab.lemonticket.controller;

import com.flab.lemonticket.entity.Performer;
import com.flab.lemonticket.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/performer")
public class PerformerController {
    @Autowired
    private PerformerService performerService;

    @PostMapping("/add")
    public ResponseEntity<Performer> addPerformer(@RequestBody Performer performer) {
        return ResponseEntity.ok(performerService.addPerformer(performer));
    }
}
