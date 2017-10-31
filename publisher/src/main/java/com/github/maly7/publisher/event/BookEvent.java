package com.github.maly7.publisher.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.ApplicationEvent;

public class BookEvent extends ApplicationEvent {

    public BookEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("source", source)
                .toString();
    }
}
