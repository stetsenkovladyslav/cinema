package com.example.admin.repository;

import com.example.admin.model.Video;
import org.springframework.data.repository.CrudRepository;



public interface VideoRepository extends CrudRepository<Video, Long> {
}
