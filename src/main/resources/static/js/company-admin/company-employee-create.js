// 임직원 등록 폼 JS
// - 필수 입력 검증
// - 엔터 키 제출 완전 차단
// - 완료 버튼 클릭 시에만 submit 허용
// - 취소 버튼은 submit 방지

document.addEventListener('DOMContentLoaded', () => {
    // 등록 폼 요소 선택
    const form = document.getElementById('new-request');

    // 폼이 없으면 이후 코드 실행하지 않음
    if (!form) return;

    // 필수 입력 필드 영역들
    const requiredFields = form.querySelectorAll('.form-field.required');

    // 공통 에러 메세지 영역
    const formError = document.querySelector('.form-error');

    // 취소 버튼 요소
    const cancelBtn = form.querySelector('.btn-cancel');

    // 완료 버튼 요소
    const submitBtn = form.querySelector('.btn-submit');

    // 완료 버튼으로 submit 시도했는지 여부 확인용 변수
    let isSubmitByButton = false;

    // 취소 버튼 클릭 시 동작
    if (cancelBtn) {
        cancelBtn.addEventListener('click', (e) => {
            // submit 기본 동작 차단
            e.preventDefault();

            // 완료 버튼으로 제출한 상태가 아니라면 초기화
            isSubmitByButton = false;
        });
    }

    // 완료 버튼 클릭 시 동작
    if (submitBtn) {
        submitBtn.addEventListener('click', () => {
            // 완료 버튼이 눌렸다는 표시만 남김
            // 실제 제출 여부는 submit 이벤트에서 판단
            isSubmitByButton = true;
        });
    }

    // submit 이벤트 처리
    form.addEventListener('submit', (e) => {
        // 완료 버튼이 아닌 방식(엔터 키 등)으로 submit 되었을 경우
        if (!isSubmitByButton) {
            // submit 차단
            e.preventDefault();
            return;
        }

        // 완료 버튼으로 submit 시도한 경우
        // 기본 submit 먼저 차단 (검증하기 위함)
        e.preventDefault();

        // 에러 발생 여부 확인용 변수
        let hasError = false;

        // 이전 에러 상태 초기화
        requiredFields.forEach((field) => {
            // 에러 스타일 제거
            field.classList.remove('is-error');

            // 필드 안 에러 메세지 선택
            const errorMsg = field.querySelector('.field-error');

            // 에러 메세지가 있을 경우 제거
            if (errorMsg) {
                errorMsg.hidden = true;
            }
        });

        // 공통 에러 메세지가 있을 경우 제거
        if (formError) {
            formError.hidden = true;
        }

        // 필수 입력 필드 순회하며 값 검사
        requiredFields.forEach((field) => {
            // input 또는 textarea 요소 찾기
            const input = field.querySelector('input, textarea');

            // 값이 없거나 공백만 입력된 경우
            if (!input || !input.value.trim()) {
                // 에러 상태로 판단
                hasError = true;

                // 해당 필드에 에러 스타일 적용
                field.classList.add('is-error');

                // 해당 필드의 에러 메세지 선택
                const errorMsg = field.querySelector('.field-error');

                // 에러 메세지가 있을 경우 화면에 표시
                if (errorMsg) {
                    errorMsg.hidden = false;
                }
            }
        });

        // 에러가 하나라도 있을 경우
        if (hasError) {
            // 제출 실패 -> 완료 버튼 클릭 상태 초기화(엔터 제출 방지용)
            isSubmitByButton = false;
            return;
        }

        // 콘솔에서 해당 내용 확인 시, 제출 가능 상태(이상 없음)
        console.log('제출 가능');

        // ▼ 서버 연동 시 실제 submit 처리 코드
        // isSubmitByButton = false;
        // form.submit();
    });
});
