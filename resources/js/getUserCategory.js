$.ajax({
    url:"getcategory",
    type:"GET",
    success:function(res)
    {
        console.log(res);
        var dynamic=document.querySelector("#getUserCategory");
        for(var i=0;i<res.length;i++){
            var fetch=document.querySelector("#getUserCategory").innerHTML;
            dynamic.innerHTML=`  <li><a  style="font-family: Lucida Console;" onclick="getcategoryproduct(${res[i].categoryId})"> ${res[i].categoryname}</a></li>`+fetch;
        
        }
     
    }
});
