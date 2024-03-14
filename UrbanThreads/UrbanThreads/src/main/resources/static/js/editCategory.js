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

$(document).ready(function() {
    $('.editCategory').click(function() {
        $('#editCategoryModal').modal('show');
    });
});


document.getElementById('categoryColor').addEventListener('input', function() {
    let rgbColor = this.value.match(/\d+/g);
})
