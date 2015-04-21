package com.note4j.account.service.impl;

import com.note4j.account.model.ErrDTO;
import com.note4j.exception.ImageUploadException;
import com.note4j.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.note4j.account.dao.AccountDao;
import com.note4j.account.model.Account;
import com.note4j.account.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getUserInfoById(int id) {
        return accountDao.get(id);
    }

    @Override
    public ErrDTO register(Account account) {
        ErrDTO<String> errDTO = new ErrDTO<String>();
        int code = ErrorCode.REGISTER_SUCCESS;
        String message = "";
        String userName = account.getUser_name();
        String phone = account.getPhone();
        String address = account.getAddress();
        String password = account.getPassword();
        if (accountDao.get(userName) != null) {
            code = ErrorCode.USERNAME_EXISTS;
            message = "usename already exist.";
        } else if (password != null && password.length() != 0) {
            code = ErrorCode.PASSWD_NOT_NULL;
            message = "password can't be null.";
        } else {
            message = "register success!";
            accountDao.add(account);
        }
        errDTO = new ErrDTO<>(code, message);
        return errDTO;
    }

    @Override
    public ErrDTO<String> updateAccountInfo(Account account) {
        ErrDTO<String> errDTO;
        String modName = account.getUser_name();
        String name = accountDao.get(account.getId()).getUser_name();
        int code = -1;
        String message = "";

        if (modName != null || modName.length() != 0) {
            if (!modName.equals(name) && accountDao.get(modName) != null) {
                code = ErrorCode.USERNAME_EXISTS;
                message = "username already exist.";
            }
        }
        if (code != -1) {
            errDTO = new ErrDTO<String>(code, message);
        } else {
            code = accountDao.update(account);
            if (code == ErrorCode.UPDATE_SUCCESS) {
                message = "update success!";
            } else {
                message = "update failure!";
            }
            errDTO = new ErrDTO<String>(code, message);
        }
        return errDTO;
    }

    public int loginCheck(Account account) {
        // TODO Auto-generated method stub
        Account acc = accountDao.get(account.getUser_name());
        int index = -1;
        if (acc != null && acc.getPassword().equals(account.getPassword())) {
            index = acc.getId();
        }
        return index;
    }



}
