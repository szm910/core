<#include "/template/header.ftl"/>
<div class="col-xs-12 text-center" >
	<div  class="col-xs-6">
 		<form action="register" class="form-horizontal" method="post" role="form">
 			<div class="form-group">
 				<label class="col-xs-2">用户名:</label>
 				<div class="col-xs-10">
 					<input type="text"  class="form-control" name="name" placeholder="请输入名称"/>	
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-xs-2">年龄:</label>
 				<div class="col-xs-10">
 					<input type="text"  class="form-control" name="age" placeholder="请输入名称"/>	
 				</div>
 			</div>
 			<div class="form-group">
 				<label  class="col-xs-2">密码：</label>
 				<div class="col-xs-10">
 					<input type="password" class="form-control " name="password" placeholder="请输入密码"/>
 				</div>	
 			</div>
 			<div class="form-group">
 				<label  class="col-xs-2">重复密码：</label>
 				<div class="col-xs-10">
 					<input type="password" class="form-control" name="repassword" placeholder="请再次输入密码"/>	
 				</div>
 			</div>
 			<div class="col-xs-12 text-right" >
 				<button type="submit" class="btn btn-default">提交</button>
 				<button type="reset" class="btn btn-default">清空</button>
 			</div>
 		</form>
 	</div>
</div>
<#include "/template/footer.ftl"/>