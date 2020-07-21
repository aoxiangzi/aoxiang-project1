Date.prototype.format = function(fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"h+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if(/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for(var k in o) {
		if(new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

function trim(str) {
	return str.replace(/^\s+|\s+$/g, "");
}

function lTrim(str) {
	return str.replace(/^\s/, '');;
}

function rTrim(str) {
	return str.replace(/(\s$)/g, "");
}

/**
 * 格式化数字，逗号分隔
 */
function formatDigit(s, n, padding) {
	if(isNaN(s) || s == null || s.length == 0 || typeof(s) === "undefined") {
		return padding;
	}

	//是否为负号
	var nSign = false;
	if(s < 0) {
		s = s + "";
		s = s.slice(1);
		nSign = true;
	}
	n = n >= 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(),
		r = s.split(".")[1];
	t = "";
	for(i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	var result;
	if(r) {
		result = t.split("").reverse().join("") + "." + r;
	} else {
		result = t.split("").reverse().join("");
	}

	if(nSign) {
		result = '-' + result;
	}
	return result;
}

function formatDigitDefault(value, digit) {
	return formatDigit(value, digit, '-');
}

function formatDigitNoSplit(s, n, padding) {
	if(isNaN(s) || s == null || s.length == 0 || typeof(s) === "undefined") {
		return padding;
	}

	//是否为负号
	var nSign = false;
	if(s < 0) {
		s = s + "";
		s = s.slice(1);
		nSign = true;
	}
	n = n >= 0 && n <= 20 ? n : 2;
	s = formatFloat(parseFloat((s + "").replace(/[^\d\.-]/g, "")), n) + "";
	var l = s.split(".")[0].split("").reverse(),
		r = s.split(".")[1];
	t = "";
	for(i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	}
	var result;
	if(r) {
		result = t.split("").reverse().join("") + "." + r;
	} else {
		result = t.split("").reverse().join("");
	}

	if(nSign) {
		result = '-' + result;
	}
	return result;
}

var formatFloat = function(value, n) {
	var f = Math.round(value * Math.pow(10, n)) / Math.pow(10, n);
	var s = f.toString();
	var rs = s.indexOf('.');
	if(rs < 0) {
		s += '.';
	}
	for(var i = s.length - s.indexOf('.'); i <= n; i++) {
		s += "0";
	}
	return s;
}

function isEmpty(o) {
	var type = Object.prototype.toString.call(o).slice(8, -1);
	switch(type) {
		case 'Number':
			return o === 0
		case 'String':
			return o === "" || o === "0"
		case 'Array':
			if(o.length === 0) {
				return true
			} else {
				for(var i = 0; i < o.length; i++) {
					var tmp = isEmpty(o[i])
					if(!tmp) {
						return false
					}
				}
				return true
			}
		case 'Object':
			return false
		default:
			return true
	}
}

function twoDigitsFmt(val) {
	if(val < 10) return "0" + val;
	return val;
}

function getNowTime() {
	//分别获取年、月、日
	var myDate = new Date();
	var year = myDate.getFullYear();
	var month = myDate.getMonth() + 1;
	var date = myDate.getDate();

	//时间拼接
	var dateTime = year + "-" + twoDigitsFmt(month) + "-" + twoDigitsFmt(date);

	return dateTime;
}

function isSuccessResp(resp) {
	if(resp && resp.code == '10000') {
		return true;
	}
	return false;
}

function createUrl(prefix, url) {
	return prefix + url + "?" + "&t=" + Date.parse(new Date());
}

/* *
 用途：js中字符串超长作固定长度加省略号（...）处理
 参数说明：
    str:需要进行处理的字符串，可含汉字
    len:需要显示多少个汉字，两个英文字母相当于一个汉字。
 */
function beautySub(str, len) {
	var reg = /[\u4e00-\u9fa5]/g, //专业匹配中文
		slice = str.substring(0, len),
		chineseCharNum = (~~(slice.match(reg) && slice.match(reg).length)),
		realen = slice.length * 2 - chineseCharNum;
	return str.substr(0, realen) + (realen < str.length ? "..." : "");
}

function getRootPath(isProjectNameExist) {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPath = curWwwPath.substring(0, pos);
	var projectName = "";
	if(isProjectNameExist) {
		projectName = pathName.substring(0, pathName.substring(1).indexOf('/') + 1);
	}
	return(localhostPath + projectName);
}

function modifyPassword(ctx) {
	jp.open({
		type: 2,
		area: ['600px', '400px'],
		title: "修改密码",
		content: ctx + "/sys/user/modifyPwd",
		btn: ['确定', '关闭'],
		btnAlign: 'c',
		yes: function(index, layero) {
			var body = top.layer.getChildFrame('body', index);
			var inputForm = $(body).find('#inputForm');
			var btn = body.find('#btnSubmit');
			var top_iframe = top.getActiveTab().attr("name"); //获取当前active的tab的iframe
			inputForm.attr("target", top_iframe); //表单提交成功后，从服务器返回的url在当前tab中展示
			if(inputForm.valid()) {
				var newPassword = inputForm.find("#newPassword").val();
				var confirmNewPassword = inputForm.find("#confirmNewPassword").val();
				if(newPassword != confirmNewPassword) {
					jp.alert("输入的2次新密码不一致，请重新输入！")
					return;
				}

				var regex2 = RegExp('^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z_!@#$%^&*`~()-+=]+$)(?![0-9_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9_!@#$%^&*`~()-+=]{10,50}$', 'g');
				if(!regex2.test(newPassword)) {
					jp.alert("密码强度未符合设置规则，请重新输入！")
					return;
				}

				jp.post(ctx + "/sys/user/savePwd", inputForm.serialize(), function(data) {
					if(data.success) {
						jp.success(data.msg);
						jp.close(index); //关闭dialog

					} else {
						jp.error(data.msg);
					}
				});
			} else {
				return;
			}
		},
		cancel: function(index) {}
	});
}

//确认对话框
function confirmx(mess, obj, href) {
	obj = obj || {
		title: '提示',
		icon: 7
	};
	top.layer.confirm(mess, obj, function(index) {
		if(typeof href == 'function') {
			href();
		} else {
			location = href;
		}
		top.layer.close(index);
	});
	return false;
}

function checkTel(tel) {
	if(!(/^((0\d{2,3}-\d{7,8})|(1[3567894]\d{9}))$/.test(tel))) {
		return false;
	}
	return true;
}

function isint(str) {
	var result = str.match(/^(-|\+)?\d+$/);
	if(result == null) return false;
	return true;
}

function getLastDay(interval) {
	var now = new Date();
	var lastDay = new Date(now.getFullYear(), now.getMonth(), now.getDate() - interval);
	return lastDay;
}

function getFirstDayOfThisMonth() {
	var firstDay = new Date();
	firstDay.setDate(1);
	return firstDay;
}

function getLastMonth() {
	var now = new Date();
	var lastMonth = new Date(now.getFullYear(), now.getMonth() - 1, now.getDate()); //上个月
	return lastMonth;
}

function contactUS(ctx) {
	jp.open({
		type: 2,
		area: ['600px', '400px'],
		title: "联系我们",
		content: ctx + "/contactUS",
		btn: [],
		btnAlign: 'c'
	});
}