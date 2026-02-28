// 충전소 등록 폼
// - 완료 버튼으로 submit 시에만 검증 실행(엔터 제출 차단)
// - 필수 입력 필드가 비어있을 경우 에러 문구/스타일 적용
// - 입력 값이 들어오면 에러 상태 해제

document.addEventListener('DOMContentLoaded', () => {
    // 폼 요소
    const form = document.getElementById('new-request');
    if (!form) return;

    // 취소/완료 버튼
    const cancelBtn = form.querySelector('.btn-cancel');
    const submitBtn = form.querySelector('.btn-submit');

    // 완료 버튼으로 제출했는지 여부(엔터 제출 차단용)
    let isSubmitByButton = false;

    // 필수 입력 필드 목록(필드/입력요소/에러문구)
    const fields = [...form.querySelectorAll('.form-field.required')].map((field) => ({
        field,
        input: field.querySelector('input, textarea'),
        errorMsg: field.querySelector('.field-error'),
    }));

    // 취소 버튼 클릭 시: submit 방지
    cancelBtn?.addEventListener('click', (e) => {
        e.preventDefault();
        isSubmitByButton = false;

        // 필요하면 이동 처리
        // history.back();
        // location.href = '/company-admin/charger-list.html';
    });

    // 완료 버튼 클릭 시: 제출 플래그 ON
    submitBtn?.addEventListener('click', () => {
        isSubmitByButton = true;
    });

    // submit 이벤트 처리
    form.addEventListener('submit', (e) => {
        // 완료 버튼이 아닌 방식(엔터 키 등) 제출 차단
        if (!isSubmitByButton) {
            e.preventDefault();
            return;
        }

        // 화면 확인 단계이므로 기본 submit 방지
        e.preventDefault();

        // 첫 번째 에러 입력창 저장(포커스 이동용)
        let firstErrorInput = null;

        // 필수 입력 검사 + 에러 초기화/적용
        // 값이 없는 입력창 에러 처리
        // 첫 번째 에러창만 기억
        fields.forEach(({ field, input, errorMsg }) => {
            // 이전 에러 상태 제거
            field.classList.remove('is-error');
            if (errorMsg) errorMsg.hidden = true;

            // 입력 값이 없거나 공백인 경우
            if (!input || !input.value.trim()) {
                // 해당 영역을 에러 상태로 표시
                field.classList.add('is-error');
                // 에러 메세지 표시
                if (errorMsg) errorMsg.hidden = false;
                // 첫 번째 에러창 저장
                if (!firstErrorInput && input) firstErrorInput = input;
            }
        });

        // 에러 발견시 첫 번째 포커스 이동 (submit 중단)
        if (firstErrorInput) {
            // 상태 초기화: 다음 제출시 엔터 제출 방지
            isSubmitByButton = false;
            // 첫 번쨰 에러 창으로 포커스 이동
            firstErrorInput.focus();
            return;
        }

        // 이상 없음 확인
        console.log('제출 가능 상태');

        // ▼ 서버 연동 시 실제 submit
        // isSubmitByButton = false;
        // form.submit();
    });

    // 입력이 시작되면 해당 필드 에러 해제
    fields.forEach(({ field, input, errorMsg }) => {
        input?.addEventListener('input', () => {
            // 공백이 아닌 값이 입력 되었을 경우만 에러 해제
            if (!input.value.trim()) return;
            // 에러 스타일 제거
            field.classList.remove('is-error');
            // 에러 메세지 숨김
            if (errorMsg) errorMsg.hidden = true;
        });
    });
});
