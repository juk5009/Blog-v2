package shop.mtcoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.board.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.blog.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blog.handler.ex.CustomApiException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.ReplyRepository;
import shop.mtcoding.blog.util.HtmlParser;

@Transactional(readOnly = true)
@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    // where 절에 걸리는 파라메터를 앞에 받기

    @Transactional
    public void 댓글쓰기(ReplySaveReqDto replySaveReqDto, int principalId) {

        // 1. content 내용을 Document로 받고, img 찾아내서(0,1,2), src를 찾아 thumbnail 추가

        int result = replyRepository.insert(replySaveReqDto.getComment(), replySaveReqDto.getBoardId(),
                principalId);
        if (result != 1) {
            throw new CustomApiException("글쓰기실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
