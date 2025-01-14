package com.example._12_spring_data_intro_exercise.repositories;

import com.example._12_spring_data_intro_exercise.entities.Author;
import com.example._12_spring_data_intro_exercise.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

}
