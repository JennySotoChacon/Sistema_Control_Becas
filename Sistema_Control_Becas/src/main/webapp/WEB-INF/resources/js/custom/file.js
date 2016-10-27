$(document).ready(function() {
    $.fn.initFile = function() {
        $(this).filestyle({buttonName: "btn-primary", size: "sm", iconName: "fa fa-folder-open-o"});
    };
    INIT_OBJE_FILE();
});
function INIT_OBJE_FILE()
{
    $(':file').initFile();
}