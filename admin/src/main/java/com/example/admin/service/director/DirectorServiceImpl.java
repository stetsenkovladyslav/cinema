package com.example.admin.service.director;

import com.example.data.dto.director.DirectorDTO;
import com.example.data.dto.director.DirectorRequest;
import com.example.admin.mapper.DirectorMapper;
import com.example.data.model.Director;
import com.example.admin.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService{

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;


    @Override
    public DirectorDTO addDirector(DirectorRequest directorRequest) {
        Director director = directorMapper.create(directorRequest);
        return directorMapper.mapToDTO(directorRepository.save(director));
    }

    @Override
    public void deleteDirectorById(long id) {
        directorRepository.deleteById(id);

    }

    @Override
    public DirectorDTO updateDirectorById(long id, DirectorRequest directorRequest) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Director with id:{" + id + "} does not exist"));
        return directorMapper.mapToDTO(directorRepository.save(directorMapper.update(director, directorRequest)));

    }

    @Override
    public DirectorDTO getDirectorById(long id) {
        return directorMapper.mapToDTO(directorRepository.getById(id));
    }

    @Override
    public Page<Director> getAllDirectors(Pageable pageable) {
        return directorRepository.findAll(pageable);
    }
}
