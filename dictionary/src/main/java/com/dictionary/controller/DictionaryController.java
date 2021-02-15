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
package com.dictionary.controller;

import com.dictionary.domain.Dictionary;
import com.dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dictionary")
@RestController
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService service;

    @PostMapping()
    Integer create(@RequestBody Dictionary dictionary) {
        return service.insert(dictionary);
    }

    @GetMapping("{id}")
    Dictionary getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping()
    List<Dictionary> findAll() {
        return service.findAll();
    }

    @PutMapping()
    void edit(@RequestBody Dictionary newDictionary) {
        service.edit(newDictionary);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
