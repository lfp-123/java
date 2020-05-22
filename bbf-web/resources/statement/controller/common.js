/**
 * 用于下载导入模板时的隐藏form表单的提交，采用post方式提交
 * 
 * @param action
 *            action映射地址
 * @param type
 *            parameter的名称
 * @param value
 *            parameter的值，这里为file的filename
 */
function downloadTemplate(action, type, value) {
	var form = document.createElement('form');
	document.body.appendChild(form);
	form.style.display = "none";
	form.action = action;
	form.id = 'excel';
	form.method = 'post';

	var newElement = document.createElement("input");
	newElement.setAttribute("type", "hidden");
	newElement.name = type;
	newElement.value = value;
	form.appendChild(newElement);

	form.submit();
}

function lastMonthDate(){ 
	var Nowdate = new Date(); 
	var vYear = Nowdate.getFullYear(); 
	var vMon = Nowdate.getMonth() + 1; 
	var vDay = Nowdate.getDate(); 
　　// 每个月的最后一天日期（为了使用月份便于查找，数组第一位设为0）
	var daysInMonth = new Array(0,31,28,31,30,31,30,31,31,30,31,30,31); 
	if(vMon==1){ 
	vYear = Nowdate.getFullYear()-1; 
	vMon = 12; 
	}else{ 
	vMon = vMon -1; 
	} 
　　// 若是闰年，二月最后一天是29号
	if(vYear%4 == 0 && vYear%100 != 0 || vYear%400 == 0 ){ 
	daysInMonth[2]= 29; 
	} 
	if(daysInMonth[vMon] < vDay){ 
	vDay = daysInMonth[vMon]; 
	} 
	if(vDay<10){ 
	vDay="0"+vDay; 
	} 
	if(vMon<10){ 
	vMon="0"+vMon; 
	} 
	var date =vYear+"-"+ vMon +"-"+vDay;
	return date; 
}

function yesterdayDate(){
	var day = new Date();
	day.setTime(day.getTime()-24*60*60*1000);
	var vYear = day.getFullYear(); 
	var vMon = day.getMonth() + 1; 
	var vDay = day.getDate(); 
	if(vDay<10){ 
		vDay="0"+vDay; 
	} 
	if(vMon<10){ 
		vMon="0"+vMon; 
	}
	var date = vYear+"-"+ vMon +"-"+vDay;
	return date;
}

function getBeginDate(){
	var day = new Date();
	day.setTime(day.getTime()-24*60*60*1000);
	var vYear = day.getFullYear(); 
	var vMon = day.getMonth() + 1; 
	var vDay = day.getDate(); 
	if(vDay<10){ 
		vDay="0"+vDay; 
	} 
	if(vMon<10){ 
		vMon="0"+vMon; 
	}
	var date = vYear+"-"+ vMon + "-01";
	return date;
}

//限制开始和结束时间为同一个月  
function limitMonthDate(e) {  
    var DateString;  
    if (e == 2) {  
        var beginDate = $dp.$("beginDate").value;  
        if (beginDate != "" && beginDate != null) {  
            var limitDate = new Date(beginDate);  
            limitDate.setDate(new Date(limitDate.getFullYear(), limitDate  
                    .getMonth() + 1, 0).getDate()); //获取此月份的天数  
            DateString = limitDate.getFullYear() + '-'  
                    + (limitDate.getMonth() + 1) + '-'  
                    + limitDate.getDate();  
            return DateString;  
        }  
    }  
    if (e == 1) {  
        var endDate = $dp.$("endDate").value;  
        if (endDate != "" && endDate != null) {  
            var limitDate = new Date(endDate);  
            limitDate.setDate("1"); //设置闲置时间为月初  
            DateString = limitDate.getFullYear() + '-'  
                    + (limitDate.getMonth() + 1) + '-'  
                    + limitDate.getDate();  
            return DateString;  
        }  
    }  
      
}  