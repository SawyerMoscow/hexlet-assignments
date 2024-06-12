package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper bookMapper;
    // BEGIN
    public List<BookDTO> getAll() {
        var posts = repository.findAll();
        var result = posts.stream()
                .map(bookMapper::map)
                .toList();
        return result;
    }

    public BookDTO findById(Long id) {
        var post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var postDTO = bookMapper.map(post);
        return postDTO;
    }

    public BookDTO create(BookCreateDTO postData) {
        var post = bookMapper.map(postData);
        repository.save(post);
        var postDTO = bookMapper.map(post);
        return postDTO;
    }

    public BookDTO update(BookUpdateDTO postData, Long id) {
        var post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        bookMapper.update(postData, post);
        repository.save(post);
        var postDTO = bookMapper.map(post);
        return postDTO;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
