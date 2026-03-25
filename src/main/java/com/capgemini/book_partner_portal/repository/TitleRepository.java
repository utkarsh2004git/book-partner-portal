package com.capgemini.book_partner_portal.repository;


import com.capgemini.book_partner_portal.entity.Title;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.web.bind.annotation.RequestParam;


@RepositoryRestResource
public interface TitleRepository extends JpaRepository<Title, String> {
        
        Optional<Title> findByTitle(@RequestParam("title")String title);

        Optional<Title> findByType(@RequestParam("type")String type);

  
}