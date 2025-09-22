//package com.example.postgredatabase.repositories;
//
//import com.example.postgredatabase.dao.impl.AuthorDAOImpl;
//import com.example.postgredatabase.dao.impl.BookDAOImpl;
//import com.example.postgredatabase.domain.Book;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class BookDAOImplTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private BookDAOImpl bookDAOImpl;
//    @InjectMocks
//    private AuthorDAOImpl authorDAOImpl;
//
//    @Test
//    public void testThatCreateBookGeneratesCorrectSql() {
//        Book book = Book.builder()
//                .isbn("1")
//                .title("Test")
//                .authorId(1L).build();
//
//        bookDAOImpl.create(book);
//
//        verify(jdbcTemplate).update(
//                eq("INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)"),
//                eq("1"),
//                eq("Test"),
//                eq(1L)
//        );
//    }
//}
