$.ajax({
    url:"getproduct",
    type:"GET",
    success:function(res)
    {
        console.log(res);
        // alert(res.length);
        var dynamic=document.querySelector("#productcard");
        for(var i=0;i<res.length;i++)
        {
            var fetch=document.querySelector("#productcard").innerHTML;

            dynamic.innerHTML=`      <div class="card">
            <div class="card__header">
              <img src="uploads/${res[i].image}" alt="card__image" class="card__image" width="600">
            </div>
            <div class="card__body">
              <p style=" font-size:12px" class="tag tag-blue">${res[i].categoryname}</p>
              <h4>${res[i].productname}</h4>
              <p>Available Quantity : ${res[i].quantity}</p>
              <p>Price : ${res[i].price} /-</p>
               
            </div>
            <div class="btn-group">
              
              <button type="button"  style="padding-left: 20px;padding-right: 16px;" onclick="deleteproduct(${res[i].productId})" class="btn btn-danger"><i class="fa fa-trash"></i>  Delete</button>
                <button type="button" style="padding-left: 21px;padding-right: 16px;"  onclick="setproduct(${res[i].productId},'${res[i].productname}',${res[i].quantity},${res[i].price},${res[i].categoryId},'${res[i].image}')" class="btn btn-primary" data-toggle="modal" data-target="#updateModal" ><i class="fa fa-pencil-square-o"></i>  Update</button>
  
                <!-- Modal -->
                <div class="modal fade" id="updateModal" role="dialog">
                  <div class="modal-dialog">
                  
                    <!-- Modal content-->
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Update Product</h4>
                      </div>
            
                      <div style="padding:20px ;">

                      <form name="updateproduct" id="updateproduct" >
                      <input type="hidden"  id="updateproductid">
                      <div class="form-group">
                        <label for="email">Product Name</label>
                        <input type="text" class="form-control" id="updatename" name="name" placeholder="Enter Product Name"  required>
                      </div>
                      <div class="form-group">
                        <label for="pwd">Quantity</label>
                        <input type="text" class="form-control" id="updatequantity" name="quantity" placeholder="Product Quantity" required>
                      </div>
                     
                      <div class="form-group">
                          <label for="pwd">Price</label>
                          <input type="text" class="form-control" id="updateprice" name="price" placeholder="Product Price" required>
                      </div>
                     
                    <p id="updateproducterror" style="color:red ;text-align: center;"></p>
      
                      <button type="button" onclick="updateproduct1()" class="btn btn-primary">Update product</button>
      
                    </form>
                      </div>
                      
            
            
            
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      </div>
                    </div>
                    
                  </div>
                </div>

                </form>

                </div>
              </div>
            </div>
          </div>`+ fetch;

        }
        

    },
    error:function(res){
        console.log(res);

    }

});