let all = document.getElementById("all");
let ch = document.querySelectorAll(".ch");
let delButtons = document.getElementsByClassName("del");
let selectDel = document.getElementById("selectdel"); // 오타 수정: '를 "로 변경

// 1. 선택 삭제 버튼 (체크된 항목들 한꺼번에 삭제)
selectDel.addEventListener("click", () => {
    let p = new URLSearchParams();
    let count = 0;

    ch.forEach((c) => {
        if (c.checked) {
            // HTML에 data-pn="값" 형태로 저장되어 있어야 합니다.
            p.append("productNum", c.getAttribute("data-pn"));
            count++;
        }
    });

    if (count === 0) {
        alert("삭제할 항목을 선택해주세요.");
        return;
    }

    if (!confirm(count + "개의 항목을 삭제하시겠습니까?")) return;

    fetch("/deleteMany", { // 선택 삭제를 위한 별도 경로 권장
        method: "POST",
        body: p
    })
    .then(r => r.text())
    .then(data => {
        if(data.trim() === "success") location.reload();
    })
    .catch(err => console.error("선택 삭제 에러:", err));
});

// 2. 개별 삭제 버튼 (옆에 붙은 버튼 클릭 시 하나만 삭제)
for (let d of delButtons) {
    d.addEventListener("click", () => {
        // 버튼 바로 이전 요소의 data-pn 값을 가져옴
        const pn = d.previousElementSibling.getAttribute("data-pn");

        if (!confirm("정말 삭제하시겠습니까?")) return;

        const p = new FormData();
        p.append("productNum", pn);

        fetch("/delete", {
            method: "POST",
            body: p
        })
        .then(r => r.text())
        .then(data => {
            console.log("삭제 결과:", data);
            if(data.trim() === "success") location.reload();
        })
        .catch(err => console.error("개별 삭제 에러:", err));
    });
}

// 3. 전체 선택 체크박스 로직
all.addEventListener("click", () => {
    ch.forEach((c) => {
        c.checked = all.checked;
    });
});

// 4. 개별 체크박스 선택 시 전체 선택 상태 동기화
ch.forEach(c => {
    c.addEventListener("click", () => {
        let isAllChecked = Array.from(ch).every(e => e.checked);
        all.checked = isAllChecked;
    });
});