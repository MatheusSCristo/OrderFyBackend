package com.orderfy.backend.models;

import jakarta.persistence.Entity;
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
@SuperBuilder
public class RestaurantModel extends AbstractModel {

}
