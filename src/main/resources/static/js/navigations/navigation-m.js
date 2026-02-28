// 메인 하단 네비게이션 영역 이벤트
const FNBNavigations = document.querySelectorAll(".NavigationTab-container");


FNBNavigations.forEach((tab) => {
    tab.addEventListener("click", (e) => {
        FNBNavigations.forEach(item => item.classList.remove("active"));
        e.currentTarget.classList.add("active");
        const badge = tab.querySelector(".NotificationBadge");
        console.log(badge.classList);
        if(badge) badge.classList.remove("active");
    });
});


