function comprar() {
  alert("¡Producto electrónico agregado al carrito!");
}

function validarFormulario() {
  let nombre = document.getElementById("nombre").value;
  let correo = document.getElementById("correo").value;

  if (nombre === "") {
    alert("Error: El campo de nombre no puede estar vacío.");
    return false;
  }

  if (!correo.includes("@")) {
    alert(
      "Error: Por favor, ingresa un correo electrónico válido (debe contener '@').",
    );
    return false;
  }

  alert(
    "¡Gracias por contactar a TechStore, " +
      nombre +
      "! Te responderemos pronto.",
  );

  document.getElementById("nombre").value = "";
  document.getElementById("correo").value = "";
  document.getElementById("mensaje").value = "";

  return true;
}
