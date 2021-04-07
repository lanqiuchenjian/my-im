package com.myim.cluster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cj
 */
@Controller
public class WebRtcController {
    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("index");
        return model;
    }
}
