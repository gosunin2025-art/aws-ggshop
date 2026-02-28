const bannerContainer = document.querySelector(".headBanner-container");
const bannerClose = () => {
    bannerContainer.classList.remove("active");
}
const bannerOpen = () => {
    bannerContainer.classList.add("active");
}
