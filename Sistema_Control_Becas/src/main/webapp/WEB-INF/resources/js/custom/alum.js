$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consAlum([{name : 'codiAlumPara', value : row.id.trim()}]);
        });
        return false;
    };
    $.fn.initDatePick = function() {
        $(this).datepicker({
            format: "dd/mm/yyyy",
            language: "es",
            orientation: "top auto",
            autoclose: true,
            todayHighlight: true
        }).on('show.bs.modal', function(e) {
            // Quitar el conflicto con bootstrap modal "show.bs.modal"
            e.stopPropagation();
        }).on('hide.bs.modal', function(e) {
            // Quitar el conflicto con bootstrap modal "hide.bs.modal"
            e.stopPropagation();
        });
    };
    $('#ModaFormAlum').on('show.bs.modal', function() {
        INIT_OBJE_MODA_ALUM();
    });
    $('#ModaFormAlum').on('hide.bs.modal', function() {
        $("#TablAlum").bootstrapTable('uncheckAll');
    });

    
    INIT_OBJE_ALUM();
});

function INIT_OBJE_ALUM()
{
    $("#TablAlum").initBootTable();
    INIT_OBJE_MODA_ALUM();
}

function INIT_OBJE_MODA_ALUM()
{
    $("#FormAlum\\:btonElim").confirmation({container: '#FormAlum'});
    $("#FormAlum\\:fechNaci").initDatePick();
    $("#FormAlum\\:gene").selectpicker();
}