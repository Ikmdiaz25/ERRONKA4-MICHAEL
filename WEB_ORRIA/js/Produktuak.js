let produktuGuztiak = [];

const displayProducts = (productsToShow) => {
  const shopContent = document.getElementById("shopContent");
  shopContent.innerHTML = "";

  if (productsToShow.length === 0) {
    shopContent.innerHTML = "<p>Ez dago produkturik aukera honekin.</p>";
    return;
  }

  productsToShow.forEach(product => {
    const div = document.createElement("div");
    div.className = 'produktu-txartela';

    div.innerHTML = `
      <img src="IMG/${product.irudia}" alt="${product.izena}" > 
      <h3>${product.izena}</h3>
      <div class="txartel-xehetasunak">
          <div class="prezio-kaxa">
             <p class="prezioa">$ ${product.prezioa}</p>
          </div>
      </div>
      <div class="tailak">
         <span class="taila-etiketa">S</span>
         <span class="taila-etiketa">M</span>
         <span class="taila-etiketa">L</span>
         <span class="taila-etiketa">XL</span>
      </div>
      <button class="erosi-btn">Erosi</button>
    `;

    const erosiBotoia = div.querySelector('.erosi-btn');
    erosiBotoia.addEventListener('click', () => {
      karritoraGehitu(product);
      alert("Produktua saskira gehitu da!");
    });
    shopContent.append(div);
  });
};

const iragaziProduktuak = () => {
  const denakBtn = document.getElementById('denakBtn');
  const kategoriaCheckboxak = document.querySelectorAll('.kategoria-checkbox:checked');

  if (denakBtn.checked) {
    displayProducts(produktuGuztiak);
    return;
  }

  const aukeratutakoKategoriak = Array.from(kategoriaCheckboxak).map(cb => parseInt(cb.value));

  if (aukeratutakoKategoriak.length === 0) {
    displayProducts([]);
    return;
  }

  const iragazitakoProduktuak = produktuGuztiak.filter(produktu =>
    aukeratutakoKategoriak.includes(produktu.kategoria_Kod)
  );

  displayProducts(iragazitakoProduktuak);
};

const gertaerakEsleitu = () => {
  const denakBtn = document.getElementById('denakBtn');
  const kategoriaCheckboxak = document.querySelectorAll('.kategoria-checkbox');

  denakBtn.addEventListener('change', (e) => {
    if (e.target.checked) {
      kategoriaCheckboxak.forEach(cb => cb.checked = false);
      iragaziProduktuak();
    }
  });

  kategoriaCheckboxak.forEach(cb => {
    cb.addEventListener('change', () => {
      const batenBatAukeratuta = Array.from(kategoriaCheckboxak).some(c => c.checked);
      if (batenBatAukeratuta) {
        denakBtn.checked = false;
      } else {
        denakBtn.checked = true;
      }
      iragaziProduktuak();
    });
  });
};

fetch('../produktu_guztiak.json')
  .then(response => {
    if (!response.ok) {
      throw new Error('Ezin izan da fitxategia kargatu');
    }
    return response.json();
  })
  .then(data => {
    produktuGuztiak = data;
    displayProducts(produktuGuztiak);
    gertaerakEsleitu();
  })
  .catch(error => {
    console.error('Errorea produktuak kargatzean:', error);
    const shopContent = document.getElementById("shopContent");
    if (shopContent) shopContent.innerHTML = "<p>Errorea: ezin izan dira produktuak kargatu.</p>";
  });

