package com.example.project1apione;

import com.example.project1apione.dao.ManagerRepository;
import com.example.project1apione.models.Manager;
import com.example.project1apione.services.ManagerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ManagerServiceTest {
    private ManagerRepository managerRepository;

    private ManagerService managerService;

    @BeforeEach
    public void initBeforeEach(){
        managerRepository = mock(ManagerRepository.class);
        managerService = new ManagerService(managerRepository);
    }

    @Test
    public void getManagerTest(){
        int manager_id = 1;

        when(managerRepository.getById(manager_id)).thenReturn(new Manager());

        Manager manager = managerService.getManager(manager_id);

        Assertions.assertNull(manager.getId());
    }
}
