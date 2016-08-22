package ee.uptime.demo.service;

import ee.uptime.demo.handler.QueryHandler;
import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@Service
public class ConcurrentRequestService {

    @Autowired
    private CachingService cachingService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public List<Future<List<Item>>> requestItems(Query query) {

        List<Future<List<Item>>> futRet = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int pageCounter = i + 1;
            Future<List<Item>> fut = executorService.submit(() -> {
                String url = RequestService.getSignedUrl(query, pageCounter);

                List<Item> resultHolder = new ArrayList<Item>();
                parseUrl(url, item -> resultHolder.add(item));
                return resultHolder;
            });
            if (i < 3) {
                futRet.add(fut);
            }
        }
        return futRet;
    }

    private void parseUrl(String url, Consumer<Item> onItem) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            QueryHandler queryHandler = new QueryHandler(onItem);
//            Thread.sleep(1000); //avoiding error 503 (one request per second)
            saxParser.parse(url, queryHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }

}
