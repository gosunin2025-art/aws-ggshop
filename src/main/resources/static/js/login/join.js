const emailInput = document.getElementById("username");
const errorMessage = document.querySelector(
    ".EmailValidationForm-module_error__cbcb7"
);
const nextButton = document.querySelector(
    ".Button-module_button__d78c2.Button-module_mint__1c1d6"
);

// 이메일 정규식
const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

emailInput.addEventListener("input", () => {
    const value = emailInput.value.trim();

    if (value === "") {
        // 아무것도 안 썼을 때
        errorMessage.style.display = "none";
        nextButton.disabled = true;
        nextButton.classList.add("Button-module_disabled__2ebf2");
        return;
    }

    if (!emailRegex.test(value)) {
        // 이메일 형식이 틀렸을 때
        errorMessage.style.display = "block";
        nextButton.disabled = true;
        nextButton.classList.add("Button-module_disabled__2ebf2");
    } else {
        // 이메일 형식이 맞을 때
        errorMessage.style.display = "none";
        nextButton.disabled = false;
        nextButton.classList.remove("Button-module_disabled__2ebf2");
    }
});
