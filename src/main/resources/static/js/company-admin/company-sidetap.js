// [메인메뉴 클릭 이벤트] 아코디언 토글 + 화살표 회전
document.addEventListener('click', (e) => {
    // 클릭한 요소가 메뉴 안이라면, 가장 가까운 메인 메뉴 찾기
    const menu = e.target.closest('.menu-items-menu');

    // 메뉴가 아닌 곳 클릭 시 종료
    if (!menu) return;

    // 메인 메뉴 바로 아래의 요소 = 서브메뉴
    const submenu = menu.nextElementSibling;

    // 다음 요소가 없거나, 서브메뉴가 아니면 종료
    // 서브메뉴 없는 단일 메뉴 보호
    if (!submenu || !submenu.classList.contains('menu-items-submenu-container')) return;

    // 현재 메뉴 안의 화살표 찾기
    // 화살표가 없는 메뉴도 있으므로 null일 수 있음
    const arrow = menu.querySelector('.menu-items-down-icon');

    // 서브메뉴가 열려있는지 hright 값으로 판단
    // height가 0px이면 닫힘, 0px이 아니면 열림
    const isOpen = submenu.style.height && submenu.style.height !== '0px';

    // 아코디언: 다른 메인 메뉴들의 서브 메뉴 닫기
    // 현재 선택된 메인메뉴의 서브메뉴만 열림
    document.querySelectorAll('.menu-items-submenu-container').forEach((box) => {
        // 현재 클릭된 서브메뉴는 제외
        if (box === submenu) return;

        // 다른 서브메뉴들의 높이를 0px로 만들어 닫음
        box.style.height = '0px';

        // 닫힌 서브메뉴의 메인메뉴 찾기(바로 위 형제)
        const prevMenu = box.previousElementSibling;
        // 해당 메인메뉴의 화살표를 원래 상태로 만들기 (열림->닫힘)
        prevMenu?.querySelector('.menu-items-down-icon')?.classList.remove('menu-items-open');
    });

    // 현재 클릭된 서브메뉴
    // 열려있으면 닫기
    // 닫혀있으면 높이만큼 열기
    submenu.style.height = isOpen ? '0px' : submenu.scrollHeight + 'px';

    // 화살표 회전 토글
    // 서브메뉴가 열릴 때: 화살표 회전
    // 서브메뉴가 닫힐 때: 원래 상태
    arrow?.classList.toggle('menu-items-open', !isOpen);
});

// [서브메뉴 클릭 이벤트] active 적용(글자 진해짐/굵어짐)
document.addEventListener('click', (e) => {
    const item = e.target.closest('.menu-items-submenu-container .menuitems-submenu');
    if (!item) return;

    // 기존 active 제거
    document.querySelectorAll('.menuitems-submenu.menuitems-active').forEach((el) => {
        el.classList.remove('menuitems-active');
    });

    // 클릭한 것 active 부여(글자 색 진하게)
    item.classList.add('menuitems-active');
});

// [더보기 버튼 이벤트]
// HTML 로드 후 JS 실행
document.addEventListener('DOMContentLoaded', function () {
    // 더보기 버튼 요소 가져오기
    const toggleBtn = document.getElementById('project-info-box');
    // 버튼이 없다면 종료
    if (!toggleBtn) return;

    // 컨테이너 가져오기
    const container = toggleBtn.closest('.project-info-container');
    // 컨테이너가 없다면 종료
    if (!container) return;

    // 드롭다운 박스 요소 가져오기
    const dropdown = container.parentElement.querySelector('.project-info-infobox');
    // 드롭다운 박스가 없을 경우 종료
    if (!dropdown) return;

    // 버튼 기준으로 드롭다운 위치 계산
    // fixed 포지션이므로 위치 직접 계산 필요
    function placeDropdown() {
        // 화면 기준 위치 정보 가져오기
        // 버튼이 화면에서 어디 있는지 계산하고
        // 아래(bottom)와 왼쪽(left) 위치만 사용
        const { bottom, left } = toggleBtn.getBoundingClientRect();

        // 열릴 위치 설정
        dropdown.style.top = bottom + 12 + 'px'; // 버튼 기준 아래 여백
        dropdown.style.left = left + 5 + 'px'; // 버튼 기준 왼쪽 여백
    }

    // 더보기 버튼 클릭
    toggleBtn.addEventListener('click', function (e) {
        e.stopPropagation(); // 바깥 클릭시 닫히는 것 방지

        // 열림 상태 토글 (테두리 색 변화)
        const opened = container.classList.toggle('project-info-expand');
        toggleBtn.setAttribute('aria-expanded', String(opened));

        // 드롭다운 닫기/열기
        if (opened) {
            // 열림: 위치 계산 후 화면에 표시
            placeDropdown();
            dropdown.hidden = false;
        } else {
            // 닫힘: hidden으로 숨김
            dropdown.hidden = true;
        }
    });

    // 드롭다운 내부 클릭시 닫히지 않게 (바깥 클릭 닫기 이벤트 차단)
    dropdown.addEventListener('click', function (e) {
        e.stopPropagation();
    });

    // 바깥 클릭시 닫기
    document.addEventListener('click', function () {
        // 열림 상태 클래스 project-info-expand 제거
        container.classList.remove('project-info-expand');

        // 토글 상태 닫힘으로 변경
        toggleBtn.setAttribute('aria-expanded', 'false');

        // 토글 박스 숨김
        dropdown.hidden = true;
    });
});
