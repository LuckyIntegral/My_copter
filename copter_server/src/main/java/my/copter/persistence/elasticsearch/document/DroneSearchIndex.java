package my.copter.persistence.elasticsearch.document;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Setter
@Document(indexName = "drone_search_index")
public class DroneSearchIndex {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Long)
    private Long droneId;

    @Field(type = FieldType.Date)
    private Date created;
}
