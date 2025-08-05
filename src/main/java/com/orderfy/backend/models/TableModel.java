package com.orderfy.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name="table")
@Table(name="tables")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TableModel extends AbstractModel {

    public String uuid;
    private Integer number;
    @ManyToOne(fetch = FetchType.LAZY)
    private RestaurantModel restaurant;
    @OneToMany(mappedBy = "table")
    private List<TabModel> tabs;

}
