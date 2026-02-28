// 메인 퀵 네비게이션 영역 이벤트
const pageTopBtn = document.querySelector(".quickButton-pageTop");

// 페이지탑 클릭 이벤트
pageTopBtn.addEventListener("click", (e) => {
    window.scrollTo({
        top: 0,
        behavior: "auto"
    });
    
})

// page 내에 이동하는 이벤트 중지
const scrollStop = () => {
    window.scrollTo({
        top: window.scrollY,
        behavior: "auto"
    });
}

let defaultScroll = 0;
window.addEventListener('scroll', function() {
    const pageTop = document.querySelector(".quickButton-pageTop");
    let thisScroll = window.scrollY || document.documentElement.scrollTop;
    console.log(document.documentElement.scrollTop);
    defaultScroll = thisScroll <= 0 ? 0 : thisScroll;
    thisScroll > defaultScroll || window.scrollY <= 0  ? pageTop.classList.remove("active") : pageTop.classList.add("active");
}, false);

window.addEventListener("wheel", scrollStop);
window.addEventListener("touchstart", scrollStop);