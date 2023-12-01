function updateCartItemCount(cartItemId, count) { // 시큐리티 사용할 경우 기본적으로 post방식
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var url = "/cartItem/" + cartItemId + "?count=" + count;

    $.ajax({
        url : url,
        type : "PATCH",
        beforeSend : function(xhr) {
            /* 데이터 전송하기 전에 헤더 csrf값을 설정*/
            xhr.setRequestHeader(header, token);
        },
        dataType: "json",
        cache : false,
        success : function(result, status){
            console.log("cartItem count update success")
        },
        error : function(jqXHR, status, error) {
            if(jqXHR.status == '401'){
                alert ('로그인 후 이용해주세요.');
                location.href='/members/login';
            } else {
                alert(jqXHR.responseJSON.message);
            }
        }
    });
}