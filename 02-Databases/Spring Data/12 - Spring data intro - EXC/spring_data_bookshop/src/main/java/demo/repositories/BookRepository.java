package demo.repositories;

import demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByReleaseDateAfter(LocalDate releaseDate);
    int countByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findAllByAuthorId(int authorId);
}
