<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "dto.TodoDto"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file="../include/header.jsp" %>
<% 
  //  TodoDto todo = (TodoDto)request.getAttribute("todo");
%>
<h1 class="mt-5">Todo Modify</h1>
<form action="${pageContext.request.contextPath}/update" method="post">
<div class="mb-3">
  <label for="title" class="form-label">title</label>
  <%-- <input type="text" class="form-control" id="title" placeholder="title" name="title" value="<%=todo.getTitle()%>"> --%>
  <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${todo.title}" readonly>
</div>
<div class="mb-3">
  <label for="createdAt" class="form-label">createdAt</label>
  <%-- <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="<%=todo.getCreatedAt()%>"> --%>
  <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="${todo.createdAt}" readonly>
</div>
<div class="mb-3">
  <label for="completed" class="form-check-label">completed</label>
  <%-- completed 가 true 면 check 표시 --%>
  <input type="checkbox" class="form-check-input" id="completed" placeholder="completed" name="completed" value="true" <c:out value="${todo.completed?'checked':''}"/> >
</div>
<div class="mb-3">
  <label for="description" class="form-label">description</label>
  <%-- <textarea class="form-control" id="description" rows="3" name="description"><%=todo.getDescription()%></textarea> --%>
  <textarea class="form-control" id="description" rows="3" name="description">${todo.description}</textarea>
</div>
<div>
   <button class="btn btn-primary" type="submit">수정</button>
   <a class="btn btn-danger" href='<c:url value="/delete?no=${todo.no}"/>'>삭제</a>
   <a class="btn btn-success" href='<c:url value="/view/list"/>'>목록</a>
</div>
<input type="hidden" name="no" value="${todo.no}">
</form>
<%@ include file="../include/footer.jsp" %>