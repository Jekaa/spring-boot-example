package com.example.springbootexample.controller;

import com.example.springbootexample.exceptions.NotFoundException;
import com.example.springbootexample.model.Member;
import com.example.springbootexample.model.MembersGroup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    private List<MembersGroup> membersGroups = new ArrayList<MembersGroup>() {{
        add(new MembersGroup("group_1", new ArrayList<Member>() {{
            add(new Member("Иван", 30));
            add(new Member("Пётр", 55));
            add(new Member("Екатерина", 20));
            add(new Member("Сергей", 70));
        }}));
        add(new MembersGroup("group_2", new ArrayList<Member>() {{
            add(new Member("Виктор", 33));
            add(new Member("Илья", 50));
            add(new Member("Елизавета", 41));
        }}));
        add(new MembersGroup("group_3", new ArrayList<Member>() {{
            add(new Member("Афанасий", 29));
            add(new Member("Мария", 50));
            add(new Member("Елена", 25));
            add(new Member("Иван", 30));
            add(new Member("Николай", 51));
            add(new Member("Екатерина", 29));
        }}));
        add(new MembersGroup("group_4", new ArrayList<Member>() {{
            add(new Member("Денис", 99));
            add(new Member("Кирилл", 81));
        }}));
    }};

    @GetMapping
    public List<MembersGroup> list() {
        return membersGroups;
    }

    @GetMapping("{groupName}")
    public MembersGroup getOne(@PathVariable String groupName) {
        return getMembersGroup(groupName);
    }

    private MembersGroup getMembersGroup(@PathVariable String groupName) {
        return membersGroups.stream()
                .filter(m -> m.getGroupName().equals(groupName))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
