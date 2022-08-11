$.ajax({
    url:"getCartProduct",
    type:"GET",
    success:function(res){
        console.log(res);
        $("#placeorder").val(res.length);
        var totalprice=0;
        var dynamic=document.querySelector("#cartproduct");
        for(var i=0;i<res.length;i++)
        {
            totalprice=totalprice+res[i].price;
            var fetch=document.querySelector("#cartproduct").innerHTML;

            dynamic.innerHTML=`
            <div class="card" style="margin-bottom: 40px;">
            <h5 class="card-header">${res[i].productname}</h5>
            <div class="card-body">
                <div style="float: right;">
                    <img src="uploads/${res[i].image}" height="200px" width="300px">          
                </div>
 
              <h5 class="card-title">Price : ${res[i].price} /-</h5>
              <p>Qty: <input type="number" size="2" min="0" max=${res[i].quantity} id="qty${i}" value=1 style="background-color:white;border-radius:8px;text-align:center "></p>
                <input type="hidden" id="productid${i}" value=${res[i].productId}>
              <p class="card-text" style="color:red">Only ${res[i].quantity} left</p>
              <a  class="btn btn-primary" type="button" onclick="removefromcart(${res[i].productId})" style="margin-top:30px;"><i class="fa fa-trash-o" aria-hidden="true"></i> Remove</a>
            </div>
          </div>`+fetch;
        }

        
        // document.getElementById("totalprice").innerHTML="Total Price : "+totalprice+"/-";
    }

});