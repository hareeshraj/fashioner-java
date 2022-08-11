$.ajax({
    url:"getbill",
    type:"GET",
    success:function(res){
        document.querySelector("#billname").innerHTML="Name : "+res[0].name;
        document.querySelector("#billprice").innerHTML="Amount Paid : "+res[0].price;
        document.querySelector("#billdate").innerHTML="Order Date : "+res[0].date;
        document.querySelector("#billtime").innerHTML="Order Time : "+res[0].time;

    }
});