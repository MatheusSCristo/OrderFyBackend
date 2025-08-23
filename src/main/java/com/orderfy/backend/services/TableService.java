package com.orderfy.backend.services;

import com.orderfy.backend.models.TableModel;
import com.orderfy.backend.repositories.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TableService {

    private final TableRepository tableRepository;


    public Optional<TableModel> findTableByUuid(String uuid){
        return tableRepository.findByUuid(uuid);
    }

    public TableModel findTableByUuidOrThrow(String uuid){
        return findTableByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Mesa não encontrada"));
    }



}
