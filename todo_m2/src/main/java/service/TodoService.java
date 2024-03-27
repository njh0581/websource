package service;

import dto.TodoDto;
import java.util.List;

//dao 에 crud 메소드를 호출
public interface TodoService {
  List<TodoDto> list();

  TodoDto getRow(String no);

  boolean insert(TodoDto inserDto);

  boolean update(TodoDto inserDto);

  boolean delete(String no);
}
