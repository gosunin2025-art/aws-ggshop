// ===== 컨텍스트 메뉴 =====
const menu = document.querySelector(".ContextMenu_container");
const backbodal = document.getElementById("backbodal");

if (backbodal) {
    backbodal.addEventListener("click", () => {
        backbodal.style.display = "none";
        if (menu) menu.style.display = "none";
    });
}

document.querySelectorAll(".SupporterProfileContextMenu_contextMenuButton").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.stopPropagation();
        if (menu) {
            menu.style.display = menu.style.display === "block" ? "none" : "block";
        }
    });
});

if (menu) {
    menu.addEventListener("click", (e) => {
        e.stopPropagation();
    });
}

document.body.addEventListener("click", () => {
    if (menu && menu.style.display === "block") {
        menu.style.display = "none";
    }
});

// ===== 프로필 팔로우 버튼 =====
const followBtn = document.querySelector(".SupporterProfileInfoCTAButton_button");

if (followBtn) {
    followBtn.addEventListener("click", () => {
        const text = followBtn.querySelector("span span");

        if (text) {
            if (text.textContent === "팔로우") {
                text.textContent = "팔로잉";
                followBtn.classList.remove("Button_mint");
                followBtn.classList.add("Button_gray");
            } else {
                text.textContent = "팔로우";
                followBtn.classList.remove("Button_gray");
                followBtn.classList.add("Button_mint");
            }
        }
    });
}

// ===== 차단 기능 =====
const blockBtn = document.querySelector(".blockButton");
const mainBtn = document.getElementById("followBtn");
const contextBtn = document.querySelector(".ContextMenubt");

const defaultState = {
    mainHTML: mainBtn ? mainBtn.innerHTML : "",
    mainClass: mainBtn ? mainBtn.className : "",
    contextHTML: contextBtn ? contextBtn.innerHTML : "",
    contextClass: contextBtn ? contextBtn.className : ""
};

function toggleBlock() {
    if (!mainBtn) return;

    const toastContainer = document.querySelector(".Toast_container");
    const toast1 = document.getElementById("toast1");
    const toast2 = document.getElementById("toast2");

    const isBlocked = mainBtn.textContent.includes("차단 해제");

    // 토스트 표시
    if (toastContainer) {
        toastContainer.querySelectorAll(".Toast_content").forEach(t => t.style.display = "none");

        if (!isBlocked) {
            if (toast1) toast1.style.display = "block";
        } else {
            if (toast2) toast2.style.display = "block";
        }

        toastContainer.classList.add('Toast_showMobile');
        setTimeout(() => {
            toastContainer.classList.remove('Toast_showMobile');
        }, 2000);
    }

    // 버튼 상태 변경
    if (!isBlocked) {
        if (mainBtn) {
            mainBtn.innerHTML = '<span><span>차단 해제하기</span></span>';
            mainBtn.className = "Button_button Button_contained Button_md SupporterProfileInfoCTAButton_button";
        }

        if (contextBtn) {
            contextBtn.innerHTML = '<span><span>차단 해제하기</span></span>';
            contextBtn.className = "Button_button Button_contained Button_md Button_block SupporterProfileInfoCTAButton_button";
        }
    } else {
        if (mainBtn) {
            mainBtn.innerHTML = defaultState.mainHTML;
            mainBtn.className = defaultState.mainClass;
        }

        if (contextBtn) {
            contextBtn.innerHTML = defaultState.contextHTML;
            contextBtn.className = defaultState.contextClass;
        }
    }
}

if (blockBtn) {
    blockBtn.addEventListener("click", (e) => {
        e.preventDefault();

        document.querySelectorAll(".ConfirmModal_overlay").forEach(modal => {
            modal.style.display = "none";
        });

        toggleBlock();
    });
}

if (mainBtn) {
    mainBtn.addEventListener("click", () => {
        if (mainBtn.textContent.includes("차단 해제")) {
            toggleBlock();
        }
    });
}

if (contextBtn) {
    contextBtn.addEventListener("click", () => {
        if (contextBtn.textContent.includes("차단 해제")) {
            toggleBlock();
        }
    });
}

// ===== 신고 기능 (버그 수정!) =====

// 차단하기 버튼 - 모달1 열기
const contextMenubtBtn = document.querySelector(".ContextMenubt");
if (contextMenubtBtn) {
    contextMenubtBtn.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();
        
        // is="modal1" 속성으로 선택
        const modal1 = document.querySelector('[is="modal1"]');
        
        if (modal1) {
            modal1.classList.add("modal-open");
            modal1.style.display = "flex";
        }
    });
}

// 컨텍스트 메뉴 신고하기 - 모달2 열기
const contextWarningBtn = document.querySelector(".ContextMenu_warning");
if (contextWarningBtn) {
    contextWarningBtn.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        // class 제어로 CSS 중복 회피
        const modal2 = document.getElementById("modal2");
        
        if (modal2) {
            modal2.classList.add("modal-open");
            modal2.style.display = "flex";
        }

        // 메뉴 닫기
        if (menu) menu.style.display = "none";
        if (backbodal) backbodal.style.display = "none";
    });
}

// ===== 팝 메뉴 신고하기 =====

// 팝 메뉴 토글
document.querySelectorAll(".PopMenu_toggle").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        const popMenu = btn.closest(".PopMenu_wrap")?.querySelector(".PopMenu_box");
        if (popMenu) {
            popMenu.style.display = popMenu.style.display === "flex" ? "none" : "flex";
        }
    });
});

// 팝 메뉴 신고 버튼
document.querySelectorAll(".PopMenu_menu").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        const modal2 = document.getElementById("modal2");
        if (modal2) {
            modal2.style.display = "flex";
        }

        // 팝 메뉴 닫기
        const popMenu = btn.closest(".PopMenu_box");
        if (popMenu) {
            popMenu.style.display = "none";
        }
    });
});

// ===== 모달 닫기 버튼 =====
document.querySelectorAll(".closebutton").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        const modal = btn.closest(".ConfirmModal_overlay");
        if (modal) {
            modal.classList.remove("modal-open");
            modal.style.display = "none";
        }
    });
});

// ===== 탭 전환 =====
document.addEventListener('DOMContentLoaded', () => {
    const tabsWrapper = document.querySelector('.Tabs_tabs');

    if (tabsWrapper) {
        const tabs = document.querySelectorAll('.Tab_tab');
        const postPanel = document.querySelector('.SupporterProfilePostt_table');
        const reviewPanel = document.querySelector('.SupporterProfileReviewList_table');

        if (postPanel) postPanel.style.display = 'grid';
        if (reviewPanel) reviewPanel.style.display = 'none';

        tabsWrapper.addEventListener('click', (e) => {
            const tab = e.target.closest('.Tab_tab');
            if (!tab) return;

            tabs.forEach(t => t.classList.remove('Tab_active'));
            tab.classList.add('Tab_active');

            const value = tab.dataset.value;

            if (value === '0') {
                if (postPanel) postPanel.style.display = 'grid';
                if (reviewPanel) reviewPanel.style.display = 'none';
            } else if (value === '1') {
                if (postPanel) postPanel.style.display = 'none';
                if (reviewPanel) reviewPanel.style.display = 'grid';
            }
        });
    }
});

// ===== 댓글 팔로우 버튼 & 토스트 중앙 정렬 =====
document.querySelectorAll(".Buttonremove").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();
        e.currentTarget.remove();
        const toastContainer = document.querySelector(".Toast_container");
        const toast3 = document.getElementById("toast3");

        if (toastContainer) {
            toastContainer.querySelectorAll(".Toast_content").forEach(t => t.style.display = "none");

            if (toast3) {
                toast3.style.display = "block";
            }

            toastContainer.classList.add('Toast_showMobile');
            setTimeout(() => {
                toastContainer.classList.remove('Toast_showMobile');
            }, 3000);
        }

        document.querySelectorAll(".ConfirmModal_overlay").forEach(modal => {
            modal.style.display = "none";
        });
    });
});

// ===== 신뢰도 배터리 =====
const score = 60;

const batteryWrapper = document.querySelector(".SupporterProfileInfo_supporterInfo .battery-wrapper");
if (batteryWrapper) {
    const batteries = batteryWrapper.querySelectorAll("i");

    if (batteries.length > 0) {
        // 모든 배터리 숨기기
        batteries.forEach(battery => {
            battery.style.display = "none";
        });

        // 점수에 따른 배터리 표시
        let level = 0;
        let color = "";

        if (score >= 0 && score < 20) {
            level = 0;
            color = "#d9534f";
        } else if (score >= 20 && score < 40) {
            level = 1;
            color = "#f0ad4e";
        } else if (score >= 40 && score < 60) {
            level = 2;
            color = "#ffc53d";
        } else if (score >= 60 && score < 80) {
            level = 3;
            color = "#73d13d";
        } else if (score >= 80 && score <= 100) {
            level = 4;
            color = "#52c41a";
        }

        // 해당 배터리만 표시
        if (batteries[level]) {
            batteries[level].style.display = "inline-block";
            batteries[level].style.color = color;
        }
    }
}