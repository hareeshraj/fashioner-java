$.ajax({
    url:"getcategory",
    type:"GET",
    success:function(res)
    {
        console.log(res);
        var dynamic=document.querySelector("#category");
        for(var i=0;i<res.length;i++){
            var fetch=document.querySelector("#category").innerHTML;
            dynamic.innerHTML=` <option>${res[i].categoryname}</option>`+fetch;
        }
     
    }
});