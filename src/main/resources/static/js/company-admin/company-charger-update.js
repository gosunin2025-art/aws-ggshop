// 충전소 수정 폼
// - 수정 완료 버튼으로 submit 시에만 검증 실행(엔터 제출 차단)
// - 설치주소 비어있을 경우 에러 문구/스타일 적용
// - 입력 값이 들어오면 에러 상태 해제

document.addEventListener('DOMContentLoaded', () => {
    // 폼 요소
    const form = document.getElementById('new-request');
    if (!form) return;

    // 설치주소 input (필수 입력 대상)
    const addressInput = document.getElementById('install-address');
    if (!addressInput) return;

    // 설치주소 필드 전체 영역 (에러 스타일 적용)
    const addressField = addressInput.closest('.form-field');

    // 설치주소 하단 에러 문구
    const addressError = addressField?.querySelector('.field-error');

    // 취소 버튼 요소
    const cancelBtn = form.querySelector('.btn-cancel');

    // 수정 완료 버튼 요소
    const submitBtn = form.querySelector('.btn-submit');

    // '수정 완료 버튼으로 제출을 시도했다'는 상태 저장용 변수
    // 엔터 키로 submit 되는 경우 false 유지
    let isSubmitByButton = false;

    // 취소 버튼 클릭 시 동작
    if (cancelBtn) {
        cancelBtn.addEventListener('click', (e) => {
            // 취소 버튼은 submit 동작을 막음
            e.preventDefault();

            // 완료 버튼 제출 상태 초기화
            isSubmitByButton = false;

            // 필요하면 이동 처리
            // history.back();
        });
    }

    // 수정 완료 버튼 클릭 시 동작
    if (submitBtn) {
        submitBtn.addEventListener('click', () => {
            // 수정 완료 버튼을 눌렀다는 표시만 남김
            // 실제 검증/제출은 submit 이벤트에서 처리
            isSubmitByButton = true;
        });
    }

    // 폼 제출 시 필수값 검증
    form.addEventListener('submit', (e) => {
        // 수정 완료 버튼이 아닌 방식(엔터 키 등)으로 submit 되었을 경우
        if (!isSubmitByButton) {
            // 엔터 키 제출 차단
            e.preventDefault();
            return;
        }

        // 화면 확인 단계이므로 실제 submit 방지(새로고침 방지)
        e.preventDefault();

        // 적용되었던 에러 스타일 제거
        if (addressField) addressField.classList.remove('is-error');

        // 설치주소 하단에 에러문구 숨김
        if (addressError) addressError.hidden = true;

        // 설치주소 값이 비어 있는지 검사
        if (!addressInput.value.trim()) {
            // 설치주소 영역에 에러 스타일 적용
            if (addressField) addressField.classList.add('is-error');

            // 설치주소 하단 에러 메세지 표시
            if (addressError) addressError.hidden = false;

            // 에러 발생 시 설치주소 입력창으로 커서 이동
            addressInput.focus();

            // 다음 submit 시도에서 엔터로 우회되지 않도록 상태 초기화
            isSubmitByButton = false;
            return;
        }

        // 필수 입력 이상 없음 (화면 단계)
        console.log('이상 없음');

        // 다음 submit 시도 대비 플래그 초기화
        isSubmitByButton = false;

        // ▼ 서버 연동 시 실제 submit
        // form.submit();
    });

    // 설치주소 input에 값이 입력될 때마다 실행되는 이벤트
    addressInput.addEventListener('input', () => {
        // 현재 입력 값이 공백이 아닌 경우
        if (addressInput.value.trim()) {
            // 설치주소 영역에 적용된 에러 스타일 제거
            if (addressField) addressField.classList.remove('is-error');

            // 설치주소 하단 에러 메세지 숨김 처리
            if (addressError) addressError.hidden = true;
        }
    });
});
