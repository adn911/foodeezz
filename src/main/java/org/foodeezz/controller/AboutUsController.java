package org.foodeezz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@Controller
@RequestMapping("/about")
public class AboutUsController {

    private static final Logger log = LoggerFactory.getLogger(AboutUsController.class);

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String showAboutUs(Model model) {

        return "aboutUs";
    }

}
