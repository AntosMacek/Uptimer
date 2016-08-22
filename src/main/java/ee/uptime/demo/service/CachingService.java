package ee.uptime.demo.service;

import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CachingService {

    private ConcurrentHashMap<List<String>, List<Item>> globalCachedItems = new ConcurrentHashMap<>();

    public List<Item> get(Query query) {
        return globalCachedItems.get(getKey(query));
    }

    public void insert(Query query, List<Item> items) {
        globalCachedItems.put(getKey(query), items);
    }

    private static List<String> getKey(Query query) {
        return Arrays.asList(query.getQuery(), query.getCategory());
    }

}
