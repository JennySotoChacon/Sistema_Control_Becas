(function( $ ){
   $.fn.sideBar = function(e) {
       $.fn.destroy = function(e) {
           $bar = $();
       };
       $bar = this;
       /*METODOS DE CIERRE Y APERTURA*/
        $.fn.cerrarL1 = function(e) {
            var lastActive = $(this).closest($bar).find('a.item1.active');
            lastActive.removeClass("active");
            lastActive.next('div').collapse('hide');
        };
        $.fn.cerrarL2 = function(e) {
            var lastActive = $(this).closest($bar).find('a.item2.active');
            lastActive.removeClass("active");
            lastActive.next('div').collapse('hide');
        };
        $.fn.cerrarL3 = function(e) {
            var lastActive = $(this).closest($bar).find('a.item3.active');
            lastActive.removeClass("active");
            lastActive.next('div').collapse('hide');
        };
        $.fn.lleno = function(e) {
            $(this).children("span.total").removeClass("badge-danger");
            $(this).children("span.total").addClass("badge-success");
        };
        $.fn.vacio = function(e) {
            $(this).children("span.total").addClass("badge-danger");
            $(this).children("span.total").removeClass("badge-success");
        };
        $bar.find('span.total').on('click', function(e) {
            e.preventDefault();
            //return false;
        });
       /*PARA LOS MENU NIVEL 1*/
       var total = 0;
       var id = "";
        $bar.find('a.item1').each(function(index) {
            id = $(this).attr("href");
            total = $(this).parent().children(id).find("a.item2").length;
            $(this).children("span.total").html(total);
            if(total === 0)
            {
                $(this).vacio();
                $(this).parent().children(id).remove();
            }
            else
            {
                $(this).lleno();
            }
            id = "";
            total = 0;
        })
        .on('click', function(e) {
            e.preventDefault();
            var clss = $(this).children("span.total").css( "background-color" );
            if (!$(this).hasClass("active")) {
                $(this).cerrarL3();
                $(this).cerrarL2();
                $(this).cerrarL1();
                $(this).addClass("active");
                $(this).next('div').collapse('show');
                $(this).children("span.total").css("background-color", clss);
            }
            else
            {
                $(this).cerrarL3();
                $(this).cerrarL2();
                $(this).cerrarL1();
            }
        });
        
        /*PARA LOS MENU NIVEL 2*/
       var total = 0;
       var id = "";
        $bar.find('a.item2').each(function(index) {
            id = $(this).attr("href");
            total = $(this).parent().children(id).find("a.item3").length;
            $(this).children("span.total").html(total);
            if(total === 0)
            {
                $(this).vacio();
                $(this).parent().children(id).remove();
            }
            else
            {
                $(this).lleno();
            }
            id = "";
            total = 0;
        })
        .on('click', function(e) {
            e.preventDefault();
            var clss = $(this).children("span.total").css( "background-color" );
            if (!$(this).hasClass("active")) {
                $(this).cerrarL3();
                $(this).cerrarL2();
                $(this).addClass("active");
                $(this).next('div').collapse('show');
                $(this).children("span.total").css("background-color", clss);
            }
            else
            {
                $(this).cerrarL3();
                $(this).cerrarL2();
            }
        });
        
        /*PARA LOS MENU NIVEL 3*/
       var total = 0;
       var id = "";
        $bar.find('a.item3').each(function(index) {
//            id = $(this).attr("href");
//            total = $(this).parent().children(id).find("a.item3").length;
//            $(this).children("span").html(total);
//            if(total === 0)
//            {
//                $(this).parent().children(id).remove();
//            }
//            id = "";
//            total = 0;
        })
        .on('click', function(e) {
            e.preventDefault();
            if (!$(this).hasClass("active")) {
                $(this).cerrarL3();
                $(this).addClass("active");
                $(this).next('div').collapse('show');
            }
            else
            {
                $(this).cerrarL3();
            }
        });
        return this;
   }; 
})( jQuery );