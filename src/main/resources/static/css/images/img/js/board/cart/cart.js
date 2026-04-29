const create = document.getElementById("create");


create.addEventListener("click", ()=>{
    let pn = create.getAttribute("data-pn")
    
    let p = new URLSearchParams();
    p.append("productNum", pn);


    fetch("../cart/create", {
        method:"POST",
        body:p
    })
    .then(r => r.text())
    .then(r =>{
        //등록성공이면 장바구니로 이동? 
        r = r.trim();
        if(r>0){
           let flag = confirm("장바니구로 이동?")
           if(flag){
                alert("이동")
           }
        }else {
            alert('장바구니 등록 실패')
        }

    })
    ;
})