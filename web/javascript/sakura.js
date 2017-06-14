/**
 * Created by Hi_old on 2017/6/7/007.
 */
//加载所有用户方法
function loadUsers(strat, max) {
    //执行回调方法
    UserService.QueryUserPages(strat, max, loadForContent);
}
//加载所有账单方法
function loadOxalis(start, max) {
    OxalisService.getOxalisByPage(start, max, loadOxalisContent);
}
//加载所有供应商方法
function loadServe(start, max) {
    ServeService.getServeByPage(start, max, loadServeContent);
}
//加载所有历史记录方法
function loadHistory(start, max) {
    LogsService.getLogs(start, max, loadLogsContent);
}
//账单添加窗口加载所有供应商
function loadserve_ols() {
    ServeService.getServe(loadServeOls);
}
//加载查询单个用户方法
function QuertyUser(id) {
    UserService.QueryUserById(id, InsertUserInfo);
}
//加载查询单个供应商方法
function QueryServe(id) {
    ServeService.QueryServeById(id, InsertServeInfo);
}
//加载查询单个账单方法
function QueryOxalis(id) {
    OxalisService.QueryOxalisById(id, InsertOxalisInfo);
}

function loadForContent(data) {
    //获取容器
    var user_info_content = document.getElementById("user_info");
    //清空原有数据
    user_info_content.innerHTML = "";
    for (var i in data) {
        //创建tr
        var _tr = document.createElement("tr");

        //用户ID
        var _td_userid = document.createElement("td");
        _td_userid.innerHTML = data[i].userId;

        //用户名
        var _td_username = document.createElement("td");
        _td_username.innerHTML = "<a href='javascript:show_User_detail(" + data[i].userId + ");'>" + data[i].userName + "</a>"

        //性别
        var _td_usersex = document.createElement("td");
        var tempSex = "?";
        if (data[i].userSex == 1) {
            tempSex = "男";
        } else {
            tempSex = "女";
        }
        _td_usersex.innerHTML = tempSex;

        //年龄
        var _td_userage = document.createElement("td");
        _td_userage.innerHTML = data[i].userYear;

        //电话
        var _td_userphone = document.createElement("td");
        _td_userphone.innerHTML = data[i].userPhone;
        //地址
        var _td_useraddress = document.createElement("td");
        _td_useraddress.innerHTML = data[i].userAddress;
        //权限
        var _td_userrank = document.createElement("td");
        var tempRank = "?";
        if (data[i].userRank == 0) {
            tempRank = "经理管理员";
        } else {
            tempRank = "普通用户组";
        }

        _td_userrank.innerHTML = tempRank;

        //组合
        _tr.appendChild(_td_userid);
        _tr.appendChild(_td_username);
        _tr.appendChild(_td_usersex);
        _tr.appendChild(_td_userage);
        _tr.appendChild(_td_userphone);
        _tr.appendChild(_td_useraddress);
        _tr.appendChild(_td_userrank);
        //添加到容器中
        user_info_content.appendChild(_tr);
    }
}

function InsertUserInfo(data) {
    document.getElementById("detail_user_Num").value = data.userId;
    document.getElementById("detail_user_name").value = data.userName;
    document.getElementById("detail_user_sex").selectedIndex = data.userSex;
    document.getElementById("detail_user_age").value = data.userYear;
    document.getElementById("detail_user_phone").value = data.userPhone;
    document.getElementById("detail_user_password").value = data.userPwd;
    document.getElementById("detail_user_address").value = data.userAddress;
    document.getElementById("detail_user_power" + data.userRank).checked = true;
    //给隐藏域赋值
    document.getElementById("targetUserId").value = data.userId;
    if (data.isCurrentUser == 1) {
        document.getElementById("detail_user_submit_div").style.display = "block";
        document.getElementById("detail_user_name").removeAttribute("disabled");
        document.getElementById("detail_user_sex").removeAttribute("disabled");
        document.getElementById("detail_user_age").removeAttribute("disabled");
        document.getElementById("detail_user_phone").removeAttribute("disabled");
        document.getElementById("detail_user_address").removeAttribute("disabled");
        document.getElementById("detail_user_power0").removeAttribute("disabled");
        document.getElementById("detail_user_power1").removeAttribute("disabled");
        document.getElementById("detail_user_submit").removeAttribute("disabled");
        document.getElementById("detail_user_changePwd").removeAttribute("disabled");
        document.getElementById("detail_user_delete").removeAttribute("disabled");
    } else if (data.currentUserRank == 0) {
        document.getElementById("detail_user_submit_div").style.display = "block";
        document.getElementById("detail_user_name").removeAttribute("disabled");
        document.getElementById("detail_user_sex").removeAttribute("disabled");
        document.getElementById("detail_user_age").removeAttribute("disabled");
        document.getElementById("detail_user_phone").removeAttribute("disabled");
        document.getElementById("detail_user_address").removeAttribute("disabled");
        document.getElementById("detail_user_power0").removeAttribute("disabled");
        document.getElementById("detail_user_power1").removeAttribute("disabled");
        document.getElementById("detail_user_submit").removeAttribute("disabled");
        document.getElementById("detail_user_changePwd").removeAttribute("disabled");
        document.getElementById("detail_user_delete").removeAttribute("disabled");
    } else {
        document.getElementById("detail_user_submit_div").style.display = "none";
        document.getElementById("detail_user_name").disabled = "disabled";
        document.getElementById("detail_user_sex").disabled = "disabled";
        document.getElementById("detail_user_age").disabled = "disabled";
        document.getElementById("detail_user_phone").disabled = "disabled";
        document.getElementById("detail_user_address").disabled = "disabled";
        document.getElementById("detail_user_power0").disabled = "disabled";
        document.getElementById("detail_user_power1").disabled = "disabled";
        document.getElementById("detail_user_submit").disabled = "disabled";
        document.getElementById("detail_user_changePwd").disabled = "disabled";
        document.getElementById("detail_user_delete").disabled = "disabled";
    }
}

function InsertServeInfo(data) {
    document.getElementById("detail_provider_Num").value = data.serveId;
    document.getElementById("detail_provider_name").value = data.serveName;
    document.getElementById("detail_provider_discription").value = data.servePicture;
    document.getElementById("detail_provider_contect").value = data.serveRelation;
    document.getElementById("detail_provider_phone").value = data.servePhone;
    document.getElementById("detail_provider_address").value = data.serveAddress;
}

function InsertOxalisInfo(data) {
    document.getElementById("detail_bill_Num").value = data.olsId;
    document.getElementById("detail_bill_money").value = data.olsMoney;
    document.getElementById("detail_bill_util").value = data.olsUnit;
    document.getElementById("detail_bill_count").value = data.olsNunber;
    document.getElementById("detail_bill_name").value = data.olsWarename;
    document.getElementById("detail_bill_discription").value = data.olsPicture;
    for (var i = 0; i < data.allServe.length; i++) {
        $("#detail_bill_belong_provider").append("<option value='" + data.allServe[i].serveId + "'>" + data.allServe[i].serveName + "</option>");
    }
    $("#detail_bill_belong_provider").val(data.serveId);
    $("#detail_bill_isPay").val(data.olsPayment);
}
function loadOxalisContent(data) {
    //获取容器
    var oxalis_info_content = document.getElementById("oxalis_info");
    //清空原有数据
    oxalis_info_content.innerHTML = "";
    for (var i in data) {
        //创建tr
        var _tr = document.createElement("tr");

        //账单编号
        var _td_olsId = document.createElement("td");
        _td_olsId.innerHTML = data[i].olsId;

        //商品名称
        var _td_olsWarename = document.createElement("td");
        _td_olsWarename.innerHTML = "<a href='javascript:show_Bill_detail(" + data[i].olsId + ");'>" + data[i].olsWarename + "</a>"

        //商品数量
        var _td_olsNunber = document.createElement("td");
        _td_olsNunber.innerHTML = data[i].olsNunber;

        //交易金额
        var _td_olsMoney = document.createElement("td");
        _td_olsMoney.innerHTML = data[i].olsMoney;

        //是否付款
        var _td_olsPayment = document.createElement("td");
        if (data[i].olsPayment == 0) {
            _td_olsPayment.innerHTML = "未付款";
        } else {
            _td_olsPayment.innerHTML = "已付款";
        }
        //供应商名称
        var _td_serveId = document.createElement("td");
        _td_serveId.innerHTML = data[i].serveName;
        //商品描述
        var _td_olsPicture = document.createElement("td");
        if (data[i].olsPicture.length > 3) {
            _td_olsPicture.innerHTML = data[i].olsPicture.substring(0, 4) + "...";
        } else {
            _td_olsPicture.innerHTML = data[i].olsPicture;
        }
        //账单时间
        var _td_createtime = document.createElement("td");
        _td_createtime.innerHTML = data[i].createtime;

        //组合
        _tr.appendChild(_td_olsId);
        _tr.appendChild(_td_olsWarename);
        _tr.appendChild(_td_olsNunber);
        _tr.appendChild(_td_olsMoney);
        _tr.appendChild(_td_olsPayment);
        _tr.appendChild(_td_serveId);
        _tr.appendChild(_td_olsPicture);
        _tr.appendChild(_td_createtime);
        //添加到容器中
        oxalis_info_content.appendChild(_tr);
    }
}
function loadServeOls(data) {
    for (var i = 0; i < data.length; i++) {
        $("#add_bill_belong_provider").append("<option value='" + data[i].serveId + "'>" + data[i].serveName + "</option>");
    }
}

function loadServeContent(data) {
    //获取容器
    var serve_info_content = document.getElementById("serve_info");
    //清空原有数据
    serve_info_content.innerHTML = "";
    for (var i in data) {
        //创建tr
        var _tr = document.createElement("tr");

        //供应商编号
        var _td_serveId = document.createElement("td");
        _td_serveId.innerHTML = data[i].serveId;

        //供应商名称
        var _td_serveName = document.createElement("td");
        _td_serveName.innerHTML = "<a href='javascript:show_Provider_detail(" + data[i].serveId + ");'>" + data[i].serveName + "</a>"

        //供应商描述
        var _td_servePicture = document.createElement("td");
        if (data[i].servePicture.length > 3) {
            _td_servePicture.innerHTML = data[i].servePicture.substring(0, 4) + "...";
        } else {
            _td_servePicture.innerHTML = data[i].servePicture;
        }
        //联系人
        var _td_serveRelation = document.createElement("td");
        _td_serveRelation.innerHTML = data[i].serveRelation;

        //电话
        var _td_servePhone = document.createElement("td");
        _td_servePhone.innerHTML = data[i].servePhone;

        //地址
        var _td_serveAddress = document.createElement("td");
        if (data[i].serveAddress.length > 3) {
            _td_serveAddress.innerHTML = data[i].serveAddress.substring(0, 4) + "...";
        } else {
            _td_serveAddress.innerHTML = data[i].serveAddress;
        }

        //组合
        _tr.appendChild(_td_serveId);
        _tr.appendChild(_td_serveName);
        _tr.appendChild(_td_servePicture);
        _tr.appendChild(_td_serveRelation);
        _tr.appendChild(_td_servePhone);
        _tr.appendChild(_td_serveAddress);
        //添加到容器中
        serve_info_content.appendChild(_tr);
    }
}

function loadLogsContent_muiltquery(data_all) {
    //取出记录
    var data = data_all[0];
    //取出分页集合
    var data_page_start = data_all[1];
    var data_page_end = data_all[2];

    //获取容器
    var history_info_content = document.getElementById("history_info");

    var pages_content = document.getElementById("logs_pages_content");
    //清空原有数据
    history_info_content.innerHTML = "";
    for (var i in data) {
        //创建tr
        var _tr = document.createElement("tr");

        //操作者
        var _td_OPName = document.createElement("td");
        _td_OPName.innerHTML = data[i].opName;

        //目标用户
        var _td_TargetName = document.createElement("td");
        if (data[i].targetName != null) {
            _td_TargetName.innerHTML = "<a href='javascript:show_User_detail(" + data[i].targetID + ");'>" + data[i].targetName + "</a>"
        } else {
            _td_TargetName.innerHTML = "无";
        }

        //目标账单
        var _td_TargetOxlName = document.createElement("td");
        if (data[i].targetOxlName != null) {
            _td_TargetOxlName.innerHTML = "<a href='javascript:show_Bill_detail(" + data[i].targetOxlID + ");'>" + data[i].targetOxlName + "</a>"
        } else {
            _td_TargetOxlName.innerHTML = "无";
        }

        //目标供应商
        var _td_TargetServeName = document.createElement("td");
        if (data[i].targetServeName != null) {
            _td_TargetServeName.innerHTML = "<a href='javascript:show_Provider_detail(" + data[i].targetServeID + ");'>" + data[i].targetServeName + "</a>"
        } else {
            _td_TargetServeName.innerHTML = "无";
        }

        //详细信息
        var _td_logDetails = document.createElement("td");
        _td_logDetails.innerHTML = data[i].logDetails;

        //操作时间
        var _td_logTime = document.createElement("td");
        _td_logTime.innerHTML = data[i].logTime;

        //操作IP
        var _td_logIP = document.createElement("td");
        if (data[i].logIP == "0:0:0:0:0:0:0:1") {
            _td_logIP.innerHTML = "本地操作";
        } else {
            _td_logIP.innerHTML = data[i].logIP;
        }

        //组合
        _tr.appendChild(_td_OPName);
        _tr.appendChild(_td_TargetName);
        _tr.appendChild(_td_TargetOxlName);
        _tr.appendChild(_td_TargetServeName);
        _tr.appendChild(_td_logDetails);
        _tr.appendChild(_td_logTime);
        _tr.appendChild(_td_logIP);
        //添加到容器中
        /*
         类容处理完成,开始处理分页
         */
        //清空原有信息
        pages_content.innerHTML = "";
        //加载分页信息
        var index = 0;
        for (var temp in data_page_start) {
            var start_P = data_page_start[temp];
            var end_P = data_page_end[index];
            var tag_a = document.createElement("a");
            tag_a.setAttribute("href", "javascript:logCompoundQueryInputPages(" + start_P + "," + end_P + ")");
            tag_a.innerHTML = "第" + (parseInt(temp) + 1) + "页";
            pages_content.appendChild(tag_a);
            pages_content.append("    ");
            index++;
        }
        history_info_content.appendChild(_tr);
    }
}


/**
 * 记载个人记录
 */
function loadLogsContent(data) {
    //获取容器
    var history_info_content = document.getElementById("history_info");
    //清空原有数据
    history_info_content.innerHTML = "";
    for (var i in data) {
        //创建tr
        var _tr = document.createElement("tr");

        //操作者
        var _td_OPName = document.createElement("td");
        _td_OPName.innerHTML = data[i].opName;

        //目标用户
        var _td_TargetName = document.createElement("td");
        if (data[i].targetName != null) {
            _td_TargetName.innerHTML = "<a href='javascript:show_User_detail(" + data[i].targetID + ");'>" + data[i].targetName + "</a>"
        } else {
            _td_TargetName.innerHTML = "无";
        }

        //目标账单
        var _td_TargetOxlName = document.createElement("td");
        if (data[i].targetOxlName != null) {
            _td_TargetOxlName.innerHTML = "<a href='javascript:show_Bill_detail(" + data[i].targetOxlID + ");'>" + data[i].targetOxlName + "</a>"
        } else {
            _td_TargetOxlName.innerHTML = "无";
        }

        //目标供应商
        var _td_TargetServeName = document.createElement("td");
        if (data[i].targetServeName != null) {
            _td_TargetServeName.innerHTML = "<a href='javascript:show_Provider_detail(" + data[i].targetServeID + ");'>" + data[i].targetServeName + "</a>"
        } else {
            _td_TargetServeName.innerHTML = "无";
        }

        //详细信息
        var _td_logDetails = document.createElement("td");
        _td_logDetails.innerHTML = data[i].logDetails;

        //操作时间
        var _td_logTime = document.createElement("td");
        _td_logTime.innerHTML = data[i].logTime;

        //操作IP
        var _td_logIP = document.createElement("td");
        if (data[i].logIP == "0:0:0:0:0:0:0:1") {
            _td_logIP.innerHTML = "本地操作";
        } else {
            _td_logIP.innerHTML = data[i].logIP;
        }

        //组合
        _tr.appendChild(_td_OPName);
        _tr.appendChild(_td_TargetName);
        _tr.appendChild(_td_TargetOxlName);
        _tr.appendChild(_td_TargetServeName);
        _tr.appendChild(_td_logDetails);
        _tr.appendChild(_td_logTime);
        _tr.appendChild(_td_logIP);
        //添加到容器中
        history_info_content.appendChild(_tr);
    }
}

//用户操作记录组合查询
function logCompoundQuery() {
    //获取用户ID
    var uid = document.getElementById("logs_user_id").value;
    //获取开始时间
    var startDateStr = document.getElementById("startDate").value;
    var endDateStr = document.getElementById("endDate").value;

    var startTimeStr = document.getElementById("startTime").value;
    var endTimeStr = document.getElementById("endTime").value;
    //调用处理时间方法,转换时间
    var startDate = handleDateTime(startDateStr, startTimeStr);
    var endDate = handleDateTime(endDateStr, endTimeStr);
    //20代表分页大小
    LogsService.logCompoundQuery(parseInt(uid), startDate, endDate, 20, loadLogsContent_muiltquery);
//获取结束时间
}


//用户操作记录组合查询分页方式
function logCompoundQueryInputPages(start, max) {
    //获取用户ID
    var uid = document.getElementById("logs_user_id").value;
    //获取开始时间
    var startDateStr = document.getElementById("startDate").value;
    var endDateStr = document.getElementById("endDate").value;

    var startTimeStr = document.getElementById("startTime").value;
    var endTimeStr = document.getElementById("endTime").value;
    //调用处理时间方法,转换时间
    var startDate = handleDateTime(startDateStr, startTimeStr);
    var endDate = handleDateTime(endDateStr, endTimeStr);
    //20代表分页大小
    LogsService.logCompoundQueryInputPages(parseInt(uid), startDate, endDate, start, max, loadLogsContent);
//获取结束时间
}

//初次调用加载方法
function serveCompnentQuery(data_all) {
    //获取容器
    var pages_content = document.getElementById("serve_page_content");
    //取出记录
    var data = data_all[0];
    //取出分页集合
    var data_page_start = data_all[1];
    var data_page_end = data_all[2];
    //显示数据
    loadServeContent(data);
    //加载分页信息
    //清空原有信息
    pages_content.innerHTML = "";
    //加载分页信息
    var index = 0;
    for (var temp in data_page_start) {
        var start_P = data_page_start[temp];
        var end_P = data_page_end[index];
        var tag_a = document.createElement("a");
        tag_a.setAttribute("href", "javascript:serveConpnentLoad(" + start_P + "," + end_P + ")");
        tag_a.innerHTML = "第" + (parseInt(temp) + 1) + "页";
        pages_content.appendChild(tag_a);
        pages_content.append("    ");
        index++;
    }
}


//执行dwr调用
function serveConpnentLoader(i) {
    //获取数据
    var serveName = document.getElementById("providerName").value;
    var serveDetails = document.getElementById("providerDescription").value;
    console.log(serveName);
    console.log(serveDetails);
    ServeService.serveCompoundQueryInputPages(serveName, serveDetails, i, serveCompnentQuery);
}

//供应商分页查询
function serveConpnentLoad(i, j) {
    //获取数据
    var serveName = document.getElementById("providerName").value;
    var serveDetails = document.getElementById("providerDescription").value;
    ServeService.serveCompoundQueryInputPages_ecc(serveName, serveDetails, i, j, loadServeContent);
}

//用户综合查询
function userQueryLoader(val){
    var userName = $("#userName").val();
    UserService.getQueryUsers(userName,val,userContextQuery);
}

//加载查询结果的分页标签和传值
function userContextQuery(data_all) {
    //获取容器
    var pages_content = document.getElementById("user_page_content");
    //取出记录
    var data = data_all[0];
    //取出分页集合
    var data_page_start = data_all[1];
    var data_page_end = data_all[2];
    //显示数据
    loadForContent(data);
    //清空原有信息
    pages_content.innerHTML = "";
    //加载分页信息
    var index = 0;
    for (var temp in data_page_start) {
        var start_P = data_page_start[temp];
        var end_P = data_page_end[index];
        var tag_a = document.createElement("a");
        tag_a.setAttribute("href", "javascript:userQueryPageLoad(" + start_P + "," + end_P + ")");
        tag_a.innerHTML = "第" + (parseInt(temp) + 1) + "页";
        pages_content.appendChild(tag_a);
        pages_content.append("    ");
        index++;
    }
}

//加载查询内容
function userQueryPageLoad(i, j) {
    //获取数据
    var userName = $("#userName").val();
    UserService.getQueryUsers_etc(userName, i, j, loadForContent);
}

//供应商综合查询
function olsQueryLoader(val){
    var olsName = $("#productName").val();
    var olsIsPay = $("#payStatus").val();
    OxalisService.getOxalisQueryPage(olsName,parseInt(olsIsPay),val,oxalisContextQuery);
}

//加载查询结果
function oxalisQueryPageLoad(i, j) {
    //获取数据
    var olsName = $("#productName").val();
    var olsIsPay = $("#payStatus").val();
    OxalisService.getOxalisQueryPage_etc(olsName, parseInt(olsIsPay), i, j, loadOxalisContent);
}

//加载查询分页按钮和传值
function oxalisContextQuery(data_all) {
    //获取容器
    var pages_content = document.getElementById("oxalis_page_content");
    //取出记录
    var data = data_all[0];
    //取出分页集合
    var data_page_start = data_all[1];
    var data_page_end = data_all[2];
    //显示数据
    loadOxalisContent(data);
    //清空原有信息
    pages_content.innerHTML = "";
    //加载分页信息
    var index = 0;
    for (var temp in data_page_start) {
        var start_P = data_page_start[temp];
        var end_P = data_page_end[index];
        var tag_a = document.createElement("a");
        tag_a.setAttribute("href", "javascript:oxalisQueryPageLoad(" + start_P + "," + end_P + ")");
        tag_a.innerHTML = "第" + (parseInt(temp) + 1) + "页";
        pages_content.appendChild(tag_a);
        pages_content.append("    ");
        index++;
    }
}