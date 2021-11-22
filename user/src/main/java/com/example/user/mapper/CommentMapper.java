package com.example.user.mapper;


import com.example.root.dto.comment.CommentDTO;
import com.example.root.dto.comment.CommentRequest;
import com.example.root.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CommentMapper extends CrudMapper<Comment, CommentDTO, CommentRequest, CommentRequest> {


    CommentDTO toDTO(Comment comment);

    Comment dtoToComment(CommentDTO commentDTO);
}
