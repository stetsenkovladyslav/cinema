package com.example.admin.mapper;


import com.example.data.dto.comment.CommentDTO;
import com.example.data.dto.comment.CommentRequest;
import com.example.data.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CommentMapper extends CrudMapper<Comment, CommentDTO, CommentRequest, CommentRequest> {


    CommentDTO toDTO(Comment comment);

    Comment dtoToComment(CommentDTO commentDTO);
}
