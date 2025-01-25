var membername = null;
var phone = null;
var age = null;
var endtime = null;
var addBtn = null;
var backBtn = null;

$(function(){
    membername = $("#membername");
    phone = $("#phone");
    age = $("#age");
    endtime = $("#endtime");
    addBtn = $("#add");
    backBtn = $("#back");

    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    membername.next().html("*");
    phone.next().html("*");
    endtime.next().html("*");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    membername.on("focus",function(){
        validateTip(membername.next(),{"color":"#666666"},"* 请输入会员名称",false);
    }).on("blur",function(){
        if(membername.val() != null && membername.val() != ""){
            validateTip(membername.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(membername.next(),{"color":"red"},imgNo+" 会员名称不能为空，请重新输入",false);
        }

    });

    phone.on("focus",function(){
        validateTip(phone.next(),{"color":"#666666"},"* 请输入会员电话",false);
    }).on("blur",function(){
        if(phone.val() != null && phone.val() != ""){
            validateTip(phone.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(phone.next(),{"color":"red"},imgNo+"会员电话不能为空，请重新输入",false);
        }
    });

    endtime.on("focus",function(){
        validateTip(endtime.next(),{"color":"#666666"},"* 请输入会员期限",false);
    }).on("blur",function(){
        if(endtime.val() != null && endtime.val() != ""){
            validateTip(endtime.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(endtime.next(),{"color":"red"},imgNo+"会员期限不能为空，请重新输入",false);
        }

    });

    addBtn.bind("click",function(){
        if(membername.attr("validateStatus") != "true"){
            membername.blur();
        }else if(phone.attr("validateStatus") != "true"){
            phone.blur();
        } else if(endtime.attr("validateStatus") != "true"){
            endtime.blur();
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