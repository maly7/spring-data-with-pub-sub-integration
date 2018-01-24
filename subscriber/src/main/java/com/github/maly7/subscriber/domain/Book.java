package com.github.maly7.subscriber.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Book {
    private String title;

    @JsonGetter("title")
    public String getTitle() {
        return title;
    }

    @JsonIgnore
    public void setTitle(String title) {
        this.title = title;
    }

    // See http://www.baeldung.com/jackson-nested-values for some better examples, this seems kinda hacky to me
    @SuppressWarnings("unchecked")
    @JsonProperty("_embedded")
    public void setEmbedded(Map<String, Object> embedded) {
        Map<String, String> identifiers = (Map<String, String>) embedded.get("identifiers");
        this.title = identifiers.get("title");
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                '}';
    }
}
