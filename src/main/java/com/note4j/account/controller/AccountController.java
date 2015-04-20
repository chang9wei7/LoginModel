package com.note4j.account.controller;

import com.note4j.account.model.Account;
import com.note4j.account.model.ErrDTO;
import com.note4j.account.service.AccountService;
import com.note4j.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account.do")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(params = "method=edit", method = RequestMethod.GET)
    public ModelAndView editPage(int id) {
        ModelAndView modelAndView = new ModelAndView("editAccount");
        Account account = accountService.getUserInfoById(id);
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @RequestMapping(params = "method=update", method = RequestMethod.POST)
    public ModelAndView updateAccountInfo(@ModelAttribute Account accountInfo, int id) {
        ModelAndView modelAndView = new ModelAndView("accountInfo");

        ErrDTO<String> result = accountService.updateAccountInfo(accountInfo);
        String message =  result.getErrcode()+" : "+result.getObj();
        modelAndView.addObject("message", message);
        Account account = accountService.getUserInfoById(id);
        modelAndView.addObject("account", account);
        return modelAndView;
    }


    @RequestMapping(params = "method=register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        String message = "Please Input your register information.";
        Account account = new Account();
        modelAndView.addObject("account", account);
        modelAndView.addObject("message", message);
        return modelAndView;
    }


    @RequestMapping(params = "method=register", method = RequestMethod.POST)
    public ModelAndView addAccount(@ModelAttribute Account accountInfo) {
        ModelAndView modelAndView;
        ErrDTO<String> result = accountService.register(accountInfo);
        String message = null;
        if (result.getErrcode() == ErrorCode.REGISTER_SUCCESS) {
            modelAndView = new ModelAndView("accountInfo");
            message = result.getObj();
            modelAndView.addObject("message", message);
            modelAndView.addObject("account", accountInfo);
        } else {
            message = "Error: " + result.getErrcode();
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("message", message);
            modelAndView.addObject("account", accountInfo);
        }
        return modelAndView;
    }

    @RequestMapping(params = "method=login", method = RequestMethod.POST)
    public ModelAndView validateAccount(@ModelAttribute Account accountInfo) {
        ModelAndView modelAndView;
        String message;
        int index = accountService.loginCheck(accountInfo);
        if (index != -1) {
            modelAndView = new ModelAndView("accountInfo");
            message = "登陆成功！";
            modelAndView.addObject("message", message);
            Account account = accountService.getUserInfoById(index);
            modelAndView.addObject("account", account);
        } else {
            message = "用户名或密码错误！";
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("message", message);
        }
        return modelAndView;
    }
}
