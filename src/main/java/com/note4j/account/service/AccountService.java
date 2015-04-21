package com.note4j.account.service;

import com.note4j.account.model.Account;
import com.note4j.account.model.ErrDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {
    /**
     * 登陆检查，通过用户名（唯一）进行校验，返回对应用户的id，不存在返回-1
     *
     * @param account
     * @return
     */
    public int loginCheck(Account account);

    public Account getUserInfoById(int id);

    public ErrDTO register(Account account);

    public ErrDTO updateAccountInfo(Account account);

}
