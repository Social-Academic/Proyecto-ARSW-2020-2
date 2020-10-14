apiclient = (function () {
        let url = "usuarios/"
        return {
            actualizarUsuario: function (id, nombre, apellido, fecha, genero, ciudad, pais, descripcion) {
                console.log(JSON.stringify([nombre, apellido, fecha, genero, ciudad, pais, descripcion]));
                var promise = $.ajax({
                    url: url + "informacionBasica/"+id,
                    type: 'PUT',
                    data: JSON.stringify([nombre, apellido, fecha, genero, ciudad, pais, descripcion]),
                    contentType: "application/json"
                });
                promise.then(function () {
                    console.info("OK");
                    //callback();
                }, function () {
                    console.info("ERROR");
                });
            },
            actualizarInformacionWork(id, empresa, cargo, inicio, fin, ciudad, descripcion){
                //console.log(JSON.stringify([empresa, cargo, inicio, fin, ciudad, descripcion]))
                var promise = $.ajax({
                    url: url + "informacionTrabajo/"+id,
                    type: 'PUT',
                    data: JSON.stringify([empresa, cargo, inicio, fin, ciudad, descripcion]),
                    contentType: "application/json"
                });
                promise.then(function () {
                    console.info("OK");
                    //callback();
                }, function () {
                    console.info("ERROR");
                });
            },
            actualizarInformacionUni(id, universidad, fechaInicio, fechaFin, descripcion){
                console.log(JSON.stringify([universidad, fechaInicio, fechaFin, descripcion]))
                var promise = $.ajax({
                    url: url + "informacionUniversidad/"+id,
                    type: 'PUT',
                    data: JSON.stringify([universidad, fechaInicio, fechaFin, descripcion]),
                    contentType: "application/json"
                });
                promise.then(function () {
                    console.info("OK");
                    //callback();
                }, function () {
                    console.info("ERROR");
                });
            },
            actualizarInformacionInterese(id, interes){
                var promise = $.ajax({
                    url: url + "intereses/"+id,
                    type: 'PUT',
                    data: JSON.stringify(interes),
                    contentType: "application/json"
                });
                promise.then(function () {
                    console.info("OK");
                    //callback();
                }, function () {
                    console.info("ERROR");
                });
            },
            actualizarImagenUsuario(formData){

                var promise = $.ajax({
                    url: url+"upload",
                    type: 'POST',
                    body: formData,
                    enctype : 'multipart/form-data', 
                    processData: false,
                    contentType: false,
                    mimeType: "multipart/form-data"

                });
                promise.then(function () {
                    console.info("OK");
                    //callback();
                }, function () {
                    console.info("ERROR");
                });
            },
            obtenerUsuario: function (id, callback) {
                jQuery.ajax({
                    url: apiUrl + id,
                    success: function (result) {
                        callback(result);
                    },
                    async: true
                });
            }

        }
})();