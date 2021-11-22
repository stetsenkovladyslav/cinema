package com.example.admin.service.user.comment;

import com.example.data.dto.comment.CommentRequest;
import com.example.data.model.Comment;

public interface CommentService {
    Comment createComment(CommentRequest commentRequest);

    void deleteCommentById(long id);
}
