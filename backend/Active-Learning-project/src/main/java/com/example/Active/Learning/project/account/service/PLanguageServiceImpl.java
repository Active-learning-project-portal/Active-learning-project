package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.repositories.BaseRepository;
import com.example.Active.Learning.project.account.repositories.PLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PLanguageServiceImpl extends BaseImpl<PLanguage, UUID> {

    @Autowired
    PLanguageRepository pLanguageRepository;

    public PLanguageServiceImpl(BaseRepository<PLanguage, UUID> baseRepository) {
        super(baseRepository);
    }

}
