const notice = document.querySelector('.notice-container');
notice.addEventListener("click", (e) => {
    notice.querySelector('.NotificationBadge.active').classList.remove('active');
})