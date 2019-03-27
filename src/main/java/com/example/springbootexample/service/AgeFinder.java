package com.example.springbootexample.service;

import com.example.springbootexample.model.MembersGroup;

import java.util.List;
import java.util.Set;

public interface AgeFinder {
    Set<String> find(List<MembersGroup> groups, int age);
}
