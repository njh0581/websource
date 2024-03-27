<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "dao.TodoDao"%>
<%@ page import = "dto.TodoDto"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%
  // 한글 처리
  request.setCharacterEncoding("utf-8");

  // no 가져오기
  String no = request.getParameter("no");

  // DB 작업
  TodoDao dao = new TodoDao();
  int result = dao.delete(no);

  // 화면 이동(read)
  response.sendRedirect("list.jsp");
%>