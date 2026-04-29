
const add = document.getElementById("add");
const result = document.getElementById("result");
const create = document.getElementById("create");

// create.addEventListener("click", ()=>{
//     let form = new FormData(); //<form></form>
//     form.append("boardTitle", "ajax title");//<input type="text">
//     form.append("boardWriter", "ajax writer");//
//     form.append("boardContents", "ajax contents");

//     fetch("./create", {
//         method:"POST",
//         body:form
//     }).then(res=>res.text())
//     .then(res=>console.log(res));

// })

let count=0;

add.addEventListener("click", function(){
    if(count>4){
        alert('최대 5개 만 가능')
        return;
    }
    let d = document.createElement("div");
    let i = document.createElement("input");
    i.type="file";
    i.name="attach";
    i.classList.add("form-control")

    let b = document.createElement("button");
    b.type="button";
    b.innerText="X";
    b.classList.add("del")

    d.append(i);
    d.append(b);
    result.prepend(d);

    count++;

})

result.addEventListener("click", function(e){
    if(e.target.classList.contains("del")){
        e.target.parentElement.remove();
        count--;
    }
})