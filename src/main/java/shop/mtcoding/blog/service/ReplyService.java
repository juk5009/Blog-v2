package shop.mtcoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.blog.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blog.handler.ex.CustomApiException;
import shop.mtcoding.blog.model.Reply;
import shop.mtcoding.blog.model.ReplyRepository;

@Slf4j // 로고를 작성할수잇다.
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

    @Transactional
    public void 댓글삭제(int id, int principalId) {
        Reply reply = replyRepository.findById(id);
        if (reply == null) {
            throw new CustomApiException("댓글이 존재하지 않습니다.");

        }
        if (reply.getUserId() != principalId) {
            throw new CustomApiException("댓글을 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN);

        }
        // 1. 인증 ok, 2.댓글 존재 유무 확인 , 3. 권한 ok
        try {
            replyRepository.deleteById(id);

        } catch (Exception e) {
            log.error("서버에러: " + e.getMessage());

            throw new CustomApiException("댓글을 삭제 실패 - 서버 에러", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
