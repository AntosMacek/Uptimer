package ee.uptime.demo.controller;

import ee.uptime.demo.handler.PageHandler;
import ee.uptime.demo.service.RequestService;
import ee.uptime.demo.handler.QueryHandler;
import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

//    public static ArrayList<Item> items;
//    public static ConcurrentHashMap<Integer, Item> cachedItems;
//    private static int pagesAmount;
    private static String signedUrl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showIndex(Map<String, Object> model) {

        Query queryForm = new Query();
        model.put("queryForm", queryForm);
        model.put("categoryList", queryForm.getCategoryList());

        return new ModelAndView("index", model);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView sendRequest(@ModelAttribute("queryForm") Query query, ModelMap model) {

        model.addAttribute("queryForm", query);
        model.addAttribute("categoryList", query.getCategoryList());

        PageHandler.cacheItems(query);

        if (PageHandler.getCachedItems().size() == 0) {
            return new ModelAndView("index", model);
        } else {
            model.addAttribute("itemsInfo", PageHandler.createPageInfo(1));
            return new ModelAndView("redirect:/1");
        }
    }

    @RequestMapping(value = "/{page}")
    public ModelAndView showResult(@PathVariable int page, ModelMap model) {

        Query queryForm = new Query();
        model.put("queryForm", queryForm);
        model.put("categoryList", queryForm.getCategoryList());



        model.addAttribute("page", page);
        model.addAttribute("itemsInfo", PageHandler.createPageInfo(page));
        model.addAttribute("pagesAmount", PageHandler.getPagesAmount());

        return new ModelAndView("index", model);
    }

//    private ArrayList<Item> createPageInfo(int page) {
//        ArrayList<Item> pageList = new ArrayList<>();
//
//        int pageIndex = 1 + (page - 1) * 13;
//        int pageLimitIndex = pageIndex + 13;
//        if (pageLimitIndex > 101) {
//            pageLimitIndex = 101;
//        }
//        for (int i = pageIndex; i < pageLimitIndex; i++) {
//            Item item = cachedItems.get(i);
//            if (item != null) {
//                pageList.add(item);
//            }
//        }
//        return pageList;
//    }
//
//    private void parseUrl(String url) {
//        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser saxParser = factory.newSAXParser();
//            QueryHandler queryHandler = new QueryHandler();
//            saxParser.parse(url, queryHandler);
//            Thread.sleep(1000); //avoiding error 503 (one request per second)
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void cacheItems(Query query) {
//        items = new ArrayList<>();
//        cachedItems =  new ConcurrentHashMap<>();
//        for (int i = 1; i < 11; i++) {
//            String url = RequestService.getSignedUrl(query);
//            parseUrl(url);
//        }
//        int itemsSize = items.size();
//        for (int i = 0; i < itemsSize; i++) {
//            cachedItems.put(i+1, items.get(i));
//        }
//        pagesAmount = cachedItems.size() / 13 + 1;
//    }

}
