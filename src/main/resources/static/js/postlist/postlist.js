const totalButton = document.querySelector(".RecentViewContents_editButton");
const controlPanel = document.querySelector(".RecentViewContents_controlPanel");
const overlayPanel = document.querySelector(".opbt");
const overlays = document.querySelectorAll(".Card_overlay");
const closeButton = document.querySelector(".closebutton");
const allButton = document.querySelector(".RecentViewContents_icon");
const deleteButton = document.querySelector(".deleteButton");
const cardButtons = document.querySelectorAll(".RecentEditCard_content");

const countDisplay = document.querySelector(".RecentViewContents_flex .RecentViewContents_bold");

//  선택 개수 
function updateCount() {
    const selected = document.querySelectorAll(".RecentEditCard_thumbnail.RecentEditCard_active");
    countDisplay.textContent = selected.length;
}

//  삭제 버튼 활성화/비활성화 
function updateDeleteButton() {
    const selected = document.querySelectorAll(".RecentEditCard_thumbnail.RecentEditCard_active");
    if (selected.length > 0) {
        deleteButton.style.opacity = "1";
    } else {
        deleteButton.style.opacity = "0.4";
    }
}

// 전체 선택/해제 
function updateAllButton() {
    const allBtnIcon = `<svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" aria-hidden="true"> 
        <path fill-rule="evenodd" clip-rule="evenodd" d="M12 1.4C6.146 1.4 1.4 6.146 1.4 12c0 5.854 4.746 10.6 10.6 10.6 5.854 0 10.6-4.746 10.6-10.6 0-5.854-4.746-10.6-10.6-10.6zm-1.5 15.84l8.495-8.495-.99-.99L10.5 15.26l-3.505-3.505-.99.99L10.5 17.24z" fill="#00c4c4"></path> 
    </svg>`;
    const defaultBtn = `<div class="RecentViewContents_iconBg" aria-hidden="true"></div>`;

    if (allButton.classList.contains("active")) {
        allButton.innerHTML = allBtnIcon;
    } else {
        allButton.innerHTML = defaultBtn;
    }
}


totalButton.addEventListener("click", () => {
    overlayPanel.style.display = "flex";
    controlPanel.style.display = "none";
    overlays.forEach(o => o.style.display = "block");
});

closeButton.addEventListener("click", () => {
    overlayPanel.style.display = "none";
    controlPanel.style.display = "flex";

    // 전체 선택 초기화
    allButton.classList.remove("active");
    updateAllButton();

    // 카드 선택 초기화
    cardButtons.forEach(btn => {
        btn.querySelector(".RecentEditCard_thumbnail").classList.remove("RecentEditCard_active");
    });

    overlays.forEach(o => o.style.display = "none");
    updateCount();
    updateDeleteButton();
});

// 카드 개별 선택
cardButtons.forEach(button => {
    button.addEventListener("click", () => {
        const thumbnail = button.querySelector(".RecentEditCard_thumbnail");
        thumbnail.classList.toggle("RecentEditCard_active");

        // 선택 개수 및 삭제 버튼 업데이트
        updateCount();
        updateDeleteButton();

        // 전체 선택 버튼 상태 업데이트
        const selectedCount = document.querySelectorAll(".RecentEditCard_thumbnail.RecentEditCard_active").length;
        if (selectedCount === cardButtons.length) {
            allButton.classList.add("active");
        } else {
            allButton.classList.remove("active");
        }
        updateAllButton();
    });
});

// 전체 선택/해제
allButton.addEventListener("click", () => {
    allButton.classList.toggle("active");
    const selectAll = allButton.classList.contains("active");

    cardButtons.forEach(button => {
        const thumbnail = button.querySelector(".RecentEditCard_thumbnail");
        if (selectAll) {
            thumbnail.classList.add("RecentEditCard_active");
        } else {
            thumbnail.classList.remove("RecentEditCard_active");
        }
    });

    updateAllButton();
    updateCount();
    updateDeleteButton();
});

//  카드 삭제
deleteButton.addEventListener("click", () => {
    const selected = document.querySelectorAll(".RecentEditCard_thumbnail.RecentEditCard_active");

    if (selected.length === 0) {
        alert("삭제할 항목을 선택하세요");
        return;
    }

    selected.forEach(thumbnail => {
        const card = thumbnail.closest(".Card_container");
        if (card) card.remove();
    });

    // 삭제 후 업데이트
    updateCount();
    updateDeleteButton();
    // 전체 선택 초기화
    allButton.classList.remove("active");
    updateAllButton();
});
