package ru.job4j.crudservlet.controller;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpSession;
import ru.job4j.crudservlet.controller.logic.ValidateService;
import ru.job4j.crudservlet.controller.logic.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import org.powermock.api.mockito.PowerMockito;
import ru.job4j.crudservlet.controller.logic.Validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletsTests {


    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        Validator validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Ivan Ivanov");
        new UserAddServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Ivan Ivanov"));
    }

    @Test
    public void whenUpdateUserThenUpdateIt() throws ServletException, IOException {
        Validator validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Ivan Ivanov");
        new UserAddServlet().doPost(req, resp);

        when(req.getParameter("name")).thenReturn("Ivan1 Ivanov1");
        when(req.getParameter("id")).thenReturn("0");
        new UserUpdateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Ivan1 Ivanov1"));
    }

    @Test
    public void whenDeleteUserThenFindUserMethodReturnNull() throws ServletException, IOException {
        Validator validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Ivan Ivanov");
        new UserAddServlet().doPost(req, resp);

        when(req.getParameter("id")).thenReturn("0");
        new UserDeleteServlet().doPost(req, resp);
        assertThat(validate.findById(0), is(IsNull.nullValue()));
    }

    @Test
    public void whenLoginUserThenSessionContainsLogin() throws ServletException, IOException {
        Validator validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("ivan");
        when(req.getParameter("password")).thenReturn("pass");
        when(req.getParameter("role")).thenReturn("administrator");
        when(req.getSession()).thenReturn(new MockHttpSession());
        new UserAddServlet().doPost(req, resp);

        new LogInServlet().doPost(req, resp);
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        assertThat(login, is("ivan"));
    }
}