package com.gfdz.test;

import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 所以spring测试模块提供的请求测试功能，测试CRUD请求
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class MvcTest {
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext context;
    @Before
    public void initMokcMvc(){
        mockMvc=  MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public  void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNum", "5")).andReturn();
        MockHttpServletRequest request = result.getRequest();
        PageInfo pi=(PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码：" + pi.getPageNum());
        System.out.println("总页码数："+pi.getPages());
        System.out.println("总记录数："+pi.getTotal());
        int[] navigatepageNums = pi.getNavigatepageNums();
        for (int navigatepageNum : navigatepageNums) {
            System.out.println(" "+navigatepageNum);
        }
    }
}
