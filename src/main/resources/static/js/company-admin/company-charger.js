// DOM이 모두 로드된 뒤(HTML요소 생성 후) 초기화 함수들을 실행
document.addEventListener('DOMContentLoaded', () => {
    // 페이지네이션 처리
    initPagination();

    // 탭(active) 처리(임시)
    initTabs();

    // 검색 submit 처리
    initSearch();
});

// [페이지네이션 초기화]
// : 페이지 번호 클릭 시 current 클래스 적용 토글
// : data-page 값을 읽어서 몇 페이지를 클릭했는지 파악
function initPagination() {
    // 페이지네이션 숫자 링크들(a[data-page]) 전부 가져오기
    const pageLinks = document.querySelectorAll('.pagination a[data-page]');

    // 페이지 링크가 없으면 종료
    // (해당 페이지에 pagination없으면 종료)
    if (!pageLinks.length) return;

    pageLinks.forEach((link) => {
        link.addEventListener('click', (e) => {
            // 임시) href가 #!일 때만 기본 이동 방지
            // 실제 URL로 이동하게 되면 제거
            e.preventDefault();

            // 기존 current 스타일 제거
            pageLinks.forEach((a) => a.classList.remove('current'));

            // 클릭된 링크에 current 스타일 추가
            link.classList.add('current');

            // 클릭된 페이지 번호(확인용)
            const page = link.dataset.page;
            console.log('선택된 페이지', page);

            // ※ 실제 이동은 a 태그의 href가 담당
        });
    });

    //  [검색 조건 유지용 페이지 이동 처리]★
    //  검색 후 페이지네이션을 클릭해도
    //  검색 조건이 유지되도록 하기 위함

    //  클릭한 페이지 번호를 hidden input(page)에 넣고
    //  기존 검색 form을 다시 submit
    //  ->서버에서는 검색 조건 + 페이지 번호를 함께 받게 됨
}

// [탭 초기화]
// : 탭 클릭 시 active 클래스 적용
function initTabs() {
    // 탭 li 요소들 가져오기
    const tabItems = document.querySelectorAll('.segmented-tabs ul li');

    // 탭이 없을 경우 실행 종료
    if (!tabItems.length) return;

    tabItems.forEach((li) => {
        li.addEventListener('click', (e) => {
            // 임시) href가 #!일 때만 기본 이동 방지
            // 실제 URL로 이동하게 되면 제거
            e.preventDefault();

            // 기존 active 스타일 제거
            tabItems.forEach((item) => item.classList.remove('active'));
            // 클릭된 탭에 active 클래스 적용
            li.classList.add('active');

            // ※ 실제 이동은 a 태그의 href가 담당
        });
    });
}

// [검색 초기화]
// : 검색 조건(select)과 검색어(input) 값 읽기
// : submit 확인용 콘솔 추가
function initSearch() {
    // 검색 폼 및 요소들
    const searchForm = document.getElementById('news-board-search');
    const searchSelect = document.getElementById('search-select-in-board');
    const searchInput = document.getElementById('search-text-in-board');

    // 필수 요소가 없으면 실행하지 않음
    // 검색 폼 없는 페이지에서 사용할 경우 에러 방지
    if (!searchForm || !searchSelect || !searchInput) return;

    searchForm.addEventListener('submit', (e) => {
        // ✅ 서버 구현 완료! e.preventDefault() 제거!

        // 선택된 검색 조건
        const searchType = searchSelect.value;

        // 입력된 검색어 (앞뒤 공백 제거)
        const keyword = searchInput.value.trim();

        // 검색어가 비어있으면 submit 방지
        if (!keyword) {
            e.preventDefault();
            alert('검색어를 입력해주세요.');
            return;
        }

        // 확인용 콘솔
        console.log('검색 조건:', searchType);
        console.log('검색어:', keyword);

        // ※ form의 action/method에 따라 서버로 자동 전달됨
    });
}

function goToList() {
    location.href = '/ev/evcharger/list/1';
}

function goToRegister() {
    location.href = '/ev/evcharger';
}