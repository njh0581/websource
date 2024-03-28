package action;

import dto.BoardDto;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReadAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    int bno = Integer.parseInt(req.getParameter("bno"));
    BoardService service = new BoardServiceImpl();

    BoardDto dto = service.read((bno));
    req.setAttribute("dto", dto);

    return new ActionForward(path, false);
  }
}
