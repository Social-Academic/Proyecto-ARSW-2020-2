login = (function () {
    function login (){
        correo = $("#email").val();
        password = $("#password").val();
        apiclient.obtenerTocken(correo, password,isLogin);

    }
    function cerrarSesion(){
        localStorage.clear();
        location.reload();
    }

    function isLogin(token){
        app.getToken(token);
    }
    return{
        cerrarSesion:cerrarSesion,
        login:login
    }
})();