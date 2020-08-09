package com.cloud.erp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.text.IsEmptyString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cloud.erp.pojo.vo.Result;
import com.cloud.erp.pojo.vo.UserInfo;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@ActiveProfiles("dev")
public class LoginControllerTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    private LoginController loginController;
    private MockMvc mockMvc;

    @Before() // 这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
    }

    @Test(expected = Exception.class)
    public void loginEmptyTest() throws Exception {
        loginController.login(null);

    }

    @Test(expected = Exception.class)
    public void loginForUserNameIsNullTest() throws Exception {
        UserInfo userInfo = new UserInfo();
        loginController.login(userInfo);
    }

    @Test
    public void loginForUserNameIsNotFoundTest() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("manger");
        Result<String> result = loginController.login(userInfo);
        collector.checkThat(userInfo.toString(), result, IsNull.notNullValue());
        collector.checkThat(userInfo.toString(), "N", IsEqual.equalTo(result.getErrorFlag()));

    }

    @Test
    public void loginSuccessTest() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Manager");
        Result<String> result = loginController.login(userInfo);
        collector.checkThat(userInfo.toString(), result, IsNull.notNullValue());
        collector.checkThat(userInfo.toString(), "Y", IsEqual.equalTo(result.getErrorFlag()));

        collector.checkThat(userInfo.toString(), result.getData(), IsNot.not(IsEmptyString.emptyOrNullString()));

    }

    @Test
    public void getCurrentUserInfoForTokenIsNullTest() throws Exception {
        String url = "/login/getCurrentUserInfo";

        String resp = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn().getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

        Result<UserInfo> result = JSON.parseObject(resp, new TypeReference<>() {});

        collector.checkThat(result, IsNull.notNullValue());
        collector.checkThat("N", IsEqual.equalTo(result.getErrorFlag()));

    }

}
