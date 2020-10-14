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
        //faltan verificaciones de fecha de nacimiento menor a la fecha actual
        apiclient.actualizarUsuario(0,$('#nombre').val(),$('#apellido').val(),$('#nacimiento').val(),genero,$("#city").val(),$('#pais').val(),$("#my-info").val());
        if(imagen !== ""){
            console.log("entre en el condicional");
            var formData = new FormData();
            formData.append("imagenUsuario", imagen);
            formData.append("idUsuario",0);
            apiclient.actualizarImagenUsuario(formData);
        }
    }
    function updateWorkInfo(){
        // falta verificaciones de fecha inicio se mayor a fecha fin 
        apiclient.actualizarInformacionWork(0, $("#compañia").val(), $("#Cargo").val(), $("#desdeInicioTrabajo").val(), $("#desdeFinTrabajo").val(), $("#Ciudad").val(), $("#descriptionTrabajo").val());
    }
    function updateUniInfo(){
        // falta verificaciones de fecha inicio se mayor a fecha fin
        apiclient.actualizarInformacionUni(0, $("#school").val(), $("#desdeInicio").val(), $("#desdeFin").val(), $("#estudiosDescripcion").val())
    }
    function updateInterestsInfo(valor)  {
        console.log(JSON.stringify(valor));
        //app.actualizarInformacionInterese(id, valor);
    }

    return {
        setGenero: function (genero) {
            setGender(genero);
        },
        updateininterests : function (value){
            updateInterestsInfo(value);
        },
        actualizarInformacionBasicaUsuario: function () {
            updateBasicInfo();
        },
        setFile : function(){
            setFile();
        },
        updatework : updateWorkInfo,
        updateUni : updateUniInfo
        
    }
})();