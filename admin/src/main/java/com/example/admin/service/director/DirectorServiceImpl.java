package com.example.admin.service.director;

import com.example.admin.DTO.DirectorDTO;
import com.example.admin.mapper.DirectorMapper;
import com.example.admin.model.Director;
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
    public Director addDirector(DirectorDTO directorDTO) {
        return directorRepository.save(new Director(directorDTO.getDirectorName()));    }

    @Override
    public void deleteDirectorById(long id) {
        directorRepository.deleteById(id);

    }

    @Override
    public void updateDirectorById(long id, DirectorDTO directorDTO) {
        directorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Director with id:{" + id + "} does not exist"));
        Director updatedDirector = directorMapper.dtoToDirector(directorDTO);
        directorRepository.save(updatedDirector);
    }

    @Override
    public Director getDirectorById(long id) {
        return directorRepository.getById(id);
    }

    @Override
    public Page<Director> getAllDirectors(Pageable pageable) {
        return directorRepository.findAll(pageable);
    }
}
