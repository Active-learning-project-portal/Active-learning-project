package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.PLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  PLanguageRepository extends IRepository<PLanguage> {

    @Query("select u from Course u where u.name like %:#{[0]}% or u.name like %:searchValue%")
    Page<PLanguage> findAllByLike(String searchValue, Pageable pageable);
}
