/**
 * 获取n天后的日期；
 * @param n
 */
function getTodayAfter(n) {
    var today;
    if (n > 0) {
        today = new Date(new Date() + n * 24 * 60 * 60 * 1000)
    } else if (n < 0) {
        n = -n;
        today = new Date(new Date() - n * 24 * 60 * 60 * 1000)
    } else {
        today = new Date()
    }

    var y = today.getFullYear();
    var m = today.getMonth() + 1;
    var d = today.getDate();
    m = m < 10 ? "0" + m : m;
    d = d < 10 ? "0" + d : d;
    return y + "-" + m + "-" + d;
}


$('.selectDate').datepicker({
    autoclose: true, //自动关闭
    beforeShowDay: $.noop,    //在显示日期之前调用的函数
    calendarWeeks: false,     //是否显示今年是第几周
    clearBtn: false,          //显示清除按钮
    daysOfWeekDisabled: [],   //星期几不可选
    endDate: Infinity,        //日历结束日期
    forceParse: true,         //是否强制转换不符合格式的字符串
    format: 'yyyy-mm-dd',     //日期格式
    keyboardNavigation: true, //是否显示箭头导航
    language: 'cn',           //语言
    minViewMode: 0,
    orientation: "auto",      //方向
    rtl: false,
    startDate: -Infinity,     //日历开始日期
    startView: 0,             //开始显示
    todayBtn: 'linked',          //今天按钮
    todayHighlight: true,    //今天高亮
    weekStart: 0              //星期几是开始
});