package com.note4j.account.controller;

import com.note4j.account.model.Account;
import com.note4j.account.model.ErrDTO;
import com.note4j.account.service.AccountService;
import com.note4j.exception.ImageUploadException;
import com.note4j.util.ErrorCode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

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
    public ModelAndView addAccount(@ModelAttribute @Valid Account accountInfo, BindingResult bindingResult) {
        ModelAndView modelAndView;
        String message;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("account", accountInfo);
            return modelAndView;
        }
        ErrDTO<String> result = accountService.register(accountInfo);
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

    @RequestMapping(params = "method=update", method = RequestMethod.POST)
    public ModelAndView updateAccountInfo(@ModelAttribute @Valid Account accountInfo, BindingResult bindingResult, int id, @RequestParam(value = "image", required = false) MultipartFile image) {
        ModelAndView modelAndView = new ModelAndView("editAccount");

        modelAndView.addObject("account", accountInfo);
        String message;
        if (bindingResult.hasErrors()) {
//            modelAndView = new ModelAndView("editAccount");
//            modelAndView.addObject("account", accountInfo);
            return modelAndView;
        }
        try {
            if (!image.isEmpty()) {
                validateImage(image);
                saveImage(id + ".jpg", image);
            }
        } catch (ImageUploadException e) {
            bindingResult.reject("image",e.getMessage());
//            bindingResult.addError(new ObjectError("image",e.getMessage()));
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }
        ErrDTO<String> result = accountService.updateAccountInfo(accountInfo);
        if (result.getErrcode() == ErrorCode.UPDATE_SUCCESS) {
            modelAndView = new ModelAndView("accountInfo");
            message = result.getErrcode() + " : " + result.getObj();
            Account account = accountService.getUserInfoById(id);
            modelAndView.addObject("message", message);
            modelAndView.addObject("account", account);
        } else {
            message = result.getErrcode() + ":" + result.getObj();
//            modelAndView = new ModelAndView("editAccount");
            modelAndView.addObject("message", message);
//            modelAndView.addObject("account", accountInfo);
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

    private void validateImage(MultipartFile image) {
        System.out.println(image.getContentType());
        if (!image.getContentType().equals("image/jpeg")) {
            throw new ImageUploadException("Only JPEG images accepted.");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws ImageUploadException {
        try {
            File file = new File("/Users/changwei/Pictures/" + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new ImageUploadException("Unable to save image.", e);
        }
    }
}