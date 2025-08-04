package com.orderfy.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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

}
