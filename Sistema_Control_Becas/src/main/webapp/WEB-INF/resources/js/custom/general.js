/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consObje([{name : 'codiObjePara', value : row.id.trim()}]);
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
    $('#ModaForm').on('show.bs.modal', function() {
        INIT_OBJE_MODA();
    });
    $('#ModaForm').on('hide.bs.modal', function() {
        $("#TablRegi").bootstrapTable('uncheckAll');
    });
     

    
    INIT_OBJE();
});

function INIT_OBJE()
{
    $("#TablRegi").initBootTable();
    $("#TablRegiHist").initBootTable();
    INIT_OBJE_MODA();
}

function INIT_OBJE_MODA()
{
    $("#FormRegi\\:btonElim").confirmation({container: '#FormRegi'});
     $("#FormRegi\\:fech").initDatePick();
     $("#FormRegi\\:fech2").initDatePick();
     
     
         
      
}