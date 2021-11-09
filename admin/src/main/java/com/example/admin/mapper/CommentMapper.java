package com.example.admin.mapper;


import com.example.admin.dto.comment.CommentDTO;
import com.example.admin.dto.comment.CommentRequest;
import com.example.admin.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CommentMapper extends CrudMapper<Comment, CommentDTO, CommentRequest, CommentRequest> {


    CommentDTO toDTO(Comment comment);

    Comment dtoToComment(CommentDTO commentDTO);
}
