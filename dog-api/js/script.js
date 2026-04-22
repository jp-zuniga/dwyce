class DogAPI {
  constructor() {
    this.baseURL = "https://dog.ceo/api";

    this.dogContainer = document.getElementById("dogContainer");
    this.requestUrl = document.getElementById("requestUrl");
    this.jsonResponse = document.getElementById("jsonResponse");

    // leer el nuevo input del datalist
    this.breedInput = document.getElementById("breedInput");
    this.razasList = document.getElementById("razas");

    document.getElementById("randomDog").addEventListener("click", () => {
      this.fetchRandomDog();
    });

    document.getElementById("multipleDogs").addEventListener("click", () => {
      this.fetchMultipleDogs(5);
    });

    document.getElementById("searchBreed").addEventListener("click", () => {
      this.fetchByBreed();
    });

    this.loadBreeds();
  }

  async loadBreeds() {
    try {
      this.dogContainer.innerHTML =
        '<div class="loading">🔄 Cargando razas...</div>';

      const url = `${this.baseURL}/breeds/list/all`;

      this.requestUrl.textContent = `GET ${url}`;

      const response = await fetch(url);
      const data = await response.json();

      this.jsonResponse.textContent = JSON.stringify(data, null, 2);

      const breeds = Object.keys(data.message);

      // limpiar y llenar el datalist
      this.razasList.innerHTML = "";

      breeds.forEach((breed) => {
        const option = document.createElement("option");
        option.value = breed;
        this.razasList.appendChild(option);
      });

      this.breedInput.placeholder = "Escribe o selecciona una raza";
      this.breedInput.disabled = false;

      document.getElementById("searchBreed").disabled = false;

      this.dogContainer.innerHTML =
        '<div class="placeholder">✅ Razas cargadas. ¡Haz clic en un botón!</div>';
    } catch (error) {
      console.error("❌ Error:", error);
      this.showError("Error cargando razas");
    }
  }

  async fetchRandomDog() {
    try {
      const url = `${this.baseURL}/breeds/image/random`;

      this.dogContainer.innerHTML =
        '<div class="loading">🔄 Cargando perrito...</div>';

      this.requestUrl.textContent = `GET ${url}`;

      const response = await fetch(url);
      const data = await response.json();

      this.jsonResponse.textContent = JSON.stringify(data, null, 2);

      this.displayImages(data.message);
    } catch (error) {
      this.showError("Error obteniendo perrito");
    }
  }

  async fetchMultipleDogs(count) {
    try {
      const url = `${this.baseURL}/breeds/image/random/${count}`;

      this.dogContainer.innerHTML =
        '<div class="loading">🔄 Cargando perritos...</div>';

      this.requestUrl.textContent = `GET ${url}`;

      const response = await fetch(url);
      const data = await response.json();

      this.jsonResponse.textContent = JSON.stringify(data, null, 2);

      this.displayImages(data.message);
    } catch (error) {
      this.showError("Error obteniendo perritos");
    }
  }

  async fetchByBreed() {
    const breed = this.breedInput.value.toLowerCase();

    if (!breed) {
      alert("¡Selecciona o escribe una raza!");
      return;
    }

    try {
      const url = `${this.baseURL}/breed/${breed}/images/random/3`;

      this.dogContainer.innerHTML =
        '<div class="loading">🔄 Buscando perritos...</div>';

      this.requestUrl.textContent = `GET ${url}`;

      const response = await fetch(url);
      const data = await response.json();

      if (data.status === "error") throw new Error(data.message);

      this.jsonResponse.textContent = JSON.stringify(data, null, 2);

      this.displayImages(data.message);
    } catch (error) {
      this.showError(`Error obteniendo raza ${breed}`);
    }
  }

  displayImages(images) {
    const imageArray = Array.isArray(images) ? images : [images];

    const html = imageArray
      .map((imgUrl) => {
        // extraer info de url
        const parts = imgUrl.split("/");
        const razaOriginal = parts[4] || "desconocida";
        const razaFormateada = razaOriginal.replace("-", " ");
        const idImagen = parts[parts.length - 1].split(".")[0];

        // implementacion de desafios
        return `
          <article class="dog-card" data-raza="${razaFormateada}">
            <header>
              <h3>Perrito ${razaFormateada}</h3>
            </header>

            <figure>
              <abbr title="Raza: ${razaFormateada}">
                <img src="${imgUrl}" alt="Fotografía de un perrito de raza ${razaFormateada}" loading="lazy">
              </abbr>
              <figcaption>Ejemplar de ${razaFormateada}</figcaption>
            </figure>

            <details>
              <summary>Ver detalles de la raza</summary>
              <div class="details-content">
                <p><strong>Raza:</strong> ${razaFormateada}</p>
                <p><strong>Dato curioso:</strong> ¡Su hocico es único como una huella dactilar!</p>
                <p><strong>ID Imagen:</strong> ${idImagen}</p>
              </div>
            </details>

            <footer>
              <p>🐾 ${razaFormateada}</p>
            </footer>
          </article>
        `;
      })
      .join("");

    this.dogContainer.innerHTML = html;
  }

  showError(message) {
    this.dogContainer.innerHTML = `<div class="error">❌ ${message}</div>`;
  }
}

document.addEventListener("DOMContentLoaded", () => {
  new DogAPI();
});
