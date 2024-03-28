package action;

import dto.BoardDto;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReplyAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    String name = req.getParameter("name");
    String title = req.getParameter("title");
    String content = req.getParameter("content");
    String password = req.getParameter("password");
    int bno = Integer.parseInt(req.getParameter("bno"));
    int reRef = Integer.parseInt(req.getParameter("reRef"));
    int reSeq = Integer.parseInt(req.getParameter("reSeq"));
    int reLev = Integer.parseInt(req.getParameter("reLev"));

    BoardDto replyDto = new BoardDto();
    replyDto.setName(name);
    replyDto.setTitle(title);
    replyDto.setContent(content);
    replyDto.setPassword(password);
    replyDto.setReRef(reRef);
    replyDto.setReSeq(reSeq);
    replyDto.setReLev(reLev);

    BoardService service = new BoardServiceImpl();
    //성공시 리스트 보여주기
    if (!service.reply(replyDto)) {
      path = "/qReplyView.do?bno=" + replyDto.getBno();
    }

    //실패시 댓글 화면

    return new ActionForward(path, true);
  }
}
