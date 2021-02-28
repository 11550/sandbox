/**
 * Copyright 2015-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.exception.Responses;
import com.directory.service.DirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("directory")
@RestController
@RequiredArgsConstructor
public class DirectoryController {

    private final DirectoryService service;

    @PostMapping()
    String create(@RequestBody Directory Directory) {
        try {
            service.insert(Directory);
            return Responses.SUCCESS.getMessage();
        } catch (Exception e) {
            return Responses.INCORRECT_INPUT.getMessage();
        }
    }

    @GetMapping("{id}")
    ResponseEntity<Object> getOne(@PathVariable Long id) {
        Directory result = service.findById(id);
        if (!Objects.isNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(Responses.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    List<Directory> findAll() {
        return service.findAll();
    }

    @PutMapping()
    ResponseEntity<String> edit(@RequestBody Directory newDirectory) {
        try {
            service.edit(newDirectory);
            return new ResponseEntity<>(Responses.SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Responses.INCORRECT_INPUT.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(Responses.SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Responses.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
