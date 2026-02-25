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

    // Login Formularioa
    const loginForm = document.querySelector('.saioa-hasi-edukiontzia form');
    if (loginForm) {
        loginForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const erabiltzailea = document.getElementById('erabiltzailea').value;
            localStorage.setItem('usuarioActivo', erabiltzailea);

            alert('Ongi etorri, ' + erabiltzailea + '!');
            window.location.href = 'index.html';
        });
    }


    const usuarioActivo = localStorage.getItem('usuarioActivo');
    if (usuarioActivo) {
        const loginBtns = document.querySelectorAll('.loginBtn');
        loginBtns.forEach(btn => {
            const enlace = btn.querySelector('a');
            if (enlace) {
                enlace.textContent = usuarioActivo;
                enlace.href = '#';
            } else {
                btn.textContent = usuarioActivo;
            }
        });
    }
});
