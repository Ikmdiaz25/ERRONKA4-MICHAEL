const produktuKontainer = document.getElementById('produktuak'); // section id="produktuak"
const kantitateaElement = document.getElementById('kantitatea');
const prezioaElement = document.getElementById('prezioa');
const hutsikMezua = document.getElementById('karritoa_hutsik');
const totalaKontainer = document.getElementById('totala');
const berrezarriBtn = document.getElementById('berrezarri');
const erosiBtn = document.getElementById('erosiBtn');

function sortuProduktuak() {
    produktuKontainer.innerHTML = "";
    const produktuak = JSON.parse(localStorage.getItem(keyLocalStorage));

    if (produktuak && produktuak.length > 0) {
        hutsikMezua.classList.add("izkutatu");
        hutsikMezua.style.display = "none";
        totalaKontainer.classList.remove("izkutatu");
        totalaKontainer.style.display = "block";

        produktuak.forEach(produktu => {
            const txartela = document.createElement("div");
            txartela.classList.add("saskia-produktua");


            txartela.innerHTML = `
                <div class="produktu-info">
                    <img src="IMG/${produktu.irudia}" alt="${produktu.izena}" class="produktu-img">
                    <div class="produktu-xehetasunak">
                        <h3>${produktu.izena}</h3>
                        <p>Prezioa: $${produktu.prezioa}</p>
                    </div>
                </div>
                <div class="produktu-akzioak">
                    <button class="kendu-btn">-</button>
                    <span>${produktu.kantitatea}</span>
                    <button class="gehitu-btn">+</button>
                </div>
            `;

            const kenduBtn = txartela.querySelector(".kendu-btn");
            const gehituBtn = txartela.querySelector(".gehitu-btn");

            kenduBtn.addEventListener("click", () => {
                karritoariKendu(produktu);
                sortuProduktuak();
            });

            gehituBtn.addEventListener("click", () => {
                karritoraGehitu(produktu);
                sortuProduktuak();
            });

            produktuKontainer.appendChild(txartela);
        });
        eguneratuTotalak(produktuak);
        if (erosiBtn) erosiBtn.disabled = false;

    } else {
        hutsikMezua.classList.remove("izkutatu");
        hutsikMezua.style.display = "block";
        totalaKontainer.classList.add("izkutatu");
        totalaKontainer.style.display = "none";
        eguneratuTotalak([]);
        if (erosiBtn) erosiBtn.disabled = true;
    }
}

function eguneratuTotalak(produktuak) {
    const kantitatea = produktuak.reduce((acc, curr) => acc + curr.kantitatea, 0);
    const prezioa = produktuak.reduce((acc, curr) => acc + (curr.prezioa * curr.kantitatea), 0);

    kantitateaElement.innerText = kantitatea;
    prezioaElement.innerText = prezioa.toFixed(2);
}

berrezarriBtn.addEventListener('click', () => {
    karritoaHustu();
    sortuProduktuak();
});

if (erosiBtn) {
    erosiBtn.addEventListener('click', () => {
        const produktuak = JSON.parse(localStorage.getItem(keyLocalStorage));
        if (produktuak && produktuak.length > 0) {
            // Irabazi guztizkoak eguneratu
            const unekoIrabaziak = produktuak.reduce((acc, curr) => acc + (curr.prezioa * curr.kantitatea), 0);
            const irabaziZaharrak = parseFloat(localStorage.getItem('totalEarnings')) || 0;
            localStorage.setItem('totalEarnings', (irabaziZaharrak + unekoIrabaziak).toFixed(2));

            // Gehien erositako produktuak gorde
            let soldProducts = JSON.parse(localStorage.getItem('soldProducts')) || {};
            produktuak.forEach(prod => {
                if (soldProducts[prod.kodea]) {
                    soldProducts[prod.kodea].kantitatea += prod.kantitatea;
                } else {
                    soldProducts[prod.kodea] = {
                        izena: prod.izena,
                        kantitatea: prod.kantitatea,
                        prezioa: prod.prezioa
                    };
                }
            });
            localStorage.setItem('soldProducts', JSON.stringify(soldProducts));
        }

        alert("Erosketa ondo burutu da! Eskerrik asko.");
        karritoaHustu();
        sortuProduktuak();
    });
}

// Hasieratu
sortuProduktuak();