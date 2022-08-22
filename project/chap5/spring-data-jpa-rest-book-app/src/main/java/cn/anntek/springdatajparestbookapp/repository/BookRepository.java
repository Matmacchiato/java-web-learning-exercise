package cn.anntek.springdatajparestbookapp.repository;

import cn.anntek.springdatajparestbookapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "book",path = "books")
public interface BookRepository extends JpaRepository<Book,Long> {
}
