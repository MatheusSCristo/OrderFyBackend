package com.orderfy.backend.repositories;

import com.orderfy.backend.models.TableModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends AbstractRepository<TableModel> {
    Optional<TableModel> findByUuid(String uuid);
}
