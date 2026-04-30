

const review_list = document.getElementById("review_list");
const review_contents = document.getElementById("review_contents")
const review_add = document.getElementById("review_add")
const update_btn = document.getElementById("update_btn");
const contents_update = document.getElementById("contents_update");
const review_update_num = document.getElementById("review_update_num")

const product_num = review_list.getAttribute("data-product-num");



getList(product_num);

//수정
update_btn.addEventListener("click", () => {

    let p = new URLSearchParams()
    p.append("reviewContents", contents_update.value);
    p.append("reviewNum", review_update_num.value);
    fetch("/review/update", {
        method: "POST",
        body: p
    })
        .then(r => r.text())
        .then(r => {
            r = r.trim()
            if (r > 0) {
                alert("수정 성공")
                getList(product_num)
                contents_update.innerText = "";
                //document.getElementById("close").click();
            } else {
                alert("수정 실패");
            }
        })
})

//삭제
review_list.addEventListener("click", (e) => {
    if (e.target.classList.contains("review_delete")) {
        let n = e.target.parentElement.getAttribute("data-review-num");
        let p = new URLSearchParams();
        p.append("reviewNum", n)

        fetch("/review/delete", {
            method: "POST",
            body: p
        })
            .then(r => r.text())
            .then(r => {
                r = r.trim();
                if (r > 0) {
                    alert('삭제 성공')
                    getList(product_num)
                } else {
                    alert('삭제 실패')
                }
            })
    }

    if (e.target.classList.contains("review_update")) {
        let c = e.target.parentElement.parentElement.firstElementChild.innerText;
        document.getElementById("contents_update").innerText = c;
        c = e.target.parentElement.getAttribute("data-review-num")
        review_update_num.value = c;
    }
})


//추가
review_add.addEventListener("click", () => {
    let p = new FormData();
    p.append("reviewContents", review_contents.value)
    p.append("productNum", product_num);
    p.append("reviewStar", 4);

    fetch("../review/create", {
        method: "POST",
        body: p
    })
        .then(r => r.text())
        .then(r => {
            r = r.trim();
            if (r > 0) {
                alert("등록 성공")
                getList(product_num);
            } else {
                alert("등록 실패")
            }

            review_contents.value = ""
            review_contents.innerText = ""
        })
})

function getList(num) {

    fetch(`/review/list?productNum=${num}`)
        .then(r => r.text())
        .then(r => {
            r = r.trim();
            review_list.innerHTML = r;
        })

}