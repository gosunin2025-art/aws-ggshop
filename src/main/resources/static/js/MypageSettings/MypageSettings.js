// 전역 변수 선언
const body = document.querySelector("body");
const overlay = document.querySelector(".ConfirmModal_overlay");
const modals = document.querySelectorAll(".ConfirmModal_confirmModal");
const passwordModal = document.getElementById("passwordModal");
const modal7 = document.getElementById("modal7");

console.log("passwordModal:", passwordModal); // 디버깅

// 각 모달 선택
const nicknameModal = modals[0];
const emailModal = modals[1];
const privateModal = modals[2];

// 메뉴 아이템 클릭 (닉네임, 이메일, 지역) - settings_menusArea 안에 있는 것만
const menuItems = document.querySelectorAll(".settings_menusArea .menuItem_container");
console.log("menuItems:", menuItems.length); // 디버깅

menuItems.forEach(item => {
    const title = item.querySelector(".menuItem_title").textContent.trim();

    item.addEventListener("click", () => {
        console.log("Menu clicked:", title); // 디버깅
        modals.forEach(m => m.style.display = "none");
        overlay.style.display = "flex";

        if (title === "닉네임") {
            nicknameModal.style.display = "flex";
        }
        else if (title === "이메일") {
            emailModal.style.display = "flex";
        }
    });
});

// ConfirmModal 닫기 버튼
const closeButtons = document.querySelectorAll(".button_close");
closeButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        overlay.style.display = "none";
        modals.forEach(m => m.style.display = "none");
        modal7.style.display = "none";
    });
});



// 이메일 확인 버튼 클릭 시 비밀번호 확인 모달 열기
const emailOkaymodal = emailModal.querySelector(".buttonEmail_okay");
emailOkaymodal.addEventListener("click", () => {
    emailModal.style.display = "none";
    privateModal.style.display = "flex";
});

// 비밀번호 설정 버튼 - settings_settingsList 내부에서만 찾기
// const settingsLists = document.querySelectorAll(".settings_settingsList");
// settingsLists.forEach(settingsList =>{
//     console.log("settingsLists:", settingsList); // 디버깅

//         settingsList.addEventListener("click",()=>{
//             document.getElementById("passwordModal").style.display ="flex";
//         })

// })

const modalOpen = (thisModal) => {
    const OpenModalName = document.getElementById(thisModal);
    body.classList.add('hidden');
    OpenModalName.classList.add('active');
};


    // const settingItems = settingsList.querySelectorAll(".settingItem_container1");
    // console.log("settingItems:", settingItems.length); // 디버깅
    
    // settingItems.forEach((item, index) => {
    //     const titleElement = item.querySelector(".settingItem_title");
    //     console.log(`settingItem ${index}:`, titleElement?.textContent.trim()); // 디버깅
        
    //     if (titleElement && titleElement.textContent.trim() === "비밀번호 설정") {
    //         console.log("비밀번호 설정 버튼 찾음!"); // 디버깅
            
    //         item.addEventListener("click", (e) => {
    //             console.log("비밀번호 설정 클릭됨!"); // 디버깅
    //             e.preventDefault();
    //             e.stopPropagation();
                
    //             if (passwordModal) {
    //                 body.style.overflow = "hidden";
    //                 passwordModal.style.display = "flex";
    //                 console.log("모달 열림"); // 디버깅
    //             } else {
    //                 console.log("passwordModal이 없습니다!"); // 디버깅
    //             }
    //         });
    //     }
    // });

// 비밀번호 모달 닫기
if (passwordModal) {
    const passwordCloseBtn = passwordModal.querySelector(".button-close");
    console.log("passwordCloseBtn:", passwordCloseBtn); // 디버깅
    
    if (passwordCloseBtn) {
        passwordCloseBtn.addEventListener("click", () => {
            console.log("비밀번호 모달 닫기 클릭"); // 디버깅
            body.style.overflow = "";
            passwordModal.style.display = "none";
        });
    }

    // 모달 외부 클릭 시 닫기
    passwordModal.addEventListener("click", (e) => {
        if (e.target === passwordModal) {
            body.style.overflow = "";
            passwordModal.style.display = "none";
        }
    });
}

// input 필드 포커스 효과 및 비밀번호 보기/숨기기
const inputFields = document.querySelectorAll(".inputField");
inputFields.forEach(inputField => {
    const input = inputField.querySelector(".input");
    const button = inputField.querySelector(".button-inInput");

    inputField.addEventListener("focusin", () => {
        inputField.classList.add("focus");
    });
    
    inputField.addEventListener("focusout", () => {
        inputField.classList.remove("focus");
    });
    
    if (button) {
        button.addEventListener("mousedown", (e) => { 
            e.preventDefault(); 
            const isPassword = input.type === "password";
            input.type = isPassword ? "text" : "password";
        });
    }
});

// 닉네임 변경 완료 시 토스트 메시지
document.addEventListener('click', (e) => {
    if (e.target.closest('.ButtonNickName')) {
        const toast = document.querySelector('.Toast_container');
        if (!toast) return;

        toast.classList.add('Toast_showMobile');

        setTimeout(() => {
            toast.classList.remove('Toast_showMobile');
        }, 3000);
    }
});


document.querySelectorAll('.toggle_input').forEach(toggle => {
    toggle.addEventListener('change', () => {
        if (toggle.checked) {
            console.log(`${toggle.id} ON 상태`);
        } else {
            console.log(`${toggle.id} OFF 상태`);
        }
    });
});



// 메뉴 아이템들 선택
const menuItems2 = document.querySelectorAll(".settings_menusArea .menuItem_container");

menuItems2.forEach(item => {
    const title = item.querySelector(".menuItem_title").textContent.trim();

    item.addEventListener("click", () => {
        console.log("Menu clicked:", title);

        // 기존 모달 숨기기
        modals.forEach(m => m.style.display = "none");
        overlay.style.display = "flex";

        if (title === "닉네임") {
            nicknameModal.style.display = "flex";
        } else if (title === "이메일") {
            emailModal.style.display = "flex";
        } else if (title === "지역") { 
            if (modal7) {
                modal7.style.display = "flex";
            }
        }
    });
});
//  토글 버튼 선택
const toggle = document.querySelector('.toggle_input');
const label = document.querySelector('.toggle_switch'); // 토글 레이블

toggle.addEventListener("click", () => {
    if (toggle.checked) {
        // 체크되어 있으면 ON 상태
        label.classList.add('toggle_active');
    } else {
        label.classList.remove('toggle_active');
    }
});
document.addEventListener("DOMContentLoaded", () => {
  const editBtn = document.querySelector(".profileEdit");
  const fileInput = document.getElementById("profileInput");
  const profileImage = document.getElementById("profileImage");

  if (!editBtn || !fileInput || !profileImage) return;

  /* 사진 변경 버튼 클릭 */
  editBtn.addEventListener("click", () => {
    fileInput.click();
  });

  /* 파일 선택 시 */
  fileInput.addEventListener("change", (e) => {
    const file = e.target.files[0];
    if (!file) return;

    if (!file.type.match(/^image\/(png|jpeg|jpg)$/)) {
      alert("이미지 파일만 가능합니다");
      fileInput.value = "";
      return;
    }

    const reader = new FileReader();
    reader.onload = (ev) => {
      // ⭐ 즉시 프로필 이미지 변경
      profileImage.src = ev.target.result;
    };

    reader.readAsDataURL(file);
    fileInput.value = "";
  });
});
