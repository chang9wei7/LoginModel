package com.note4j.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.note4j.account.model.Account;

@Controller
public class LinkController {

    @RequestMapping("/")
    public ModelAndView fisrtPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        Account account = new Account();
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @RequestMapping("/index")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        Account account = new Account();
        modelAndView.addObject("account", account);
        return modelAndView;
    }



}
