const verifyBtn = document.getElementById("verify-next");
const alertBox = document.getElementById("alertify-o");
const okBtn = document.getElementById("alertify-o-ok");

verifyBtn.addEventListener("click", () => {
    alertBox.classList.add("active");
});

okBtn.addEventListener("click", () => {
    alertBox.classList.remove("active");
});


const step1 = document.getElementById("verify-step1");
const step2 = document.getElementById("verify-step2");

verifyBtn.addEventListener("click", () => {
    step1.style.display = "none";   
    step2.style.display = "block";  
});
