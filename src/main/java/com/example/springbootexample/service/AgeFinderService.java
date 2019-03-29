package com.example.springbootexample.service;

import com.example.springbootexample.model.MembersGroup;

import java.util.List;
import java.util.Set;

/**
 * Сервис для поиска набора групп по возрастному признаку
 */
public interface AgeFinderService {

    /**
     * Поиск групп по возрастному признаку участников
     * @param groups группы, среди которых ищем
     * @param age параметр возраста
     * @return набор групп, удовлетворяющих условиям поиска
     */
    Set<String> find(List<MembersGroup> groups, int age);
}
