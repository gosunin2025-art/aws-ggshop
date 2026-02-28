// input[type="date"] 클릭 영역 확장
const datepicker = document.getElementById('datepicker');

if (datepicker) {
    datepicker.addEventListener('click', function () {
        this.showPicker();
    });
}

// [페이지 사이즈 드롭다운 요소 선택]
const container = document.querySelector('.box-footer .select2.select2-container');
const selection = container?.querySelector('.select2-selection-single');
const dropdown = container?.querySelector('.select2-dropdown');

// [페이지 사이즈 옵션 관련 요소 선택]
const rendered = container?.querySelector('.select2-selection-rendered');
const options = container?.querySelectorAll('.select2-results-option');

// 선택박스 클릭시 none 클래스 토글(=열림/닫힘)
selection?.addEventListener('click', (e) => {
    e.stopPropagation(); // 바깥 영역 클릭시 닫히는 코드와 충돌 방지
    dropdown?.classList.toggle('none'); // none 있으면 닫힘, 없으면 열림
});

// 바깥 영역 클릭시 닫기
document.addEventListener('click', () => {
    dropdown?.classList.add('none');
});

// 페이지 사이즈 옵션 선택
options?.forEach((li) => {
    li.addEventListener('click', (e) => {
        e.stopPropagation(); // 클릭 후 바로 닫히는 것 방지

        // 클릭한 옵션(li)의 텍스트 값 가져오기
        const value = li.textContent.trim();

        // 선택된 값 표시
        rendered.textContent = value;
        rendered.setAttribute('title', value);

        // 기존 선택 해제
        options.forEach((item) => {
            item.setAttribute('aria-selected', 'false');
        });

        // 현재 옵션 선택
        li.setAttribute('aria-selected', 'true');

        // 선택 후 드롭다운 닫기
        dropdown.classList.add('none');
    });
});

// 옵션 hover시 하이라이트 디자인 처리 (배경 + 텍스트 색)
options?.forEach((li) => {
    li.addEventListener('mouseenter', () => {
        // 다른 옵션들 하이라이트 제거
        options.forEach((item) => {
            item.classList.remove('select2-results-option-highlighted');
        });

        // 현재 옵션만 하이라이트 적용
        li.classList.add('select2-results-option-highlighted');
    });
});

// // [날짜 조회, 조회버튼 클릭 -> 선택 날짜 반영]
// const searchBtn = document.getElementById('date-choice');

// document.addEventListener('DOMContentLoaded', () => {
//     // URL에서 date 파라미터 읽기
//     const url = new URL(window.location.href);
//     const date = url.searchParams.get('date'); // YYYY-MM-DD

//     // 새로고침 후에도 날짜 유지 + 충전일자 컬럼 반영
//     if (datepicker && date) {
//         datepicker.value = date;

//         // 각 행의 첫 번째 셀 나타내기(충전일자)
//         const chargeDateCells = document.querySelectorAll('.list-center-cols-container .list-row > .list-cell:first-child');

//         // 충전일자에 해당하는 컬럼에 선택 날짜 표시
//         chargeDateCells.forEach((cell) => {
//             cell.textContent = date;
//         });
//     }

//     // 조회버튼 클릭 시
//     searchBtn?.addEventListener('click', (e) => {
//         e.preventDefault(); // form 기본 submit 방지

//         // input에서 선택된 날짜 가져오기
//         const pickedDate = datepicker.value;

//         // 날짜 미선택 시 조회 동작 X
//         if (!pickedDate) return;

//         // 선택한 날짜를 URL에 저장 후 새로고침
//         const nextUrl = new URL(window.location.href);
//         nextUrl.searchParams.set('date', pickedDate);

//         window.location.href = nextUrl.toString();
//     });
// });
