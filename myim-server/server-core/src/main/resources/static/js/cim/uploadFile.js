var imgSuffix = ["jpg","jpeg","png"]
bindAvatar()
function bindAvatar() {
    if(window.URL.createObjectURL){
        bindAvatar3();
    }else if(window.FileReader){
        bindAvatar2();
    }else {
        bindAvatar1();
    }
}
/*Ajax上传至后台并返回图片的url*/
function bindAvatar1() {
    $("#avatarSlect").change(function () {
        var csrf = $("input[name='csrfmiddlewaretoken']").val();
        var formData=new FormData();
        formData.append("csrfmiddlewaretoken",csrf);
        formData.append('avatar', $("#avatarSlect")[0].files[0]);  /*获取上传的图片对象*/
        $.ajax({
            url: '/upload_avatar/',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (args) {
                console.log(args);  /*服务器端的图片地址*/
                $("#avatarPreview").attr('src','/'+args);  /*预览图片*/
                $("#avatar").val('/'+args);  /*将服务端的图片url赋值给form表单的隐藏input标签*/
            }
        })
    })
}
/*window.FileReader本地预览*/
function bindAvatar2() {
    console.log(2);
    $("#avatarSlect").change(function () {
        var obj=$("#avatarSlect")[0].files[0];
        var fr=new FileReader();
        fr.onload=function () {
            $("#avatarPreview").attr('src',this.result);
            console.log(this.result);
            $("#avatar").val(this.result);
        };
        fr.readAsDataURL(obj);
    })
}
/*window.URL.createObjectURL本地预览*/
function bindAvatar3() {
    console.log(3);
    $("#avatarSlect").change(function () {
        var obj=$("#avatarSlect")[0].files[0];
        var wuc=window.URL.createObjectURL(obj);
        $("#avatarPreview").remove();
        if (inArray(imgSuffix, obj.name.split(".").slice(-1)[0])) {
            $("#appendFile").append("<img id='avatarPreview' src='" + wuc + "' title='点击更换图片' style='position: absolute; z-index: 9; float: left; width: 100px; height: 100px;'>")
        }else{
            $("#appendFile").append("<div id='avatarPreview' title='点击更换文件' style='position: absolute; z-index: 9; float: left; width: 100px; height: 100px;'>" + obj.name + "</div>")
        }
        $("#avatar").val(wuc);
    })
}

var inArray = function(arr, item) {
    for(var i = 0; i < arr.length; i++) {
        if(arr[i].toLowerCase() == item.toLowerCase()) {
            return true;
        }
    }
    return false;
};

function uploadFile() {
    var formData=new FormData();
    formData.append('applyFiles', $("#avatarSlect")[0].files[0]);  /*获取上传的图片对象*/
    $.ajax({
        url: 'http://localhost:8480/api/file/target/mul',
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function (result) {
            console.log(result);  /*服务器端的图片地址*/

            for(let key  in result){
                $("#messageList").append("<h6 style='text-align: right;'>" + "自己:" + $('#account').val() + " 时间:" + new Date().toLocaleString() + "</h6>");
                if (inArray(imgSuffix, key.split(".").slice(-1)[0])) {
                    $("#messageList").append("<img  src='" + result[key] + "' title='' style='text-align: right;' class='alert alert-info'>")
                }else{
                    $("#messageList").append("<a id='download' href='"  + result[key] + "' title='点击下载文件' target='_blank' style='text-align: right;' class='alert alert-info'>" + key + "</a>")
                }
            }
            $('#messageList').scrollTop( $('#messageList')[0].scrollHeight )
        }
    })
}