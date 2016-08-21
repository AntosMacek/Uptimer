package ee.uptime.demo.controller;

import ee.uptime.demo.handler.PageHandler;
import ee.uptime.demo.model.Item;
import ee.uptime.demo.model.Query;
import ee.uptime.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Autowired
    private QueryService cachingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showIndex(Map<String, Object> model) {


        Query queryForm = new Query("","", 0);
        model.put("queryForm", queryForm);
        model.put("categoryList", queryForm.getCategoryList());

        return new ModelAndView("index", model);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView handleSearchRequest(@ModelAttribute("queryForm") Query query, ModelMap model) throws ExecutionException, InterruptedException {

        model.addAttribute("queryForm", query);
        model.addAttribute("categoryList", query.getCategoryList());

        List<Item> items = cachingService.search(query);
        PageHandler.createCachedItems(query);

        model.addAttribute("itemsInfo", items);
        return new ModelAndView("redirect:/1");
    }

    @RequestMapping(value = "/{page}")
    public ModelAndView showResult(@PathVariable int page, ModelMap model) {

        Query queryForm = new Query("","", page);
        model.put("queryForm", queryForm);
        model.put("categoryList", queryForm.getCategoryList());

        model.addAttribute("page", page);
        model.addAttribute("itemsInfo", PageHandler.createPageInfo(page));
        model.addAttribute("pagesAmount", PageHandler.getPagesAmount());

        return new ModelAndView("index", model);
    }

}
