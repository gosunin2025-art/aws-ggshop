const tooltipButton = document.querySelector(".Tooltip-module_button__1c562");

document.addEventListener("DOMContentLoaded", () => {
    const wrapper = document.querySelector(".Tooltip-wrapper");
    const button = wrapper.querySelector(".Tooltip-module_button__1c562");

    button.addEventListener("click", (e) => {
        e.stopPropagation();
        wrapper.classList.toggle("active");
        button.classList.toggle("active"); // 아이콘 토글용
    });

    document.addEventListener("click", () => {
        wrapper.classList.remove("active");
        button.classList.remove("active");
    });
});
