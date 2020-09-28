var app =(function (){
    var genero=null;

    function setGender(g){
        genero=g;
    }
    function updateBasicInfo(){
        apiclient.actualizarUsuario(0,$('#nombre').val(),$('#apellido').val(),$('#nacimiento').val(),genero,$("#city").val(),$('#pais').val(),$("#my-info").val());
    }


    return {
        setGenero: function (genero) {
            setGender(genero);
        },

        actualizarInformacionBasicaUsuario: function () {
            updateBasicInfo();
        }
    }

})();