package action;

import dto.TodoDto;
import javax.servlet.http.HttpServletRequest;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoCreateAction implements Action {

  private String path;

  public TodoCreateAction(String path) {
    this.path = path;
  }

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    TodoService service = new TodoServiceImpl();
    String title = req.getParameter("title");
    String description = req.getParameter("description");

    TodoDto inserDto = new TodoDto();
    inserDto.setTitle(title);
    inserDto.setDescription(description);

    boolean result = service.insert(inserDto);

    return new ActionForward(path, true);
  }
}
