package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper authorMapper;
    // BEGIN
    public List<AuthorDTO> getAll() {
        var posts = repository.findAll();
        var result = posts.stream()
                .map(authorMapper::map)
                .toList();
        return result;
    }

    public AuthorDTO findById(Long id) {
        var post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var postDTO = authorMapper.map(post);
        return postDTO;
    }

    public AuthorDTO create(AuthorCreateDTO postData) {
        var post = authorMapper.map(postData);
        repository.save(post);
        var postDTO = authorMapper.map(post);
        return postDTO;
    }

    public AuthorDTO update(AuthorUpdateDTO postData, Long id) {
        var post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        authorMapper.update(postData, post);
        repository.save(post);
        var postDTO = authorMapper.map(post);
        return postDTO;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
