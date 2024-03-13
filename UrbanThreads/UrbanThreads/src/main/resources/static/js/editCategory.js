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

document.getElementById('categoryColor').addEventListener('input', function() {
    let rgbColor = this.value.match(/\d+/g);
    document.getElementById('hexColor').value = rgbToHex(rgbColor[0], rgbColor[1], rgbColor[2]);
});

// Function to convert RGB to Hex
function rgbToHex(r, g, b) {
    r = Math.round(parseInt(r)).toString(16);
    g = Math.round(parseInt(g)).toString(16);
    b = Math.round(parseInt(b)).toString(16);

    if (r.length === 1) r = "0" + r;
    if (g.length === 1) g = "0" + g;
    if (b.length === 1) b = "0" + b;

    return "#" + r + g + b;
}
