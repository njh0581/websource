package action;

import dto.BoardDto;
import dto.SearchDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardSearchAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    String criteria = req.getParameter("criteria");
    String keyword = req.getParameter("keyword");
    SearchDto searchDto = new SearchDto();
    //BoardService list 호출
    BoardService service = new BoardServiceImpl();
    List<BoardDto> list = service.searchList(searchDto);

    //req 결과 담기
    req.setAttribute("list", list);
    req.setAttribute("search", searchDto);

    return new ActionForward(path, false);
  }
}
