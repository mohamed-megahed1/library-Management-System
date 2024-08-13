package com.mohamedahmed.libraryManagementSystem.repository.specification;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {
    private BookDto bookDto;

    public BookSpecification(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    @Override
    public Predicate toPredicate(Root<Book> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        List<Predicate> predicates=new ArrayList<>();

        //book title
        if (bookDto.getTitle() != null || !bookDto.getTitle().isEmpty()){
            predicates.add(cb.like(root.get("title"),"%"+bookDto.getTitle()+"%"));
        }
        //book author
        if (bookDto.getAuthor() != null || !bookDto.getAuthor().isEmpty()){
            predicates.add(cb.like(root.get("author"),"%"+bookDto.getAuthor()+"%"));
        }
        //book isbn
        if (bookDto.getIsbn() != null || !bookDto.getIsbn().isEmpty()){
            predicates.add(cb.like(root.get("isbn"),"%"+bookDto.getIsbn()+"%"));
        }
        //book publisher
        if (bookDto.getPublisher() != null || !bookDto.getPublisher().isEmpty()){
            predicates.add(cb.like(root.get("publisher"),"%"+bookDto.getPublisher()+"%"));
        }
        //book edition
        if (bookDto.getEdition() != null || !bookDto.getEdition().isEmpty()){
            predicates.add(cb.like(root.get("edition"),"%"+bookDto.getEdition()+"%"));
        }
        //book category
        if (bookDto.getCategory() != null || !bookDto.getCategory().isEmpty()){
            predicates.add(cb.like(root.get("category"),"%"+bookDto.getCategory()+"%"));
        }

        //book language
        if (bookDto.getLanguage() != null || !bookDto.getLanguage().isEmpty()){
            predicates.add(cb.like(root.get("language"),"%"+bookDto.getLanguage()+"%"));
        }
        //book description
        if (bookDto.getDescription() != null || !bookDto.getDescription().isEmpty()){
            predicates.add(cb.like(root.get("description"),"%"+bookDto.getDescription()+"%"));
        }




         return cb.and(predicates.toArray(new Predicate[0]));
    }
}
