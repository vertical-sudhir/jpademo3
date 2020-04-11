package com.example.demo.repositeries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long>{

}
