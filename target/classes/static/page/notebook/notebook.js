var indexOpts = (function($) {
	
	var user = Utils.checkLogin();

	var cureentUser=sessionStorage.getItem("mgmtUser")
	var user = JSON.parse(cureentUser);//转为json对象
	var id=user.id;
	var userToken=user.loginToken;
	var storage=window.localStorage;//用于将笔记本id存入本地，以便后期再指定笔记本下新建笔记
	var noteId=null;//笔记id,用于修改笔记内容
	var oldNoteTitle=null;//用于修改笔记内容
	var oldNoteContent=null;//用于修改笔记内容
	$("#userName").text(user.userName);
	
	// =====================================初始化数据=====================================
    var ReturnObj = function() {
    	
     };
	var opts = new ReturnObj();
	$(function() {
		opts.query();//加载当前用户的所有笔记本
		
	});
	
	// =====================================事件绑定=====================================
	//加载某一笔记本下面的所有笔记
	$("#notebook_li").on("click","li",function(){
		 opts.querynote(this);
	});
	 
	//加载某一笔记下面的笔记内容
	$("#note_li").on("click","li",function(){
			opts.querynoteContent(this);
	});
	//新建笔记本
	$("#create").click(function(){
		opts.addNoteBook();
		
	});
	//新建笔记
	$("#create2").click(function(){
		opts.addNote();
	});
	
	//保存或修改笔记内容
	$("#save_note").click(function(){
		opts.saveOrUpdateContent();
		});
	
	//单条或批量删除笔记本
	$("#move").click(function(){
		opts.deleteNoteBook();
		   
			});
	
	
	//退出登录
	$("#logout").click(function(){
			opts.loginOut();
			});
	
	//搜索笔记
	$("#serach_notes").click(function(){
			opts.serachNotesByKey();
			});
		
   // =====================================操作=====================================
	
	/**
	 *退出登陆
	 */
	ReturnObj.prototype.loginOut = function(){
		 $.ajax({
				url:Utils.getPath()+"/mngUser/quitLogin",
				data:{"userId":id },
				type:"post",
				success:function(result){
					if(result.tokenState==0){
						 sessionStorage.setItem("mgmtUser", JSON.stringify(null));
						  // $.cookie("auto", "false", { expire: -1});
						   //alert($.cookie("auto")+"INDEX"); 
					     window.location.href = Utils.getPath() + "/page/login.html";
					}
			},
				error:function(){
					alert("退出登录失败");
				}
			});
	}
	
			
					

		
	//查询该用户的所有笔记本
	ReturnObj.prototype.query = function(){
	 $.ajax({
				url:Utils.getPath()+"/notebook/select",
				headers:{"userToken":userToken,"userId":id},
				type:"post",
				data:{"cn_user_id":id },
				dataType:"json",
				success:function(result){
					if (Utils.parseOptResult(result)) {//token仍存在，未失效
						if(result.state==0){ //查询笔记本成功
							//alert(JSON.stringify(result.data));
							for (var i = 0;i <result.data.length;i++) {
								var sli = "";
								sli += '<li  value='+result.data[i].cn_notebook_id+' title='+result.data[i].cn_notebook_name+'>';
								sli +='<a href="javascript:void(0);" class="clearfix" style="line-height:20px;"';
								sli += '<div class="blue" style="line-height:18px;padding:5px 15px;" id="createrName" >'+'</div>';
								sli += '<div class="msg-time"><i class="fa fa-book"  rel="tooltip-bottom ">'+result.data[i].cn_notebook_name+'</i>';
								sli +='</div>';
								sli += '</a></li>';
								$("#notebook_li").append(sli);
							}
						
						}else if(result.state==1){//笔记本不存在
							alert(result.message);
						}
					}
					
				},
				error:function(){
					alert("笔记本查询失败");
				}
			});
	}
	
	
	//查询某笔记本下所有笔记
	ReturnObj.prototype.querynote = function(btn){
		 $("#myEditor").text("");//清除原有的笔记内容
	     $("#input_note_title").val("");//清除原有的笔记标题
		 $("#first").siblings('li').remove();//换笔记本加载笔记时，先移除原有的笔记
		//设置选中高亮效果
	     $(btn).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
		 $(btn).addClass('selected');// 设置选中
		 var ok=true;
		 var li= $("#note_li li:eq(1)");//获取第二个li
		
		if(li.length>0){//判断笔记是否已加载，防止重复加载；此处应该存在逻辑bug
			ok=false;
		}
		if(ok==true){
			var cn_notebook_id = $(btn).val();
			storage.setItem("cn_notebook_id",cn_notebook_id);//将笔记本id存入本地
			$.ajax({
				url:Utils.getPath()+"/note/selectAllNote",
				headers:{"userToken":userToken,"userId":id},
				type:"post",
				data:{"cn_notebook_id":cn_notebook_id},
				dataType:"json",
				success:function(result){
					if (Utils.parseOptResult(result)) {//token仍存在，未失效
						if(result.state==0){ //查询笔记成功
							for (var i = 0;i <result.data.length;i++) {
								var sli = "";
								sli += '<li value='+result.data[i].cn_note_id+'>';
								sli +='<a href="javascript:void(0);" class="clearfix" style="line-height:20px;"';
								sli += '<div class="blue" style="line-height:18px;padding:5px 15px;" id="createrName" >'+'</div>';
								sli += '<div class="msg-time"><i class="fa fa-file-text-o"  rel="tooltip-bottom ">'+result.data[i].cn_note_title+'</i></div>';
								sli += '</a></li>';
								$("#note_li").append(sli);
					}
						
						}else if(result.state==1){//笔记不存在
							
						}
					}
			
				},
				error:function(){
					alert("笔记查询失败");
				}
			});
		}
	}
	
	//加载某一笔记下面的笔记内容
	ReturnObj.prototype.querynoteContent = function(btn){
		//设置选中高亮效果
	     $(btn).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
		 $(btn).addClass('selected');// 设置选中
			
		$("#myEditor").text("");//清除原有的笔记内容
		$("#input_note_title").val("");//清除原有的笔记标题
				var ok=true
				if(ok==true){
					var cn_note_id = $(btn).val();
					noteId=$(btn).val();//笔记id,用于修改笔记内容
					$.ajax({
						url:Utils.getPath()+"/note/queryNoteContent",
						headers:{"userToken":userToken,"userId":id},
						type:"post",
						data:{"cn_note_id":cn_note_id},
						dataType:"json",
						success:function(result){
							if (Utils.parseOptResult(result)) {//token仍存在，未失效
								if(result.state==0){ //查询笔记内容成功
									$("#myEditor").text(result.data.cn_note_body);
									oldNoteContent=result.data.cn_note_body;
									$("#input_note_title").val((result.data.cn_note_title));
									oldNoteTitle=result.data.cn_note_title;
								}else if(result.state==1){//笔记内容不存在
									
								}
							}
						
						},
						error:function(){
							alert("笔记内容加载失败");
						}
					});
				}
	}
	//新建笔记本
	ReturnObj.prototype.addNoteBook = function(){
			var bookName=$("#input_notebook").val();
			if(bookName==null||bookName==''){
				$("#errMessage").text("请输入笔记本名称");
				return;
			}else{
				$.ajax({
					url:Utils.getPath()+"/notebook/add",
					headers:{"userToken":userToken,"userId":id},
					type:"post",
					data:{"cn_notebook_name":bookName,
						  "cn_user_id":id},
					dataType:"json",
					success:function(result){
						if (Utils.parseOptResult(result)) {//token仍存在，未失效
							if(result.state==0){ //
								//alert(JSON.stringify(result.data));
								var sli = "";
								sli += '<li  value='+result.data.cn_notebook_id+'>';
								sli +='<a href="javascript:void(0);" class="clearfix" style="line-height:20px;"';
								sli += '<div class="blue" style="line-height:18px;padding:5px 15px;" id="createrName" >'+'</div>';
								sli += '<div class="msg-time"><i class="fa fa-book" rel="tooltip-bottom ">'+result.data.cn_notebook_name+'</i></div>';
								sli += '</a></li>';
								$("#notebook_li").append(sli);
								
							}else if(result.state==1){//
								$("#errMessage").text("笔记本新增失败");
							}
						}
						
					},
					error:function(){
						alert("笔记本新增失败");
					}
				});
				document.getElementById("modalBasic").style.display="none";//藏
				document.getElementById("modalBasic2").style.display="none";//藏
			}
			
		}
	
	//新建笔记
	ReturnObj.prototype.addNote = function(){
		var cn_notebook_id=storage.getItem("cn_notebook_id");//获取当前点击的笔记本id
		var noteName=$("#input_note").val();
		
		if(cn_notebook_id==null||cn_notebook_id==""){
			alert("请选择笔记本");
			document.getElementById("modalBasic_2").style.display="none";//藏
			document.getElementById("modalBasic_2_2").style.display="none";//藏
			return;
		}
		if(noteName==null||noteName==''){
			$("#errMessage2").text("请输入笔记名称");
			return;
		}
		else{
			$.ajax({
				url:Utils.getPath()+"/note/insertNote",
				headers:{"userToken":userToken,"userId":id},
				type:"post",
				data:{"cn_note_title":noteName,
					  "cn_notebook_id":cn_notebook_id,
					  "cn_user_id":id},
				dataType:"json",
				success:function(result){
					if (Utils.parseOptResult(result)) {//token仍存在，未失效
						if(result.state==0){ //
							//alert(JSON.stringify(result.data));
							var sli = "";
							sli += '<li value='+result.data.cn_note_id+'>';
							sli +='<a href="javascript:void(0);" class="clearfix" style="line-height:20px;"';
							sli += '<div class="blue" style="line-height:18px;padding:5px 15px;" id="createrName" >'+'</div>';
							sli += '<div class="msg-time"><i class="fa fa-file-text-o" rel="tooltip-bottom ">'+result.data.cn_note_title+'</i></div>';
							sli += '</a></li>';
							$("#note_li").append(sli);
							
						}else if(result.state==1){//
							$("#errMessage2").text("笔记新增失败");
						}
					}
				
				},
				error:function(){
					alert("笔记新增失败");
				}
			});
			document.getElementById("modalBasic_2").style.display="none";//藏
			document.getElementById("modalBasic_2_2").style.display="none";//藏
		}
		
		}
	
	//保存笔记或修改内容
	ReturnObj.prototype.saveOrUpdateContent = function(){
		var noteTitle=$("#input_note_title").val();
		//获取此标签中的内容<script type="text/plain" id="myEditor"></script>
	    var content=document.getElementById("myEditor").innerHTML;//

		if(noteId==null||noteId==""){
			alert("请选择笔记");
			document.getElementById("modalBasic_2").style.display="none";//藏
			document.getElementById("modalBasic_2_2").style.display="none";//藏
			return;
		}
	   
	   if((oldNoteTitle==noteTitle)&&(oldNoteContent==content)){
		   console.log("标题，内容无变更");
	    	return;
	    }else if((oldNoteTitle==noteTitle)&&oldNoteContent==null&&content==""){
	    	 console.log("标题，内容无变更");
	    	return;
	    
	    } else if(noteTitle==""){
	    	alert("标题不能为空");
	    }else{
	    	$.ajax({
				url:Utils.getPath()+"/note/updateOrSave",
				headers:{"userToken":userToken,"userId":id},
				type:"post",
				data:{"cn_note_title":noteTitle,
					  "cn_note_id":noteId,
					  "cn_note_body":content,
					},
				dataType:"json",
				success:function(result){
					if (Utils.parseOptResult(result)) {//token仍存在，未失效
						if(result.state==0){ //
							//alert(JSON.stringify(result.data));
							alert("保存成功");
							
						}else if(result.state==1){//
							$("#errMessage2").text("修改或保存内容失败");
						}
					}
					
				},
				error:function(){
					alert("修改或保存内容失败");
				}
			});
			document.getElementById("modalBasic_2").style.display="none";//藏
			document.getElementById("modalBasic_2_2").style.display="none";//藏
	    }
	    // noteId=null;//清空原有的笔记id
	     alert(noteId)
		}
	
	    //在删除笔记本框中显示的笔记
		ReturnObj.prototype.queryNoteBookForDelete = function(){
			 $.ajax({
						url:Utils.getPath()+"/notebook/select",
						headers:{"userToken":userToken,"userId":id},
						type:"post",
						data:{"cn_user_id":id },
						dataType:"json",
						success:function(result){
							if (Utils.parseOptResult(result)) {//token仍存在，未失效
								if(result.state==0){ //查询笔记本成功
									for (var i = 0;i <result.data.length;i++) {
										var sli = "";
										sli += '<li  value='+result.data[i].cn_notebook_id+'>';
										sli += '<div class="msg-time"><input type="checkbox" name="noteBookId" value='+result.data[i].cn_notebook_id+'>';
										sli +=result.data[i].cn_notebook_name;
										sli +='</div>';
										sli += '</li>';
										$("#deleteNoteBook_ul").append(sli);
									}
								
								}else if(result.state==1){//笔记本不存在
									alert(result.message);
								}
							}
							
						},
						error:function(){
							alert("笔记本查询失败");
						}
					});
			}
		
		//删除选中的笔记本
		ReturnObj.prototype.deleteNoteBook = function(){
			var ids = $("input:checkbox[name='noteBookId']:checked").map(function(index,elem) {
		            return $(elem).val();
		        }).get().join(',');//获取多个选中的复选框的值
			 	alert(ids);
		        var ok=true;
		        if(ids==""||ids==null){
		        	alert("请选择要删除的笔记本");
		        	ok=false;
		        }
		        if(ok){
		       	 $.ajax({
						url:Utils.getPath()+"/notebook/delete",
						headers:{"userToken":userToken,"userId":id},
						type:"post",
						data:{"ids":ids },
						dataType:"json",
						success:function(result){
							if (Utils.parseOptResult(result)) {//token仍存在，未失效
								if(result.state==0){ //删除笔记本成功
									alert("删除笔记本成功");
									$("#notebook_li").empty();
									$("#deleteNoteBook_ul").empty();
									$("#note_li").empty();
									$("#myEditor").text("");
									$("#input_note_title").val("");
									
									opts.queryNoteBookForDelete();
									opts.query();
								}else if(result.state==1){
									
								}
							}
							
						},
						error:function(){
							alert("笔记本删除失败");
						}
					});
		        }
		        
			}
		
	//页面搜索功能
	ReturnObj.prototype.serachNotesByKey = function(){
			$("#notebook_li>li").not(":eq(0)").remove();//清除ul下第一个li以外的所有li
			$("#note_li>li").not(":eq(0)").remove();
			$("#myEditor").text("");//清除原有的笔记内容
			$("#input_note_title").val("");//清除原有的笔记标题
			
			var noteName=$("#search_note").val();
			var ok=true;
			var cn_notebook_id=null;
			if(noteName==null||noteName==""){
				opts.query();
				ok=false;
			}
		        if(ok){
		       	 $.ajax({
						url:Utils.getPath()+"/note/selectByKey",
						headers:{"userToken":userToken,"userId":id},
						type:"post",
						data:{"name":noteName },
						dataType:"json",
						success:function(result){
							if (Utils.parseOptResult(result)) {//token仍存在，未失效
								if(result.state==0){
									$("#notebook_li>li").not(":eq(0)").remove();//清除ul下第一个li以外的所有li
									$("#note_li>li").not(":eq(0)").remove();
									
									for(var i=0;i<result.data.length;i++){
										if(result.data[i].cn_notebook_id!=cn_notebook_id){
											var sli="";
											sli += '<li  value='+result.data[i].cn_notebook_id+'disabled="disabled" >';
											sli +='<a href="javascript:void(0);" class="clearfix" style="line-height:20px;"';
											sli += '<div class="blue" style="line-height:18px;padding:5px 15px;" id="createrName" >'+'</div>';
											sli += '<div class="msg-time"><i class="fa fa-book"  rel="tooltip-bottom ">'+result.data[i].cn_notebook_name+'</i>';
											sli +='</div>';
											sli += '</a></li>';
											$("#notebook_li").append(sli);
											cn_notebook_id=result.data[i].cn_notebook_id;
										}
											var sl = "";
											sl += '<li value='+result.data[i].cn_note_id+'>';
											sl +='<a href="javascript:void(0);" class="clearfix" style="line-height:20px;"';
											sl += '<div class="blue" style="line-height:18px;padding:5px 15px;" id="createrName" >'+result.data[i].cn_notebook_name+'</div>';
											sl += '<div class="msg-time"><i class="fa fa-file-text-o"  rel="tooltip-bottom ">'+result.data[i].cn_note_title+'</i></div>';
											sl += '</a></li>';
											$("#note_li").append(sl);
										}
								}else{
									alert("搜索的笔记不存在");
								}
							}
							
						},
						error:function(){
							alert("笔记搜索失败");
						}
					});
		        }
		        
			}
	return opts;
})(jQuery);