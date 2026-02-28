const banner = document.querySelector("div.RollingBannerCard_bannerWrapper");
const firstBanner = document.createElement("a");
const lastBanner = document.createElement("a");
const buttonDots = document.querySelectorAll("div.RollingBannerCard_navigationDot");

let count = 1;
let dotButtons = buttonDots[0];

firstBanner.className = "RollingBannerCard_banner RollingBannerCard_rollingFirst";
lastBanner.className = "RollingBannerCard_banner RollingBannerCard_hide";

firstBanner.innerHTML = `
    <div class="RollingBannerCard_contentWrapper RollingBannerCard_contentWrapperWithImg">
        <div class="RollingBannerCard_subCopy">글로벌 서비스 오픈</div>
        <div class="RollingBannerCard_mainCopy">라이라이차차차</div>
    </div>
    <img src="/static/image/User Profile/39984391-earth.jpg" loading="lazy"
        style="width: 48px; height: 48px; margin-top: auto; margin-bottom: auto; margin-left: 8px;">`;

lastBanner.innerHTML = `
    <div class="RollingBannerCard_contentWrapper RollingBannerCard_contentWrapperWithImg">
        <div class="RollingBannerCard_subCopy">ㅡㅡㅡㅡ</div>
        <div class="RollingBannerCard_mainCopy">ㅡㅡㅡㅡㅡ</div>
    </div>
    <img src="/static/image/User Profile/178407772-electric-plug-icon-vector-illustration-eps-10.jpg" loading="lazy"
        style="width: 48px; height: 48px; margin-top: auto; margin-bottom: auto; margin-left: 8px;">`;

banner.appendChild(firstBanner);
banner.prepend(lastBanner);



banner.style.transform = `translateX(-370px)`;

setInterval(() => {
    count++;
    banner.style.transform = `translateX(-${370 * count}px)`;
    banner.style.transition = `transform 0.5s`;
    if (count === 4) {
        setTimeout(() => {
            banner.style.transition = `0s`;
            banner.style.transform = `translateX(-370px)`;
        }, 500);
        count = 1;
    }
    buttonDots.forEach(dot => dot.classList.remove("RollingBannerCard_currentIndex"));
    buttonDots[count - 1].classList.add("RollingBannerCard_currentIndex");


    console.log(0);
}, 2000);

// const WishIconButtons = document.querySelectorAll("button[type=cbutton]");

// document.addEventListener("click", (e) => {
//     const button = e.target.closest(".WishIconButton_container");

//     with (button) {
//         e.preventDefault();
//         e.stopPropagation();
//         svg = button.querySelector("svg");
//         svg && svg.classList.toggle("WishIconButton_isActive");
//     }
// });


// 북마크 등록
// const buttonBookMark = document.querySelector(
//     ".btnBookmark.qnaSpB.devQnaDetailBookmark",
// );
// const bookMarkLayer = document.querySelector(
//     ".book-mark-layer.tooltip-layer.qnaSpA",
// );

// buttonBookMark.addEventListener("click", (e) => {
//     if (!buttonBookMark.classList.contains("on")) {
//         bookMarkLayer.style.opacity = "1";
//         setTimeout(() => {
//             bookMarkLayer.style.opacity = "0";
//         }, 975);
//     } else {
//         bookMarkLayer.style.opacity = "0";
//     }
//     buttonBookMark.classList.toggle("on");
// });

//          토스트창
const wishButtons = document.querySelectorAll(".WishIconButton_container");
const toastAdd = document.querySelector(".Toast_container.toast-add");
const toastRemove = document.querySelector(".Toast_container.toast-remove");

function showToast(toast) {
    toast.classList.add("show");
    setTimeout(() => {
        toast.classList.remove("show");
    }, 2000);
}

wishButtons.forEach(button => {
    button.addEventListener("click", (e) => {
        e.preventDefault();
        const svg = button.querySelector("svg");
        const isActive = svg.classList.toggle("WishIconButton_isActive");

        showToast(isActive ? toastAdd : toastRemove);
    });
});

            // 모달
const closes = document.querySelectorAll(".button_close");
const modal = document.querySelector(".ReactModalPortal");
const wishOpens = document.querySelectorAll(".wish_modalOpen");

wishOpens.forEach(wishOpen => {
    wishOpen.addEventListener("click", (e) => {
        e.preventDefault();
        modal.style.display = "flex";
    })
})

closes.forEach(close => {
    close.addEventListener("click", (e) => {
        modal.style.display = "none";
    })
})


const notificationBadges = document.querySelectorAll(".NotificationBadge_badge");

notificationBadges.forEach(notificationBadge => {
    notificationBadge.addEventListener("click", (e) => {
        e.preventDefault(); 
        notificationBadge.remove();      
    });
});


// 찜목록 색빼기
const wishButtonremoves = document.querySelectorAll(".WishButton_button");
const svgAdd = document.querySelector(".Toast_container.toast-add");
const svgRemove = document.querySelector(".Toast_container.toast-remove");


wishButtonremoves.forEach(button => {
    button.addEventListener("click", () => {
        const path = button.querySelector("svg path");

        if (path === "#f66") {
            path.style.setProperty("fill", "#f2125296666", "important"); 
        } else {
              path.style.setProperty("fill","transparent");
        }
    });
});

function showwish(toast) {
    toast.classList.add("show");
    setTimeout(() => {
        toast.classList.remove("show");
    }, 2000);
}

wishButtonremoves.forEach(button => {
    button.addEventListener("click", (e) => {
        e.preventDefault();
        const svg = button.querySelector("svg");
        const isActive = svg.classList.toggle("WishIconButton_isActive");

        showwish(isActive ? svgRemove : add);
    });
});

const score =89;  

const batteryWrapper = document.querySelector(".MyGGshSupporterProfileCard_profileUser .battery-wrapper");
if (batteryWrapper) {
    const batteries = batteryWrapper.querySelectorAll("i");

    // 숨기기
    batteries.forEach(battery => battery.style.display = "none");

    // 점수 계산 및 색 지정
    let lengs = 0;
    let color = "";

    if (score >= 0 && score < 20) {
        lengs = 0;
        color = "red";
    } else if (score >= 20 && score < 40) {
        lengs = 1;
        color = "#FFC53D";
    } else if (score >= 40 && score < 60) {
        lengs = 2;
        color = "#E5E53D";
    } else if (score >= 60 && score < 80) {
        lengs = 3;
        color = "#B9EB45";
    } else if (score >= 80 && score <= 100) {
        lengs = 4;
        color = "#52C41A";
    }

    // 해당 아이콘만 보이게 하고 색 적용
    batteries[lengs].style.display = "inline-block";
    batteries[lengs].style.color = color;
}
