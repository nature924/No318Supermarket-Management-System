var productname = null;
var price = null;
var purchaseprice = null;
var manufacturer = null;
var place = null;
var addBtn = null;
var backBtn = null;

$(function(){
    productname = $("#productname");
    price = $("#price");
    purchaseprice = $("#purchaseprice");
    manufacturer = $("#manufacturer");
    place = $("#place");
    addBtn = $("#add");
    backBtn = $("#back");

    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    productname.next().html("*");
    price.next().html("*");
    purchaseprice.next().html("*");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    productname.on("focus",function(){
        validateTip(productname.next(),{"color":"#666666"},"* 请输入商品名称",false);
    }).on("blur",function(){
        if(productname.val() != null && productname.val() != ""){
            validateTip(productname.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(productname.next(),{"color":"red"},imgNo+" 商品名称不能为空，请重新输入",false);
        }

    });

    price.on("focus",function(){
        validateTip(price.next(),{"color":"#666666"},"* 请输入商品进价",false);
    }).on("blur",function(){
        if(price.val() != null && price.val() != ""){
            validateTip(price.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(price.next(),{"color":"red"},imgNo+"商品进价不能为空，请重新输入",false);
        }
    });

    purchaseprice.on("focus",function(){
        validateTip(purchaseprice.next(),{"color":"#666666"},"* 请输入商品售价",false);
    }).on("blur",function(){
        if(purchaseprice.val() != null && purchaseprice.val() != ""){
            validateTip(purchaseprice.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(purchaseprice.next(),{"color":"red"},imgNo+"商品售价不能为空，请重新输入",false);
        }

    });



    addBtn.bind("click",function(){
        if(productname.attr("validateStatus") != "true"){
            productname.blur();
        }else if(price.attr("validateStatus") != "true"){
            price.blur();
        }else if(purchaseprice.attr("validateStatus") != "true"){
            purchaseprice.blur();
        }else{
            if(confirm("是否确认提交数据")){
                $("#productForm").submit();
            }
        }
    });

    backBtn.on("click",function(){
        if(referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });
});