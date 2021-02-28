package com.directory.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Directory {

    private Long id;

    private String name;

    private String abbreviation;
}
