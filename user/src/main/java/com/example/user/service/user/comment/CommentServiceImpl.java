package com.example.user.service.user.comment;


import com.example.root.dto.comment.CommentRequest;
import com.example.root.model.Comment;
import com.example.user.mapper.CommentMapper;
import com.example.user.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comment createComment(CommentRequest commentRequest) {
        Comment comment = commentMapper.create(commentRequest);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(long id) {

    }
}
