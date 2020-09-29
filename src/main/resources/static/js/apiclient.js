apiclient = (function () {
        let url = "http://localhost:8080/socialacedemy/"
        return {
            actualizarUsuario: function (id, nombre, apellido, fecha, genero, ciudad, pais, descripcion) {
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
            actualizarImagenUsuario(formData){

                console.log(url+"upload");
                window.alert("holi");
                var promise = $.ajax({
                    url: url+"upload",
                    type: 'POST',
                    body: formData,
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