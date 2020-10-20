login = (function () {
    function login (){
        correo = $("#email").val();
        password = $("#password").val();
        apiclient.obtenerTocken(correo, password,isLogin);

    }
    function isLogin(token){
        app.getToken(token);
    }
    return{
        login:login
    }
})();