package ee.uptime.demo.controller;

import ee.uptime.demo.handler.PageHandler;
import ee.uptime.demo.model.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Map;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

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

        PageHandler.createCachedItems(query);

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

}
