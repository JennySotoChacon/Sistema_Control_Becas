$(document).ready(function() {
    $.fn.initFile = function() {
        $(this).filestyle({buttonName: "btn-primary", size: "sm", iconName: "fa fa-folder-open-o"});
    };
    INIT_OBJE_FILE();
});


initList = function()
    {
        $("#resultado li").on("click", function(){
        var textoFilaLista = $(this).text().trim();
        var res = textoFilaLista.split(" ---> ");
        if(res[1]== "folder")
        {

            consObje([{name : 'codiObjePara', value : res[0]}]);
        }
        else
        {
            if(res[1]== "jpg")
            {
                 
                 $('#imagepreview').attr('src', "/Sistema_Control_Becas/images/"+res[0]); // here asign the image to the modal when the user click the enlarge link
                 $('#imagemodal').modal('show'); // imagemodal is the id attribute assigned to the bootstrap modal, then i use the show function
                
               
            }
            else if(res[1]== "image/jpeg")
            {
                 
                 $('#imagepreview').attr('src', "/Sistema_Control_Becas/images/"+res[0]); // here asign the image to the modal when the user click the enlarge link
                 $('#imagemodal').modal('show'); // imagemodal is the id attribute assigned to the bootstrap modal, then i use the show function
                
               
            }
            else
            {
                console.log(res[1]);
                console.log('xd');
            }
        }

    });
    }
    
   
function INIT_OBJE_FILE()
{
    $(':file').initFile();
    $('#FormRegi\\:file').initFile();
    initList();
}