<#include "/template/header.ftl"/>
<div class="col-xs-12 text-center" >
	<div  class="col-xs-6">
 		<form action="login" class="form-horizontal" method="post" role="form">
 			<div class="form-group">
 				<label class="col-xs-2">用户名:</label>
 				<div class="col-xs-10">
 					<input type="text"  class="form-control" name="name" placeholder="请输入名称"/>	
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-xs-2">密码:</label>
 				<div class="col-xs-10">
 					<input type="password"  class="form-control" name="password" placeholder="请输入密码"/>	
 				</div>
 			</div>
 			<div>
 			<p class="col-xs-10 col-xs-offset-2 text-left bg-danger">${error!}</p>
 			</div>
 			<div class="col-xs-12 text-right" >
 				<button type="submit" class="btn btn-default">提交</button>
 				<button type="reset" class="btn btn-default">清空</button>
 				<a href="register">注册</a>
 			</div>
 		</form>
 	</div>
</div>
<#include "/template/footer.ftl"/>