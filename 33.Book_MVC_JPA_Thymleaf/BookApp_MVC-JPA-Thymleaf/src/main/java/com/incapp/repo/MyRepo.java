package com.incapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incapp.entity.Book;

@Repository
public interface MyRepo extends JpaRepository<Book, String> {

}
