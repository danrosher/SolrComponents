package com.github.danrosher.solr.handler.component;

import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.handler.component.SearchComponent;

import java.io.IOException;

public class DelayingSearchComponent extends SearchComponent {
    @Override
    public void prepare(ResponseBuilder rb) throws IOException {
        rb.rsp.addHttpHeader("Warning", "This is a test warning");
    }

    @Override
    public void process(ResponseBuilder rb) throws IOException {
        int sleep = rb.req.getParams().getInt("sleep",0);
        try {
            if (sleep > 0) {
                Thread.sleep(sleep);
            }
        } catch (InterruptedException e) {
            // Do nothing?
        }
    }

    @Override
    public String getDescription() {
        return "SearchComponent used to add delay to each request";
    }

}
