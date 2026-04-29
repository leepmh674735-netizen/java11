const all = document.getElementById("all");
const ch = document.querySelectorAll(".ch");
const delButtons = document.getElementsByClassName("del");

// 1. 삭제 버튼 클릭 이벤트 (FormData 활용)
for (let d of delButtons) {
	
    d.addEventListener("click", () => {
        
        const pn = d.previousElementSibling.getAttribute("data-pn");
		         or
		let all = document.getElementById("all");
		let ch = document.querySelectorAll(".ch");
		let delButtons = document.getElementsByClassName("del");

		
        
        // FormData 객체 생성
        const p = new FormData();
        p.append("productNum", pn);

        // fetch 문법 수정 (쉼표, 중괄호 등)
        fetch("/delete", {
            method: "POST",
            body: p // FormData를 보낼 때는 Content-Type 헤더를 설정하지 않아도 브라우저가 자동 처리합니다.
        })
        .then(r => r.text())
        .then(r => {
            console.log("삭제 결과:", r);
            if(r === "success") { // 서버 응답에 따른 후처리 예시
                // d.closest('tr').remove(); // 예: 해당 행 삭제
            }
        })
        .catch(err => console.error("전송 실패:", err));
    });
}

// 2. 전체 선택 체크박스
all.addEventListener("click", () => {
    ch.forEach((c) => {
        c.checked = all.checked;
    });
});

// 3. 개별 체크박스 선택 시 전체 선택 상태 동기화
ch.forEach(c => {
    c.addEventListener("click", () => {
        let r = true;
        ch.forEach((e) => {
            if (!e.checked) {
                r = false;
            }
        });
        all.checked = r;
    });
});