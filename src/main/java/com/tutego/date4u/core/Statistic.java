package com.tutego.date4u.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.tutego.date4u.core.core.NewPhotoEvent;

@Service
public class Statistic {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onNewPhotoEvent(NewPhotoEvent event){
        log.info("New Photo: {}", event);
    }
}
