// DOM kargatzean exekutatu
document.addEventListener('DOMContentLoaded', () => {
    kargatuDatuErrealak();
});

function kargatuDatuErrealak() {
    // 1. Irabazi Guztizkoak
    const totalEarnings = parseFloat(localStorage.getItem('totalEarnings')) || 0;
    const irabaziElementua = document.getElementById('irabaziakTotala');

    if (irabaziElementua) {
        irabaziElementua.innerText = totalEarnings.toFixed(2) + " €";
    }

    // 2. Gehien Erositako Produktua
    const soldProductsStr = localStorage.getItem('soldProducts');
    const produktuIzarraElement = document.getElementById('produktuIzarra');
    const produktuIzarraKopuruElement = document.getElementById('produktuIzarraKopuru');

    if (soldProductsStr && produktuIzarraElement && produktuIzarraKopuruElement) {
        const soldProducts = JSON.parse(soldProductsStr);
        let gehienSaldutakoa = null;
        let maxKantitatea = 0;

        // Objektuaren gakoak errepasatu salduena aurkitzeko
        for (const argibideak in soldProducts) {
            const produktu = soldProducts[argibideak];
            if (produktu.kantitatea > maxKantitatea) {
                maxKantitatea = produktu.kantitatea;
                gehienSaldutakoa = produktu;
            }
        }

        if (gehienSaldutakoa) {
            produktuIzarraElement.innerText = gehienSaldutakoa.izena;
            produktuIzarraElement.classList.remove('testu-txikiagoa');
            // Izen oso luzeak konpontzeko edertze-puntua
            if (gehienSaldutakoa.izena.length > 15) {
                produktuIzarraElement.style.fontSize = "1.8rem";
            }
            produktuIzarraKopuruElement.innerText = maxKantitatea + " salmenta";
        }
    }
}
