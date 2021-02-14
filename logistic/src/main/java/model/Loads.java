package model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Loads extends MasterEntity {
    private int id;

    private String name;

    private Location location;

    public Loads(String name) {
        this.name = name;
    }
}

