// 模拟后台接口
function getData(data) {
	var start = (params.current - 1) * params.size;
	var end = params.current *params.size;
	
	return {
		total: data.length,
		list: data.splice( (params.current - 1) * params.size, params.size )
	}
}

// 设置tbody的html
function setTbody (arr) {
	var html = '';
	for (var i = 0; i < arr.length; i++) {
		var item = arr[i];
		html += '<tr>' +
			'<td>' + item.index + '</td>' +
			'<td>' + item.batch + '</td>' +
			'<td>' + item.id + '</td>' +
			'<td>' + item.name + '</td>' +
			'<td>' + item.phone +'</td>' +
			'<td>' + item.status + '</td>' +
			'<td>\' + item.hit + \'</td>' +
			'<td>\' + item.time + \'</td>' +
			'<td>\' + item.operate + \'</td></tr>';
	}
	$('.tbody').html(html);
}

// 初始化分页
$('.box2').MyPaging({
	size: 10,
	total: 0,
	current: 1,
	prevHtml: '上一页',
	nextHtml: '下一页',
	layout: 'total, totalPage, prev, pager, next, jumper',
	jump: function () {
		var _this = this;

		// 模拟ajax获取数据
		setTimeout(function () {
			var res = getData({
				size: _this.size,
				current: _this.current
			});

			setTbody(res.list);

			// 必须调用
			_this.setTotal(res.total);
		}, 100);
	}
});