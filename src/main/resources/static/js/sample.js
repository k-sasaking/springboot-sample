
/* Ajaxの処理 */
$(function(){
    // Pyblish/Unpublishボタンが押された時
    $('.publish-btn').click(function(){
        
		var btnId = $(this).val();
		var doPublish = $(this).text() == "Publish" ? "publish/" : "unpublish/" ;
		var doPublishFlg = $(this).text() == "Publish" ? true : false ;
		var result = false
		
		// 初期メッセージを表示
		if(doPublishFlg){
	    	result = confirm("公開しますか？");
		} else{
	    	result = confirm("非公開にしますか？");			
		}
    	if(result){
    		
	        // Ajaxの定義
	        $.ajax({
	          type: "GET",
	          url: "/admin/" + doPublish + btnId,
	          async: true,
	          dataType: 'json',
	          // successの処理
	          success: function(data) {
	              success(data, btnId, doPublishFlg);
	          },
	          // errorの処理
	          error: function(XMLHttpRequest, textStatus, errorThrown) {
	              error(XMLHttpRequest, textStatus, errorThrown);
	          }
	        });
    	}
    });
        
});

// Ajax通信成功時処理
function success(data, btnId, flg) {
	if(flg){
	  alert("公開しました");
	  $("#pubtbn-" + btnId).removeClass('btn-primary');
	  $("#pubtbn-" + btnId).addClass('btn-info');
	  $("#pubtbn-" + btnId).text('Unpublish');
	}else {
	  alert("非公開にしました");
	  $("#pubtbn-" + btnId).removeClass('btn-info');
	  $("#pubtbn-" + btnId).addClass('btn-primary');		
	  $("#pubtbn-" + btnId).text('Publish');
	}
}
 
// Ajax通信失敗時処理
function error(XMLHttpRequest, textStatus, errorThrown) {
  alert("error" + textStatus);	
}
