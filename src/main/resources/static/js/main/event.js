const wishbtns = document.querySelectorAll('.wishbtn');
const alarmbtns = document.querySelectorAll('.alarmbtn');

// 찜(위시리스트) 이벤트
wishbtns.forEach(wishbtn => {
    wishbtn.addEventListener("click", (e) => {
        e.currentTarget.classList.toggle("active");
        const showToastId = e.currentTarget.classList[1] === 'active' ? 'wishAdd' : "wishRemove";
        // console.log(e.currentTarget.classList[1] === 'active');
        showToast(showToastId);
    })
});

// 찜(알람리스트) 이벤트
alarmbtns.forEach(alarmbtn => {
    alarmbtn.addEventListener('click', (e) => {
        const btnIdx = e.currentTarget.dataset.index;
        if(e.currentTarget.classList[1] === 'active') {
            const modal = document.getElementById("modalAlarmConfirm");
            modal.dataset.index = btnIdx;
            modalOpen('modalAlarmConfirm');
        } else {
            e.currentTarget.classList.add('active');
            showToast('alarmAdd');
        }
    });
});

//찜(알람리스트) 팝업 이벤트 
const confirmAlarmCancel = () => {
    const modal = document.getElementById('modalAlarmConfirm');
    const target = document.querySelector(`.alarmbtn[data-index="${modal.dataset.index}"]`); 
    if (target) {
        target.classList.remove('active'); 
        showToast('alarmRemove'); 
        modalCloses();
        modal.dataset.index = null;
    }
};