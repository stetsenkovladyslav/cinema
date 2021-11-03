package com.example.admin.service.director;

import com.example.admin.DTO.DirectorDTO;
import com.example.admin.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectorService {


    Director addDirector(DirectorDTO directorDTO);

    void deleteDirectorById(long id);

    void updateDirectorById(long id, DirectorDTO directorDTO);

    Director getDirectorById(long id);

    Page<Director> getAllDirectors(Pageable pageable);
}
