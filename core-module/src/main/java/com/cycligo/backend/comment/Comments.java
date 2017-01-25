package com.cycligo.backend.comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class Comments {
    List<CommentDto> comments;

    public List<CommentDto> getComments() {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        return comments;
    }
}
