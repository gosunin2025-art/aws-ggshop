// 모든 토글 input 선택
const toggleInputs = document.querySelectorAll('.MarketingNotificationSettingsToggle_input');

toggleInputs.forEach(input => {
    // input 바로 뒤의 label 찾기
    const label = input.previousElementSibling || input.nextElementSibling;

    // 초기 상태 반영
    if (input.checked) {
        label.classList.add('MarketingNotificationSettingsToggle_active');
    }

    // 클릭/체크 이벤트
    input.addEventListener('change', () => {
        if (input.checked) {
            label.classList.add('MarketingNotificationSettingsToggle_active');
        } else {
            label.classList.remove('MarketingNotificationSettingsToggle_active');
        }
    });
});

