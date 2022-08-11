//place order
$("#cartplaceorder").submit(function(e)
{
    const cart = new Map();
    var length=$("#placeorder").val();
    if (confirm("Are you sure to place order?")) {
    for(var i=0;i<length;i++)
    {
      var productid=$("#productid"+i).val();
      var quantity=$("#qty"+i).val();
    //   alert(productid+" "+ quantity );
      cart.set(productid,quantity);
    }
    var value=JSON.stringify(Object.fromEntries(cart));
   var data={
        "map":value
    };
    $.ajax({
        url:"cartorder",
        type:"POST",
        data:data,
        success:function(res){
            console.log(res);
            window.location.replace("success");

        }
    });
    }
    e.preventDefault();
});

//remove from cart
function removefromcart(productid){
    // alert("remove");
    data={
        "id":productid
    };
    $.ajax({
        url:"removefromcart",
        type:"POST",
        data:data,
        success:function(res){
            alert("Item succesfully removed");
            window.location.replace("Cart");

        }

    });
}
// to load home page
function onloadhome(){
    window.location.replace("User");
}