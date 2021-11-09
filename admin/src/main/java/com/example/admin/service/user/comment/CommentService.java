package com.example.admin.service.user.comment;

import com.example.admin.dto.comment.CommentRequest;
import com.example.admin.model.Comment;

public interface CommentService {
    Comment createComment(CommentRequest commentRequest);

    void deleteCommentById(long id);
}
