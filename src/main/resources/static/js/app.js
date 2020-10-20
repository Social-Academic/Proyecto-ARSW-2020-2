var app =(function (){
    var genero="";
    var imagen = "";
    var publicacion ;
    var token;
    function setGender(g){
        genero=g;
    }
    function setFile(){

        imagen = $("#imagenPerfil")[0].files[0];

    }
    function onload(){
        if(localStorage.getItem("Authorization") === null){
            location.href = "/" ;
            return
        }
    }
    function onloadLogin(){
        if(localStorage.getItem("Authorization") !== null){
            location.href = "/timeline.html" ;
            return
        }
    }
    function onloadUsuario(){
        apiclient.obtenerUsuarioCorreo(localStorage.getItem("correo"), localStorage.getItem("Authorization"), cargarInformacion);
    }
    function cargarInformacion(Usuario){ 
        localStorage.setItem("id", Usuario.id);
        $("#nombreUsuario").text(Usuario.nombre+" "+Usuario.apellido);
        if(Usuario.cargo !== ""){
            $("#puesto").text(Usuario.cargo);
        }else{
            $("#puesto").text(""); 
        }
    }
    function getToken(newToken){
        token = newToken.access_token;
        correo = localStorage.getItem("correo"); 
        localStorage.setItem("Authorization",token);
        //apiclient.obtenerUsuarioCorreo(correo, token);
        location.href = "/timeline.html"; 
        
        
    }
    function updatePassword(){
        oldPassword = $("#oldPassword").val();
        newPassword = $("#newPassword").val();
        confirmPassword = $("#confirmPassword").val();
        if(newPassword === confirmPassword){
            apiclient.actualizarPassword(localStorage.getItem("id"), oldPassword, newPassword);
        }else{
            alert("la nueva contraseña no coincide");
        }
    }
    function updateBasicInfo(){
        //faltan verificaciones de fecha de nacimiento menor a la fecha actual
        apiclient.actualizarUsuario(localStorage.getItem("id"),$('#nombre').val(),$('#apellido').val(),$('#nacimiento').val(),genero,$("#city").val(),$('#pais').val(),$("#my-info").val(), localStorage.getItem("Authorization"),onloadUsuario);
        if(imagen !== ""){
            console.log("entre en el condicional");
            var formData = new FormData();
            formData.append("imagenUsuario", imagen);
            formData.append("idUsuario",0);
            apiclient.actualizarImagenUsuario(formData, localStorage.getItem("Authorization"));
        }
        location.reload(); 
    }
    function updateWorkInfo(){
        // falta verificaciones de fecha inicio se mayor a fecha fin 
        apiclient.actualizarInformacionWork(localStorage.getItem("id"), $("#compañia").val(), $("#Cargo").val(), $("#desdeInicioTrabajo").val(), $("#desdeFinTrabajo").val(), $("#Ciudad").val(), $("#descriptionTrabajo").val(),localStorage.getItem("Authorization"));

    }
    function updateUniInfo(){
        // falta verificaciones de fecha inicio se mayor a fecha fin
        apiclient.actualizarInformacionUni(localStorage.getItem("id"), $("#school").val(), $("#desdeInicio").val(), $("#desdeFin").val(), $("#estudiosDescripcion").val(),localStorage.getItem("Authorization"))
    }
    function updateInterestsInfo(valor)  {
        console.log(JSON.stringify(valor));
        //app.actualizarInformacionInterese(id, valor);
    }

    function  crearPublicacion(){
        publicacion = $("#publicacion").val();
        apiclient.crearPublicacion(crearPublicacion, publicacion, localStorage.getItem("Authorization"));
        location.reload();
        app.cargarPublicaciones();

    }
    function cargarPublicaciones(publi){
        if (publi == null){
            return new Error("No se encontro");
        }
        lista = [];
        var lista  = publi.map(function(pb){
            return {contenido:pb.contenido, idU:pb.idusuario, fecha:pb.fechaPublicacion, idP:pb.id}
        })
        lista.map(function(pb){
            var div = `<div class=\"post-content\">
                                <div class="post-date hidden-xs hidden-sm">
                                  <h5>usuario</h5>
                                  <p class="text-grey" id="fecha"+${pb.idP}> ${pb.fecha} </p>
                                </div><!--Post Date End--> 
                
                                <div class="post-container"> 
                                  <img src="http://placehold.it/300x300" alt="user" class="profile-photo-md pull-left" />
                                  <div class="post-detail">
                                    <div class="user-info">
                                      <h5><a href="timeline.html" class="profile-link" id="usuario"+${pb.idU}>usuario</a> </h5>
                                    </div>
                                    <div class="reaction">
                                      <a class="btn text-green"><i class="icon ion-thumbsup"></i> 0</a>
                                      <a class="btn text-red"><i class="fa fa-thumbs-down"></i> 0</a>
                                    </div>
                                    <div class="line-divider"></div>
                                    <div class="post-text">
                                      <p id="contenido"+${pb.idP}>  ${pb.contenido}  </p>
                                    </div>
                                    <div class="line-divider"></div>
                
                                    <div class="post-comment">
                                      <img src="http://placehold.it/300x300" alt="" class="profile-photo-sm" />
                                      <input type="text" class="form-control" placeholder="Post a comment">
                                    </div>
                                  </div>
                                </div>
                              </div>
                
                            </div>`
            $("#divPublicaciones").append(div);
        })

    }
    function cargarPublicaciones(){
        
        apiclient.obtenerPublicaciones(localStorage.getItem("id"),cargarPublicaciones,localStorage.getItem("Authorization"));
    }
    function cerrarSesion(){
        localStorage.clear();
        location.reload(); 
    }

    return {
        updatePassword:updatePassword,
        onloadUsuario:onloadUsuario,
        onloadLogin:onloadLogin,
        cargarPublicaciones:cargarPublicaciones,
        onload: onload, 
        getToken:getToken,
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
        updateUni : updateUniInfo,
        crearPublicacion: crearPublicacion,
        cerrarSesion:cerrarSesion
        
    }
})();