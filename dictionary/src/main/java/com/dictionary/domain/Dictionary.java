package com.dictionary.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {

    private Long id;

    private String name;

    private String abbreviation;
}
