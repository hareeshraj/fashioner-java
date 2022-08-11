$.ajax({
    url:"editcategory",
    type:"GET",
    success:function(res)
    {
        console.log(res);
        var dynamic=document.querySelector("#editcategory");
        for(var i=0;i<res.length;i++){
            var fetch=document.querySelector("#editcategory").innerHTML;
            dynamic.innerHTML=`  <div>
            <input type="text" class="form-control-md" id="" value=${res[i].categoryname} name="" disabled>
            <button class="btn btn-primary" type ="button" id="deletecategory" onclick="deleteCategory(${res[i].categoryId})"><i class="fa fa-trash"></i>  Delete</button>
          </div><br>`+fetch;
        }
     
    }
});