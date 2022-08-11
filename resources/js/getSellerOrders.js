$.ajax({
    url:"getsellerorders",
    type:"GET",
    success:function(res){
        console.log(res);

        var dynamic=document.querySelector("#sellerorder");
        for(var i=0;i<res.length;i++)
        {
            
            var fetch=document.querySelector("#sellerorder").innerHTML;
            dynamic.innerHTML=`
            <div class="card" style="margin-bottom: 40px;">
            <h5 class="card-header" style="color: #2C3E50">${res[i].productname}</h5>
            <div class="card-body">
                <div style="float: right;">
                    <img src="uploads/${res[i].image}" height="200px" width="300px">          
                </div>
                <p class="card-text" >Quantity : ${res[i].quantity}</p>
              <h5 class="card-title" style="color:#99A3A4">Price : ${res[i].price} /-</h5>
             <p style="margin-top:50px; ">Ordered By : ${res[i].name}</p>
             <p>Mobile Number : ${res[i].mobilenumber}</p>
             <p>Delivery Address : ${res[i].address}</p>
              <p >Order date :  ${res[i].date}</p>

             <p>Order time :  ${res[i].time}</p>

             

             
                 </div>
          </div>`+fetch;
        }

    }

});
