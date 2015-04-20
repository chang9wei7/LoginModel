package com.note4j.account.dao.impl;

import java.util.List;

import com.note4j.util.ErrorCode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.note4j.account.dao.AccountDao;
import com.note4j.account.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public int add(Account account) {
        // TODO Auto-generated method stub
        return (int) getCurrentSession().save(account);
    }

    public Account get(String name) {
        String hql = "from Account as accounts where accounts.user_name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setString("name", name);
        query.setMaxResults(1);
        List<Account> accList = query.list();
        if (accList != null && accList.size() != 0) {
            return accList.get(0);
        }
        return null;
    }

    public Account get(int id) {
        // TODO Auto-generated method stub
        Account account = (Account) getCurrentSession().get(Account.class, id);
        return account;
    }

    public int update(Account account) {
        // TODO Auto-generated method stub
        Account accToUpdate = get(account.getId());
        String name = account.getUser_name();
        String phone = account.getPhone();
        String address = account.getAddress();
        if(name != null&&name.length() !=0){
            accToUpdate.setUser_name(name.trim());
        }
        if(phone != null&& phone.length()!=0){
            accToUpdate.setPhone(phone);
        }
        if (address != null && address.length() != 0) {
            accToUpdate.setAddress(address);
        }
        try{
            getCurrentSession().update(accToUpdate);
            return ErrorCode.UPDATE_SUCCESS;
        }catch (HibernateException e){
            return ErrorCode.UPDATE_FAILURE;
        }
    }

    public Account del(int id) {
        // TODO Auto-generated method stub
        return null;
    }

}
