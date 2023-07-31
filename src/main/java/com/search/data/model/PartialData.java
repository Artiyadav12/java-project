package com.search.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class PartialData {
    private Integer id;
    private String name;

    public PartialData(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
