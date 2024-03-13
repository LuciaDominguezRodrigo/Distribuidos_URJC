function  warnDelete() {
    var confirmation = confirm("¿deseas eliminar esta categoria?");

    if (confirmation) {
        alert("La categoria ha sido eliminada");
        return true;
    } else {
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
        return true;
    } else {
        return false;
    }
}