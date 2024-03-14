async function loadMore() {
    const response = await fetch(`/newProducts`);
    const newEvent = await response.text();
    const eventContainerStructure = document.getElementById("productsChart");
    eventContainerStructure.innerHTML += newEvent;
}

async function filtrarProductos(){
    document.getElementById("filterSelection").submit();
}