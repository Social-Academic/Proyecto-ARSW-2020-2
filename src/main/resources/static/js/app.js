var app =(function (){
    var genero="";
    var imagen = "";
    function setGender(g){
        genero=g;
    }
    function setFile(){

        imagen = $("#imagenPerfil")[0].files[0];

    }
    function updateBasicInfo(){
        console.log("llego");
        apiclient.actualizarUsuario(0,$('#nombre').val(),$('#apellido').val(),$('#nacimiento').val(),genero,$("#city").val(),$('#pais').val(),$("#my-info").val());
        if(imagen !== ""){
            console.log("entre en el condicional");
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
            setFile();
        }
    }
})();