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

// [검색 필터 드롭다운]
// filter-select2 컨테이너로 동작

// 필터 드롭다운 컨테이너만 선택 (검색 종류 select2)
const filterContainer = document.querySelector('.filter-select2');

// 필터 드롭다운 내부 요소 선택 (컨테이너 기준으로만 찾기)
const filterSelection = filterContainer?.querySelector('.select2-selection-single');
const filterDropdown = filterContainer?.querySelector('.select2-dropdown');
const filterRendered = filterContainer?.querySelector('.select2-selection-rendered');
const filterOptions = filterContainer?.querySelectorAll('.select2-results-option');

// 초기 상태 닫힘 고정
filterDropdown?.classList.add('none');

// 선택박스 클릭 -> 열기/닫기
filterSelection?.addEventListener('click', (e) => {
    // 문서 클릭 닫기와 충돌 방지
    e.stopPropagation();
    // none 이 있으면 닫기, 없으면 열림
    filterDropdown?.classList.toggle('none');
});

// 바깥 영역 클릭시 닫기
document.addEventListener('click', (e) => {
    filterDropdown?.classList.add('none');
});

// 옵션 클릭시 선택값 반영 + 선택 스타일 적용 + 드롭다운 적용
filterOptions?.forEach((li) => {
    li.addEventListener('click', (e) => {
        e.stopPropagation();

        //화면에 보여줄 목록 내용(설치일별/위치별/충전량별)
        const label = li.textContent.trim();

        // 선택된 값으로 텍스트 변경
        if (filterRendered) {
            filterRendered.textContent = label;
            filterRendered.setAttribute('title', label);
        }

        // 옵션 선택 해제시, 선택 스타일 해제
        filterOptions.forEach((item) => item.setAttribute('aria-selected', 'false'));

        // 옵션 선택시, 선택 스타일 적용
        li.setAttribute('aria-selected', 'true');

        // 옵션 선택시 드롭다운 닫기
        filterDropdown?.classList.add('none');
    });
    // 목록에 마우스 올렸을 때 하이라이트 디자인 적용 (hover 디자인)
    li.addEventListener('mouseenter', () => {
        filterOptions.forEach((item) => item.classList.remove('select2-results-option-highlighted'));
        li.classList.add('select2-results-option-highlighted');
    });
});
