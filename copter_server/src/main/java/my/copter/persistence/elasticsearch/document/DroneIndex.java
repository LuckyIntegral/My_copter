package my.copter.persistence.elasticsearch.document;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "droneindex")
public class DroneIndex {

    @Id
    private String id;

    @Field(name = "droneInfo", type = FieldType.Text)
    private String droneInfo;

    @Field(name = "droneId", type = FieldType.Long)
    private Long droneId;
}
