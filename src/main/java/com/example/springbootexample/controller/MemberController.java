package com.example.springbootexample.controller;

import com.example.springbootexample.exceptions.DuplicateGroupException;
import com.example.springbootexample.exceptions.NotFoundException;
import com.example.springbootexample.model.Member;
import com.example.springbootexample.model.MembersGroup;
import com.example.springbootexample.service.AgeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("members")
public class MemberController {

    @Autowired
    AgeFinder ageFinder;

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
    public MembersGroup getGroupByName(@PathVariable String groupName) {
        return getMembersGroup(groupName);
    }

    private MembersGroup getMembersGroup(@PathVariable String groupName) {
        return membersGroups.stream()
                .filter(m -> m.getGroupName().equals(groupName))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("youngerThan/{age}")
    private Set<String> youngerThan(@PathVariable int age) {
        return ageFinder.find(membersGroups, age);
    }

    @PostMapping
    public MembersGroup createGroup(@RequestBody MembersGroup newMembersGroup) {
        if (membersGroups.stream()
                .anyMatch(group -> group.getGroupName().equals(newMembersGroup.getGroupName()))) {
            throw new DuplicateGroupException();
        } else {
            membersGroups.add(newMembersGroup);
            return newMembersGroup;
        }
    }

    @PutMapping("{groupName}")
    public MembersGroup addMember(@PathVariable String groupName, @RequestBody Member member) {
        MembersGroup membersGroup = getMembersGroup(groupName);
        membersGroup.getMembers().add(member);
        return membersGroup;
    }

    @DeleteMapping("{groupName}")
    public void deleteGroup(@PathVariable String groupName) {
        MembersGroup removeMembersGroup = getMembersGroup(groupName);
        membersGroups.remove(removeMembersGroup);
    }
}
