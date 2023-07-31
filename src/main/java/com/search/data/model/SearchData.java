package com.search.data.model;

import lombok.*;
import com.datastax.driver.mapping.annotations.Table;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "serach_data")
@Data
//@Document(indexName = "productindex")
public class SearchData implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @PrimaryKey
    private Integer id;
    @Column(name = "name")
   // @Field(type = FieldType.Text, name = "name")
    private String name;
    @Column(name = "stored_name")
    //@Field(type = FieldType.Text, name = "stored_name")
    private String storedName;


}
