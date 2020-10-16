var app =(function (){
    var genero="";
    var imagen = "";
    var publicacion ;
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

    function  crearPublicacion(){
        publicacion = $("#publicacion").val();
        apiclient.crearPublicacion(publicacion);
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
            console.log(pb.contenido);
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
        updateUni : updateUniInfo,
        crearPublicacion: crearPublicacion,
        cargarPublicaciones: function (){
            apiclient.obtenerPublicaciones(0,cargarPublicaciones);
        }
        
    }
})();