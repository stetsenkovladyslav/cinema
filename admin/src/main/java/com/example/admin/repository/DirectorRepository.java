package com.example.admin.repository;

import com.example.admin.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    List<Director> getDirectorByIdIn(List<Long> ids);
}
