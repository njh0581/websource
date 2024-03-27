// submit 발생시
// submit 기능 중지
// code, title, writer, price 비어 있는지 확인
// code : 4자리 인지 확인
//값이 존재한다면 submit

document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();

  const criteria = document.querySelector("#criteria");
  const keyward = document.querySelector("#keyword");

  if (criteria.value == "검색 조건 선택") {
    alert("검색 조건을 선택해 주세요.");
    criteria.focus();
    return;
  } else if (!keyword.value) {
    alert("검색어를 확인해 주세요.");
    keyward.focus();
    return;
  }
  e.target.submit();
});
