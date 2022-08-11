//load orders
function vieworders()
{
    window.location.replace("Sellerorders");
}
//delete category
function deleteCategory(id){
    if (confirm("Are you sure delete category?")) {   
      
        var data={
            "id":id
        };
        $.ajax({
            url:"deletecategory",
            type:"POST",
            data:data,
            success:function(res)
            {
                alert("category deleted succesfully");
                window.location.replace("Seller");
            }
    
        });
    }
    
}
// update name
function updatename(){
    if (confirm("Are you sure to update profile?")) {    
    var name=$("#sellername").val();
    var data={
        "name":name
    };
    $.ajax({
        url:"updatename",
        type:"POST",
        data:data,
        success:function(res)
        {
            window.location.replace("Seller");
        }

    });
}
}
//set edit name
function editname(){
    document.getElementById("sellername").disabled = false;

}
//change password
$("#changepassword").submit(function(e)
{

    if (confirm("Are you sure to change password?")) {
        var form=$(this);
        $.ajax({
            url:"changepassword",
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
//update product
function updateproduct1()
{
    document.getElementById("updateproducterror").innerHTML="";
    var name=  $("#updatename").val();
    var id=  $("#updateproductid").val();
    var quantity=$("#updatequantity").val();
   
    var price=$("#updateprice").val();
    
   
    var data={
        "productid":id,
        "name":name,
        "quantity":quantity,
        "price":price,
    };

    // alert("update "+name+" "+id);
    if (confirm("Are you sure to Update product?")) {
        $.ajax({
            url:"updateproduct",
            type:"POST",
            data:data,
            success:function(res){
                console.log(res);
                alert("Product updated succesfully");
                window.location.replace("Seller");
            },
            error:function(res){
                alert("Something went wrong");
    
            }
    
        });
    }

}
//set product
function setproduct(id,name,quantity,price,category)
{
  
    // alert("update"+id+name);
    $("#updatename").val(name);
    $("#updatequantity").val(quantity);
    $("#updatecategory").val(category);
    $("#updateprice").val(price);
    $("#updateproductid").val(id);
  
}
function deleteproduct(id)
{
 
    var data={
        "productid":id
    };
    if (confirm("Are you sure to delete product?")) {
    $.ajax({
        url:"deleteproduct",
        type:"POST",
        data:data,
        success:function(res){
            console.log(res);
            alert("Product deleted succesfully");
            window.location.replace("Seller");
        },
        error:function(res){
            alert("Something went wrong");

        }

    });
}

}
// add product
$("#product").submit(function(e)
{

    document.getElementById("producterror").innerHTML=""; 
    if (confirm("Are you sure to add product?")) {
        var formdata = $('#product')[0];
        var form1 = new FormData(formdata);
        form1.append("file",image_upload.files[0]);
    $.ajax({
        url:"addproduct",
        type:"POST",
        processData:false,
        contentType:false,
        cache: false,
        data:form1,
        success:function(res){
            console.log("success"+res);
            alert("Product added succesfully");
            window.location.replace("Seller");

        },
        error:function(res){
            console.log(res);
            if(res.responseText === "You already added this product")
            {
                $("#name").val("");
            }
            document.getElementById("producterror").innerHTML=res.responseText;

        }

    }); 
}
    e.preventDefault();
});


//add product category
$("#productCategory").submit(function(e)
{
    document.getElementById("categoryerror").innerHTML="";
    var form = $(this);
    if (confirm("Are you sure to add this category?")) {
    $.ajax({
        url:"addcategory",
        type:"POST",
        data:form.serialize(),
        success:function(res)
        {
            console.log("success "+res);
            alert("Category added succesfully");
            window.location.replace("Seller");
        },
        error:function(res)
        {
            if(res.responseText === "Category already exist")
            {
                $("#categoryname").val("");
            }
            document.getElementById("categoryerror").innerHTML=res.responseText;

        }


    });
}

    e.preventDefault();
});








