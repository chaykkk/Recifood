package com.ezen.service;

import com.ezen.entity.Board;
import com.ezen.entity.BoardReply;

import java.util.List;

public interface BoardReplyService {

    void insertBoardReply(BoardReply boardReply); // 댓글 등록

    void updateBoardReply(BoardReply boardReply); // 댓글 수정

    void deleteBoardReply(BoardReply boardReply); // 댓글 삭제

    List<BoardReply> getBoardReplyList(Board board);
}
