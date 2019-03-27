package com.example.springbootexample.service;

import com.example.springbootexample.model.Member;
import com.example.springbootexample.model.MembersGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Finder {
    public static interface OldMemberFinder {
        public Set<String> findOldMembers(List<MembersGroup> listMembers);
    }

    public static class FinderOldMan implements OldMemberFinder {
        public Set<String> findOldMembers(List<MembersGroup> groups) {
            Set<String> groupsNames = new HashSet<>();
            for (MembersGroup membersGroup : groups) {
                for (Member member : membersGroup.getMembers()) {
                    if (member.getAge() > 50) {
                        String name = member.getName();
                        groupsNames.add(name);
                    }
                }
            }
            return groupsNames;
        }
    }
}