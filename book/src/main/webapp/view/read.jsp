<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/include/header.jsp"%>
<h3 class="border-bottom mb-3">도서상세보기</h3>
<form action="" method="post">
<div class="mb-3">
  <label for="code" class="form-label">code</label>
  <input type="text" class="form-control" id="code" value="${dto.code}" readonly name="code">
</div>
<div class="mb-3">
  <label for="title" class="form-label">title</label>
  <input type="text" class="form-control" id="title" value="${dto.title}" readonly name="title">
</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" class="form-control" id="writer" value="${dto.writer}" readonly name="writer">
</div>
<div class="mb-3">
  <label for="price" class="form-label">price</label>
  <input type="text" class="form-control" id="price" value="${dto.price}" readonly name="price">
</div>
<div class="mb-3">
  <label for="description" class="form-label">description</label>
  <textarea class="form-control" id="description" rows="3" name="description" readonly>${dto.description}</textarea>
</div>
<div>
<a class="btn btn-primary" href='<c:url value="/list"/>'>목록</a>
</div>
</form>
<%@ include file="/include/section.jsp"%>
<%@ include file="/include/footer.jsp"%>