package action;

import dto.BoardDto;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardDeleteAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    BoardDto deleteDto = new BoardDto();
    deleteDto.setBno(Integer.parseInt(req.getParameter("bno")));
    deleteDto.setPassword(req.getParameter("password"));

    String page = req.getParameter("page");
    String amount = req.getParameter("amount");
    String criteria = req.getParameter("criteria");
    String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

    BoardService service = new BoardServiceImpl();

    //bno 이용해서 행 조회
    BoardDto dto = service.read(deleteDto.getBno());
    // bno == re_ref : 원본글
    if (dto.getBno() == dto.getReRef()) {
      // 비밀번호확인후 일치한다면
      // deleteAll호출
      if (service.pwdCheck(deleteDto)) {
        service.deleteAll(dto.getReRef());
        path +=
          "?page=" +
          page +
          "&amount=" +
          amount +
          "&criteria=" +
          criteria +
          "&keyword=" +
          keyword;
      } else {
        path =
          "/view/qna_board_pwdCheck.jsp?bno=" +
          deleteDto.getBno() +
          "&page=" +
          page +
          "&amount=" +
          amount +
          "&criteria=" +
          criteria +
          "&keyword=" +
          keyword;
      }
    } else {
      if (!service.delete(deleteDto)) {
        path =
          "/view/qna_board_pwdCheck.jsp?bno=" +
          deleteDto.getBno() +
          "&page=" +
          page +
          "&amount=" +
          amount +
          "&criteria=" +
          criteria +
          "&keyword=" +
          keyword;
      } else {
        path +=
          "?page=" +
          page +
          "&amount=" +
          amount +
          "&criteria=" +
          criteria +
          "&keyword=" +
          keyword;
      }
    }

    return new ActionForward(path, true);
  }
}
