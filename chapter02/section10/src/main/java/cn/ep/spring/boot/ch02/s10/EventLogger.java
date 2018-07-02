package cn.ep.spring.boot.ch02.s10;

import org.ehcache.event.*;
import org.slf4j.*;

public class EventLogger implements CacheEventListener<Object, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogger.class);

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
        LOGGER.info("Event: " + event.getType() + " Key: " + event.getKey() + " old value: " + event.getOldValue()
                + " new value: " + event.getNewValue());
    }

}