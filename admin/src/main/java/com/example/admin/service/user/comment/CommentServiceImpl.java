package com.example.admin.service.user.comment;

import com.example.admin.dto.comment.CommentRequest;
import com.example.admin.mapper.CommentMapper;
import com.example.admin.model.Comment;
import com.example.admin.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;


//
//    @Override
//    public Review create(ReviewDto reviewDto) {
//        var review = new Review();
//        modelMapper.map(reviewDto, review);
//        review = reviewRepo.save(review);
//        return review;
//    }

    @Override
    public Comment createComment(CommentRequest commentRequest) {
        Comment comment = commentMapper.create(commentRequest);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(long id) {

    }
}
