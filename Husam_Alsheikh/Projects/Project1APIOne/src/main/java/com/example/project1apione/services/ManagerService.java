package com.example.project1apione.services;

import com.example.project1apione.dao.ManagerRepository;
import com.example.project1apione.models.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    /**
     * @param managerRepository Autowire Manager Repo
     */
    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    /**
     * @param manager_id Manager Id
     * @return Returns manager using the manager id
     */
    public Manager getManager(Integer manager_id){
        return managerRepository.getById(manager_id);
    }
}
