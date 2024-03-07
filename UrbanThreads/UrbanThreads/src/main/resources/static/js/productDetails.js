function checkStock(xs, s, m, l, xl, xxl) {
    let selectedSize = document.getElementById("sizeSelect").value;
    let selectedSizeQuantity = 0;
    switch (selectedSize) {
        case 'XS': selectedSizeQuantity = xs;break;
        case 'S': selectedSizeQuantity = s;break;
        case 'M': selectedSizeQuantity = m;break;
        case 'L': selectedSizeQuantity = l;break;
        case 'XL': selectedSizeQuantity = xl;break;
        case 'XXL': selectedSizeQuantity = xxl;break;
        break;
    }
    let availabilityTag = document.getElementById("availability");

    if (selectedSizeQuantity > 0){
        availabilityTag.innerText = ("En stock. Quedan " + selectedSizeQuantity + " unidades");
        availabilityTag.classList.remove("d-none");
        availabilityTag.classList.remove("text-danger");
        availabilityTag.classList.add("text-success");
    }
    else{
        availabilityTag.innerText = ("No disponible en stock");
        availabilityTag.classList.remove("d-none");
        availabilityTag.classList.remove("text-success");
        availabilityTag.classList.add("text-danger");
    }

}