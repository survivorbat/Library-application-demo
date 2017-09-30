$(document).ready(function(){
    if($("#reserverings_datum").length!=-1) {
        var date=new Date();
        $("#reserverings_datum").val(date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate());
        $("#reserverings_datumdisplay").text(date.getDate()+"-"+date.getMonth()+"-"+date.getFullYear());
    }
    var uitleningExemplaarid = $("#uitlening_datum");
    setTimeout(function() {
        if (uitleningExemplaarid.length !== -1) {
            uitleningExemplaarid.on("click", function () {
                calculateDays();
            });
            uitleningExemplaarid.on("change", function () {
                calculateDays();
            });
            calculateDays(uitleningExemplaarid);
        }
    },400);
});
function calculateDays(){
    var today = new Date();
    var e = $("#uitlening_exemplaarid");
    today.setDate(today.getDate()+parseInt(e.find("option[value="+e.val()+"]").attr("tijd")));
    $("#uitlening_datum").val(today.getFullYear()+"-"+today.getMonth()+"-"+today.getDate());
    $("#uitlening_datum_display").text(today.getDate()+"-"+today.getMonth()+"-"+today.getFullYear());
}