package action;

import dto.BookDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import service.BookService;
import service.BookServiceImpl;

public class BookListAction implements Action {

  private String path;

  public BookListAction(String path) {
    this.path = path;
  }

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    //service listAll() 호출
    BookService service = new BookServiceImpl();
    List<BookDto> list = service.listAll();
    req.setAttribute("list", list);

    return new ActionForward(path, false);
  }
}
