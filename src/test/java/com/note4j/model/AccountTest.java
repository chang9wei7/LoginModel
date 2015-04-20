package com.note4j.model;

import com.note4j.account.model.Account;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import javax.validation.*;
import java.util.Set;

/**
 * Created by changwei on 15/4/20.
 */
public class AccountTest {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void userNameIsNull() {
        Account accountTest = new Account(null, "shanxi", "1231111", "12311112");
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(accountTest);
        validator.validate(accountTest);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("username can't be null.",constraintViolations.iterator().next().getMessage());
    }

}
