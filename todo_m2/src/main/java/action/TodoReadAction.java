package action;

import dto.TodoDto;
import javax.servlet.http.HttpServletRequest;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoReadAction implements Action {

  private String path;

  public TodoReadAction(String path) {
    this.path = path;
  }

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    //TodoServlet 에서 했던 작업
    TodoService service = new TodoServiceImpl();
    String no = req.getParameter("no");
    TodoDto todo = service.getRow(no);
    req.setAttribute("todo", todo);

    //setAttribute면 false
    return new ActionForward(path, false);
  }
}
