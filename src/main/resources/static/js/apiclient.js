apiclient = (function () {
        let url = "http://localhost:8080/socialacedemy/"
        return {
            actualizarUsuario: function (id, nombre, apellido, fecha, genero, ciudad, pais, descripcion) {
                console.log(id);
                console.log(apellido);
                console.log(fecha);
                console.log(genero);
                console.log(ciudad);
                console.log(pais);
                console.log(descripcion);
                console.log(url + id);
                var promise = $.ajax({
                    url: url + id,
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