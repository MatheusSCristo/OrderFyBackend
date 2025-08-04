package com.orderfy.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity(name="restaurant")
@Table(name="restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RestaurantModel extends AbstractModel {
    private String name;
    @Column(name = "cnpj_cpf")
    private String cnpjCpf;
    @OneToMany(mappedBy = "restaurant")
    private List<EmployeeModel> employees;

}
