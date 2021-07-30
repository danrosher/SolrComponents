package com.github.danrosher.solr.handler.component;

import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.handler.component.SearchComponent;

public class ServerTimer extends SearchComponent {
    @Override
    public void prepare(ResponseBuilder rb) {
    }

    @Override
    public void process(ResponseBuilder rb) {
        rb.rsp.addHttpHeader("Server-Timing", "solr;dur="+rb.req.getRequestTimer().getTime());
    }

    @Override
    public String getDescription() {
        return "Server-Timing";
    }
}
