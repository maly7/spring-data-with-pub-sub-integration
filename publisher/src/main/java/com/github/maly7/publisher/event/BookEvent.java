package com.github.maly7.publisher.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BookEvent {
    private Long id;

    public BookEvent() {
        this(0L);
    }

    public BookEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }
}
