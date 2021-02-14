/**
 *    Copyright 2015-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.reference.controller;

import com.reference.domain.Reference;
import com.reference.mapper.ReferenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reference")
@RestController
@Slf4j
public class ReferenceController {

  private final ReferenceMapper mapper;

  public ReferenceController(ReferenceMapper mapper) {
    this.mapper = mapper;
  }

  @GetMapping()
  List<Reference> findAll() {
    log.info("getting all references.");
    return mapper.findAll();
  }

  @PostMapping()
  Integer newReference(@RequestBody Reference newReference) {
    log.info("creating a new reference: " + newReference);
    return mapper.insert(newReference);
  }

  @GetMapping("{id}")
  Reference oneReference(@PathVariable Long id) {
    log.info("getting reference by id: " + id);
    return mapper.findById(id);
  }

  @PutMapping()
  void editReference(@RequestBody Reference newReference) {
    log.info("update reference with id: " + newReference.getId());
    mapper.edit(newReference);
  }

  @DeleteMapping("{id}")
  void deleteReference(@PathVariable Long id) {
    log.info("delete reference with id: " + id);
    mapper.delete(id);
  }
}
