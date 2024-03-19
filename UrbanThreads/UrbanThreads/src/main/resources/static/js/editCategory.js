function  warnDelete() {
    var confirmation = confirm("¿deseas eliminar esta categoria?");

    if (confirmation) {
        alert("La categoria ha sido eliminada");
        return true;
    } else {
        return false;
    }
}
function  warnCreate() {
    var confirmation = confirm("Si se crea una una categoria con un nombre ya existente," +
        " no se creará");

    if (confirmation) {
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

$(document).ready(function() {
    $('.editCategory').click(function() {
        $('#editCategoryModal').modal('show');
    });
});

