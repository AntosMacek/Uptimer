package ee.uptime.demo.service;

import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class QueryService {

    @Autowired
    private CachingService cachingService;

    @Autowired
    private ConcurrentRequestService concurrentRequestService;

//    private List<String> urlListToParse;
//
//    private ArrayList<Item> itemList;

    public QueryService() {

    }

    public List<Item> search(Query query) throws ExecutionException, InterruptedException {//returns first 13 and stores others in cache
        List<Item> items = cachingService.get(query);
        if (items != null) {
            return items;
        }
        List<Future<List<Item>>> futs = concurrentRequestService.requestItems(query);

        int itemCount = 0;
        List<Item> res = new ArrayList<>();
        for (Future<List<Item>> fut : futs) {
            for (Item item : fut.get()) {
                if (itemCount > 13) {
                    break;
                }
                res.add(item);
            }
        }

        return res;
    }


//    public ArrayList<Item> getItemList() {
//        return itemList;
//    }
}
