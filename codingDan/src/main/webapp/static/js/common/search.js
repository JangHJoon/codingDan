/**
 * name="searchForm"인 폼 요소들을 
 * 쿼리 스트링을 만들어서 현재 url에 붙여서 요청 
 */
function searchList()
{
	var frm = document.searchForm;
	
	if(frm)
	{
		var params = [];

		// form elements 검색 요소를 추출
		[].forEach.call(
			document.searchForm
			,function(a){ 
				var tagName = a.tagName; 
				if( tagName=='SELECT' || tagName=="TEXTAREA" )
				{  
    				params.push(a.name + "=" + a.value);
    			}
    			
				if( tagName == "INPUT" )
				{  
    				// checkbox, radio 분기 처리해야함
    				params.push(a.name + "=" + a.value);
    			}
    			
    			
    		});
		
		// url param에서 페이징 관련 파라미터 추출 => 무시
		/*
		location.search.substr(1).split("&")
				.forEach(function(a){
					var name = a.split("=")[0];
					if(name == "page" || name == "rowPerPage" || name == "pagePerView")
					{
						params.push(a);
					}
				});
		*/
		
		var path = location.pathname;
	
		location.href = path + "?" + params.join("&");
	
	}
	
}