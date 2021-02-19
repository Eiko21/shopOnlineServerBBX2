package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model;

import lombok.Getter;
import lombok.Setter;

public enum CommentEnum {

    END_OF_CONTRACT(1, "Termination of the contract with the supplier"),
    TEMPORARILY_OUT_OF_SALE(2, "Out of sale, temporarily"),
    BAD_CONDITION(3, "Bad condition of the product"),
    NO_COMMENTS(4, "No comments");

    @Getter @Setter Integer commentId;
    @Getter @Setter String comment;

    CommentEnum(int commentId, String comment) {
        this.commentId = commentId;
        this.comment = comment;
    }

    public static CommentEnum geCommentFromId(Integer id){
        for(CommentEnum e: values()){
            if(e.commentId.equals(id)) return e;
        }
        return END_OF_CONTRACT;
    }
}
