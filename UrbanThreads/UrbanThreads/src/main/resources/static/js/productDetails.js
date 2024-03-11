function checkStock(xs, s, m, l, xl, xxl) {
    let selectedSize = document.getElementById("sizeSelect").value;
    let selectedSizeQuantity = 0;
    switch (selectedSize) {
        case 'XS':
            selectedSizeQuantity = xs;
            break;
        case 'S':
            selectedSizeQuantity = s;
            break;
        case 'M':
            selectedSizeQuantity = m;
            break;
        case 'L':
            selectedSizeQuantity = l;
            break;
        case 'XL':
            selectedSizeQuantity = xl;
            break;
        case 'XXL':
            selectedSizeQuantity = xxl;
            break;
            break;
    }

    let quantityField = document.getElementById("quantity");
    quantityField.max = selectedSizeQuantity;
    if (quantityField.value > selectedSizeQuantity) {
        quantityField.value = selectedSizeQuantity;
    }

    let availabilityTag = document.getElementById("availability");
    let addToCartButton = document.getElementById("addToCart");

    if (selectedSizeQuantity > 0) {
        availabilityTag.innerText = ("En stock. Quedan " + selectedSizeQuantity + " unidades");

        availabilityTag.classList.remove("d-none");
        availabilityTag.classList.remove("text-danger");
        availabilityTag.classList.add("text-success");

        addToCartButton.classList.remove("d-none");
        addToCartButton.classList.add("d-block");
    } else {
        availabilityTag.innerText = ("No disponible en stock");

        availabilityTag.classList.remove("d-none");
        availabilityTag.classList.remove("text-success");
        availabilityTag.classList.add("text-danger");

        addToCartButton.classList.remove("d-block");
        addToCartButton.classList.add("d-none");
    }

}

function confirmDelete() {
    var confirmation = confirm("¿deseas eliminar este producto?");

    if (confirmation) {
        alert("El producto ha sido eliminado");
        return true;
    } else {
        alert("El producto no ha sido eliminado");
        return false;
    }

}
