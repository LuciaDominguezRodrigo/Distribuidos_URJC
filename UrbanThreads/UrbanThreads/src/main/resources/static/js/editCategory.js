function  warnDelete() {
    var confirmation = confirm("¿deseas eliminar esta categoria?");

    if (confirmation) {
        alert("La categoria ha sido eliminada");
        return true;
    } else {
        alert("La categoria no ha sido eliminada");
        return false;
    }
}