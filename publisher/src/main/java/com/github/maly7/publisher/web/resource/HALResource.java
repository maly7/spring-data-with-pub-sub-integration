package com.github.maly7.publisher.web.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.Map;

public class HALResource extends ResourceSupport {

    @JsonProperty("_embedded")
    private Map<String, Object> embedded = new HashMap<>();

    public void embed(String name, Object value) {
        embedded.put(name, value);
    }

    public Map<String, Object> getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Map<String, Object> embedded) {
        this.embedded = embedded;
    }
}
