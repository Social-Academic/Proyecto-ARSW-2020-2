const apiUrl = "http://localhost:8080/socialacademy/"
apiclient = (function () {
        let url = "http://localhost:8080/socialacademy/"
        return {
            actualizarUsuario: function (id, nombre, apellido, fecha, genero, ciudad, pais, descripcion) {
                var promise = $.ajax({
                    url: "/socialacademy" + id,
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