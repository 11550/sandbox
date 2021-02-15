package com.dictionary.service;

import com.dictionary.domain.Dictionary;
import com.dictionary.mapper.DictionaryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictionaryService {

    private final DictionaryMapper mapper;

    public Integer insert(Dictionary dictionary) {
        log.info("creating dictionary: {}", dictionary);
        return mapper.insert(dictionary);
    }

    public Dictionary findById(Long id) {
        log.info("getting dictionary where id = {}", id);
        return mapper.findById(id);
    }

    public List<Dictionary> findAll() {
        log.info("getting all dictionaries.");
        return mapper.findAll();
    }

    public void edit(Dictionary newDictionary) {
        log.info("update dictionary where id = {}", newDictionary.getId());
        mapper.edit(newDictionary);
    }

    public void delete(Long id) {
        log.info("delete dictionary where id = {}", id);
        mapper.delete(id);
    }
}
