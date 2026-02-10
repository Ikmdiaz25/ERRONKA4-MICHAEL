document.addEventListener('DOMContentLoaded', () => {
    const menuBotoia = document.getElementById('menu-ireki');
    const menuLeihoa = document.getElementById('menu-leihoa');
    const itxiBotoia = document.getElementById('menu-itxi');

    if (menuBotoia && menuLeihoa) {
        menuBotoia.addEventListener('click', () => {
            menuLeihoa.showModal();
        });
    }

    if (itxiBotoia && menuLeihoa) {
        itxiBotoia.addEventListener('click', () => {
            menuLeihoa.close();
        });
    }

    if (menuLeihoa) {
        menuLeihoa.addEventListener('click', (e) => {
            const rect = menuLeihoa.getBoundingClientRect();
            const isInDialog = (rect.top <= e.clientY && e.clientY <= rect.top + rect.height &&
                rect.left <= e.clientX && e.clientX <= rect.left + rect.width);

        });
    }
});
