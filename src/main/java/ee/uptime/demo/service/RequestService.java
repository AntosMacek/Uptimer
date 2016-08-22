package ee.uptime.demo.service;

import ee.uptime.demo.handler.SignedRequestsHandler;
import ee.uptime.demo.model.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RequestService {

    static Logger log = Logger.getLogger(RequestService.class.getName());

    public static String getSignedUrl(Query query, int page) {

        SignedRequestsHandler helper = new SignedRequestsHandler();

        Map<String, String> params = new HashMap<String, String>();

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", "AKIAJILKKBDHTI6DGRFQ");
        params.put("AssociateTag", "paintnguess-20");
        params.put("SearchIndex", query.getCategory());
        params.put("ItemPage", page + "");
        params.put("Keywords", query.getQuery());
        params.put("ResponseGroup", "ItemAttributes,Offers");
        params.put("Sort", "price");

        String requestUrl = helper.sign(params);

        log.info("Signed URL: \"" + requestUrl + "\"");

        return requestUrl;
    }

}