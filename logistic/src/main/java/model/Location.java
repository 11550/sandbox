package model;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private int id;

    private String name;

    Set<Loads> loads;

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Location(String name) {
        this.name = name;
    }
}

