package ee.uptime.demo;

import ee.uptime.demo.model.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
 * This class shows how to make a simple authenticated call to the
 * Amazon Product Advertising API.
 *
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class RequestService {

    static Logger log = Logger.getLogger(RequestService.class.getName());

    public static String getSignedUrl(Query query) {

        /*
         * Set up the signed requests helper.
         */
        SignedRequestsHelper helper = new SignedRequestsHelper();

        String requestUrl = null;

        Map<String, String> params = new HashMap<String, String>();

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", "AKIAJILKKBDHTI6DGRFQ");
        params.put("AssociateTag", "paintnguess-20");
        params.put("SearchIndex", query.getCategory());
        params.put("Keywords", query.getQuery());
        params.put("ResponseGroup", "ItemAttributes,Offers");
        params.put("Sort", "price");

        requestUrl = helper.sign(params);

        log.info("Signed URL: \"" + requestUrl + "\"");

        return requestUrl;
    }

}