package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement(name = "dbinfo")
public class MasterEntity {
    @XmlTransient
    protected int id;

    @XmlElement(name = "location")
    List<Location> locationList;

}
