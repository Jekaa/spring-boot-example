package com.example.springbootexample;

import com.example.springbootexample.controller.MemberController;
import com.example.springbootexample.model.MembersGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootExampleApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberController memberController;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(memberController).isNotNull();
    }

    @Test
    public void callRoot() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/members"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Gson gson = new GsonBuilder().serializeNulls().create();
        List<MembersGroup> membersGroups = new ArrayList<>();
        membersGroups.addAll(memberController.getMembersGroups());
        Assert.assertEquals(gson.toJson(membersGroups), content);
    }

    @Test
    public void callYoungerThan50() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/members/youngerThan/50"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("[\"group_4\",\"group_1\",\"group_3\"]", content);
    }
}
