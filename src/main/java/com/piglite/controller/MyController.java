package com.piglite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/demo")
public class MyController {
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/getword")
    public  String getword(Model model){
        model.addAttribute("greeting", "Hello Spring MVC, good!");
        return "getword";
    }

    @RequestMapping("/hello")
    public  String gotoOther(Model model)
    {
        return "redirect:/demo/welcome";
    }

    @RequestMapping("/user")
    public String userinfo(Model model, @RequestParam(value = "name",defaultValue = "Geust")String name){
        model.addAttribute("name",name);
        if("admin".equals(name)) {
            model.addAttribute("email", "admin@zjw.cn");
        }else
        {
            model.addAttribute("email","No set");
        }
        return "userInfo";
    }

    @RequestMapping("/web/fe/{sitePrefix}/{language}/document/{id}/{naturalText}")
    public String documentView(Model model,
                               @PathVariable(value = "sitePrefix") String sitePrefix,
                               @PathVariable(value = "language") String language,
                               @PathVariable(value = "id") Long id,
                               @PathVariable(value = "naturalText") String naturalText) {

        model.addAttribute("sitePrefix", sitePrefix);
        model.addAttribute("language", language);
        model.addAttribute("id", id);
        model.addAttribute("naturalText", naturalText);

        String documentName = "Java tutorial for Beginners";
        if(id == 8108) {
            documentName = "Spring MVC for Beginners";
        }

        model.addAttribute("documentName", documentName);

        return "documentView";
    }

    // Simple example, method returns String.
    @RequestMapping(value = "/saveResult")
    @ResponseBody
    public String authorInfo(Model model) {
        return "saved,will return this message.";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        return "welcome";
    }

    @RequestMapping(value = "/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "welcome";
    }
}
