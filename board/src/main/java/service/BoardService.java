package service;

import dto.BoardDto;
import java.util.List;

public interface BoardService {
  List<BoardDto> getList();

  boolean insert(BoardDto insertDto);

  BoardDto read(int bno);

  boolean update(BoardDto updateDto);

  boolean delete(BoardDto deleteDto);

  boolean reply(BoardDto replyDto);
}
