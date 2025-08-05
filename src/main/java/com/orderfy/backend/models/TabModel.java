package com.orderfy.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orderfy.backend.enums.TabStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity(name="tab")
@Table(name="tabs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TabModel extends AbstractModel {

    @ManyToOne(fetch = FetchType.LAZY)
    private TableModel table;
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerModel customer;
    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeModel waiter;
    @Enumerated(EnumType.STRING)
    private TabStatus  status;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "closed_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp closedAt;
    @OneToMany(mappedBy = "tab")
    private List<TabItemModel> items;
}
