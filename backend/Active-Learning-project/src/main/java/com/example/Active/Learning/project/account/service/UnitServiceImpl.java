package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.models.units.Unit;
import com.example.Active.Learning.project.account.repositories.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UnitServiceImpl extends BaseImpl{
    public UnitServiceImpl(BaseRepository<Unit, UUID> baseRepository) {
        super(baseRepository);
    }
}
