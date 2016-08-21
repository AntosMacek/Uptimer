package ee.uptime.demo.handler;

import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import ee.uptime.demo.service.RequestService;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PageHandler {

    private static ConcurrentHashMap<String, List<Item>> globalCachedItems = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Integer, Item> cachedItems;
    private static ArrayList<Item> items;
    private static Query initialQuery;

    public static ArrayList<Item> createPageInfo(int page) {
        ArrayList<Item> pageList = new ArrayList<>();

        int pageIndex = 1 + (page - 1) * 13;
        int pageLimitIndex = pageIndex + 13;
        if (pageLimitIndex > 101) {
            pageLimitIndex = 101;
        }
        for (int i = pageIndex; i < pageLimitIndex; i++) {
            Item item = cachedItems.get(i);
            if (item != null) {
                pageList.add(item);
            }
        }
        return pageList;
    }

    public static void createCachedItems(Query query) {
        //globalCachedItems.

//        items = new ArrayList<>();
//        cachedItems = new ConcurrentHashMap<>();
//        initialQuery = query;
//
//        for (int i = 1; i < 4; i++) {
//            query.setPage(i);
//            String url = RequestService.getSignedUrl(query);
//            parseUrl(url);
//        }
//
//        int itemsSize = items.size();
//        for (int i = 0; i < itemsSize; i++) {
//            cachedItems.put(i + 1, items.get(i));
//        }
    }
//
//    public static void createCachedItems() {
//        for (int i = 4; i < 11; i++) {
//            initialQuery.setPage(i);
//            String url = RequestService.getSignedUrl(initialQuery);
//            parseUrl(url);
//        }
//
//        int itemsSize = items.size();
//        for (int i = 0; i < itemsSize; i++) {
//            cachedItems.put(i + 1, items.get(i));
//        }
//
//    }


    private static void parseUrl(String url) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            QueryHandler queryHandler = new QueryHandler();
            saxParser.parse(url, queryHandler);
            Thread.sleep(1000); //avoiding error 503 (one request per second)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getPagesAmount() {
        return cachedItems.size() / 13 + 1;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static ConcurrentHashMap<Integer, Item> getCachedItems() {
        return cachedItems;
    }
}
