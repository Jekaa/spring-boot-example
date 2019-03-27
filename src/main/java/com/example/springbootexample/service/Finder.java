package com.example.springbootexample.service;

import com.example.springbootexample.model.MembersGroup;

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
                                    .anyMatch(member -> member.getAge() > 50))
                    .map(MembersGroup::getGroupName)
                    .collect(Collectors.toSet());
        }
    }
}
