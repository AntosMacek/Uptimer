package ee.uptime.demo.controller;

import ee.uptime.demo.JavaCodeSnippet;
import ee.uptime.demo.QueryHandler;
import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    public static final ArrayList<Item> items = new ArrayList<>();
    private static String signedUrl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Map<String, Object> model) {

        Query queryForm = new Query();
        model.put("queryForm", queryForm);
        model.put("categoryList", queryForm.getCategoryList());

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String showResult(@ModelAttribute("queryForm") Query query, Map<String, Object> model) {

        model.put("queryForm", query);
        model.put("categoryList", query.getCategoryList());

        signedUrl = JavaCodeSnippet.getSignedUrl(query);
        parseUrl(signedUrl);

        model.put("itemList", items);

        System.out.println(items);

        return "index";

    }

//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView index(Map<String, Object> model) {
//        Query queryForm = new Query();
//        model.put("queryForm", queryForm);
//        model.put("categoryList", queryForm.getCategoryList());
//
//        return new ModelAndView("index", model);
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ModelAndView sendRequest(@ModelAttribute("queryForm") Query query, ModelMap model) {
//        signedUrl = JavaCodeSnippet.getSignedUrl(query);
//        parseUrl(signedUrl);
//
//        System.out.println(items);
//
//        model.addAttribute("items", items);
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

}
