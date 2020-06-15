package com.group18.asdc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.group18.asdc.entities.User;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.CommonUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    EmailService emailService;

    @MockBean
    DataSource dataSource;

    @Mock
    CommonUtil commonUtil;

    @Mock
    User userObj;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHTMLCalls() throws Exception {
        mockMvc.perform(get("/coursepage")).andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(get("/adminpage")).andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));

        mockMvc.perform(get("/forgot-password")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));

    }

    @Test
    public void resetPasswordTest() throws Exception {

        doAnswer(invocation -> {
            String arg0 = invocation.getArgument(0);
            User arg1 = invocation.getArgument(1);

            arg1.setEmail("justin@dal.ca");
            arg1.setBannerId("B00838575");
            assertEquals("B00838575", arg0);
            return null;
        }).when(userService).loadUserWithBannerId(isA(String.class), isA(User.class));

        doAnswer(invocation -> {
            String arg0 = invocation.getArgument(0);
            String arg1 = invocation.getArgument(1);
            String arg2 = invocation.getArgument(1);

            assertEquals("justin@dal.ca", arg0);
            return null;
        }).when(emailService).sendSimpleMessage(isA(String.class), isA(String.class), isA(String.class));

        mockMvc.perform(get("/resetPassword").param("username", "B00838575")).andExpect(status().isOk())
                .andExpect(model().attribute("sentEmail", "justin@dal.ca"));

    }

    @Test
    public void resetPasswordUserNotAvaiableTest() throws Exception {

        doAnswer(invocation -> {
            String arg0 = invocation.getArgument(0);
            User arg1 = invocation.getArgument(1);
            assertEquals("B00838575", arg0);
            return null;
        }).when(userService).loadUserWithBannerId(isA(String.class), isA(User.class));

        mockMvc.perform(get("/resetPassword").param("username", "B00838575")).andExpect(status().isOk())
                .andExpect(model().attribute("BANNER_ID_NOT_EXIST", true));

    }

    @Test
    public void verifyResetPasswordTest() throws Exception {

        //
        when(userService.updatePassword(isA(User.class))).thenReturn(Boolean.TRUE);

        //
        mockMvc.perform(post("/resetPassword")
            .param("bannerId","B00838575")
            .param("generatedPassword","PASSWORD")
            .param("newPassword","NEWPASSWORD")
            .param("confirmNewPassword","NEWPASSWORD")
            .sessionAttr("RESET_PASSWORD", "PASSWORD")
            .with(csrf()))
            .andExpect(redirectedUrl("login-success"));                
               

    }

    @Test
    public void verifyResetPasswordFailTest() throws Exception {

        //
        when(userService.updatePassword(isA(User.class))).thenReturn(Boolean.TRUE);

        //
        mockMvc.perform(post("/resetPassword")
            .param("bannerId","B00838575")
            .param("generatedPassword","NEWPASSWORD")
            .param("newPassword","NEWPASSWORD")
            .param("confirmNewPassword","NEWPASSWORD")
            .sessionAttr("RESET_PASSWORD", "PASSWORD")
            .with(csrf()))
            .andExpect(model().attribute("genPasswordError", true));
               

    }

    @Test
    public void verifyResetPasswordmatchErrorTest() throws Exception {

        //
        when(userService.updatePassword(isA(User.class))).thenReturn(Boolean.TRUE);

        //
        mockMvc.perform(post("/resetPassword")
            .param("bannerId","B00838575")
            .param("generatedPassword","PASSWORD")
            .param("newPassword","NEWPASSWORD")
            .param("confirmNewPassword","PASSWORD")
            .sessionAttr("RESET_PASSWORD", "PASSWORD")
            .with(csrf()))
            .andExpect(model().attribute("confirmPasswordError", true));
               

    }

    @Test
    public void verifyResetPasswordupdateErrorTest() throws Exception {

        //
        when(userService.updatePassword(isA(User.class))).thenReturn(Boolean.FALSE);

        //
        mockMvc.perform(post("/resetPassword")
            .param("bannerId","B00838575")
            .param("generatedPassword","PASSWORD")
            .param("newPassword","NEWPASSWORD")
            .param("confirmNewPassword","NEWPASSWORD")
            .sessionAttr("RESET_PASSWORD", "PASSWORD")
            .with(csrf()))
            .andExpect(model().attribute("passwordResetError", true));
               

    }


}