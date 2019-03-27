package com.example.springbootexample.service;

import com.example.springbootexample.model.Member;
import com.example.springbootexample.model.MembersGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Finder {
    public static interface OldMemberFinder {
        public Set<String> findOldMembers(List<MembersGroup> listMembers);
    }

    public static class FinderOldMan implements OldMemberFinder {
        public Set<String> findOldMembers(List<MembersGroup> groups) {
            return groups
                    .stream()
                    .filter(
                            group -> group.getMembers()
                                    .stream()
                                    .filter(member -> member.getAge() > 50)
                                    .findFirst()
                                    .isPresent())
                    .map(group -> group.getGroupName())
                    .collect(Collectors.toSet());
        }
    }
}
