$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            var cadena =row.movimiento.trim();
            console.log("Esta es la cadena:"+cadena);
            var cut = cadena.split('>');
            var cut2 = cut[1].split('<');
            ///alert(cut2[0]);
            //alert(cut[2]);
            if(cut2[0]=='Salida')
            {
                console.log("id:"+row.id.trim());
              consObjeSali([{name : 'codiObjePara', value : row.id.trim()}]);
              $('#SaliForm').modal('show');

            }
            else
                {
                  consObje([{name : 'codiObjePara', value : row.id.trim()}]);
                 $('#ModaForm').modal('show');
            }

            /*var res = cadena.substring(10, 4);
            console.log(row.movimiento.trim());*/

        });
        return false;
    };
    $('#ModaForm').on('hidden.bs.modal', function () {
  $('.modal').modal('hide');
});
$('#SaliForm').on('hidden.bs.modal', function () {
  $('.modal').modal('hide');
});
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
    $('#SaliForm').on('show.bs.modal', function() {
        INIT_OBJE_MODA_SALI();
    });
    $('#SaliForm').on('hide.bs.modal', function() {
        $("#TablRegi").bootstrapTable('uncheckAll');
    });

    $('#ModaForm').on('show.bs.modal', function() {
        INIT_OBJE_MODA();
    });
    $('#ModaForm').on('hide.bs.modal', function() {
        $("#TablRegi").bootstrapTable('uncheckAll');
    });


    INIT_OBJE_SALI();
    INIT_OBJE();
});

function INIT_OBJE_SALI()
{
    $("#TablRegi").initBootTable();
    INIT_OBJE_MODA_SALI();
}

function INIT_OBJE_MODA_SALI()
{
        console.log("Inicialice salidas");
    $("#FormRegi1\\:btonElim1").confirmation({container: '#FormRegi1'});
    $("#FormRegi1\\:fech1").initDatePick();
}

function INIT_OBJE()
{
    $("#TablRegi").initBootTable();
    INIT_OBJE_MODA();
}

function INIT_OBJE_MODA()
{
    console.log("Inicialice entradas");
    $("#FormRegi\\:btonElim").confirmation({container: '#FormRegi'});
     $("#FormRegi\\:fech").initDatePick();
     
}
