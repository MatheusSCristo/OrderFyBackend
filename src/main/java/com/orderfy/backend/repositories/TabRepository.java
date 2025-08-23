package com.orderfy.backend.repositories;

import com.orderfy.backend.enums.TabStatus;
import com.orderfy.backend.models.TabModel;
import com.orderfy.backend.models.TableModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabRepository extends AbstractRepository<TabModel> {

    List<TabModel> findAllByTableAndStatus(TableModel table, com.orderfy.backend.enums.TabStatus status);

    List<TabModel> findAllByTableAndClosedAtIsNull(TableModel table);

    Optional<TabModel> findFirstByTableAndCustomerNameAndStatusOrderByCreatedAtDesc(
            TableModel table, String customerName, TabStatus status
    );
}