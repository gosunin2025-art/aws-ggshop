document.querySelectorAll(".VehicleRegistration").forEach(el => {
    el.addEventListener("click", () => {
        document.getElementById("modal1").style.display = "flex";
    });
});
document.querySelectorAll(".Vehiclechange").forEach(el => {
    el.addEventListener("click", () => {
        document.getElementById("modal4").style.display = "flex";
    });
});


document.querySelectorAll('.ButtonNickName').forEach(btn => {
    btn.addEventListener('click', () => {
        document.querySelectorAll('.ConfirmModal_overlay').forEach(modal => {
            modal.style.display = 'none';
        });
    });
});

// 모달1 에서 모달2 열기
document.querySelector("#modal1 .VehicleRegistration").addEventListener("click", (e) => {
    e.stopPropagation();
    document.getElementById("modal2").style.display = "flex";
});


document.querySelector("#modal4 .Vehiclechange").addEventListener("click", (e) => {
    e.stopPropagation();
    document.getElementById("modal2").style.display = "flex";
});


// x 취소 버튼
document.querySelectorAll(".closebutton").forEach(btn => {
    btn.addEventListener("click", () => {
        const modal = btn.closest(".ConfirmModal_overlay");
        if (modal) {
            modal.style.display = "none";
        }
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const modal5 = document.getElementById("modal5");
    const btn = document.querySelector(".closebutton1");

    if (btn && modal5) {
        btn.addEventListener("click", (e) => {
            
            document.querySelectorAll(".ConfirmModal_overlay").forEach(modal => {
                modal.style.display = "none";
            });

            modal5.style.display = "flex";
        });
    }
});


document.querySelectorAll(".okayButton").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        document.querySelectorAll(".ConfirmModal_overlay").forEach(modal => {
            modal.style.display = "none";
        });

        const toastContainer = document.querySelector(".Toast_container");
        const toast1 = document.getElementById("toast1");

        toastContainer.querySelectorAll(".Toast_content").forEach(t => t.style.display = "none");
        toast1.style.display = "block";

        toastContainer.classList.add('Toast_showMobile');
        setTimeout(() => {
            toastContainer.classList.remove('Toast_showMobile');
        }, 3000);
    });
});

document.querySelectorAll(".changeButton").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        document.querySelectorAll(".ConfirmModal_overlay").forEach(modal => {
            modal.style.display = "none";
        });

        const toastContainer = document.querySelector(".Toast_container");
        const toast1 = document.getElementById("toast2");

        toastContainer.querySelectorAll(".Toast_content").forEach(t => t.style.display = "none");
        toast1.style.display = "block";

        toastContainer.classList.add('Toast_showMobile');
        setTimeout(() => {
            toastContainer.classList.remove('Toast_showMobile');
        }, 3000);
    });
});


// 삭제 버튼
document.querySelectorAll(".deletbutton").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();
        document.getElementById("modal3").style.display = "flex";
    });
});

document.querySelectorAll(".deletOkayButton").forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        const toastContainer = document.querySelector(".Toast_container");
        const toast3 = document.getElementById("toast3");

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



