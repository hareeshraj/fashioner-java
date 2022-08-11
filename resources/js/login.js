
$("#login").submit(function(e)
{
    
    document.getElementById("error").innerHTML="";
    var mobilenumber=$("#mobilenumber").val();
    var password=$("#password").val();
    var isChecked=$("#check").is(':checked');
    var role;
    if(isChecked==true)
    {
        role="Seller";
    }else{
        role="User";
    }
    // alert("number "+mobilenumber+" password "+password+" role "+role);
    var data={
        "mobilenumber":mobilenumber,
        "password":password,
        "role":role
    };
    $.ajax({
        url:"login",
        type:"POST",
        data:data,
        success:function(res){
            console.log("success "+res);
            alert("Succesfully loggedin as "+res.role);
            if(res.role=="User")
            {
                window.location.replace("User");
            }else if(res.role=="Seller"){
                 window.location.replace("Seller");
            }
            
        },
        error:function(res){
            console.log(res);
            if(res.responseText === "Enter 10 digit mobileNumber")
            {
                $("#mobilenumber").val("");
            }
            else if(res.responseText === "User not found" || res.responseText === "Seller not found")
            {
                $("#mobilenumber").val("");
            }
            else if(res.responseText === "User password wrong" || res.responseText==="Seller password wrong")
            {
                $("#password").val("");
            }
            document.getElementById("error").innerHTML=res.responseText;
              
        }

    });
    e.preventDefault();

});


