document.addEventListener("DOMContentLoaded", () => {
    // ===== 팔로우 버튼 =====
    const followButtons = document.querySelectorAll(".FollowingCard-module_right button");

    followButtons.forEach((btn) => {
        const textSpan = btn.querySelector("span span"); // 또는 "span:last-child"
        const id = btn.dataset.id; // HTML에서 <button data-id="123"> 필요

        // 저장된 상태 불러오기
        const saved = localStorage.getItem("follow_" + id);
        if (saved === "following") {
            textSpan.textContent = "팔로잉";
            btn.classList.remove("Button-module_mint");
            btn.classList.add("Button-module_gray");
        } else {
            textSpan.textContent = "팔로우";
            btn.classList.remove("Button-module_gray");
            btn.classList.add("Button-module_mint");
        }

        // 클릭 이벤트
        btn.addEventListener("click", () => {
            if (textSpan.textContent === "팔로잉") {
                textSpan.textContent = "팔로우";
                btn.classList.remove("Button-module_gray");
                btn.classList.add("Button-module_mint");
                localStorage.setItem("follow_" + id, "unfollow");
            } else {
                textSpan.textContent = "팔로잉";
                btn.classList.remove("Button-module_mint");
                btn.classList.add("Button-module_gray");
                localStorage.setItem("follow_" + id, "following");
            }
        });
    });

    // ===== 탭 토글 =====
    const followingTab = document.querySelector('[data-value="1"]');
    const followerTab = document.querySelector('[data-value="3"]');
    const followingPanel = document.querySelector(".FollowingSupporter-module_container");
    const followerPanel = document.querySelector(".FollowerSupporter-module_container");

    if (followingTab && followerTab && followingPanel && followerPanel) {
        followerTab.addEventListener("click", () => {
            followingPanel.style.display = "none";
            followerPanel.style.display = "block";
            followingTab.classList.remove("Tab-module_active");
            followerTab.classList.add("Tab-module_active");
        });

        followingTab.addEventListener("click", () => {
            followerPanel.style.display = "none";
            followingPanel.style.display = "block";
            followerTab.classList.remove("Tab-module_active");
            followingTab.classList.add("Tab-module_active");
        });
    }
});

console.log("followingPanel:", document.querySelector(".FollowingSupporter-module_container"));
console.log("followerPanel:", document.querySelector(".FollowerSupporter-module_container"));

// 혹은 이렇게도 확인
console.log("모든 div:", document.querySelectorAll("div[class*='Supporter']"));