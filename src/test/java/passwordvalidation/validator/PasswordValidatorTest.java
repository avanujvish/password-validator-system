package passwordvalidation.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passwordvalidation.rule.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Main Password validator component that orchestrates
 * multiple validation rules Test.
 * @author anuj
 * @version 1.0
 * @since 2025-09-09
 */
public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp(){
        passwordValidator =  new PasswordValidator();
    }

    @Test
    void testPasswordValidateNull(){
        String password=null;
        assertFalse(passwordValidator.validatePassword(password));
    }

    @Test
    void testPasswordValidateLowerCase(){
        String password="aabbcc";
        assertFalse(passwordValidator.validatePassword(password));
    }

    @Test
    void testPasswordValidateUpperCase(){
        String password="AABBCC";
        assertFalse(passwordValidator.validatePassword(password));
    }

    @Test
    void testPasswordValidateNumber(){
        String password="12345";
        assertFalse(passwordValidator.validatePassword(password));
    }

    @Test
    void testPasswordValidateNumberAndLowerCase(){
        String password="12345aaa";
        assertTrue(passwordValidator.validatePassword(password));
    }

    @Test
    void testPasswordValidate(){
        String password="Password123";
        assertTrue(passwordValidator.validatePassword(password));
    }
}
