document.querySelector('.Tabs-module_tabs').addEventListener('click', (e) => {
    const tab = e.target.closest('.Tab-module_tab');

    document.querySelectorAll('.Tab-module_tab').forEach(tab => tab.classList.remove('Tab-module_active'));

    tab.classList.add('Tab-module_active');

    const value = tab.dataset.value;
    const panels = document.querySelectorAll('.TabPanels-module_tabPanels > ul'); 
    panels.forEach((panel, index) => {
        if ((value === '1' && index === 0) || (value === '3' && index === 1)) {
            panel.style.display = 'block';
        } else {
            panel.style.display = 'none';
        }
    });
});





const toast = document.querySelector(".Toast-module_container");
const buttons = document.querySelectorAll(".close_button");
const lists = document.querySelectorAll(".BlockedSupporterCard-module_blockSupporterCard");


buttons.forEach(button => {
    button.addEventListener("click", (e) => {
        toast.querySelector(".Toast-module_content");

        toast.classList.add("Toast-module_show");

        setTimeout(() => {
            toast.classList.remove("Toast-module_show");
        }, 1000);
    });
});


buttons.forEach(button => {
  button.addEventListener("click", () => {
    
    lists.remove();
  });
});
