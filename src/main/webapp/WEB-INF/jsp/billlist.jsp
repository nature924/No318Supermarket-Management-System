<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>

<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>销售管理页面</span>
       </div>
       <div class="search">
       <form method="post" action="${pageContext.request.contextPath }/billlist.html">
			<input type="hidden" name="pageIndex" value="1"/>
			<span>商品名称：</span>
			<input name="queryProductName" type="text" value="${queryProductName }">
			 
			<span>供应商：</span>
			<select name="queryProviderId">
				<c:if test="${providerList != null }">
				   <option value="0">--请选择--</option>
				   <c:forEach var="provider" items="${providerList}">
				   		<option <c:if test="${provider.id == queryProviderId }">selected="selected"</c:if>
				   		value="${provider.id}">${provider.proName}</option>
				   </c:forEach>
				</c:if>
       		</select>
			 
			<span>是否付款：</span>
			<select name="queryIsPayment">
				<option value="0">--请选择--</option>
				<option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
				<option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>
       		</select>
			
			 <input	value="查 询" type="submit" id="searchbutton">
			 <a href="${pageContext.request.contextPath }/billadd.html">添加订单</a>
		</form>
       </div>
       <!--账单表格 样式和供应商公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0">
          <tr class="firstTr">
			  <th width="10%">
				  <input type="checkbox" id="all" value="">
			  </th>
              <th width="10%">订单编码</th>
              <th width="15%">商品名称</th>
              <th width="10%">供应商</th>
              <th width="10%">订单金额</th>
              <th width="10%">是否付款</th>
              <th width="10%">创建时间</th>
              <th width="25%">操作</th>
          </tr>
          <c:forEach var="bill" items="${page.list }" varStatus="status">
				<tr>
					<td>
						<input type="checkbox" name="id" value="${bill.id}">
					</td>
					<td>
					<span>${bill.billCode }</span>
					</td>
					<td>
					<span>${bill.productName }</span>
					</td>
					<td>
					<span>${bill.providerName}</span>
					</td>
					<td>
					<span>${bill.totalPrice}</span>
					</td>
					<td>
					<span>
						<c:if test="${bill.isPayment == 1}">未付款</c:if>
						<c:if test="${bill.isPayment == 2}">已付款</c:if>
					</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
      </table>
	  <p><input class="deleteButton" type="button" value="批量删除" onclick="deleteAll()"/></p>
      <input type="hidden" id="totalPageCount" value="${page.total}"/>
		  	<c:import url="rollpage.jsp">
				<c:param name="totalCount" value="${page.count}"/>
	          	<c:param name="currentPageNo" value="${page.index}"/>
	          	<c:param name="totalPageCount" value="${page.total}"/>
     </c:import>
  </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<style>
	.deleteButton {
		width: 120px;
		height: 40px;
		border-width: 0px;
		border-radius: 3px;
		background: #1E90FF;
		cursor: pointer;
		outline: none;
		font-family: Microsoft YaHei;
		color: white;
		font-size: 17px;
		margin-top: 10px;
	}
	.deleteButton:hover { /* 鼠标移入按钮范围时改变颜色 */
		background: #5599FF;
	}
</style>
<%@include file="../jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billlist.js"></script>
<script type="text/javascript">
	//全选
	var oall=document.getElementById("all");
	var oid=document.getElementsByName("id");
	oall.onclick=function(){//勾选全选时
		for(var i=0;i<oid.length;i++){
			//所有的选择框和全选一致
			oid[i].checked=oall.checked;
		}
	};
	//点击复选框
	for(var i=0;i<oid.length;i++){
		oid[i].onclick=function(){
			//判断是否全部选中,遍历集合
			for(var j=0;j<oid.length;j++){
				if(oid[j].checked==false){
					oall.checked=false;
					break;
				}else{
					oall.checked=true;
				}
			}
		};
	}
	function deleteAll(){
		var r=confirm("是否确认删除？");
		if(r==true){
			//确认删除
			var ids="";
			var n=0;
			for(var i=0;i<oid.length;i++){
				if(oid[i].checked==true){//选中为true
					var id=oid[i].value;
					if(n==0){
						ids+="ids="+id;
					}else{
						ids+="&ids="+id;
					}
					n++;
				}
			}
			//上面会拼接出一个名为ids的数组ids=1&ids=2&ids=3&ids=4……
			$.get(path + "/deleteByIds",ids,function(data){
				if(data=="ok"){
					alert("删除成功!");
					//删除成功后，调用action方法刷新页面信息
					location.reload();
					$("input[name=id]").removeAttr("checked");
				}else{
					alert("请选中行!");
				}
			});
			return true;
		}else{
			//不删除
			return false;
		}
	}
</script>