package com.example.springbootexample.service;

import com.example.springbootexample.model.Member;
import com.example.springbootexample.model.MembersGroup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class AgeFinderImplTest {

    AgeFinderImpl ageFinder = new AgeFinderImpl();
    List<MembersGroup> membersGroups = new ArrayList<>();

    @Before
    public void init() {
        List groups = Arrays.asList(
                new MembersGroup(
                        "groupName",
                        Arrays.asList(
                                new Member("memberName", 77),
                                new Member("memberName", 25),
                                new Member("memberName", 51)
                        )
                ),
                new MembersGroup(
                        "groupName",
                        Arrays.asList(
                                new Member("memberName", 12),
                                new Member("memberName", 25),
                                new Member("memberName", 49)
                        )
                )
        );
        membersGroups.addAll(groups);
    }

    @Test
    public void findOne() {
        Set<String> resultSet = ageFinder.find(membersGroups, 50);
        Assert.assertEquals(1, resultSet.size());
        Assert.assertEquals("groupName", resultSet.stream().findFirst().get());
    }

    @Test
    public void notFound() {
        Set<String> resultSet = ageFinder.find(membersGroups, 77);
        Assert.assertEquals(0, resultSet.size());
    }
}
