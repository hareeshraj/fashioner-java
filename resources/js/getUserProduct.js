$.ajax({
    url:"getuserproduct",
    type:"GET",
    success:function(res)
    {
        console.log(res);
        // alert(res.length);
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
            <button type="button"  onclick="setordersummary('${res[i].productname}',${res[i].price},${res[i].quantity},${res[i].productId},'${res[res.length-1].address}')" class="btn btn-primary" id="buybtn" data-toggle="modal" data-target="#buynowModal">BUY NOW</button>
          </div>

          </div>`+ fetch;

        }
        

    },
    error:function(res){
        console.log(res);

    }

});











