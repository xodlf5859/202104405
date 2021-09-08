<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

   <script>
window.onload = function(){
	MemberPictureThumb($('#pictureView')[0], '${member.picture}', '<%=request.getContextPath()%>');
 	}

function changePicture_go(){
	//파일 불러오기
	var picture = $('input#inputFile')[0];
	//파일 포맷확인
	var fileFormat = picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
	
	//이미지 확장자 JPG 확인
	if(!(fileFormat=="JPG" || fileFormat=="JPEG")){
	      alert("이미지는 jpg/jpeg 형식만 가능합니다.");
	      return;
	 }
	//이미지 파일 용량 체크
    if(picture.files[0].size>1024*1024*1){
	      alert("사진 용량은 1MB 이하만 가능합니다.");
	      return;
   	};
   	
   document.getElementById('inputFileName').value=picture.files[0].name;
   	
   
   if (picture.files && picture.files[0]) {
	      var reader = new FileReader();
	      
	       reader.onload = function (e) {
	    	   //이미지 미리보기
	           $('div#pictureView')
	              .css({'background-image':'url('+e.target.result+')',
	                 'background-position':'center',
	                 'background-size':'cover',
	                 'background-repeat':'no-repeat'
	                 });
	        }
	      reader.readAsDataURL(picture.files[0]);
	      
	   }
   
   //이미지 변경 확인
   $('input[name="uploadPicture"]').val(picture.files[0].name);

}

function modify_go(){
	var form=$('form[role="form"]');
	form.submit();
}
 </script>
