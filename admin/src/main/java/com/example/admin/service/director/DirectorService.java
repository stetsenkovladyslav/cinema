package com.example.admin.service.director;

import com.example.admin.dto.director.DirectorDTO;
import com.example.admin.dto.director.DirectorRequest;
import com.example.admin.dto.genre.GenreDTO;
import com.example.admin.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectorService {


    DirectorDTO addDirector(DirectorRequest directorRequest);

    void deleteDirectorById(long id);

    DirectorDTO updateDirectorById(long id, DirectorRequest directorRequest);

    DirectorDTO getDirectorById(long id);

    Page<Director> getAllDirectors(Pageable pageable);
}
