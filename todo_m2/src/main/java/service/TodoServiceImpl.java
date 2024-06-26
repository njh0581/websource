package service;

import dao.TodoDao;
import dto.TodoDto;
import java.util.List;

public class TodoServiceImpl implements TodoService {

  TodoDao dao = new TodoDao();

  @Override
  public List<TodoDto> list() {
    return dao.getList();
  }

  @Override
  public TodoDto getRow(String no) {
    return dao.getRow(no);
  }

  @Override
  public boolean insert(TodoDto inserDto) {
    // 1이면 true, 아니면 false
    return dao.insert(inserDto) == 1;
  }

  @Override
  public boolean update(TodoDto inserDto) {
    return dao.update(inserDto) == 1;
  }

  @Override
  public boolean delete(String no) {
    return dao.delete(no) == 1;
  }
}
