package com.directory.service;

import com.directory.domain.Directory;
import com.directory.mapper.DirectoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectoryService {

    private final DirectoryMapper mapper;

    public Integer insert(Directory directory) {
        log.info("creating directory: {}", directory);
        Integer result = mapper.insert(directory);
        System.out.println(result);
        return result;
    }

    public Directory findById(Long id) {
        log.info("getting directory where id = {}", id);
        return mapper.findById(id);
    }

    public List<Directory> findAll() {
        log.info("getting all directories.");
        return mapper.findAll();
    }

    public void edit(Directory newDirectory) {
        log.info("update directory where id = {}", newDirectory.getId());
        mapper.edit(newDirectory);
    }

    public void delete(Long id) {
        log.info("delete directory where id = {}", id);
        mapper.delete(id);
    }
}
