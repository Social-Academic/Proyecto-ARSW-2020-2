apiclient = (function () {
    let url = "usuarios/"
    return {
        obtenerUsuarioCorreo(correo, token, callback){
            console.log(token);
            var promise = $.ajax({
                url: url+"correos/"+correo,
                type: 'GET',
                headers: {"Authorization" : "Bearer "+token},
                contentType: "application/json"
            });
            promise.then(function (data) {
                console.info("OK");
                console.log(data)
                callback(data);
            }, function (data) {
                console.info(data)
                console.info("Credenciales incorrectas");
            });
        },
        obtenerTocken(correo, newpassword, callback){
            var promise = $.ajax({
                url: "/oauth/token",
                type: 'POST',
                headers: {"Authorization" : "Basic U29jaWFsQWNhZGVteVdlYjpIb2xhMTIzNA==", "Content-Type" : "application/x-www-form-urlencoded"},
                data: {username: correo, password: newpassword, "grant_type": "password"},
                contentType: "application/x-www-form-urlencoded"
            });
            promise.then(function (data) {
                console.info("OK");
                localStorage.setItem("correo", correo);
                callback(data);
            }, function (data) {
                console.info(data)
                console.info("Credenciales incorrectas");
            });
        },
        actualizarUsuario: function (id, nombre, apellido, fecha, genero, ciudad, pais, descripcion, token, callback) {
            //console.log(JSON.stringify([nombre, apellido, fecha, genero, ciudad, pais, descripcion]));

            var promise = $.ajax({
                url: url + "informacionBasica/"+id,
                type: 'PUT',
                data: JSON.stringify([nombre, apellido, fecha, genero, ciudad, pais, descripcion]),
                headers: {"Authorization" : "Bearer "+token},
                contentType: "application/json"
            });
            promise.then(function () {
                console.info("OK");
                callback();
            }, function () {
                console.info("ERROR");
            });
        },
        actualizarInformacionWork(id, empresa, cargo, inicio, fin, ciudad, descripcion, token){
            //console.log(JSON.stringify([empresa, cargo, inicio, fin, ciudad, descripcion]))
            var promise = $.ajax({
                url: url + "informacionTrabajo/"+id,
                type: 'PUT',
                data: JSON.stringify([empresa, cargo, inicio, fin, ciudad, descripcion]),
                headers: {"Authorization" : "Bearer "+token},
                contentType: "application/json"
            });
            promise.then(function () {
                console.info("OK");
                //callback();
            }, function () {
                console.info("ERROR");
            });
        },
        actualizarInformacionUni(id, universidad, fechaInicio, fechaFin, descripcion, token){
            console.log(JSON.stringify([universidad, fechaInicio, fechaFin, descripcion]))
            var promise = $.ajax({
                url: url + "informacionUniversidad/"+id,
                type: 'PUT',
                data: JSON.stringify([universidad, fechaInicio, fechaFin, descripcion]),
                headers: {"Authorization" : "Bearer "+token},
                contentType: "application/json"
            });
            promise.then(function () {
                console.info("OK");
                //callback();
            }, function () {
                console.info("ERROR");
            });
        },
        actualizarInformacionInterese(id, interes, token){
            var promise = $.ajax({
                url: url + "intereses/"+id,
                type: 'PUT',
                data: JSON.stringify(interes),
                headers: {"Authorization" : "Bearer "+token},
                contentType: "application/json"
            });
            promise.then(function () {
                console.info("OK");
                //callback();
            }, function () {
                console.info("ERROR");
            });
        },
        actualizarPassword(id,oldpassword,newpassword){
            var promise = $.ajax({
                url: url + "intereses/"+id,
                type: 'PUT',
                data: JSON.stringify([oldpassword,newpassword]),
                headers: {"Authorization" : "Bearer "+token},
                contentType: "application/json"
            });
            promise.then(function () {
                console.info("OK");
                alert("contraseña cambiada")
                //callback();
            }, function () {
                alert("La contraseña antigua no coincide")
                console.info("ERROR");
            });
        },
        actualizarImagenUsuario(formData,token, callback){
            var promise = $.ajax({
                url: url+"upload",
                type: 'POST',
                body: formData,
                enctype : 'multipart/form-data',
                processData: false,
                contentType: false,
                headers: {"Authorization" : "Bearer "+token},
                mimeType: "multipart/form-data"

            });
            promise.then(function () {
                console.info("OK");
                callback();
            }, function () {
                console.info("ERROR");
            });
        },
        obtenerUsuario: function (id, callback,token) {
            jQuery.ajax({
                url: apiUrl + id,
                headers: {"Authorization" : "Bearer "+token},
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        },
        crearPublicacion: function (id, publicacion, token){
            var promise = $.ajax({
                url: url+id+"/publicaciones",
                method: "POST",
                timeout: 0,
                headers: {"Authorization" : "Bearer "+token, "Content-Type": "text/plain"},
                data: publicacion,

            });
            promise.then(function () {
                console.info("OK");
                //callback();
            }, function () {
                console.info("ERROR");
            });
        },
        obtenerPublicaciones: function (id, callback, token) {
            jQuery.ajax({
                url: url + id +"/publicaciones",
                headers: {"Authorization" : "Bearer "+token},
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        },
        crearUsuario: function (nombre, apellido, correo, password, fecha, genero, ciudad){
            var promise = $.ajax({
                url: "/usuarios",
                method: "POST",
                data: JSON.stringify([nombre, apellido, correo, password, fecha, genero, ciudad]),
                contentType: "application/json"
            });
            promise.then(function () {
                console.info("OK");
                //callback();
            }, function () {
                console.info("ERROR");
            });
        }

    }
})();