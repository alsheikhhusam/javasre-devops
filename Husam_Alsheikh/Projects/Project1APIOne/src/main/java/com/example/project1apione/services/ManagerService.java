package com.example.project1apione.services;

import com.example.project1apione.dao.ManagerRepository;
import com.example.project1apione.models.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager getManager(Integer manager_id){
        return managerRepository.getById(manager_id);
    }
}
