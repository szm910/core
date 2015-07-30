<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#assign path="${request.getContextPath()}">
<link rel="stylesheet" href="${path}/css/bootstrap.css">
<link rel="stylesheet" href="${path}/css/base.css">
<script language="javascript" src="${path}/js/angular-1.3.12.js"></script>
<title>首页</title>
</head>
<body>

<div class="ng-scope">
        <!--top-->
        <div class="orderMange_top">
            <div class="content">
                <div class="col-xs-6">
                    <div class="pull-left"><span>您好！</span></div>
                    <div class="topUserName pull-left">
                        <a href="javascript:void(0);return false;"><span>1234</span><span class="caret"></span></a>
                        <div class="userMsgDiv">
                            <div class="pull-left avatar"><img style="width:60px;height:60px" class="img-circle" src="/photo/avatar/16/avatar_8b752463-e5ce-4ee4-96fb-e5e6f25f698d.jpg" alt="1234"></div>
                            <div class="pull-right m-r-25 m-t-15"><a href="/Users/account#userbase">账号管理</a> | <a href="../user/logout">退出</a></div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6 orderMange_top_r">
                    <div class="topUserName pull-right m-l-20">
                        <a href="javascript:void(0);return false;"><span class="list_bg order_r2_c24"></span><span>客户服务</span><span class="caret"></span></a>
                        <div class="userMsgDiv agentserve">
                            <ul>
                                <li class="serveMsg divider"><span class="qq" title="点击打开服务窗口"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=479445545&amp;site=qq&amp;menu=yes">479445545</a></span></li>
                                <li class="serveMsg"><span class="glyphicon glyphicon-earphone font-size-16 text-warning"></span><span class="m-l-10">0532-55676706</span></li>
                            </ul>
                        </div>
                    </div>
                    <div class="pull-right m-l-20"><a href="/agent/ProductFavorites/list" target="_blank"><span class="list_bg order_r2_c22"></span><span>收藏夹</span></a></div>
                    <div class="pull-right m-l-20"><a href="/agent/shoppingcarts/list" target="_blank"><span class="list_bg order_r8_c2"></span><span>购物车</span><span class="text-danger ng-binding" ng-bind="shcartCount"></span></a></div>
                </div>
            </div>
        </div>
        <div style="clear:both"></div>
    </div>