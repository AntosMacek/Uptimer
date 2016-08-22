package ee.uptime.demo.handler;

import ee.uptime.demo.model.Item;
import java.util.ArrayList;
import java.util.List;


public class PageHandler {

    public static ArrayList<Item> createPageInfo(int page, List<Item> itemList) {
        ArrayList<Item> pageList = new ArrayList<>();

        int pageIndex = (page - 1) * 13;
        int pageLimitIndex = pageIndex + 13;
        int itemSize = itemList.size();
        if (pageLimitIndex > itemSize) {
            pageLimitIndex = itemSize;
        }
        for (int i = pageIndex; i < pageLimitIndex; i++) {
            Item item = itemList.get(i);
            if (item != null) {
                pageList.add(item);
            }
        }
        return pageList;
    }

}
