// layout
const body = document.querySelector('body');
const modals = document.querySelectorAll('.modal');

// buttons
const zz1Btn = document.getElementById('QuickV2G');
const confirms = document.querySelectorAll('.button-confirm');
const closes = document.querySelectorAll('.button-close');

const inputContainers = document.querySelectorAll(".currentPassword-wrap");
const inputFields = document.querySelectorAll('.FormModal-content .inputField');
//

// 포커스
inputFields.forEach((inputField) => {
    const input = inputField.querySelector('.input');
    const button = inputField.querySelector('.button-inInput');
    console.log(button);
    inputField.addEventListener('focusin', () => {
        inputField.classList.add('focus');
    });
    inputField.addEventListener('focusout', () => {
        inputField.classList.remove('focus');
    });
    if (button) {
        button.addEventListener('mousedown', (e) => {
            e.preventDefault();
        });
        button.addEventListener('mousedown', (e) => {
            console.log(input.type);
            const isPassword = input.type === 'password';
            input.type = isPassword ? 'text' : 'password';
        });
        button.addEventListener('mousedown', (e) => {
            const isPassword = input.type === 'password';
            button.innerHTML = isPassword ? `<span><span><svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="WithProps-icon pass-icon"><path d="M15 12C15 13.6569 13.6569 15 12 15C10.3431 15 9 13.6569 9 12C9 10.3431 10.3431 9 12 9C13.6569 9 15 10.3431 15 12Z" fill="#495057"></path><path fill-rule="evenodd" clip-rule="evenodd" d="M21.9999 12C20.5443 7.33923 16.6167 4 12 4C7.38322 4 3.45561 7.33923 2 12C3.45561 16.6608 7.38322 20 12 20C16.6167 20 20.5443 16.6608 21.9999 12ZM17 12C17 14.7614 14.7614 17 12 17C9.23858 17 7 14.7614 7 12C7 9.23858 9.23858 7 12 7C14.7614 7 17 9.23858 17 12Z" fill="#495057"></path></svg></span></span>` : `<span><span><svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="WithProps-icon pass-icon"><path fill-rule="evenodd" clip-rule="evenodd" d="M5.59085 17.5606L2.57568 20.5757L3.42421 21.4243L21.4242 3.42427L20.5757 2.57574L17.4483 5.70317C15.856 4.62176 13.9921 4 12 4C7.38322 4 3.45561 7.33923 2 12C2.69632 14.2296 3.95832 16.1567 5.59085 17.5606ZM8.06558 15.0858L9.49702 13.6544C9.18292 13.1802 9 12.6114 9 12C9 10.3431 10.3431 9 12 9C12.6114 9 13.1802 9.18292 13.6544 9.49702L15.0858 8.06558C14.236 7.39808 13.1645 7 12 7C9.23858 7 7 9.23858 7 12C7 13.1645 7.39808 14.236 8.06558 15.0858Z" fill="#495057"></path><path d="M7.33956 18.7818C8.74616 19.562 10.3279 20 12 20C16.6167 20 20.5443 16.6608 21.9999 12C21.4003 10.0801 20.3812 8.38441 19.0673 7.05403L16.4341 9.68723C16.7956 10.3788 17 11.1656 17 12C17 14.7614 14.7614 17 12 17C11.1656 17 10.3788 16.7956 9.68723 16.4341L7.33956 18.7818Z" fill="#495057"></path></svg></span></span>`;
        });
    }
});

// 모달 영역
// ※사용 시 최상단 부모 태그의 id를 적어주셔야 합니다.
// onclikc="modalOpen([닫을 모달아이디]);"
const modalOpen = (thisModal) => {
    const OpenModalName = document.getElementById(thisModal);
    body.classList.add('hidden');
    OpenModalName.classList.add('active');
};

// ※닫기 버튼에 넣어주세요.
// onclikc="modalCloses()"
const modalCloses = () => {
    const modals = document.querySelectorAll('.modal.active');
    body.classList.remove('hidden');
    modals.forEach((modal) => {
        console.log(modal);
        modal.classList.remove('active');
    });
};

// ※닫기 버튼에 넣어주세요.
// onclikc="onlyeClose([닫을 모달아이디]);"
const onlyClose = (onlyOne) => {
    const modal = document.getElementById(onlyOne);
    body.classList.remove('hidden');
    modal.classList.remove('active');
};

// 쇼 이벤트
const showToast = (showId) => {
    let timeSet = null;
    const showContainer = document.getElementById(showId);
    if (timeSet) clearTimeout(timeSet);
    document.querySelectorAll('.toast_show').forEach((el) => {
        el.classList.remove('toast_show');
        el.classList.add('none');
    });

    showContainer.classList.remove('none', 'toast_hide');
    showContainer.classList.add('toast_show');

    timeSet = setTimeout(() => {
        hideToast(showContainer);
    }, 3000);
};

// 하이드 이벤트
const hideToast = (targetEl) => {
    targetEl.classList.remove('toast_show');
    targetEl.classList.add('toast_hide');
    setTimeout(() => {
        if (targetEl.classList.contains('toast_hide')) {
            targetEl.classList.add('none');
        }
    }, 550);
};
