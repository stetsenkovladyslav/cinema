package com.example.user.service.comment;

import com.example.root.dto.comment.CommentRequest;
import com.example.root.model.Comment;

public interface CommentService {
    Comment createComment(CommentRequest commentRequest);

    void deleteCommentById(long id);
}
