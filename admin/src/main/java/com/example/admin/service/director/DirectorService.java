package com.example.admin.service.director;

import com.example.root.dto.director.DirectorDTO;
import com.example.root.dto.director.DirectorRequest;
import com.example.root.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DirectorService {


    DirectorDTO addDirector(DirectorRequest directorRequest);

    void deleteDirectorById(long id);

    DirectorDTO updateDirectorById(long id, DirectorRequest directorRequest);

    DirectorDTO getDirectorById(long id);

    Page<Director> getAllDirectors(Pageable pageable);
    List<Director> getAllByIds(List<Long> ids);
}
