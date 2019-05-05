package com.example.demo;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

@Controller
public class Home_Controller {

    final static Logger logger=Logger.getLogger("Home_Controller");
     @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView Home() {
         PropertyConfigurator.configure("src/log4j.properties");
         logger.info("Calling Home");
         return new ModelAndView("home");
    }
}
