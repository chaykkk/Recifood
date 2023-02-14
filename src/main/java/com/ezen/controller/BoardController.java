package com.ezen.controller;

import com.ezen.entity.Board;
import com.ezen.entity.BoardReply;
import com.ezen.entity.Member;
import com.ezen.service.BoardReplyService;
import com.ezen.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ezen.entity.Role.ADMIN;

@Controller
@Log4j2
public class BoardController {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardReplyService boardReplyService;

//    @GetMapping("/boardList")
//    public String boardList() {
//        return "board/boardList";
//    }
    @GetMapping("/board")
    public String getBoard(Board board, Model model) {
        log.info("게시글 조회" + board);
        List<BoardReply> boardReplyList =boardReplyService.getBoardReplyList(board);

        if(boardReplyList != null && !boardReplyList.isEmpty()) {
            model.addAttribute("boardReplyList", boardReplyList);
        }

        model.addAttribute("board", boardService.getBoard(board));
        return "board/getBoard";
    }

    @GetMapping("/insertBoard")
    public String insertBoardForm(@SessionAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board, @SessionAttribute("member") Member member) {
        log.info(board);
        board.setMember(member);

        if(member.getRole() == ADMIN) {
            board.setCategory("1");
            boardService.insertBoard(board);
            return "redirect:/boardList?category=1";
        } else {
            board.setCategory("2");
            boardService.insertBoard(board);
            return "redirect:/boardList?category=2";
        }
    }

    @GetMapping("/updateBoard")
    public String updateBoardForm(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "board/updateBoard";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {

        if(board.getCategory().equals(1)) {
            boardService.updateBoard(board);
            return "redirect:/boardList?category=1";
        } else {
            board.setCategory("2");
            boardService.updateBoard(board);
            return "redirect:/boardList?category=2";
        }
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        log.info(board.getBoardSeq() + "번째 게시글 삭제");
        log.info(board.getCategory());

        if(board.getCategory().equals(1)) {
            boardService.deleteBoard(board);
            return "redirect:/boardList?category=1";
        } else {
            boardService.deleteBoard(board);
            return "redirect:/boardList?category=2";
        }
    }

//    @RequestMapping("/boardList")
//    public String getBoardList(@RequestParam(value = "page", defaultValue = "0") int page, Search search, Model model) {
//        if(search.getSearchCondition() == null) {
//            search.setSearchCondition("TITLE");
//        }
//        if(search.getSearchKeyword() == null) {
//            search.setSearchKeyword("");
//        }
//        Page<Board> boardList = boardService.getBoardList(page, search);
//        log.info("게시글 목록: " + boardList.getContent());
//        model.addAttribute("boardList", boardList);
//
//        return "board/freeBoardList";
//    }

    @RequestMapping("/boardList")
    public String getFreeBoardList(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "category", defaultValue = "0") int category, Model model) {

        Page<Board> boardList = boardService.findByCategory(page, String.valueOf(category));
        log.info("게시글 목록: " + boardList.getContent());
        model.addAttribute("boardList", boardList);
        model.addAttribute("category", category);

        return "board/freeBoardList";
    }
}
