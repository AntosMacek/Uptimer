package ee.uptime.demo.service;

import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CachingService {

    private ConcurrentHashMap<Query, List<Item>> globalCachedItems = new ConcurrentHashMap<>();

    public List<Item> get(Query query){ return globalCachedItems.get(query); }

    public void  insert(Query query , List<Item> items ) {
        globalCachedItems.put(query, items);
    }

}
