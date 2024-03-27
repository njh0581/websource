package action;

import dto.BoardDto;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardModifyAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    // 수정작업
    BoardDto updateDto = new BoardDto();
    updateDto.setContent(req.getParameter("content"));
    updateDto.setTitle(req.getParameter("title"));
    //NumberFormatException: Cannot parse null string
    updateDto.setBno(Integer.parseInt(req.getParameter("bno")));
    updateDto.setPassword(req.getParameter("password"));

    BoardService service = new BoardServiceImpl();

    if (!service.update(updateDto)) {
      //수정 실패시 수정화면으로 돌아가기
      path = "/qModify.do?bno=" + updateDto.getBno();
    } else {
      path += "?bno=" + updateDto.getBno();
    }

    return new ActionForward(path, true);
  }
}
