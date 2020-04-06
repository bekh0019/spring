package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
/**
 * This class returns all
 * http message converters for current application
 */
@Component
public class RefreshListener {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        handlerAdapter.getMessageConverters()
                .forEach(System.out::println);
    }
}
