/*****左侧导航与右侧tabs功能----------begin******/
/**
* 左侧导航菜单点击事件
*/
$(
function(){
$('.sider li a').click(function(){
var classId = 'index';
var subtitle = $(this).text();

var url = $(this).attr('cmshref');
//alert(url);
var rel = $(this).attr('rel');
//左侧直接打开弹窗sss
if(rel=='dialog'){
	var type = $(this).attr('type');
	//alert(type);
	openDialog(type,url,subtitle);
	return false;
}
//更新内容到右侧的tabs内容区
if(!$('#tabs_'+classId).tabs('exists',subtitle)){
	$('#tabs_'+classId).tabs('add',{
		title:subtitle,
		content:subtitle+'haha',
		closable:true,
		href:url,
		tools:[{
				iconCls:'icon-mini-refresh',
				handler:function(){
					updateTab(classId,url,subtitle);
				}
			}]
	});
	return false;
}else{
	$('#tabs_'+classId).tabs('select',subtitle);
	return false;
}
});
});
/**
* 更新tab功能
* 更新后往往会有JS错误	
*/
function updateTab(classId,url,subtitle){
	
$('#tabs_'+classId).tabs('select',subtitle);
var tab = $('#tabs_'+classId).tabs('getSelected');
tab.panel('refresh', url);
}
/*****左侧导航与右侧tabs功能----------end******/

/*****顶部特效----------begin******/
/**
* 更换EasyUI主题的方法
* @param themeName
* 主题名称
*/
changeTheme = function(themeName) {
var $easyuiTheme = $('#easyuiTheme');
var url = $easyuiTheme.attr('href');
var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
$easyuiTheme.attr('href', href);
var $iframe = $('iframe');
if ($iframe.length > 0) {
for ( var i = 0; i < $iframe.length; i++) {
var ifr = $iframe[i];
$(ifr).contents().find('#easyuiTheme').attr('href', href);
}
}
$.cookie('easyuiThemeName', themeName, {
expires : 7
});
};
function logoutFun(b) {
	$.getJSON('/userController/logout.action', function(result) {
		if (b) {
			location.replace('/index.jsp');
		} else {
			$('#sessionInfoDiv').html('');
			$('#user_login_loginDialog').dialog('open');
			$('#layout_east_onlineDatagrid').datagrid('load', {});
		}
	});
}
function userInfoFun() {
	$('<div/>').dialog({
		href : '/userController/userInfo.action',
		width : 490,
		height : 285,
		modal : true,
		title : '用户信息',
		buttons : [ {
			text : '修改密码',
			iconCls : 'icon-edit',
			handler : function() {
				var d = $(this).closest('.window-body');
				$('#user_userInfo_form').form('submit', {
					url : '/userController/modifyCurrentUserPwd.action',
					success : function(result) {
						try {
							var r = $.parseJSON(result);
							if (r.success) {
								d.dialog('destroy');
							}
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						} catch (e) {
							$.messager.alert('提示', result);
						}
					}
				});
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		},
		onLoad : function() {
		}
	});
}
/*****顶部特效--------end******/

/*****通用函数--------begin******/
/*
 *  submitForm  提交表单时执行
 *  classId 为当前表单的id
 */
function submitForm(classId){
    var url = $('#form_'+classId).attr('action');
    alert("表单提交成功！，请修改jeasyui.common.js的submitForm方法！");
	$('#dialog_cms').dialog('close');
    return false;
    $('#form_'+classId).form('submit',{
        url:url,
        onSubmit:function(){
        //$('#dialog').dialog('refresh', '__APP__/Setting/add');
        //alert(url);
        //$('#dialog').dialog('close');
        },
        success:function(msg){
            var data = $.parseJSON(msg);
            //alert(data.msg+'=======dede====');
            //return false;
            formAjax(data,classId);
        }
    });

}
function formAjax(data,classId){
    //alert(classId);
    //return false;

    if(data.status==1){
        $.messager.alert(data.info,data.info,'error');
    }else if(data.status==2){
        $.messager.show({
            title:data.info,
            msg:data.info,
            timeout:5000,
            showType:'slide'
        });
        $('#treegrid_'+classId).treegrid('reload');
        if(data.isclose=='ok'){
            $('#dialog_'+classId).dialog('close');
            dialogOnClose(classId);
        }
    }
}
/*
 *openDialog 弹出框
 *href 传递控制器的url地址
 *title 弹出窗口的标题
 */
function openDialog(classId,href,title){
    $('#dialog_cms').dialog({
        href:href,
        width:1000,
        height:680,
        resizable:true,
        title:title,
        modal:true,
        resizable:true,
        collapsible:true,
        maximizable:true,
        cache: false,
        onClose:function(){
            dialogOnClose(classId);
        },
        buttons:[{
            text:'保存',
            iconCls:'icon-ok',
            handler:function(){
                submitForm(classId);
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                dialogOnClose(classId);
            }
        }
        ]
    });
//$('#dialog'+classId).dialog('refresh', href);
}
/*
* 关闭dialog时，销毁dialog代码
*/
function dialogOnClose(classId){
    $('#dialog_cms').dialog('destroy');
    $('body.easyui-layout').append('<div id="dialog_cms"  data-options="iconCls:\'icon-save\'"></div>');
    var frame = $('iframe[src="about:blank"]');//destroy与iframe冲突问题，大概是内存释放的原因
    frame.remove();
}
/*****通用函数--------end******/

/*****news函数--------begin******/
var admin_news_viewFun = function(id){
		alert("打开前台页面");
};

var admin_news_cancelFun = function(id){
		var hrefcancel = 'news/del.html';
		var href = hrefcancel;
		var title = '删除信息';
		$.messager.confirm(title,href, function(){
			$.ajax({
				url:href,
				type:'post',
				data:{
					id:ids
				},
				dataType:'json',
				success:function(data){
					formAjax(data,classId);
				}
			});
		});//$
};

function admin_user_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:360,
		height:180,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#useraddform').form('submit', {
				url : 'AddUserAction!AddUser.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}

function admin_user_editFun(classId,hrefedit) {
    var node = $('#datagrid_'+classId).datagrid('getSelected');
	if(!node){
		$.messager.alert( '请选择数据',   '请选择要编辑的数据' ,'icon-info'  );
	}
	else{
    $('<div></div>').dialog({
		href: hrefedit,
		width: 360,
		height: 180,
		modal: true,
		resizable:true,
		collapsible:true,
		maximizable:true,
        title: '编辑用户',
        buttons: [{
            text: '确定',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#usereditform').form('submit', {
                    url: 'EditUserAction!EditUser.action',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                d.dialog('destroy');
						   $('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
		$('#user_id').val(node.id);
		$('#user_username').val(node.username);
		$('#user_password').val(node.password);
        }
    });
	}
}

function admin_user_cancelFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].username + '】等'+rows.length+'条记录？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'DeleteUserAction!DeleteUser.action',
                    data: {
                        idArray: idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_tags_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:360,
		height:180,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#tagsaddform').form('submit', {
				url : 'AddTagsAction!AddTags.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}

function admin_tags_editFun(classId,hrefedit) {
    var node = $('#datagrid_'+classId).datagrid('getSelected');
	if(!node){
		$.messager.alert( '请选择数据',   '请选择要编辑的数据' ,'icon-info'  );
	}
	else{
    $('<div></div>').dialog({
		href: hrefedit,
		width: 390,
		height: 300,
		modal: true,
		resizable:true,
		collapsible:true,
		maximizable:true,
        title: '编辑提示信息',
        buttons: [{
            text: '确定',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#tagseditform').form('submit', {
                    url: 'EditTagsAction!EditTags.action',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                d.dialog('destroy');
						   $('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
		$('#tags_id').val(node.id);
		$('#tags_tagname').val(node.tagname);
		$('#tags_selected').val(node.selected);
		$('#tags_imageaddress').val(node.imageaddress);
        }
    });
	}
}

function admin_tags_cancelFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].tagname + '】等'+rows.length+'条记录？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'DeleteTagsAction!DeleteTags.action',
                    data: {
                        idArray:idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_tags_searchFun(classId,keyword) {
	var kw = encodeURI(keyword);
	$.ajax({
              url: 'SearchTagsAction!SearchTags.action',
			method:'post',
              data: {
                   keyword:kw
                    },
               cache: false,
               dataType: 'JSON',
               success: function(r) {
               if (r.success) {
                   $('#datagrid_'+classId).datagrid('loadData',r.rows);
                   }
			else{
				$.messager.show({
                        msg: r.msg,
                            title: '提示'
                        });
				}
			}
		})
}

function admin_lives_searchFun(classId,keyword) {
	var kw = encodeURI(keyword);
	$.ajax({
              url: 'SearchLivesAction!SearchLives.action',
			method:'post',
              data: {
                   keyword:kw
                    },
               cache: false,
               dataType: 'JSON',
               success: function(r) {
               if (r.success) {
                   $('#datagrid_'+classId).datagrid('loadData',r.rows);
                   }
			else{
				$.messager.show({
                        msg: r.msg,
                            title: '提示'
                        });
				}
			}
		})
}

function admin_sites_searchFun(classId,keyword) {
	var kw = encodeURI(keyword);
	$.ajax({
              url: 'SearchSitesAction!SearchSites.action',
			method:'post',
              data: {
                   keyword:kw
                    },
               cache: false,
               dataType: 'JSON',
               success: function(r) {
               if (r.success) {
                   $('#datagrid_'+classId).datagrid('loadData',r.rows);
                   }
			else{
				$.messager.show({
                        msg: r.msg,
                            title: '提示'
                        });
				}
			}
		})
}

function admin_trailers_searchFun(classId,keyword) {
	var kw = encodeURI(keyword);
	$.ajax({
              url: 'SearchTrailersAction!SearchTrailers.action',
			method:'post',
              data: {
                   keyword:kw
                    },
               cache: false,
               dataType: 'JSON',
               success: function(r) {
               if (r.success) {
                   $('#datagrid_'+classId).datagrid('loadData',r.rows);
                   }
			else{
				$.messager.show({
                        msg: r.msg,
                            title: '提示'
                        });
				}
			}
		})
}

function admin_messages_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:460,
		height:280,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#messagesaddform').form('submit', {
				url : 'AddMessagesAction!AddMessages.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}

function admin_messages_editFun(classId,hrefedit) {
    var node = $('#datagrid_'+classId).datagrid('getSelected');
	if(!node){
		$.messager.alert( '请选择数据',   '请选择要编辑的数据' ,'icon-info'  );
	}
	else{
    $('<div></div>').dialog({
		href: hrefedit,
		width: 460,
		height: 280,
		modal: true,
		resizable:true,
		collapsible:true,
		maximizable:true,
        title: '编辑提示信息',
        buttons: [{
            text: '确定',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#messageseditform').form('submit', {
                    url: 'EditMessagesAction!EditMessages.action',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                d.dialog('destroy');
						   $('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
		$('#messages_id').val(node.id);
		$('#messages_content').val(node.content);
		$('#messages_selected').val(node.selected);
        }
    });
	}
}

function admin_messages_cancelFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].id + '】等'+rows.length+'条记录？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'DeleteMessagesAction!DeleteMessages.action',
                    data: {
                        idArray: idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_movies_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:520,
		height:480,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#moviesaddform').form('submit', {
				url : 'AddMoviesAction!AddMoviesAuto.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}

function admin_movies_editFun(classId,hrefedit) {
    var node = $('#datagrid_'+classId).datagrid('getSelected');
	if(!node){
		$.messager.alert( '请选择数据',   '请选择要编辑的数据' ,'icon-info'  );
	}
	else{
    $('<div></div>').dialog({
		href: hrefedit,
		width: 520,
		height: 480,
		modal: true,
		resizable:true,
		collapsible:true,
		maximizable:true,
        title: '编辑提示信息',
        buttons: [{
            text: '确定',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#movieseditform').form('submit', {
                    url: 'EditMoviesAction!EditMovies.action',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                d.dialog('destroy');
						   $('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
		$('#movies_id').val(node.id);
		$('#movies_movietag').val(node.movietag);
		$('#movies_moviename').val(node.moviename);
		$('#movies_filepath').val(node.filepath);
		$('#movies_photopath').val(node.photopath);
		$('#movies_description').val(node.description);
		$('#movies_duration').val(node.duration);
		$('#movies_doubanid').val(node.doubanid);
		$('#movies_doubanscore').val(node.doubanscore);
		$('#movies_country').val(node.country);
		$('#movies_year').val(node.year);
		$('#movies_director').val(node.director);
		$('#movies_actor').val(node.actor);
		$('#movies_count').val(node.count);
		$('#movies_newestscore').val(node.newestscore);
		$('#movies_hottestscore').val(node.hottestscore);
		$('#movies_classicscore').val(node.classicscore);
        }
    });
	}
}

function admin_movies_addTagsFun(classId,href,title) {
		var idArray = ' ';
		var rows = $('#datagrid_'+classId).datagrid('getSelections');
		//var node = $('#datagrid_'+classId).datagrid('getSelected');
		for(var i = 0; i < rows.length; i++){
			idArray = idArray + rows[i].id + ' ';
		}
		//$.messager.alert(rows[0].id);
		
		if(rows.length)
		{
		$('<div></div>').dialog({
		href:href,
		width:520,
		height:480,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#moviesaddtagsform').form('submit', {
				url : 'AddMovieTagsAction!AddMovieTags.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		},
		onLoad:function(){
			$('#id').val(idArray);
		}
		
		});
		}		
		else
		{
			$.messager.alert( '请选择数据',   '请选择要添加标签的电影' ,'icon-info'  );
		}
		
}

function admin_movies_importFun(classId,href,title) {

		$('<div></div>').dialog({
		href:href,
		width:520,
		height:480,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				
				$('#moviesimportmoviesform').form('submit', {
				url : 'ImportMoviesAction!ImportMovies.action',
				onSubmit: function () {
					var flag = $(this).form('validate');
					if (flag) {
						$.messager.progress({
						title:'提示',
						msg:'正在导入电影中，请等待...'
						});
						}
					return flag
				},
				success : function(result) {
				
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						$.messager.progress('close');
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
	
		
		});
		
		
}

function admin_movies_getinfoFun(classId) {
	var idArray = ' ';
	var doubanidArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		doubanidArray = doubanidArray + rows[i].doubanid + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要获取【' + rows[0].moviename + '】等'+rows.length+'条电影的信息？',
        function(b) {
            if (b) {
				$.messager.progress({
						title:'提示',
						msg:'正在获取电影信息中，请等待...'
						});
                $.ajax({
                    url: 'GetMoviesInfoAction!GetMoviesInfo.action',
                    data: {
                        idArray: idArray,
						doubanidArray:doubanidArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
							$.messager.progress('close');
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_movies_cancelFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].moviename + '】等'+rows.length+'条记录？',
        function(b) {
            if (b) {
				$.messager.progress({
						title:'提示',
						msg:'正在删除电影中，请等待...'
						});
                $.ajax({
                    url: 'DeleteMoviesAction!DeleteMovies.action',
                    data: {
                        idArray: idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
							$.messager.progress('close');
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_movies_removeCountFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].moviename + '】等'+rows.length+'条记录的播放次数？',
        function(b) {
            if (b) {
				$.messager.progress({
						title:'提示',
						msg:'正在修改电影中，请等待...'
						});
                $.ajax({
                    url: 'DeleteMoviesAction!DeleteCount.action',
                    data: {
                        idArray: idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
							$.messager.progress('close');
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_movies_searchFun(classId,keyword) {
	//alert("fdgd");
	var kw = encodeURI(keyword);
	$.ajax({
              url: 'SearchMoviesAction!SearchMovies.action',
			method:'post',
              data: {
                   keyword:kw
                    },
               cache: false,
               dataType: 'JSON',
               success: function(r) {
               if (r.success) {
                   $('#datagrid_'+classId).datagrid('loadData',r.rows);
                   }
			else{
				$.messager.show({
                        msg: r.msg,
                            title: '提示'
                        });
				}
			}
		})
}

function admin_sites_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:360,
		height:180,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#sitesaddform').form('submit', {
				url : 'AddSitesAction!AddSites.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}

function admin_sites_editFun(classId,hrefedit) {
    var node = $('#datagrid_'+classId).datagrid('getSelected');
	if(!node){
		$.messager.alert( '请选择数据',   '请选择要编辑的数据' ,'icon-info'  );
	}
	else{
    $('<div></div>').dialog({
		href: hrefedit,
		width: 390,
		height: 300,
		modal: true,
		resizable:true,
		collapsible:true,
		maximizable:true,
        title: '编辑提示信息',
        buttons: [{
            text: '确定',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#siteseditform').form('submit', {
                    url: 'EditSitesAction!EditSites.action',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                d.dialog('destroy');
						   $('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
		$('#sites_id').val(node.id);
		$('#sites_sitename').val(node.sitename);
		$('#sites_sitepath').val(node.sitepath);
        }
    });
	}
}

function admin_sites_cancelFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].sitename + '】等'+rows.length+'条记录？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'DeleteSitesAction!DeleteSites.action',
                    data: {
                        idArray:idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_lives_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:360,
		height:180,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#livesaddform').form('submit', {
				url : 'AddLivesAction!AddLives.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}

function admin_lives_editFun(classId,hrefedit) {
    var node = $('#datagrid_'+classId).datagrid('getSelected');
	if(!node){
		$.messager.alert( '请选择数据',   '请选择要编辑的数据' ,'icon-info'  );
	}
	else{
    $('<div></div>').dialog({
		href: hrefedit,
		width: 390,
		height: 300,
		modal: true,
		resizable:true,
		collapsible:true,
		maximizable:true,
        title: '编辑提示信息',
        buttons: [{
            text: '确定',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#liveseditform').form('submit', {
                    url: 'EditLivesAction!EditLives.action',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                d.dialog('destroy');
						   $('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
		$('#lives_id').val(node.id);
		$('#lives_livename').val(node.livename);
		$('#lives_liveaddress').val(node.liveaddress);
		$('#lives_imageaddress').val(node.imageaddress);
        }
    });
	}
}

function admin_lives_cancelFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].livename + '】等'+rows.length+'条记录？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'DeleteLivesAction!DeleteLives.action',
                    data: {
                        idArray:idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_trailers_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:520,
		height:480,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#trailersaddform').form('submit', {
				url : 'AddTrailersAction!AddTrailers.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}

function admin_trailers_editFun(classId,hrefedit) {
    var node = $('#datagrid_'+classId).datagrid('getSelected');
	if(!node){
		$.messager.alert( '请选择数据',   '请选择要编辑的数据' ,'icon-info'  );
	}
	else{
    $('<div></div>').dialog({
		href: hrefedit,
		width: 390,
		height: 300,
		modal: true,
		resizable:true,
		collapsible:true,
		maximizable:true,
        title: '编辑提示信息',
        buttons: [{
            text: '确定',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#trailerseditform').form('submit', {
                    url: 'EditTrailersAction!EditTrailers.action',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                d.dialog('destroy');
						   $('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
		$('#movies_id').val(node.id);
		$('#movies_movietag').val(node.movietag);
		$('#movies_moviename').val(node.moviename);
		$('#movies_filepath').val(node.filepath);
		$('#movies_photopath').val(node.photopath);
		$('#movies_description').val(node.description);
		$('#movies_duration').val(node.duration);
		$('#movies_doubanid').val(node.doubanid);
		$('#movies_country').val(node.country);
		$('#movies_year').val(node.year);
		$('#movies_director').val(node.director);
		$('#movies_actor').val(node.actor);
		$('#movies_count').val(node.count);
        }
    });
	}
}

function admin_trailers_getinfoFun(classId) {
	var idArray = ' ';
	var doubanidArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		doubanidArray = doubanidArray + rows[i].doubanid + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要获取【' + rows[0].moviename + '】等'+rows.length+'条电影的信息？',
        function(b) {
            if (b) {
				$.messager.progress({
						title:'提示',
						msg:'正在获取电影信息中，请等待...'
						});
                $.ajax({
                    url: 'GetTrailersInfoAction!GetTrailersInfo.action',
                    data: {
                        idArray: idArray,
						doubanidArray:doubanidArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
							$.messager.progress('close');
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_trailers_importFun(classId,href,title) {

		$('<div></div>').dialog({
		href:href,
		width:520,
		height:480,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				
				$('#trailersimportmoviesform').form('submit', {
				url : 'ImportTrailersAction!ImportTrailers.action',
				onSubmit: function () {
					var flag = $(this).form('validate');
					if (flag) {
						$.messager.progress({
						title:'提示',
						msg:'正在导入影片中，请等待...'
						});
						}
					return flag
				},
				success : function(result) {
				
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						$.messager.progress('close');
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
	
		
		});
		
		
}

function admin_trailers_addTagsFun(classId,href,title) {
		var idArray = ' ';
		var rows = $('#datagrid_'+classId).datagrid('getSelections');
		//var node = $('#datagrid_'+classId).datagrid('getSelected');
		for(var i = 0; i < rows.length; i++){
			idArray = idArray + rows[i].id + ' ';
		}
		//$.messager.alert(rows[0].id);
		
		if(rows.length)
		{
		$('<div></div>').dialog({
		href:href,
		width:520,
		height:480,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#trailersaddtagsform').form('submit', {
				url : 'AddTrailerTagsAction!AddTrailerTags.action',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						d.dialog('destroy');
						$('#datagrid_'+classId).datagrid('reload');		//使用动态载入方式比较友好
					}
					else{
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
					}	
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		},
		onLoad:function(){
			$('#id').val(idArray);
		}
		
		});
		}		
		else
		{
			$.messager.alert( '请选择数据',   '请选择要添加标签的预告片' ,'icon-info'  );
		}
		
}

function admin_trailers_cancelFun(classId) {
	var idArray = ' ';
	var rows = $('#datagrid_'+classId).datagrid('getSelections');
    //var node = $('#datagrid_'+classId).datagrid('getSelected');
	for(var i = 0; i < rows.length; i++){
		idArray = idArray + rows[i].id + ' ';
		}
    if (rows) {
        $.messager.confirm('询问', '您确定要删除【' + rows[0].livename + '】等'+rows.length+'条记录？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'DeleteTrailersAction!DeleteTrailers.action',
                    data: {
                        idArray:idArray
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#datagrid_'+classId).datagrid('reload');
                        }
					else{
					$.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
					}
                        
                    }
                });
            }
        });
    }
}

function admin_news_appendFun(classId,href,title) {
		$('#datagrid_newstab0').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div></div>').dialog({
		href:href,
		width:1000,
		height:680,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#newsform').form('submit', {
				url : 'news/save.html',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
					$('#datagrid_newstab0').datagrid('insertRow', {
						index : 0,
						row : r.obj
					});
					d.dialog('destroy');
					}
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});
}
function admin_news_editFun(id) {
		href = 'news/edit.html?id='+id;
		title = '编辑文档';
		$('#datagrid_newstab0').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');	//清除选择
		$('<div></div>').dialog({
		href:href,
		width:1000,
		height:680,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#newseditform').form('submit', {
					url : 'news/editsave.html',
					success: function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									$('#datagrid_newstab0').datagrid('updateRow', {
										index: $('#datagrid_newstab0').datagrid('getRowIndex', id),
										row: r.obj
									});
									d.dialog('destroy');
								}
							} catch(e) {
								$.messager.alert('提示', result);
							}
					}
				});
			}
		} ],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
            var index = $('#datagrid_newstab0').datagrid('getRowIndex', id);
            var rows = $('#datagrid_newstab0').datagrid('getRows');
            var o = rows[index];
            o.roleIds = stringToList(rows[index].roleIds);
            $('#newseditform').form('load', o);
        }
		});
}
function admin_news_removeFun() {
    var rows = $('#datagrid_newstab0').datagrid('getChecked');
    var ids = [];
    if (rows.length > 0) {
        $.messager.confirm('确认', '您是否要删除当前选中的项目？',
        function(r) {
            if (r) {
                var flag = true;
                $.ajax({
                    url: 'news/del.html',
                    data: {
                        ids: ids.join(',')
                    },
                    dataType: 'json',
                    success: function(result) {
                        if (result.success) {
                            $('#datagrid_newstab0').datagrid('load');
                            $('#datagrid_newstab0').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                        }
                        $.messager.show({
                            title: '提示',
                            msg: result.msg
                        });
                    }
                });
            }
        });
    } else {
        $.messager.show({
            title: '提示',
            msg: '请勾选要删除的记录！'
        });
    }
}
function admin_newscat_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:600,
		height:400,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#newsaddform').form('submit', {
				url : 'news/newscatsave.html',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						/*$('#treegrid_newssort').treegrid('append', {	//此段数据对显示效果有影响
							parent: r.obj.pid,
							data: [r.obj]
						});*/
						d.dialog('destroy');
						$('#treegrid_newssort').treegrid('reload');		//使用动态载入方式比较友好
					}
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}
function admin_newscat_editFun(id) {
    if (id != undefined) {
        $('#treegrid_newssort').treegrid('select', id);
    }
    var node = $('#treegrid_newssort').treegrid('getSelected');
    $('<div></div>').dialog({
        href: 'news/newssortedit.html',
        width: 600,
        height: 400,
        modal: true,
        title: '编辑分类',
        buttons: [{
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#newscateditform').form('submit', {
                    url: 'news/newscatsave.html',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                $('#treegrid_newssort').treegrid('reload');
                                d.dialog('destroy');
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
            //$('#newscateditform').form('load', node);	//自动载入节点信息
        }
    });
}

function admin_newscat_cancelFun(id) {
    if (id != undefined) {
        $('#treegrid_newssort').treegrid('select', id);
    }
    var node = $('#treegrid_newssort').treegrid('getSelected');
    if (node) {
        $.messager.confirm('询问', '您确定要删除【' + node.name + '】？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'news/catdel.html',
                    data: {
                        id: node.id
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#treegrid_newssort').treegrid('remove', r.obj); 	//显示删除效果
                            $('#treegrid_newssort').treegrid('reload');
                        }
                        $.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
                    }
                });
            }
        });
    }
}
/*****news函数--------end******/

/*****pictures函数--------begin******/
var admin_pics_viewFun = function(id){
		alert("打开前台页面");
};
var admin_pics_editFun = function(id){
		var classId = 'picstab0';
		var hrefadd = 'pictures/add.html';
		var title = '添加图片';
		openDialog(classId,hrefadd,title);
};
var admin_pics_cancelFun = function(id){
		var hrefcancel = 'pictures/del.html';
		var href = hrefcancel;
		var title = '删除信息';
		$.messager.confirm(title,href, function(){
			$.ajax({
				url:href,
				type:'post',
				data:{
					id:ids
				},
				dataType:'json',
				success:function(data){
					formAjax(data,classId);
				}
			});
		});//$
};
/*****pictures函数--------end******/

/*****friendlink函数--------begin******/
var admin_links_viewFun = function(id){
		alert("打开前台页面");
};
var admin_links_editFun = function(id){
		var classId = 'picstab0';
		var hrefadd = 'pictures/add.html';
		var title = '添加链接';
		openDialog(classId,hrefadd,title);
};
var admin_links_cancelFun = function(id){
		var hrefcancel = 'pictures/del.html';
		var href = hrefcancel;
		var title = '删除链接';
		$.messager.confirm(title,href, function(){
			$.ajax({
				url:href,
				type:'post',
				data:{
					id:ids
				},
				dataType:'json',
				success:function(data){
					formAjax(data,classId);
				}
			});
		});//$
};
/*****friendlink函数--------end******/

