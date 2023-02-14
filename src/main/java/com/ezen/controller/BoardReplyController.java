package com.ezen.controller;

import com.ezen.entity.Board;
import com.ezen.entity.BoardReply;
import com.ezen.entity.Member;
import com.ezen.entity.Recipe;
import com.ezen.service.BoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BoardReplyController {

    @Autowired
    BoardReplyService boardReplyService;
//    @Autowired
//    BoardReplyRepository boardReplyRepository;

//    @RequestMapping("/getBoardReply")
//    public @ResponseBody List<BoardReply> boardReplyList(@ModelAttribute BoardReply boardReply) {
//        return boardReplyService.getBoardReplyList(board);
//    }

    @PostMapping("/insertReply")
    public @ResponseBody void insertReply(@RequestBody Map<String, String> map, BoardReply boardReply) {
        System.out.println("사용자명: "+map.get("replyUsername"));
        System.out.println("게시판 Seq: " + map.get("boardSeq"));
        System.out.println("댓글 내용: " + map.get("replyContent"));

        Member member = new Member();
        member.setUsername(map.get("replyUsername"));
        Board board = new Board();
        board.setBoardSeq(Long.parseLong(map.get("boardSeq")));

        boardReply.setContent(map.get("replyContent"));
        boardReply.setMember(member);
        boardReply.setBoard(board);

        boardReplyService.insertBoardReply(boardReply);
    }
}
