function editarPedidoMostrar() {
    $('#eliminarProductoModal').modal('show');
}
/*
    function eliminarProducto( productId ) {
        $.ajax({
            type: "POST",
            url: "/deleteProductOrder",
            data: { productId: productId },
            success: function(response) {
                window.location.href = "/orderPage";
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });*/


function cancelarPedido(){
    var confirmation = confirm("Si cancela, se borrará su pedido y deberá empezar de nuevo");

    if (confirmation) {
        alert("El pedido ha sido eliminado");
        return true;
    } else {
        alert("El pedido sigue vigente");
        return false;
    }
}

function realizarPedido(){
    var confirmation = confirm("Si acepta, realizará su pedido");

    if (confirmation) {
        alert("El pedido ha sido realizado");
        return true;
    } else {
        alert("El pedido no se ha realizado, puede continuar su compra");
        return false;
    }
}

function cambiarPedido(){
    document.getElementById("orderSelection").submit();
}

function editarProducto(id){
    document.getElementById("editButton-"+id).classList.add("d-none");
    document.getElementById("currentQuantity-"+id).classList.add("d-none");
    document.getElementById("editQuantity-"+id).classList.remove("d-none");
}

