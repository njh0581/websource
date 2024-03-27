package action;

import dto.TodoDto;
import javax.servlet.http.HttpServletRequest;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoUpdateAction implements Action {

  private String path;

  public TodoUpdateAction(String path) {
    this.path = path;
  }

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    TodoService service = new TodoServiceImpl();
    String completed = req.getParameter("completed");
    String description = req.getParameter("description");
    String no = req.getParameter("no");

    TodoDto dto = new TodoDto();
    dto.setCompleted(Boolean.parseBoolean(completed));
    dto.setDescription(description);
    dto.setNo(Integer.parseInt(no));

    boolean result = service.update(dto);

    return new ActionForward(path, true);
  }
}
