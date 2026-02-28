  //  공통 모달 함수
function openModal(id) {
  const modal = document.getElementById(id);
  if (modal) modal.style.display = "flex";
}

function closeModal(id) {
  const modal = document.getElementById(id);
  if (modal) modal.style.display = "none";
}

  //  전역 상태
let selectedStar = 0;

  //  이벤트 위임 (모든 클릭)
document.addEventListener("click", (e) => {

  // 리뷰 쓰기
  if (e.target.closest(".opreviewbtn")) {
    openModal("modal1");
  }

  // 리뷰 X → 나가기 확인
  if (e.target.closest(".clreviewbtn")) {
    openModal("modal2");
  }

  // 나가기 취소
  if (e.target.closest("#modal2 .ConfirmModal-module_negativeButton")) {
    closeModal("modal2");
  }

  // 나가기 확인
  if (e.target.closest(".okbtn")) {
    closeModal("modal1");
    closeModal("modal2");
  }

  // 등록하기 버튼 → 확인 모달
  if (e.target.closest(".Submitbutton")) {
    openModal("modal4");
  }

  // 등록 취소
  if (e.target.closest("#modal4 .closebtn")) {
    closeModal("modal4");
  }

  // 등록 확인
  if (e.target.closest(".submitbtn")) {
    closeModal("modal4");
    closeModal("modal1");
  }

  // 크롭 모달 닫기
  if (e.target.closest(".clreviewbtns")) {
    closeModal("modal3");
  }

  // 이미지 삭제
  if (e.target.classList.contains("ImageUploader-module_closeButton")) {
    const imgBox = e.target.closest(".uploaded-image");
    if (imgBox) imgBox.remove();
    updateFileCount();
  }
});

  //  별점
const stars = document.querySelectorAll(".Rating-module_star");
const ratingMessage = document.querySelector(".SatisfactionRating-module_ratingMessage");

const starMessages = [
  "별점을 선택해주세요",
  "최악이에요",
  "별로예요",
  "보통이에요",
  "좋아요",
  "최고예요!"
];

stars.forEach((star, index) => {
  star.addEventListener("click", () => {
    selectedStar = (selectedStar === index + 1) ? 0 : index + 1;

    stars.forEach((s, i) => {
      s.style.color = i < selectedStar
        ? "rgb(238, 241, 50)"
        : "rgb(233, 236, 239)";
    });

    ratingMessage.textContent = starMessages[selectedStar];
    checkSubmitButton();
  });
});

  //  댓글 + 등록 버튼
const commentBox = document.querySelector(".SatisfactionComment-module_textarea");
const charCountText = document.querySelector(".SatisfactionComment-module_lengthLabel");
const submitButton = document.querySelector(".SatisfactionFooter-module_footer button");

commentBox?.addEventListener("input", () => {
  charCountText.textContent = `${commentBox.value.length} / 최소 10자 이상`;
  checkSubmitButton();
});

function checkSubmitButton() {
  const valid =
    commentBox.value.length >= 10 &&
    selectedStar > 0;

  submitButton.disabled = !valid;
  submitButton.classList.toggle("Button-module_disabled", !valid);
}

  //  이미지 업로드
const imageUploader = document.querySelector(".ImageUploader-module_container");
const fileInput = imageUploader.querySelector('input[type="file"]');
const uploadBtn = imageUploader.querySelector(".ImageUploader-module_button");
const imageList = imageUploader.querySelector(".ImageUploader-module_imageContainer");
const imageCountText = uploadBtn.querySelector("span");

const maxFileCount = 5;

/* 업로드 버튼 클릭 → 파일 선택 */
uploadBtn.addEventListener("click", () => {
  if (imageList.children.length >= maxFileCount) {
    alert("최대 5장까지 업로드 가능합니다");
    return;
  }
  fileInput.click();
});

/* 파일 선택 시 */
fileInput.addEventListener("change", (e) => {
  const file = e.target.files[0];
  if (!file) return;

  if (!file.type.match(/^image\/(png|jpeg|jpg)$/)) {
    alert("이미지 파일만 업로드 가능합니다");
    fileInput.value = "";
    return;
  }

  const reader = new FileReader();
  reader.onload = (ev) => {
    const div = document.createElement("div");
    div.className = "uploaded-image";

    const img = document.createElement("img");
    img.src = ev.target.result;

    const delBtn = document.createElement("button");
    delBtn.className = "ImageUploader-module_closeButton";
    delBtn.textContent = "×";

    div.appendChild(img);
    div.appendChild(delBtn);
    imageList.appendChild(div);

    updateFileCount();
  };

  reader.readAsDataURL(file);
  fileInput.value = "";
});

/* 이미지 개수 업데이트 */
function updateFileCount() {
  imageCountText.textContent =
    `${document.querySelectorAll(".uploaded-image").length}/${maxFileCount}`;
}
