/**
 * multi.select.js
 * @authors Jack Chan (fulicat@qq.com)
 * @date    2016-05-11 10:59:22
 * @update  2016-05-11 13:56:35
 * @update  2016-05-11 15:13:06
 * @update  2016-05-11 19:25:21
 * @update  2016-05-11 22:43:21
 * @update  2016-05-12 11:09:06
 * @update  2016-05-12 13:00:30
 * @version 1.5.3 beta
 */

(function(e){
	$.fn.multiSelect = function(opt, data, separator){
		data = (data==undefined ? {} : data);
		separator = (separator==undefined ? ';' : separator);
		if(typeof opt=='string'){
			switch(opt){
				case 'enable':
					$(this).removeClass('active disabled');
					$(this).find('input[type="checkbox"]').removeAttr('disabled', 'disabled');
					break;
				case 'disable':
					$(this).removeClass('active').addClass('disabled');
					$(this).find('.multi-select-value').val('');
					$(this).find('.multi-select-text').val('');
					$(this).find('input[type="checkbox"]').prop('checked', false).attr('disabled', 'disabled');
					break;
				case 'reset':
					$(this).find('.multi-select-value').val('');
					$(this).find('.multi-select-text').val('');
					$(this).find('input[type="checkbox"]').removeAttr('disabled', 'disabled').removeAttr('checked', 'checked');
				case 'empty':
					$(this).find('.multi-select-value').val('');
					$(this).find('.multi-select-text').val('');
					$(this).find('.multi-select-text').html('');
					break;
				case 'check':
					var $checkbox;
					if(typeof data=='object'){
						if(data.length==undefined){
							data = [data];
						};
						var item;
						for(itemIndex in data){
							item = data[itemIndex];
							$(this).find('input[type="checkbox"]:not(.multi-select-checkall)').each(function(index, checkbox){
								this.text = $(this).data('text');
								if(item.value!=undefined){
									if(item.value==this.value){
										$checkbox = $(checkbox);
										return;
									};
								}else if(item.text!=undefined){
									if(item.text==this.text){
										$checkbox = $(checkbox);
										return;
									};
								};
							});
							if($checkbox){
								if(item.checked){
									$checkbox.removeAttr('checked','checked');
								};
								if($checkbox.attr('checked')){
									$checkbox.removeAttr('checked','checked').trigger('click').removeAttr('checked','checked');
								}else{
									$checkbox.attr('checked','checked').trigger('click').attr('checked','checked');
								};
							};
						};
					}else{
						data = (data>-1 ? parseInt(data+1) : 0);
						$checkbox = $(this).find('input[type="checkbox"]').eq(data);
						if($checkbox){
							if($checkbox.attr('checked')){
								$checkbox.removeAttr('checked','checked').trigger('click').removeAttr('checked','checked');
							}else{
								$checkbox.attr('checked','checked').trigger('click').attr('checked','checked');
							};
						};
					};
					
					break;
				case 'checkAll':
					$(this).multiSelect('check', -1);
					break;
				case 'getText':
					return $(this).find('.multi-select-text').val();
					break;
				case 'getValue':
					return $(this).find('.multi-select-value').val();
					break;
				case 'setTextValue':
					if(typeof data=='string'){
						$(this).find('.multi-select-text').val(data);
						$(this).find('.multi-select-value').val(data);
					}else{
						console.error('Error: data type is not string');
					};
					break;
				case 'setText':
					if(typeof data=='string'){
						$(this).find('.multi-select-text').val(data);
					}else{
						console.error('Error: data type is not string');
					};
					break;
				case 'setValue':
					if(typeof data=='string'){
						$(this).find('.multi-select-value').val(data);
						if(separator===1 || separator===true)$(this).find('.multi-select-text').val(data);
					}else if(typeof data=='object' && data.length){
						if(data.length){
							var html = [];
							var text = [], value = [];
							$.each(data, function(index, item){
								html.push('<li class="multi-select-item" data-index="'+ index +'"><label><input type="checkbox" value="'+ $.trim(item.value!=undefined?item.value:item.text) +'" data-text="'+ $.trim(item.text!=undefined?item.text:item.value) +'"'+(item.checked?' checked="checked"':'')+'><span title="'+ $.trim(item.text!=undefined?item.text:item.value) +'">'+ $.trim(item.text!=undefined?item.text:item.value) +'</span></label></li>');
								text.push($.trim(item.text!=undefined?item.text:item.value));
								value.push($.trim(item.value!=undefined?item.value:item.text));
							});
							html.unshift('<li class="multi-select-item" data-index="-1"><label><input type="checkbox" class="multi-select-checkall"><span>全部</span></label></li>');
							$(this).find('.multi-select-list').html(html.join(''));
							$(this).find('.multi-select-value').val(value.join(separator));
							$(this).find('.multi-select-text').val(text.join(separator));
						};
					}else{
						console.error('Error: data type is not an array');
					};
					break;
				case 'fetch':
					if(data.length){
						var html=[];
						$.each(data, function(index, item){
							html.push('<li class="multi-select-item" data-index="'+ index +'"><label><input type="checkbox" value="'+ $.trim(item.value!=undefined?item.value:item.text) +'" data-text="'+ $.trim(item.text!=undefined?item.text:item.value) +'"'+(item.checked?' checked="checked"':'')+'><span title="'+ $.trim(item.text!=undefined?item.text:item.value) +'">'+ $.trim(item.text!=undefined?item.text:item.value) +'</span></label></li>');
						});
						html.unshift('<li class="multi-select-item" data-index="-1"><label><input type="checkbox" class="multi-select-checkall"><span>全部</span></label></li>');
						$(this).find('.multi-select-list').html(html.join(''));
					};
					break;
			};
			return this;
		};
		var opts = $.extend({checkall:1, separator:';',eventType:'click',click:function(){},check:function(){}}, opt);
		opts.isTrigger = 1;
		opts.$instance = undefined;
		opts.EventReset = function(){
			if(opts.$instance){
				opts.$instance = undefined;
			};
		};
		opts.EventHide = function(){
			$('.multi-select').removeClass('active');
			opts.EventReset();
		};
		opts.EventCheck = function(isCheckAll){
			isCheckAll = isCheckAll || false;
			//if(opts.$instance){
				if(isCheckAll){
					opts.$instance.text = [];
					opts.$instance.value = [];
					if(this.checked){
						opts.$instance.find('input[type="checkbox"]:not(.multi-select-checkall)').each(function(index, item){
							opts.$instance.value.push(this.value);
							opts.$instance.text.push($(this).data('text'));
							this.checked = true;
						});
					}else{
						opts.$instance.find('input[type="checkbox"]:not(.multi-select-checkall)').prop('checked', false);
					};
				}else{
					this.text = $.trim($(this).data('text'));
					this.value = $.trim(this.value);
					opts.$instance.value = opts.$instance.value || [];
					opts.$instance.text = opts.$instance.text || [];
					if(this.checked){
						if(opts.$instance.value.indexOf(this.value) < 0){
							opts.$instance.value.push(this.value);
						};
						if(opts.$instance.text.indexOf(this.text) < 0){
							opts.$instance.text.push(this.text);
						};
					}else{
						if(opts.$instance.value.indexOf(this.value) > -1){
							opts.$instance.value.splice(opts.$instance.value.indexOf(this.value), 1);
						};
						if(opts.$instance.text.indexOf(this.text) > -1){
							opts.$instance.text.splice(opts.$instance.text.indexOf(this.text), 1);
						};
					};
				};
				opts.$instance.selectedIndex = function(separator){
					var indexs = [];
					opts.$instance.find('input[type="checkbox"]:not(.multi-select-checkall)').each(function(index, item){
						if(this.checked)indexs.push(index);
					});
					if(typeof separator==undefined){
						return indexs;
					}else{
						return indexs.join(separator);
					};
				};
				opts.$instance.find('.multi-select-text').val(opts.$instance.text.join(opts.separator));
				opts.$instance.find('.multi-select-value').val(opts.$instance.value.join(opts.separator));
				this.index = function(){
					return $(this).parent().parent().index() - 1;
				};
				opts.check.apply(this, [opts.$instance]);
				opts.isTrigger = 1;
			//};
			
		};
		
		$(document).off(opts.eventType, this.selector +'-text');
		$(document).off(opts.eventType, this.selector +'-list');
		$(document).off('click', this.selector +'- input[type="checkbox"]');
		$(document).off('click', opts.EventHide);
		$(document).on(opts.eventType, this.selector +'-text', function(e){
			e.stopPropagation();
			if($(this).parent().hasClass('active')){
				$(this).parent().toggleClass('active');
			}else{
				opts.EventHide();
				opts.$instance = $(this).parent().addClass('active');
			};
			opts.$instance.text = opts.$instance.find('.multi-select-text').val().split(opts.separator);
			opts.$instance.text = opts.$instance.text.filter(function(v){return v!==''});
			opts.$instance.value = opts.$instance.find('.multi-select-value').val().split(opts.separator);
			opts.$instance.value = opts.$instance.value.filter(function(v){return v!==''});
			if(typeof opts[opts.eventType]=='function'){
				opts[opts.eventType].apply(this, [e, opts.$instance]);
			};
			opts.isTrigger = 0;
		}).on(opts.eventType, this.selector +'-list', function(e){
			e.stopPropagation();
		}).on('click', this.selector +' input[type="checkbox"]', function(e){
			e.stopPropagation();
			if(opts.isTrigger){
				//opts.EventHide();
				opts.$instance = $(this).parent().parent().parent().parent();
				opts.$instance.text = opts.$instance.find('.multi-select-text').val().split(opts.separator);
				opts.$instance.text = opts.$instance.text.filter(function(v){return v!==''});
				opts.$instance.value = opts.$instance.find('.multi-select-value').val().split(opts.separator);
				opts.$instance.value = opts.$instance.value.filter(function(v){return v!==''});
			};
			var isCheckAll = $(this).hasClass('multi-select-checkall');
			opts.EventCheck.apply(this, [isCheckAll]);
		}).on('click', opts.EventHide);

		return this;
	};
}($));