package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

@Setter
@Getter
@ToString
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@DatabaseTable(tableName = "loads"/*, schemaName = "logistic"*/)
public class Loads {
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String name;

    @DatabaseField(columnName = "Loc_id", foreign = true, foreignColumnName = "id")
    private long locationId;

    public Loads() {

    }
}

