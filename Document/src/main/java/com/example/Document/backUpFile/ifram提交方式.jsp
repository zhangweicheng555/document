<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/tags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/common/head.jsp" %>
<style>
	.daochubaogao {color:#2F5471; font-size:13px;width:60px; border:0px; height:24px;}
</style>
<script src='<s:url value="/dwr/interface/MakeReportDwr.js"/>'></script>
<script>

	function validate(){
	   	 var serchZH=$("#stationNo").val();
	   	  if(!serchZH || serchZH.length<=0){
	   	     errorMsg("站号不能为空");
	  		 return false;
	   	  }else{
		   	  return true;
	   	  }
	}



function MakeReport(){
	if(validate()){
		var serchZH=$("#stationNo").val();
		 if(!confirm('确定要生成报告吗？')){
       		 return;
    	}
    toggleBtnD();
    showload();
    $("#myform").submit();
    window.setTimeout(function(){hideload();},4000);
	}
}





jQuery(document).ready(function(){
    calculateWarpDivHeight(new TableFixed("myDataTable","myDataDiv"),jQuery(window),24,105);
 });
</script>
</head>
<body scroll="no" style="overflow:hidden" class="bodywin">
<%@ include file="/common/load.jsp" %>
<%@ include file="/common/msg.jsp" %>

	<iframe src="about:blank" name="blankFrame" id="blankFrame" style="display: none;"></iframe>
	
<form id="myform" target="blankFrame" action="<%=ctx%>/system/nbiot/nobPlane_exportReport.action" method="post">
<div class="searchbox">
	<div class="search_bj">
	站号：<s:textfield name="stationNo" id="stationNo" maxlength="30"></s:textfield> &nbsp;&nbsp; <input type="button" onclick="MakeReport()" value="" class="ButtonSearch" onmouseover="this.className='ButtonSearch2'" onmouseout="this.className='ButtonSearch'" />
  	</div>
  </div>
</form> 
<div class="contentbox">
<div class="title">
  <ul>
    <li class="tit"><span></span></li>
    <li class="win_btn">
    </li>
  </ul>
</div>


    <div id="myDataDiv" style="overflow:auto;width:100%">
    <table width ="100%" id="myDataTable" class="display_view"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      
  </tr>
    </table>
    </div>
    
  <s:if test="page.datas==null || page.datas.isEmpty()">
	<div class="nodata">输入站号生成报告</div>
  </s:if>
  <%@ include file="/common/Page.jsp" %>
</div>
</body>
</html>