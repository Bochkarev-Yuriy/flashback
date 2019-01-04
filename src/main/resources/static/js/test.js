var defaultStartDate = "1970-01-01";
var defaultEndDate = "2218-01-01";

$(document).ready(function () {
    $(".myFilter").on("keyup", function () {
        var allConversionUserFilter = $('.allConversionUserFilter').val();
        var allConversionChannelFilter = $('.allConversionChannelFilter').val();
        var startDate = $('.startDate').val() === '' ? defaultStartDate : $('.startDate').val();
        var endDate = $('.endDate').val() === '' ? defaultEndDate : $('.endDate').val();

        $("#testTable > tbody > tr").each(function () {
            $(this).toggle(
                (checkRangeDate($(this).children('.date').text(), startDate, endDate) > -1) &&
                ($(this).children('.from_login').text().toLowerCase().indexOf(allConversionUserFilter) > -1) &&
                ($(this).children('.channel').text().toLowerCase().indexOf(allConversionChannelFilter) > -1));
        });
    });
});

function checkRangeDate(columnDate, startDate, endDate) {
    if (getDateByDateTimeString(columnDate).getTime() >= getDateByDateTimeString(startDate).getTime() &&
        getDateByDateTimeString(columnDate).getTime() <= getDateByDateTimeString(endDate).getTime()) {
        return 0;
    } else {
        return -1;
    }
}

function getDateByDateTimeString(dateTimeString) {
    var stringDate = dateTimeString.toString();
    var bits = stringDate.split(/\D/);
    return new Date(bits[0], --bits[1], bits[2]);
}
