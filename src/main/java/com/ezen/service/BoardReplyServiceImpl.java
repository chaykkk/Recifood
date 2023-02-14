package com.ezen.service;

import com.ezen.entity.Board;
import com.ezen.entity.BoardReply;
import com.ezen.persistence.BoardReplyRepository;
import com.ezen.persistence.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardReplyServiceImpl implements BoardReplyService{

    @Autowired
    private BoardReplyRepository boardReplyRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void insertBoardReply(BoardReply boardReply) {
        boardReplyRepository.save(boardReply);
    }

    @Override
    public void updateBoardReply(BoardReply boardReply) {
        boardReplyRepository.save(boardReply);
    }

    @Override
    public void deleteBoardReply(BoardReply boardReply) {
        boardReplyRepository.deleteById(boardReply.getBoardReplySeq());
    }

    @Override
    public List<BoardReply> getBoardReplyList(Board board) {
        
        return boardReplyRepository.findAllByBoard_BoardSeq(board.getBoardSeq());
    }
}
