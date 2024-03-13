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
$(document).ready(function() {
    $('.createButton').click(function() {
        $('#createCategoryModal').modal('show');
    });
});

function  warnCreate() {
    var confirmation = confirm("¿deseas crear esta nueva categoria?");

    if (confirmation) {
        alert("La categoria ha sido creada");
        return true;
    } else {
        alert("La categoria no ha sido creada");
        return false;
    }
}