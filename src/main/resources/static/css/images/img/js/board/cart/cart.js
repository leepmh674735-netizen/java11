const create = document.getElementById("create");

create.addEventListener("click", () => {
    // 1. 데이터 가져오기 (data-pn 속성값)
    const pn = create.getAttribute("data-pn");
    
    // 2. 전송할 파라미터 설정 (URLSearchParams 이용)
    const p = new URLSearchParams();
    p.append("productNum", pn);

    // 3. fetch 요청 실행
    fetch("../cart/create", {
        method: "POST",
        headers: {
            // URLSearchParams를 body로 보낼 때 브라우저가 자동으로 설정해주기도 하지만, 
            // 명시적으로 설정하는 것이 안전합니다.
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: p
    })
    .then(res => res.text())
    .then(res => {
        // 4. 응답 결과 처리
        const result = res.trim();
        
        if (parseInt(result) > 0) { // 결과가 숫자인 경우를 대비해 변환 후 비교
            const moveFlag = confirm("장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?");
            if (moveFlag) {
                // 실제 페이지 이동 로직 추가
                location.href = "../cart/list"; // 장바구니 목록 주소 (프로젝트에 맞게 수정)
            }
        } else {
            alert('장바구니 등록에 실패했습니다.');
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("서버 통신 중 오류가 발생했습니다.");
    });
});