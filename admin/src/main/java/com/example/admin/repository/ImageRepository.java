package com.example.admin.repository;

import com.example.root.model.Genre;
import com.example.root.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {

}