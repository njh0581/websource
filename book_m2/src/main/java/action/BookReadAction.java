package action;

import dto.BookDto;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookReadAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    String code = req.getParameter("code");
    BookService service = new BookServiceImpl();
    BookDto dto = service.read(Integer.parseInt(code));
    req.setAttribute("dto", dto);

    return new ActionForward(path, false);
  }
}
