document.querySelectorAll(".Buttonremove").forEach(removebtn => {
    removebtn.addEventListener("click", () => {
        removebtn.remove(".Buttonremove");
    });
});

document.querySelectorAll(".PopMenu_toggle").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.stopPropagation();

        const menu = btn.closest(".PopMenu_wrap")
            .querySelector(".PopMenu_box");

        if (menu.style.display === "block") {
            menu.style.display = "none";
        } else {
            menu.style.display = "block";
        }
    });
});

document.querySelectorAll(".PopMenu_menu_r").forEach(btn => {
    btn.addEventListener("click", () => {

        document.getElementById("modal").style.display = "flex";
        document.querySelector(".PopMenu_box").style.display = "none";
    });
});



document.querySelectorAll(".closebutton").forEach(btn => {
    btn.addEventListener("click", () => {
        document.getElementById("modal").style.display = "none";
    });
});

document.querySelectorAll(".Buttonremove").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        const toastContainer = document.querySelector(".Toast_container");
        const toast3 = document.getElementById("toast1");

        toastContainer.querySelectorAll(".Toast_content").forEach(t => t.style.display = "none");
        toast3.style.display = "block";

        toastContainer.classList.add('Toast_showMobile');
        setTimeout(() => {
            toastContainer.classList.remove('Toast_showMobile');
        }, 3000);

        document.querySelectorAll(".ConfirmModal_overlay").forEach(modal => {
            modal.style.display = "none";
        });
    });
});
