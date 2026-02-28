// 직원 정보 수정 폼 JS
// - 엔터 제출 차단, '수정 완료' 버튼 클릭으로만 제출 가능
// - 필수 입력값 비어있으면 에러 표시, 입력되면 에러 해제

document.addEventListener('DOMContentLoaded', () => {
    // 폼 요소
    const form = document.getElementById('new-request');
    if (!form) return;

    // 버튼 요소
    const cancelBtn = form.querySelector('.btn-cancel');
    const submitBtn = form.querySelector('.btn-submit');

    // 완료 버튼으로 제출되었는지 확인 (엔터키 제출 차단용)
    let isSubmitByButton = false;

    // 필수 입력 필드 모음(필드/입력요소/에러문구)
    const fields = [...form.querySelectorAll('.form-field.required')].map((field) => ({
        // 필드 전체 영역
        field,
        // 실제 입력 요소, 값 검즘, 포커스 이동에 사용
        input: field.querySelector('input, textarea'),
        // 에러 메세지 요소, 값이 비어있을 경우 에러 표시에 사용
        errorMsg: field.querySelector('.field-error'),
    }));

    // 취소 버튼: 제출 막기
    cancelBtn?.addEventListener('click', (e) => {
        e.preventDefault();
        isSubmitByButton = false;
        // history.back();
    });

    // 수정 완료 버튼: 버튼 클릭 제출 표시용(버튼 클릭 구분용)
    submitBtn?.addEventListener('click', () => {
        isSubmitByButton = true;
    });

    // 제출 시 확인
    form.addEventListener('submit', (e) => {
        // 엔터 등 버튼 외 submit은 차단
        if (!isSubmitByButton) return e.preventDefault();

        // 화면 단계: 새로고침 방지 + 검증 후 submit 제어
        e.preventDefault();

        // 첫 번째 에러 입력창을 기업하기 위한 변수
        // 검증 실패시 수정할 위치를 안내하기 위함
        let firstErrorInput = null;

        fields.forEach(({ field, input, errorMsg }) => {
            // 이전 검증 결과가 남아있지 않도록 에러 초기화
            field.classList.remove('is-error');
            if (errorMsg) errorMsg.hidden = true;

            // 입력 요소가 없거나, 값이 비어있을 경우
            // 필수 입력 누락으로 판단
            if (!input || !input.value.trim()) {
                // 해당 영역을 에러 상태로 표시
                field.classList.add('is-error');
                // 에러 메세지 표시
                if (errorMsg) errorMsg.hidden = false;
                // 첫 번째 에러 입력창만 저장
                // 가장 위에 있는 항목으로 포커스를 이동하기 위함
                if (!firstErrorInput && input) firstErrorInput = input;
            }
        });

        // 하나라도 에러가 발견되었을 경우
        // 수정 위치를 알려주고 제출 처리 중단
        if (firstErrorInput) {
            // 다음 submit 시 엔터 키로 우회 제출되지 않도록 상태 초기화
            isSubmitByButton = false;

            // 가장 먼저 발견된 에러 입력창으로 포커스 이동
            firstErrorInput.focus();

            // 이후 submit 로직 실행하지 않음
            return;
        }

        // 이상 없음 확인
        console.log('제출 가능 상태');

        // ▼ 서버 연동 시 실제 submit
        // isSubmitByButton = false;
        // form.submit();
    });

    // 사용자가 값을 입력하기 시작하면 해당 필드 에러 해제
    fields.forEach(({ field, input, errorMsg }) => {
        input?.addEventListener('input', () => {
            // 공백이 아닌 값이 입력된 경우만 처리
            if (!input.value.trim()) return;
            // 입력 확인시 에러 스타일 제거
            field.classList.remove('is-error');
            // 에러 메세지 숨김
            if (errorMsg) errorMsg.hidden = true;
        });
    });
});
