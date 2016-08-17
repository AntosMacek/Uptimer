package ee.uptime.demo.controller;

import ee.uptime.demo.RequestService;
import ee.uptime.demo.QueryHandler;
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

    public static ArrayList<Item> items;
    public static ConcurrentHashMap<Integer, Item> cachedItems;
    private static int pagesAmount;
    private static String signedUrl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Map<String, Object> model) {

        Query queryForm = new Query();
        model.put("queryForm", queryForm);
        model.put("categoryList", queryForm.getCategoryList());

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView sendRequest(@ModelAttribute("queryForm") Query query, ModelMap model) {

        model.addAttribute("queryForm", query);
        model.addAttribute("categoryList", query.getCategoryList());

        cacheItems(query);
        
        if (cachedItems.size() == 0) {
            model.addAttribute("nothingFound", "There was no results");
            return new ModelAndView("index", model);
        } else {
            model.addAttribute("itemsInfo", createPageInfo(1));
            return new ModelAndView("redirect:/result/1");
        }
    }

    @RequestMapping(value = "/result/{page}")
    public ModelAndView showResult(@PathVariable int page, ModelMap model) {

        model.addAttribute("page", page);
        model.addAttribute("itemsInfo", createPageInfo(page));
        model.addAttribute("pagesAmount", pagesAmount);

        return new ModelAndView("result", model);
    }

    private ArrayList<Item> createPageInfo(int page) {
        ArrayList<Item> pageList = new ArrayList<>();

        int pageIndex = 1 + (page - 1) * 13;
        int pageLimitIndex = pageIndex + 13;
        if (pageLimitIndex > 101) {
            pageLimitIndex = 101;
        }
        for (int i = pageIndex; i < pageLimitIndex; i++) {
            pageList.add(cachedItems.get(i));
        }
        return pageList;
    }

//    @RequestMapping(value="/{pageId}", method = RequestMethod.GET)
//    public ModelAndView edit(@PathVariable int pageId, @ModelAttribute("queryForm") Query query, ModelMap model) {
//
//        model.addAttribute("queryForm", query);
//        model.addAttribute("categoryList", query.getCategoryList());
//
//        cacheItems(query);
//
//        model.addAttribute("itemMap", cachedItems);
//        int total = cachedItems.size() / 13 + 1;
//        if(pageId == 1) {
//
//        } else {
//            pageId = (pageId - 1) * total + 1;
//        }
//
//        return new ModelAndView("index", model);
//    }

    private void parseUrl(String url) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            QueryHandler queryHandler = new QueryHandler();
            saxParser.parse(url, queryHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cacheItems(Query query) {
        items = new ArrayList<>();
        cachedItems =  new ConcurrentHashMap<>();
        for (int i = 1; i < 11; i++) {
            String url = RequestService.getSignedUrl(query);
            parseUrl(url);
        }
        int itemsSize = items.size();
        for (int i = 0; i < itemsSize; i++) {
            cachedItems.put(i+1, items.get(i));
        }
        pagesAmount = cachedItems.size() / 13 + 1;
    }

}
