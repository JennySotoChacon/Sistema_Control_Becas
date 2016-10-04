$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consTipoEsta([{name : 'codiTipoEstaPara', value : row.id.trim()}]);
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
    $('#ModaFormTipoEsta').on('show.bs.modal', function() {
        INIT_OBJE_MODA_TIPO_ESTA();
    });
    $('#ModaFormTipoEsta').on('hide.bs.modal', function() {
        $("#TablTipoEsta").bootstrapTable('uncheckAll');
    });

    
    INIT_OBJE_TIPO_ESTA();
});

function INIT_OBJE_TIPO_ESTA()
{
    $("#TablTipoEsta").initBootTable();
    INIT_OBJE_MODA_TIPO_ESTA();
}

function INIT_OBJE_MODA_TIPO_ESTA()
{
    $("#FormTipoEsta\\:btonElim").confirmation({container: '#FormTipoEsta'});
}