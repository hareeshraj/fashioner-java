//buynow
$("#buyNow").submit(function(e){
    var productid=$("#buynowProductid").val();
    var quantity=$("#buynowquantity").val();
    var price=$("#buynowtotal").val();
    var totalquantity=$("#totalqty").val();
    // alert(productid+" "+price+" "+quantity);
    if (confirm("Are you sure to place order?")) {
        var data={
            "buynowProductid":productid,
            "buynowquantity":quantity,
            "buynowtotal":price,
            "totalquantity":totalquantity

        };
    $.ajax({
        url:"buynow",
        type:"POST",
        data:data,
        success:function(res){
            console.log(res);
            window.location.replace("success");
            
        },
        error:function(res){
            alert("Something went wrong");
        }
    });
}
    e.preventDefault();


});
//totalpriceupdate
function totalpriceupdate()
{
    var quantity=$("#buynowquantity").val();
    var price=$("#buynowprice").val();
    $("#buynowtotal").val(quantity*price);
}
//set order summary
function setordersummary(name,price,quantity,productid,address)
{
    var qty=$("#qty").val();
    // alert(name+" "+price+" "+qty);
    
    $("#buynowProductname").val(name);
    $("#buynowprice").val(price);
    $("#buynowaddress").val(address);
    $("#buynowquantity").val(1);
    $("#buynowquantity").attr("max",quantity);
    $("#buynowtotal").val(price);
    $("#buynowProductid").val(productid);
    $("#totalqty").val(quantity);


    
 
    // <p>Qty: <input type="number" size="2" min="0" max=${res[i].quantity} id="qty" style="background-color:#EAEDED;border-radius:8px;text-align:center "></p>
                
}

//add to cart
function addToCart(productId)
{
   
    // alert("product id "+productId);
    data={
        "productid":productId
    };
    $.ajax({
        url:"addtocart",
        type:"POST",
        data:data,
        success:function(res){
            console.log(res);
            alert("Product added to cart");
        },
        error:function(res){
            console.log(res);
            if(res.responseText==="product already exist")
            {
                alert("product already exist in cart");
            }
        }

    })

}
//load cart page
function loadcartpage()
{
    window.location.replace("Cart");
}
// load order page 
function loadorderpage()
{
    window.location.replace("Userorders");
}
//load home page
function onloadhome(){
    window.location.replace("User");
}
//get category products
function getcategoryproduct(categoryid)
{
    // alert("category id"+categoryid);
    var data={
        id:categoryid
    };
    $.ajax({
        url:"getcategoryproduct",
        type:"GET",
        data:data,
        success:function(res)
        {
            console.log(res);
            document.querySelector("#filterproduct").innerHTML=res[0].categoryname;
            document.querySelector("#productcard").innerHTML="";
            var dynamic=document.querySelector("#productcard");
        for(var i=0;i<res.length-1;i++)
        {
            var fetch=document.querySelector("#productcard").innerHTML;
            dynamic.innerHTML=`      <div class="card">
            <div class="card__header">
              <img src="uploads/${res[i].image}" alt="card__image" class="card__image" width="600">
            </div>
            <div class="card__body">
              <p style=" font-size:12px" class="tag tag-blue">${res[i].categoryname}</p>
              <h4>${res[i].productname}</h4>
            
              <p>Price : ${res[i].price} /-</p>
              <p style="color:red;text-align:center" >only ${res[i].quantity} left</p> 

            </div>
            
            <div class="btn-group">
            <button type="button" class="btn btn-default" id="cartbtn" onclick="addToCart(${res[i].productId})">ADD TO CART</button>
            <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#buynowModal" onclick="setordersummary('${res[i].productname}',${res[i].price},${res[i].quantity},${res[i].productId},'${res[res.length-1].address}')" >BUY NOW</button>
          </div>

          </div>`+ fetch;

        }
        }
    })
    
}
//change password
$("#changepassword").submit(function(e)
{

    if (confirm("Are you sure to change password?")) {
        var form=$(this);
        $.ajax({
            url:"changeuserpassword",
            type:"POST",
            data:form.serialize(),
            success:function(res){
                console.log(res);
                alert("succesfully password changed");
                $.ajax({
                    url: "logout",
                    type: "GET",
                  });
                window.location.replace("Login");
               
            },
            error:function(res){
                if(res.responseText==="Password mismatch")
                {
                    $("#oldpassword").val("");  
                }
                document.getElementById("passworderror").innerHTML=res.responseText;
                
    
            }
    
        });
    }
    e.preventDefault();
    

});

// update name
function updatename(){
    if (confirm("Are you sure to update profile?")) {    
    var name=$("#username").val();
    var address=$("#useraddress").val();
    var data={
        "name":name,
        "address":address
    };
    $.ajax({
        url:"updateusername",
        type:"POST",
        data:data,
        success:function(res)
        {
            window.location.replace("User");
        }

    });
}
}
//set edit name
function editname(){
    document.getElementById("username").disabled = false;
    document.getElementById("useraddress").disabled = false;

}