function mostrarOcultarInfo(id) {
  const elemento = document.getElementById(id);
  elemento.classList.toggle("oculto");
}

function resaltarCanciones() {
  const lista = document.getElementById("listaCanciones");
  lista.classList.toggle("resaltado");
}
