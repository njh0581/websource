package service;

import dto.BookDto;
import dto.ChangeDto;
import dto.MemberDto;
import java.util.List;

public interface BookService {
  //dao와 연동
  //CRUD
  BookDto read(int code);

  List<BookDto> listAll();

  List<BookDto> searchListAll(String criteria, String keyword);

  boolean create(BookDto insertDto);

  boolean update(BookDto insertDto);

  boolean delete(int code);

  //member
  MemberDto login(MemberDto loginDto);

  boolean change(ChangeDto changeDto);

  boolean register(MemberDto insertDto);

  // boolean leave(String password);

  boolean leave(MemberDto delDto);
}
