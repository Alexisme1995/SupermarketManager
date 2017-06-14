//菜单显隐层脚本
function showDiv(val) {
    if (val == 1) {
        loadOxalis(0,20);
        document.getElementById("menu1").className="menu1_div_checked";
        document.getElementById("menu2").className="menu2_div";
        document.getElementById("menu3").className="menu3_div";
        document.getElementById("menu4").className="menu4_div";
        $("#history_Div").hide();
        $("#user_Div").hide();
        $("#provider_Div").hide();
        $("#bill_Div").fadeIn();
    }
    if (val == 2) {
        loadServe(0,20);
        document.getElementById("menu1").className="menu1_div";
        document.getElementById("menu2").className="menu2_div_checked";
        document.getElementById("menu3").className="menu3_div";
        document.getElementById("menu4").className="menu4_div";
        $("#history_Div").hide();
        $("#user_Div").hide();
        $("#bill_Div").hide();
        $("#provider_Div").fadeIn();
    }
    if (val == 3) {
        //使用DWR加载数据
        loadUsers(0,20);
        document.getElementById("menu1").className="menu1_div";
        document.getElementById("menu2").className="menu2_div";
        document.getElementById("menu3").className="menu3_div_checked";
        document.getElementById("menu4").className="menu4_div";
        $("#history_Div").hide();
        $("#bill_Div").hide();
        $("#provider_Div").hide();
        $("#user_Div").fadeIn();
    }
    if (val == 4) {
        loadHistory(0, 20);
        document.getElementById("menu1").className="menu1_div";
        document.getElementById("menu2").className="menu2_div";
        document.getElementById("menu3").className="menu3_div";
        document.getElementById("menu4").className="menu4_div_checked";
        $("#bill_Div").hide();
        $("#provider_Div").hide();
        $("#user_Div").hide();
        $("#history_Div").fadeIn();
    }
    if (val == 5) {
        swal({
                title: "退出登录?",
                text: "您将返回登录页面",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: true
            },
            function (isConfirm) {
                if (isConfirm) {
                    window.location = "login.jsp";
                }
            });
    }
}

//添加账单脚本
function show_Bill_add() {
    loadserve_ols();
    $("#bill_add_div").layerModel({
        title: "添加账单"
    });
}
//添加供应商脚本
function show_Provider_add() {
    $("#provider_add_div").layerModel({
        title: "添加供应商"
    });
}
//添加用户脚本
function show_User_add() {
    $("#user_add_div").layerModel({
        title: "添加用户"
    });
}
//查看详细账单脚本
function show_Bill_detail(val) {
    QueryOxalis(val);
    $("#bill_detail_div").layerModel({
        title: "账单详情"
    });
}
//查看供应商详情脚本
function show_Provider_detail(val) {
    QueryServe(val);
    $("#provider_detail_div").layerModel({
        title: "供应商详情"
    });
}
//查看用户详情脚本
function show_User_detail(val) {
    QuertyUser(val);
    document.getElementById("changeTarget").value = val;
    $("#user_detail_div").layerModel({
        title: "用户详情"
    });
}
//修改密码窗口
function change_Pwd() {
    $("#user_detail_div").close();
    $("#user_change_pwd_div").layerModel({
        title: "修改密码"
    });
}

//添加用户成功窗口
function add_userAction() {
    swal({
            title: "添加成功",
            text: "成功添加一名用户",
            type: "success",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
        },
        function (isConfirm) {
            if (isConfirm) {
                document.getElementById("addUserForm").submit();
            }
        });
}
//修改用户成功窗口
function update_userAction() {
    swal({
            title: "更新成功",
            text: "已成功更新此用户信息",
            type: "success",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
        },
        function (isConfirm) {
            if (isConfirm) {
                document.getElementById("userFrom").submit();
            }
        });
}

//删除用户询问窗口
function delete_userAction(position) {
    var targetForm = document.getElementById("userFrom");
    targetForm.action = position;
    //提交表单前确认
    swal({
            title: "确定要删除吗?",
            text: "删除该用户会将该与该用户有关的内容全部删除,包括历史记录,创建的供应商.",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消操作",
            closeOnConfirm: false,
            closeOnCancel: true
        },
        function (isConfirm) {
            if (isConfirm) {
                targetForm.submit();
                swal("删除成功", "已成功删除该用户!", "success");
            }
        });
}

//删除供应商询问窗口
function delete_serveAction(position) {
    var targetForm = document.getElementById("detail_provider_form");
    targetForm.action = position;
    //提交表单前确认
    swal({
            title: "确定要删除吗?",
            text: "删除该供应商会将该与该供应商有关的内容全部删除.",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消操作",
            closeOnConfirm: false,
            closeOnCancel: true
        },
        function (isConfirm) {
            if (isConfirm) {
                targetForm.submit();
                swal("删除成功", "已成功删除该供应商!", "success");
            }
        });
}
//更新供应商窗口
function update_serveAction() {
    swal({
            title: "更新成功",
            text: "已成功更新此供应商信息",
            type: "success",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
        },
        function (isConfirm) {
            if (isConfirm) {
                document.getElementById("detail_provider_form").submit();
            }
        });
}
//添加供应商窗口
function add_serveAction() {
    swal({
            title: "添加成功",
            text: "成功添加一名供应商",
            type: "success",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
        },
        function (isConfirm) {
            if (isConfirm) {
                document.getElementById("addServeForm").submit();
            }
        });
}

//修改账单确认窗口
function update_olsAction() {
    swal({
            title: "更新成功",
            text: "已成功更新此账单信息",
            type: "success",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
        },
        function (isConfirm) {
            if (isConfirm) {
                document.getElementById("updateBillForm").submit();
            }
        });
}
//添加账单成功窗口
function add_olsAction() {
    swal({
            title: "添加成功",
            text: "已成功添加一张账单",
            type: "success",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
        },
        function (isConfirm) {
            if (isConfirm) {
                document.getElementById("add_olsAction").submit();
            }
        });
}
//删除账单确认窗口
function delete_olsAction(position){
    var targetForm = document.getElementById("updateBillForm");
    targetForm.action = position;
    //提交表单前确认
    swal({
            title: "确定要删除吗?",
            text: "删除该账单会将与该账单有关的内容全部删除.",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消操作",
            closeOnConfirm: false,
            closeOnCancel: true
        },
        function (isConfirm) {
            if (isConfirm) {
                targetForm.submit();
                swal("删除成功", "已成功删除该账单!", "success");
            }
        });
}
//修改密码确认窗口
function changePwdConfirmWindow() {
    swal({
            title: "确定要修改吗?",
            text: "只允许修改自己,或者经理进行操作",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "修改",
            cancelButtonText: "取消操作",
            closeOnConfirm: false,
            closeOnCancel: true
        },
        function (isConfirm) {
            if (isConfirm) {
                document.getElementById("user_change_pwd").submit();
                swal("修改成功", "成功修改密码,请妥善保管!", "success");
            }
        });
    var g = new Date();
}

//处理时间
function handleDateTime(datestr, timestr) {
    //处理日期
    //获取一个[-]的位置
    var _position = datestr.indexOf("-");
    //获取年份
    var year = datestr.substring(0, _position);
    //截取去除年份以及[-].
    datestr = datestr.substring(_position + 1);
    //第二次获取[-]位置
    var _position2 = datestr.indexOf("-");
    //获取月份
    var month = datestr.substring(0, _position2);
    //截取去除月份以及[-],剩下的部分就是日期
    datestr = datestr.substring(_position2 + 1);

    var date = new Date();
    date.setFullYear(parseInt(year));
    date.setMonth(parseInt(month) - 1);
    date.setDate(parseInt(datestr));
    //冒号位置
    var bitPosation = timestr.indexOf(":")
    var spacePosation = timestr.indexOf(" ")
    var amPosation = timestr.indexOf("am");
    var pmPosation = timestr.indexOf("pm");
    //获取时间
    var min = timestr.substring(bitPosation + 1, spacePosation);
    var hours = timestr.substring(0, bitPosation);
    if (pmPosation != -1) {
        hours = parseInt(hours) + 12;
    }
    date.setHours(parseInt(hours));
    date.setMinutes(min);
    return date;
}

//处理日期
function HandleDate(datestr) {
    //2017-5-5
    //获取一个[-]的位置
    var _position = datestr.indexOf("-");
    //获取年份
    var year = datestr.substring(0, _position);
    //截取去除年份以及[-].
    datestr = datestr.substring(_position + 1);
    //第二次获取[-]位置
    var _position2 = datestr.indexOf("-");
    //获取月份
    var month = datestr.substring(0, _position2);
    //截取去除月份以及[-],剩下的部分就是日期
    datestr = datestr.substring(_position2 + 1);
    var d = new Date();
    d.setFullYear(parseInt(year));
    d.setMonth(parseInt(month) - 1);
    d.setDate(datestr);
    return d;
}