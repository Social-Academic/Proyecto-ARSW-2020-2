amigos = (function () {
    var idAmigo; 
    function pintarAmigos (info){
        console.log(info);
        for(var i =0; i < info.length; i++){
            amigo = `<div class="col-md-6 col-sm-6">
                        <div class="friend-card">
                            <div class="card-info">
                                <div class="friend-info">
                                    <a href="newsfeed-messages.html" class="pull-right text-green" onclick="amigos.getId(${info[i][3]})">Enviar mensaje</a>
                                    <h5><a href="timeline.html" class="profile-link">${info[i][0]+" "+info[i][1]}</a></h5>
                                    <p>${info[i][2]}</p>
                                    </div>
                            </div>
                        </div>
                    </div>`
            $("#Amistades").append(amigo);
        }
        

    }
    function cargarAmigos(){
        apiclient.cargarAmistades(localStorage.getItem("id"), localStorage.getItem("Authorization"), pintarAmigos);
    }
    function getId(numero){
        console.log(numero);
        localStorage.setItem("Amigo",numero);
        chat.crearChat();
    }
    return{
        cargarAmigos:cargarAmigos,
        getId:getId
    }
})();