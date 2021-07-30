package com.github.danrosher.solr.handler.component;

import org.apache.solr.SolrTestCaseJ4;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServerTimerTest  extends SolrTestCaseJ4 {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("enable.update.log", "false"); // schema12 doesn't support _version_
        initCore("solrconfig.xml", "schema12.xml");
    }

    @Test
    public void testHeader() throws Exception {
        SolrQueryRequest req = req(
            "q", "*:*",
            "sleep", "1000");
        SolrQueryResponse rsp = h.queryAndResponse("/select", req);
        String headerVal = rsp.getHttpHeader("Server-Timing");
        String timing = headerVal.replaceFirst("solr;dur=","");
        assertTrue(Float.parseFloat(timing) > 1000);
        req.close();
    }

}
