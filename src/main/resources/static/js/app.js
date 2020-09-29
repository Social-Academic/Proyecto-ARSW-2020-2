var app =(function (){
    var genero="";
    var imagen = "";
    function setGender(g){
        genero=g;
    }
    function setFile(file){
        imagen = $("#imagenPerfi").val(); 
    }
    function updateBasicInfo(){
        console.log("llego");
        apiclient.actualizarUsuario(0,$('#nombre').val(),$('#apellido').val(),$('#nacimiento').val(),genero,$("#city").val(),$('#pais').val(),$("#my-info").val());
        if(imagen !== ""){
            var formData = new FormData();
            formData.append("imagenUsuario", imagen);
            formData.append("idUsuario",0);
            apiclient.actualizarImagenUsuario(formData);
        }
    }


    return {
        setGenero: function (genero) {
            setGender(genero);
        },
        actualizarInformacionBasicaUsuario: function () {
            updateBasicInfo();
        },
        setFile : function(){
            setFile()
        }
    }
})();