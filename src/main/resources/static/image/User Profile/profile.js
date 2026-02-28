const banner = document.querySelector("div.RollingBannerCard_container");
let count = 0;

setInterval(() => {
    count++;
    banner.style.transform = `translate(-${366 * count}px)`;
    banner.style.transition = `transform 1.5s`;

    if (count === 3) {
        setTimeout(() => {
            banner.style.transform = `translate(0px)`;
            banner.style.transition = `transform 0s`;
        }, 500);
        count = 0;
    }
}, 1000);
