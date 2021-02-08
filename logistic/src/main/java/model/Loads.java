package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Loads extends MasterEntity {
    @XmlAttribute
    @Id
    private int id;

    @XmlAttribute
    private String name;

    private int idLocations;
}

