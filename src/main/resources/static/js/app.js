var app = (function () {
    var genero = "";
    var imagen = "";
    var publicacion;
    var token;
    var publicacionID;
    function setGender(g) {
        genero = g;
    }

    function setFile() {

        imagen = $("#imagenPerfil")[0].files[0];

    }

    function onload() {
        if (localStorage.getItem("Authorization") === null) {
            location.href = "/";
            return
        }
    }

    function onloadLogin() {
        if (localStorage.getItem("Authorization") !== null) {
            location.href = "/timeline.html";
            return
        }
    }

    function onloadUsuario() {
        apiclient.obtenerUsuarioCorreo(localStorage.getItem("correo"), localStorage.getItem("Authorization"), cargarInformacion);
    }

    function cargarInformacion(Usuario) {
        localStorage.setItem("id", Usuario.id);
        $("#nombreUsuario").text(Usuario.nombre + " " + Usuario.apellido);
        $("#nombreUsuarioRespons").text(Usuario.nombre + " " + Usuario.apellido);
        if (Usuario.cargo !== "") {
            $("#puesto").text(Usuario.cargo);
            $("#puestoRespons").text(Usuario.cargo);
        } else {
            $("#puesto").text("Estudiante");
            $("#puestoRespons").text("Estudiante");
        }
        localStorage.setItem("nombreUsuario", Usuario.nombre + " " + Usuario.apellido);

    }

    function getToken(newToken) {
        token = newToken.access_token;
        correo = localStorage.getItem("correo");
        localStorage.setItem("Authorization", token);
        //apiclient.obtenerUsuarioCorreo(correo, token);
        location.href = "/timeline.html";


    }

    function updatePassword() {
        oldPassword = $("#oldPassword").val();
        newPassword = $("#newPassword").val();
        confirmPassword = $("#confirmPassword").val();
        if (newPassword === confirmPassword) {
            apiclient.actualizarPassword(localStorage.getItem("id"), oldPassword, newPassword);
        } else {
            alert("la nueva contraseña no coincide");
        }
    }

    function updateBasicInfo() {
        //faltan verificaciones de fecha de nacimiento menor a la fecha actual
        apiclient.actualizarUsuario(localStorage.getItem("id"), $('#nombre').val(), $('#apellido').val(), $('#nacimiento').val(), genero, $("#city").val(), $('#pais').val(), $("#my-info").val(), localStorage.getItem("Authorization"), onloadUsuario);
        if (imagen !== "") {
            console.log("entre en el condicional");
            var formData = new FormData();
            formData.append("imagenUsuario", imagen);
            formData.append("idUsuario", 0);
            apiclient.actualizarImagenUsuario(formData, localStorage.getItem("Authorization"));
        }
        location.reload();
    }

    function updateWorkInfo() {
        // falta verificaciones de fecha inicio se mayor a fecha fin
        apiclient.actualizarInformacionWork(localStorage.getItem("id"), $("#compañia").val(), $("#Cargo").val(), $("#desdeInicioTrabajo").val(), $("#desdeFinTrabajo").val(), $("#Ciudad").val(), $("#descriptionTrabajo").val(), localStorage.getItem("Authorization"));

    }

    function updateUniInfo() {
        // falta verificaciones de fecha inicio se mayor a fecha fin
        apiclient.actualizarInformacionUni(localStorage.getItem("id"), $("#school").val(), $("#desdeInicio").val(), $("#desdeFin").val(), $("#estudiosDescripcion").val(), localStorage.getItem("Authorization"))
    }

    function updateInterestsInfo(valor) {
        console.log(JSON.stringify(valor));
        //app.actualizarInformacionInterese(id, valor);
    }

    function crearPublicacion() {
        publicacion = $("#publicacion").val();
        if (publicacion == null) {
            window.alert("hay nada");

        } else {
            apiclient.crearPublicacion(localStorage.getItem("id"), publicacion, localStorage.getItem("Authorization"));
            location.reload();
            cargarPublicaciones();
        }


    }

    function pintarPublicaciones(publi) {
        if (publi == null) {
            return new Error("No se encontro");
        }
        lista = [];

        var lista = publi.map(function (pb) {
            return {contenido: pb.contenido, idU: pb.idusuario, fecha: pb.fechaPublicacion, idP: pb.id}
        })
        var nombre = localStorage.getItem("nombreUsuario")
        lista.map(function (pb) {
            var div = `<div class=\"post-content\">
                                <div class="post-date hidden-xs hidden-sm">
                                  <h5>${nombre}</h5>
                                  <p class="text-grey" id="fecha${pb.idP}"> ${pb.fecha} </p>
                                </div><!--Post Date End--> 
                
                                <div class="post-container"> 
                                  <img src="images/ecdbe23f-5164-480d-affe-02f751f1c8ff_WhatsApp%20Image%202019-06-21%20at%203.51.09%20PM.jpeg" alt="user" class="profile-photo-md pull-left" />
                                  <div class="post-detail">
                                    <div class="user-info">
                                      <h5><a href="timeline.html" class="profile-link" id="usuario${pb.idU}">${nombre}</a> </h5>
                                    </div>
                                    <div class="reaction">
                                      <a class="btn text-green"  onclick="app.agregarReacion(1,${pb.idP}, ${pb.idU});app.obtenerReaccionesBien(${pb.idU}, ${pb.idP})" ><i class="icon ion-thumbsup" id="positivos${pb.idP}" ></i></a>
                                      <a class="btn text-red" onclick="app.agregarReacion(0,${pb.idP}, ${pb.idU});app.obtenerReaccionesMal(${pb.idU}, ${pb.idP})" ><i class="fa fa-thumbs-down" id="negativos${pb.idP}"></i></a>
                                    </div>
                                    <div class="line-divider"></div>
                                    <div class="post-text">
                                      <p id="contenido${pb.idP}">  ${pb.contenido}  </p>
                                    </div>
                                    <div class="line-divider"></div>
                                    
                                    <div id="comentarios${pb.idP}"></div>
                                    
                                    <div class="line-divider"></div>
                                    <div class="post-comment">
                                      <img src="images/ecdbe23f-5164-480d-affe-02f751f1c8ff_WhatsApp%20Image%202019-06-21%20at%203.51.09%20PM.jpeg" alt="" class="profile-photo-sm" />
                                      <input type="text" id="comentario${pb.idP}" class="form-control" placeholder="Post a comment">
                                      <br> <button type="button" class="boton_personalizado" onclick=app.crearComentario(${pb.idP},document.getElementById("comentario${pb.idP}").value,${pb.idU})>comentar</button>
                                   
                                    </div>

                                  </div>
                                </div>
                              </div>
                
                            </div>`
            $("#divPublicaciones").append(div);
            publicacionID = pb.idP;
        })
    }

    function pintarComentarios(publi) {
        if (publi == null) {
            return new Error("No se encontro");
        }
        lista = [];
        var lista = publi.map(function (pb) {
            return {contenido: pb.contenido}
        });
        var nombre = localStorage.getItem("nombreUsuario");
        var ruta = "#comentarios"+publicacionID;
        $(ruta).empty();
        lista.map(function (pb) {
            var div = `<div class="post-comment">
                       <img src="http://placehold.it/300x300" alt="" class="profile-photo-sm" />
                       <p><a href="timeline.html" class="profile-link"> ${nombre} </a> ${pb.contenido} </p>
                       </div>`
            $(ruta).append(div);
        })
    }

    function cargarPublicaciones() {
        apiclient.obtenerPublicaciones(localStorage.getItem("id"), pintarPublicaciones, localStorage.getItem("Authorization"));
        apiclient.obtenerPublicaciones(localStorage.getItem("id"),pintarCometarioalIniciar, localStorage.getItem("Authorization"));
    }

    function crearReaccion(reaccion, idpubli, idU) {
        apiclient.crearReacion(localStorage.getItem("id"), reaccion, idpubli, localStorage.getItem("Authorization"));
    }

    function obtenerReaccionesBien(idUsuario, idPublicacion) {
        apiclient.obtenerReaccionesBien(idUsuario, idPublicacion, localStorage.getItem("Authorization"), dibujarBien);
    }

    function obtenerReaccionesMal(idUsuario, idPublicacion) {
        apiclient.obtenerReaccionesMal(idUsuario, idPublicacion, localStorage.getItem("Authorization"), dibujarMal);
    }

    function dibujarBien(dato, idPublicacion) {
        document.getElementById("positivos".concat(idPublicacion)).innerHTML = dato.toString();
    }

    function dibujarMal(dato, idPublicacion) {
        document.getElementById("negativos".concat(idPublicacion)).innerHTML = dato.toString();
    }
    function crearComentario(idPublicacion, comentario,idUpublicacion){
        apiclient.crearComentario(localStorage.getItem("id"),idPublicacion, comentario,idUpublicacion,localStorage.getItem("Authorization"),cargarComentario);
    }

    function cargarComentario(idP,idUPublicacion){

        apiclient.obtenerComentario(idUPublicacion,idP, pintarComentarios, localStorage.getItem("Authorization"))
    }


    function pintarCometarioalIniciar(publi){
        if (publi == null) {
            return new Error("No se encontro");
        }
        lista = [];
        var lista = publi.map(function (pb) {
            return {idU: pb.idusuario, idP: pb.id}
        })
        lista.map(function (pb) {
            publicacionID = pb.idP;
            cargarComentario(pb.idP,pb.idU);
        })

    }
    function obtenerUsuario(){
        apiclient.obtenerUsuario(localStorage.getItem("id"),pintarInfo,localStorage.getItem("Authorization"));
    }
    function pintarInfo(usuario){
        if (usuario == null) {
            return new Error("No se encontro");
        }

        app.getWeatherByName(usuario.ciudad);
        $("#infoPersonal").text(usuario.descripcion);
        $("#empresa").text(usuario.empresa);
        $("#cargo").text(usuario.cargo);
        $("#descripcionTrabajo").text(usuario.descripcionTrabajo);
        $("#fechas").text(usuario.inicioTrabajo);
        $("#universidad").text(usuario.universidad);
        $("#carrera").text(usuario.carrera);
        $("#fechasU").text(usuario.inicioUniversidad);
        $("#descripcionUni").text(usuario.descripcionUniversiad);
        $("#fechaNacimiento").text(usuario.fecha);
        $("#ubicacion").text(usuario.ciudad);


    }
    function initMap(mapa) {
        console.log(mapa);

        lati = mapa.coord.lat;
        longi = mapa.coord.lon;

        const uluru = {lat: lati, lng: longi};
        const map = new google.maps.Map(document.getElementById("map"), {
            zoom: 10,
            center: uluru,
        });
        const marker = new google.maps.Marker({
            position: uluru,
            map: map,
        });
    }

    return {
        updatePassword: updatePassword,
        onloadUsuario: onloadUsuario,
        onloadLogin: onloadLogin,
        cargarPublicaciones: cargarPublicaciones,
        onload: onload,
        getToken: getToken,
        setGenero: function (genero) {
            setGender(genero);
        },
        updateininterests: function (value) {
            updateInterestsInfo(value);
        },
        actualizarInformacionBasicaUsuario: function () {
            updateBasicInfo();
        },
        setFile: function () {
            setFile();
        },
        updatework: updateWorkInfo,
        updateUni: updateUniInfo,
        crearPublicacion: crearPublicacion,
        crearAlInciar: cargarPublicaciones,
        agregarReacion(reaccion, idpubli, idU) {
            crearReaccion(reaccion, idpubli);
        },

        obtenerReaccionesBien(idUsuario, idPublicacion) {
            obtenerReaccionesBien(idUsuario, idPublicacion);
        },
        obtenerReaccionesMal(idUsuario, idPublicacion) {
            obtenerReaccionesMal(idUsuario, idPublicacion);
        },
        crearComentario(idPublicacion,comentario, idUsuarioPublicacion) {
            publicacionID = idPublicacion;
            crearComentario(idPublicacion,comentario,idUsuarioPublicacion);
        },
        obtenerInfoUsuario: obtenerUsuario,
        getWeatherByName: function getDatos(nombre) {
            apiclient.getWeatherByName(nombre, initMap );
        }

    }
})();
