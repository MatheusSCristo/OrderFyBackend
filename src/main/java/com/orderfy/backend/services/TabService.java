package com.orderfy.backend.services;

import com.orderfy.backend.enums.TabStatus;
import com.orderfy.backend.models.TabModel;
import com.orderfy.backend.models.TableModel;
import com.orderfy.backend.repositories.TabRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TabService {

    private final TabRepository tabRepository;

    public List<TabModel> findAllByTable(TableModel table){
        return tabRepository.findAllByTableAndClosedAtIsNull(table);
    }

    public Optional<TabModel> findFirstByTableAndCustomerNameAndStatus(
            TableModel table, String customerName, TabStatus status
    ){
        return tabRepository.findFirstByTableAndCustomerNameAndStatusOrderByCreatedAtDesc(table, customerName, status);
    }

    public TabModel save(TabModel tabModel){
        return tabRepository.save(tabModel);
    }


}
