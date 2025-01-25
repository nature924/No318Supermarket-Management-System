var memberObj;

function deleteProvider(obj){
    $.ajax({
        type:"POST",
        url:path+"/memberdel.json",
        data:{proid:obj.attr("proid")},
        dataType:"json",
        success:function(data){
            if(data.delResult == "true"){//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            }else if(data.delResult == "false"){//删除失败
                changeDLGContent("对不起，删除会员【"+obj.attr("proname")+"】失败");
            }else if(data.delResult == "notexist"){
                changeDLGContent("对不起，会员【"+obj.attr("proname")+"】不存在");
            }
        },
        error:function(data){
            //alert("对不起，删除失败");
            changeDLGContent("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG(){
    $('.zhezhao').css('display', 'block');
    $('#removeProv').fadeIn();
}

function cancleBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removeProv').fadeOut();
}
function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}
$(function(){
    // $(".viewProvider").on("click",function(){
    //     //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
    //     var obj = $(this);
    //     window.location.href=path+"/providerview.html?&proid="+ obj.attr("proid");
    // });

    $(".modifyMember").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/membermodify.html?&proid="+ obj.attr("proid");
    });

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteProvider(memberObj);
    });

    $(".deleteMember").on("click",function(){
        memberObj = $(this);
        changeDLGContent("你确定要删除会员【"+memberObj.attr("proname")+"】吗？");
        openYesOrNoDLG();
    });
});