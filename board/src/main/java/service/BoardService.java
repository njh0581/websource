package service;

import dto.BoardDto;
import dto.SearchDto;
import java.util.List;

public interface BoardService {
  List<BoardDto> list(SearchDto searchDto);

  boolean insert(BoardDto insertDto);

  BoardDto read(int bno);

  boolean update(BoardDto updateDto);

  boolean delete(BoardDto deleteDto);

  boolean reply(BoardDto replyDto);

  boolean updateCount(int bno);

  List<BoardDto> searchList(SearchDto searchDto);

  int getTotalRows(String criteria, String keyword);
}
