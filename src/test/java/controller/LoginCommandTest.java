package controller;

import controller.commands.authorization.LoginCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.constants.UrlHolder;
import utils.pickers.request.LoginDataRequestPicker;
import validators.UserLoginValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public abstract class LoginCommandTest {
    @Mock
    LoginDataRequestPicker loginDataRequestPicker;
    @Mock
    UserLoginValidator userLoginValidator;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    Errors errors;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenNullUserRedirectToLogin() {
        LoginCommand loginCommand = new LoginCommand(loginDataRequestPicker,userLoginValidator);
        when(loginDataRequestPicker.pick(request)).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(userLoginValidator.validate(null,errors)).thenReturn(false);
        assertEquals(loginCommand.execute(request,response),UrlHolder.LOGIN);
    }
}