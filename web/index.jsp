<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="page" uri="http://Sukura_Project.org/tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>超市账单管理系统</title>

    <%--DWR服务JS--%>
    <script type='text/javascript' src='dwr/interface/OxalisService.js'></script>
    <script type='text/javascript' src='dwr/interface/UserService.js'></script>
    <script type='text/javascript' src='dwr/interface/ServeService.js'></script>
    <script type='text/javascript' src='dwr/interface/LogsService.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <%--加载数据控制JS--%>
    <script type="text/javascript" src="javascript/sakura.js"></script>

    <script src="javascript/jquery-3.1.1.js"></script>
    <script src="javascript/index.js"></script>
    <script src="javascript/jquery.layerModel.js"></script>
    <%--SweetAlert支持库--%>
    <script type="text/javascript" src="javascript/dist/sweetalert-dev.js"></script>
    <link type="text/css" rel="stylesheet" href="css/sweetalert.css"/>
    <%--时间选择框支持库--%>
    <link type="text/css" rel="stylesheet" href="css/lq.datetimepick.css"/>
    <script type="text/javascript" src="javascript/lq.datetimepick.js"></script>
    <script type="text/javascript" src="javascript/selectUi.js"></script>

    <link rel="stylesheet" href="css/timedropper.css"/>
    <script type="text/javascript" src="javascript/timedropper.js"></script>


    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <link type="text/css" rel="stylesheet" href="css/layerModel.css"/>
    <style>
        .outDiv {
            padding: 10px;
            display: none;
        }
    </style>
    <script>
        $(document).ready(loadOxalis(0, 20));
    </script>
</head>
<body>
<%--头部LOGO开始--%>
<div id="header">
    <div class="title"></div>
    <div class="welcome">欢迎您:<s:property value="#session.user.userName"/> (<s:if
            test="#session.user.userRank == 0">经理管理员</s:if><s:else>普通用户组</s:else>)
    </div>
</div>
<%--头部LOGO结束--%>

<%--右区菜单开始--%>
<div id="left_Menu"
     style="float:left;border-right: double 3px #328CFA; background-color:#EBEBEB;height:100%;width:200px">
    <ul class="left-menu">
        <li><a href="javascript:showDiv(1);">
            <div id="menu1" class="menu1_div_checked"></div>
        </a></li>
        <li><a href="javascript:showDiv(2);">
            <div id="menu2" class="menu2_div"></div>
        </a></li>
        <li><a href="javascript:showDiv(3);">
            <div id="menu3" class="menu3_div"></div>
        </a></li>
        <li><a href="javascript:showDiv(4);">
            <div id="menu4" class="menu4_div"></div>
        </a></li>
        <li><a href="javascript:showDiv(5);">
            <div id="menu5" class="menu5_div"></div>
        </a></li>
    </ul>
</div>
<%--右区菜单结束--%>

<%--左区内容开始--%>
<div id="right_Context" style="width:100%;">
    <div style="margin-left: 203px;">
        <%--账单管理开始--%>
        <div id="bill_Div" style="display: block;">

            <%--组合查询开始--%>
            <div class="menu">
                <form method="post">
                    商品名称：<input type="text" name="productName" id="productName" class="myinput"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    是否付款：<select name="payStatus" id="payStatus" class="myinput">
                    <option value="2" selected>全部</option>
                    <option value="1">已付款</option>
                    <option value="0">未付款</option>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" onclick="olsQueryLoader(3)" value="组合查询" class="button"/>
                </form>
            </div>
            <%--组合查询结束--%>

            <%--账单管理主体开始--%>
            <div id="bill_manager_main">
                <div id="title_bill" style="height: 50px;">
                    <div style="margin-top: 5px;padding-top:10px;margin-left:10px">
                        <font style="font-size: 25px;float: left;">账单管理 &gt;&gt;</font>
                    </div>
                    <div style="float: right;margin-top:8px;margin-right: 10px;">
                        <input type="button" value="添加账单" class="input-button"
                               onclick="show_Bill_add();"/>
                    </div>
                    <div style="clear: both;"></div>
                </div>

                <hr/>

                <div id="bill_Table" style="width: 100%;text-align: center;">
                    <div class="content">
                        <div style="padding: 10px;">
                            <table class="list">
                                <thead>
                                <tr>
                                    <td>账单编号</td>
                                    <td>商品名称</td>
                                    <td>商品数量</td>
                                    <td>交易金额</td>
                                    <td>是否付款</td>
                                    <td>供应商名称</td>
                                    <td>商品描述</td>
                                    <td>账单时间</td>
                                </tr>
                                </thead>
                                <tbody id="oxalis_info">
                                </tbody>
                                <tr>
                                    <td id="oxalis_page_content" colspan="8" style="text-align: right"><page:OxalisPage size="20"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <%--账单管理主体结束--%>
        </div>
        <%--账单管理结束--%>


        <%--供应商管理开始--%>
        <div id="provider_Div" style="display: none;">
            <%--组合查询开始--%>
            <div class="menu">
                供应商名称：<input type="text" id="providerName" name="providerName" class="myinput"/>&nbsp;&nbsp;&nbsp;&nbsp;
                供应商描述：<input type="text" id="providerDescription" name="providerDescription" class="myinput"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input onclick="serveConpnentLoader(3);" type="button" value="组合查询" class="button"/>
            </div>
            <%--组合查询结束--%>

            <%--供应商管理主体开始--%>
            <div id="provider_manager_main">
                <div id="title_provider" style="height: 50px;">
                    <div style="margin-top: 5px;padding-top:10px;margin-left:10px">
                        <font style="font-size: 25px;float: left;">供应商管理 &gt;&gt;</font>
                    </div>
                    <div style="float: right;margin-top:8px;margin-right: 10px;">
                        <input type="button" value="添加供应商"
                                <s:if test="#session.user.userRank == 1"> class="input-button-disabled" disabled="disabled" </s:if>
                                <s:else> class="input-button"</s:else>
                               onclick="show_Provider_add();"/>
                    </div>
                    <div style="clear: both;"></div>
                </div>

                <hr/>

                <div id="provider_Table" style="width: 100%;text-align: center;">
                    <div class="content">
                        <div style="padding: 10px;">
                            <table class="list">
                                <thead>
                                <tr>
                                    <td>供应商编号</td>
                                    <td>供应商名称</td>
                                    <td>供应商描述</td>
                                    <td>联系人</td>
                                    <td>电话</td>
                                    <td>地址</td>
                                </tr>
                                </thead>
                                <tbody id="serve_info">
                                </tbody>
                                <tr>
                                    <td id="serve_page_content" colspan="6" style="text-align: right"><page:ServesPage
                                            size="20"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <%--供应商管理主体结束--%>
        </div>
        <%--供应商管理结束--%>


        <%--用户管理开始--%>
        <div id="user_Div" style="display: none;">
            <%--组合查询开始--%>
            <div class="menu">
                <form method="get">
                    用户名称：<input type="text" name="userName" id="userName" class="myinput"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" onclick="userQueryLoader(3);" value="用户查询" class="button"/>
                </form>
            </div>
            <%--组合查询结束--%>

            <%--用户管理主体开始--%>
            <div id="user_manager_main">
                <div id="title_user_manager" style="height: 50px;">
                    <div style="margin-top: 5px;padding-top:10px;margin-left:10px">
                        <font style="font-size: 25px;float: left;">用户管理 &gt;&gt;</font>
                    </div>
                    <div style="float: right;margin-top:8px;margin-right: 10px;">
                        <input type="button" value="添加用户"
                               onclick="show_User_add();" <s:if
                                test="#session.user.userRank == 1"> class="input-button-disabled" disabled="disabled" </s:if><s:else> class="input-button"</s:else> />
                    </div>
                    <div style="clear: both;"></div>
                </div>

                <hr/>

                <div id="user_Table" style="width: 100%;text-align: center;">
                    <div class="content">
                        <div style="padding: 10px;">
                            <table class="list">
                                <thead>
                                <tr>
                                    <td>用户编号</td>
                                    <td>用户名称</td>
                                    <td>性别</td>
                                    <td>年龄</td>
                                    <td>电话</td>
                                    <td>地址</td>
                                    <td>权限</td>
                                </tr>
                                </thead>
                                <tbody id="user_info">
                                </tbody>
                                <tr>
                                    <td id="user_page_content" colspan="7" style="text-align: right"><page:UsersPage size="20"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <%--用户管理主体结束--%>
        </div>
        <%--用户管理结束--%>

        <%--操作记录开始--%>
        <div id="history_Div" style="display: none;">
            <%--组合查询开始--%>
            <div class="menu">
                用户名称：<page:UserSelector/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                从&nbsp;<input type="text" name="datepicker" id="startDate" class="form-control"
                              value="2015-5-20"/>&nbsp;<input type="text" id="startTime"/>
                &nbsp;&nbsp;
                至&nbsp;<input type="text" name="datepicker" id="endDate" class="form-control"
                              value="2015-5-20"/>&nbsp;<input type="text" id="endTime"/>
                <input type="button" value="组合查询" class="button" onclick="logCompoundQuery();  "/>
                <%--日期选择框控制--%>
                <script type="text/javascript">
                    var date = new Date();
                    var date2 = new Date();
                    //逆推5天
                    date2.setDate(date.getDate() - 5);
                    var year = date.getFullYear();
                    var day = date.getDate();
                    var month = date.getMonth();
                    document.getElementById("startDate").value = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate();
                    document.getElementById("endDate").value = year + "-" + (month + 1) + "-" + day;
                    $(function () {
                        $("#startDate").on("click", function (e) {
                            e.stopPropagation();
                            $(this).lqdatetimepicker({
                                css: 'datetime-day',
                                dateType: 'D',
                                selectback: function () {

                                }
                            });

                        });

                        $("#endDate").on("click", function (e) {
                            e.stopPropagation();
                            $(this).lqdatetimepicker({
                                css: 'datetime-day',
                                dateType: 'D',
                                selectback: function () {

                                }
                            });

                        });
                    });
                    $("#startTime").timeDropper();
                    $("#endTime").timeDropper();
                </script>
            </div>
            <%--组合查询结束--%>

            <%--操作记录主体开始--%>
            <div id="history_main">
                <div id="title" style="height: 50px;">
                    <div style="margin-top: 5px;padding-top:10px;margin-left:10px">
                        <font style="font-size: 25px;float: left;">用户操作记录 &gt;&gt;</font>
                    </div>
                </div>

                <hr/>

                <div id="history_Table" style="width: 100%;text-align: center;">
                    <div class="content">
                        <div style="padding: 10px;">
                            <table class="list">
                                <thead>
                                <tr>
                                    <td>操作者</td>
                                    <td>用户对象</td>
                                    <td>账单对象</td>
                                    <td>供应商对象</td>
                                    <td>详细信息</td>
                                    <td>操作时间</td>
                                    <td>操作地址</td>
                                </tr>
                                </thead>
                                <tbody id="history_info">
                                <tr>
                                    <td><a href="javascript:show_User_detail();">unknow</a></td>
                                    <td><a href="javascript:show_User_detail();">unknow</a></td>
                                    <td><a href="javascript:show_Bill_detail();">unknow</a></td>
                                    <td><a href="javascript:show_Provider_detail();">unknow</a></td>
                                    <td>unknow</td>
                                    <td>unknow</td>
                                    <td>unknow</td>
                                </tr>
                                </tbody>
                                <tr>
                                    <td id="logs_pages_content" colspan="7" align="right" style="text-align: right">
                                        <page:LogsPage size="20"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <%--用户管理主体结束--%>
        </div>
        <%--用户管理结束--%>
    </div>
</div>
<%--左区内容结束--%>
<div style="clear: left;"></div>


<%--添加账单开始--%>
<div id="bill_add_div" class="outDiv">
    <form method="post" action="oxalis!addOls" id="add_olsAction">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">交易金额：</td>
                    <td><input type="text" id="add_bill_money" name="add_bill_money" class="myinput"/></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">交易单位：</td>
                    <td><input type="text" id="add_bill_util" name="add_bill_util" class="myinput"/></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">交易数量：</td>
                    <td><input type="text" id="add_bill_count" name="add_bill_count" class="myinput"/></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">商品名称：</td>
                    <td><input type="text" id="add_bill_name" name="add_bill_name" class="myinput"/></td>
                </tr>
                <tr>
                    <td class="field">商品描述：</td>
                    <td><textarea name="add_bill_discription" id="add_bill_discription"
                                  style="resize:none;width: 227px;" class="myinput"></textarea></td>
                </tr>
                <tr>
                    <td class="field">所属供应商：</td>
                    <td>
                        <select id="add_bill_belong_provider" name="add_bill_belong_provider" class="myinput">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="field">是否付款：</td>
                    <td>
                        <select id="add_bill_isPay" name="add_bill_isPay" class="myinput">
                            <option value="1" selected="selected">已付款</option>
                            <option value="0">未付款</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <div style="float: right;margin-top: 10px;">
            <input type="button" onclick="add_olsAction();" value="提交" class="input-button"/>
            <input type="button" value="取消" class="input-button" onclick="$('#bill_add_div').close();"/>
        </div>
        <div style="clear: right;"></div>
    </form>
</div>
<%--添加账单结束--%>

<%--添加供应商开始--%>
<div id="provider_add_div" class="outDiv">
    <form method="post" action="serve!addServe" id="addServeForm">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">供应商名称：</td>
                    <td><input type="text" id="add_provider_name" name="add_provider_name" class="myinput"/></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">供应商描述：</td>
                    <td><textarea name="add_provider_discription" id="add_provider_discription" class="myinput"
                                  style="resize:none;width: 227px;"></textarea></td>
                </tr>
                <tr>
                    <td class="field">公司联系人：</td>
                    <td><input type="text" id="add_provider_contect" name="add_provider_contect" class="myinput"/></td>
                </tr>
                <tr>
                    <td class="field">供应商电话：</td>
                    <td><input type="text" id="add_provider_phone" name="add_provider_phone" class="myinput"/></td>
                </tr>
                <tr>
                    <td class="field">供应商地址：</td>
                    <td><input type="text" id="add_provider_address" name="add_provider_address" class="myinput"/></td>
                </tr>

            </table>
        </div>
        <div style="float: right;margin-top: 10px;">
            <input type="button" onclick="add_serveAction();" value="提交" class="input-button"/>
            <input type="button" value="取消" class="input-button"
                   onclick="$('#provider_add_div').close();"/>
        </div>
        <div style="clear: right;"></div>
    </form>
</div>
<%--添加供应商结束--%>

<%--添加用户开始--%>
<div id="user_add_div" class="outDiv">
    <form method="post" action="user!addUser" id="addUserForm" name="addUserForm">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">用户名称：</td>
                    <td><input type="text" id="add_user_name" name="add_user_name" class="myinput"/></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">用户密码：</td>
                    <td><input type="password" id="add_user_password" name="add_user_password" class="myinput"
                    /></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">确认密码：</td>
                    <td><input type="password" id="add_user_cpwd" name="add_user_cpwd" class="myinput"
                    /></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">用户性别：</td>
                    <td>
                        <select id="add_user_sex" name="add_user_sex" class="myinput">
                            <option value="1" selected="selected">男</option>
                            <option value="0">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户年龄：</td>
                    <td><input type="text" id="add_user_age" name="add_user_age" class="myinput"/></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                <tr>
                    <td class="field">用户电话：</td>
                    <td><input type="text" id="add_user_phone" name="add_user_phone" class="myinput"/></td>
                    <td><font style="color: red;">*</font></td>
                </tr>
                </tr>
                <tr>
                    <td class="field">用户地址：</td>
                    <td><textarea name="add_user_address" id="add_user_address" class="myinput"
                                  style="resize:none;width: 227px;"></textarea></td>
                </tr>
                <tr>
                    <td class="field">用户权限：</td>
                    <td>
                        <input type="radio" name="add_user_power" id="add_user_power1" value="1" checked/>普通用户
                        <input type="radio" name="add_user_power" id="add_user_power0" value="0"/>经理
                    </td>
                </tr>
            </table>
        </div>
        <div style="float: right;margin-top: 10px;">
            <input type="button" value="提交" class="input-button" onclick="add_userAction();"/>
            <input type="button" value="取消" class="input-button" onclick="$('#user_add_div').close();"/>
        </div>
        <div style="clear: right;"></div>
    </form>
</div>
<%--添加用户结束--%>

<%--查看详细账单开始--%>
<div id="bill_detail_div" class="outDiv">
    <form method="post" id="updateBillForm" action="oxalis!updateOls">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">账单编号：</td>
                    <td><input type="text" id="detail_bill_Num" name="detail_bill_Num" class="myinput"
                               value="加载中" readonly="readonly" style="background:#EBEBE4"/></td>
                </tr>
                <tr>
                    <td class="field">交易金额：</td>
                    <td><input type="text" id="detail_bill_money" name="detail_bill_money" class="myinput"
                               value="加载中"/></td>
                </tr>
                <tr>
                    <td class="field">交易单位：</td>
                    <td><input type="text" id="detail_bill_util" name="detail_bill_util" class="myinput"
                               value="加载中"/></td>
                </tr>
                <tr>
                    <td class="field">交易数量：</td>
                    <td><input type="text" id="detail_bill_count" name="detail_bill_count" class="myinput"
                               value="加载中"/></td>
                </tr>
                <tr>
                    <td class="field">商品名称：</td>
                    <td><input type="text" id="detail_bill_name" name="detail_bill_name" class="myinput"
                               value="加载中"/></td>
                </tr>
                <tr>
                    <td class="field">商品描述：</td>
                    <td><textarea name="detail_bill_discription" id="detail_bill_discription" class="myinput"
                                  style="resize:none;width: 227px;">加载中</textarea></td>
                </tr>
                <tr>
                    <td class="field">所属供应商：</td>
                    <td>
                        <select id="detail_bill_belong_provider" name="detail_bill_belong_provider" class="myinput">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="field">是否付款：</td>
                    <td>
                        <select id="detail_bill_isPay" name="detail_bill_isPay" class="myinput">
                            <option value="1" selected="selected">已付款</option>
                            <option value="0">未付款</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <div style="float: right;margin-top: 10px;">
            <input type="button" onclick="update_olsAction();" value="修改" class="input-button"/>
            <input type="button" onclick="delete_olsAction('oxalis!deleteOls')" value="删除" class="input-button"/>
            <input type="button" value="取消" class="input-button"
                   onclick="$('#bill_detail_div').close();"/>
        </div>
        <div style="clear: right;"></div>
    </form>
</div>
<%--查看详细账单结束--%>

<%--查看详细供应商开始--%>
<div id="provider_detail_div" class="outDiv">
    <form method="post" action="serve!updateServe" id="detail_provider_form" name="detail_provider_form">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">供应商编号：</td>
                    <td><input type="text" id="detail_provider_Num" name="detail_provider_Num" class="myinput"
                               value="加载中"
                               readonly="readonly" style="background: #EBEBE4"/></td>
                </tr>
                <tr>
                    <td class="field">供应商名称：</td>
                    <td><input type="text" id="detail_provider_name" name="detail_provider_name" class="myinput"
                               value="加载中"
                            <s:if test="#session.user.userRank == 1"> disabled="disabled" </s:if> /></td>
                </tr>
                <tr>
                    <td class="field">供应商描述：</td>
                    <td><textarea name="detail_provider_discription" id="detail_provider_discription" class="myinput"
                                  style="resize:none;width: 227px;" <s:if
                            test="#session.user.userRank == 1"> disabled="disabled" </s:if> >加载中</textarea></td>
                </tr>
                <tr>
                    <td class="field">公司联系人：</td>
                    <td><input type="text" id="detail_provider_contect" name="detail_provider_contect" class="myinput"
                               value="加载中"
                            <s:if test="#session.user.userRank == 1"> disabled="disabled" </s:if> /></td>
                </tr>
                <tr>
                    <td class="field">供应商电话：</td>
                    <td><input type="text" id="detail_provider_phone" name="detail_provider_phone" class="myinput"
                               value="加载中"
                            <s:if test="#session.user.userRank == 1"> disabled="disabled" </s:if> /></td>
                </tr>
                <tr>
                    <td class="field">供应商地址：</td>
                    <td><input type="text" id="detail_provider_address" name="detail_provider_address" class="myinput"
                               value="加载中"
                            <s:if test="#session.user.userRank == 1"> disabled="disabled" </s:if> /></td>
                </tr>
            </table>
        </div>
        <div style="float: right;margin-top: 10px;">
            <s:if test="#session.user.userRank == 0">
                <input type="button" value="修改" class="input-button" onclick="update_serveAction();"/>
                <input type="button" value="删除" class="input-button" onclick="delete_serveAction('serve!deleteServe')"/>
            </s:if>
            <input type="button" value="取消" class="input-button"
                   onclick="$('#provider_detail_div').close();"/>
        </div>
        <div style="clear: right;"></div>
    </form>
</div>
<%--查看详细供应商结束--%>

<%--查看详细用户开始--%>
<div id="user_detail_div" class="outDiv">
    <form id="userFrom" method="post" action="user!changeUser">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">用户编号：</td>
                    <td><input type="text" id="detail_user_Num" name="detail_user_Num" class="myinput" value="加载中"
                               disabled="disabled"/></td>
                </tr>
                <tr>
                    <td class="field">用户名称：</td>
                    <td><input type="text" id="detail_user_name" name="detail_user_name" class="myinput" value="加载中"
                            <s:if
                                    test="#session.user.userRank == 1"> disabled="disabled" </s:if>/>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户性别：</td>
                    <td>
                        <select id="detail_user_sex" name="detail_user_sex" class="myinput" <s:if
                                test="#session.user.userRank == 1"> disabled="disabled" </s:if>>
                            <option value="0">女</option>
                            <option value="1">男</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户年龄：</td>
                    <td><input type="text" id="detail_user_age" name="detail_user_age" value="加载中" class="myinput"<s:if
                            test="#session.user.userRank == 1"> disabled="disabled" </s:if>/></td>
                <tr>
                    <td class="field">用户电话：</td>
                    <td><input type="text" id="detail_user_phone" name="detail_user_phone" value="加载中" class="myinput"
                            <s:if
                                    test="#session.user.userRank == 1"> disabled="disabled" </s:if>/>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户地址：</td>
                    <td><textarea name="detail_user_address" id="detail_user_address" class="myinput"
                                  style="resize:none;width: 227px;"
                            <s:if test="#session.user.userRank == 1"> disabled="disabled" </s:if>>加载中</textarea></td>
                </tr>
                <tr>
                    <td class="field">用户权限：</td>
                    <td>
                        <input type="radio" name="detail_user_power" id="detail_user_power1" value="1" <s:if
                                test="#session.user.userRank == 1"> disabled = "disabled"</s:if> />普通用户
                        <input type="radio" name="detail_user_power" id="detail_user_power0" value="0" <s:if
                                test="#session.user.userRank == 1"> disabled = "disabled"</s:if> />经理
                        <input type="hidden" name="detail_user_password" id="detail_user_password"/>
                    </td>
                </tr>
            </table>
        </div>
        <div style="float: right;margin-top: 10px;">
            <div id="detail_user_submit_div" style="display: none;float:left;margin-right:5px;">
                <input type="button" name="detail_user_submit" id="detail_user_submit" value="修改" class="input-button"
                       onclick="update_userAction();"/>
                <input type="button" name="detail_user_changePwd" id="detail_user_changePwd" value="修改密码"
                       class="input-button" onclick="change_Pwd();"/>
                <input type="button" name="detail_user_delete" id="detail_user_delete" value="删除" class="input-button"
                       onclick="delete_userAction('user!deleteUser');"/>
                <input id="detail_user_hidden" type="hidden" <s:if
                        test="#session.user.userRank == 1"> value="1"</s:if><s:else>value="0"</s:else> />
            </div>
            <div style="float:left">
                <input type="button" value="取消" class="input-button"
                       onclick="$('#user_detail_div').close();"/>
            </div>
        </div>
        <div style="clear: right;"></div>
        <input type="hidden" id="targetUserId" name="targetUserId"/>
    </form>
</div>
<%--查看详细用户结束--%>

<%--修改密码--%>
<div id="user_change_pwd_div" class="outDiv">
    <form method="post" id="user_change_pwd" action="user!changeUserPW">
        <input type="hidden" name="changeTarget" id="changeTarget"/>
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">新密码：</td>
                    <td><input type="password" id="userNewPwd" name="userNewPwd" class="myinput"/></td>
                </tr>
                <tr>
                    <td class="field">重复新密码：</td>
                    <td><input type="password" id="userCpwd" name="userCpwd" class="myinput"/></td>
                </tr>
            </table>
        </div>
        <div style="float: right;margin-top: 10px;">
            <input type="button" value="修改" class="input-button" onclick="changePwdConfirmWindow();"/>
            <input type="button" value="取消" class="input-button"
                   onclick="$('#user_change_pwd_div').close();"/>
        </div>
        <div style="clear: right;"></div>
    </form>
</div>
<%--查看详细用户结束--%>

</body>
</html>
