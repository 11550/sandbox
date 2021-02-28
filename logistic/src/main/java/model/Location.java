package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DatabaseTable(tableName = "location"/*, schemaName = "logistic"*/)
public class Location {
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String name;
}

