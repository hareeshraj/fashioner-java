$("#signup").submit(function(e)
{
    document.getElementById("error").innerHTML="";
    var form = $(this);
    $.ajax({
        url:"signup",
        type:"POST",
        data:form.serialize(),
        success:function(res){
            console.log("success "+res);
            alert("Succesfully signuped");
            window.location.replace("Login");
        },
        error:function(res){
            console.log(res);
            if(res.responseText === "Mobile number already exist")
            {
                $("#mobilenumber").val("");
            }
            else if(res.responseText === "Enter 10 digit mobileNumber")
            {
                $("#mobilenumber").val("");
            }
            else if(res.responseText === "password mismatch")
            {
               
            $("#password").val("");
            $("#confirmpassword").val("");
            }
            document.getElementById("error").innerHTML=res.responseText;
              
        }

    });
    e.preventDefault();

});