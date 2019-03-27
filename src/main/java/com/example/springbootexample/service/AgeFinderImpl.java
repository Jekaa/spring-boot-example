package com.example.springbootexample.service;

import com.example.springbootexample.model.MembersGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgeFinderImpl implements AgeFinder {

    @Override
    public Set<String> find(List<MembersGroup> groups, int age) {
        return groups
                .stream()
                .filter(
                        group -> group.getMembers()
                                .stream()
                                .anyMatch(member -> member.getAge() > age))
                .map(MembersGroup::getGroupName)
                .collect(Collectors.toSet());
    }
}
