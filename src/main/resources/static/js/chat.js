chat = (function (){
    var nombreAmigo = "";

    mensaje = $("#campoMensaje").val();
    var connectAndSubscribe = function (callback) {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
    
        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/buyticket.'+idchat, function (message) {
               var theObject=JSON.parse(message.body);
               callback();
    
            });
        });
    }
    function crearChat(){
        apiclient.crearChat(localStorage.getItem("Amigo"),localStorage.getItem("id"), localStorage.getItem("Authorization"), ubicarChat);
        

    }
    function cargarChats(){
        apiclient.obtenerChats(localStorage.getItem("id"), localStorage.getItem("Authorization"),pintarChats);
    }
    function pintarChats(info){
        console.log(info); 
        for(var i =0; i < info.length; i++){
            pintarChat(info[i])
        }
    }
    function pintarChat(chat){
        nombreAmigo = chat[0];
        chat = `<li class="active">
                    <a onclick="chat.ubicarChat(${chat[0]})" data-toggle="tab">
                        <div class="contact">
                            <img src="" alt="" class="profile-photo-sm pull-left"/>
                            <div class="msg-preview">
                                <h6>${chat[1]}" "${chat[2]}</h6>
                            </div>
                        </div>
                    </a>
                </li>`
        $("#chats").append(chat);
    }
    function ubicarChat (idChat){
        
        apiclient.obtenerMensajes(localStorage.getItem("id"), idChat, localStorage.getItem("Authorization"), pintarMensajes); 
       
                   
    }
    function pintarMensajes(info){
        for(var i =0; i < info.length; i++){
            if(info[i][2] == "derecha"){
                console.log("derecha")
                mensajeDer =    `<li class="right">
                                <img src="" alt="" class="profile-photo-sm pull-right" />
                                <div class="chat-item">
                                    <div class="chat-item-header">
                                        <h5>${nombreAmigo}</h5>
                                        <small class="text-muted">${info[i][0]}</small>
                                    </div>
                                    <p>${info[i][1]} <i class="em em-expressionless"></i></p>
                                <p><i class="em em-hand"></i></p>
                                </div>
                            </li>`
                $("#chat").append(mensajeDer);
            }else if (info[i][2]== "izquierda"){
                console.log("derecha")
                mensajeIzq =    `<li class="left">
                                    <img src="http://placehold.it/300x300" alt="" class="profile-photo-sm pull-left" />
                                    <div class="chat-item">
                                        <div class="chat-item-header">
                                            <h5>${localStorage.getItem("nombreUsuario")}</h5>
                                            <small class="text-muted">${info[i][0]}</small>
                                        </div>
                                        <p>${info[i][1]}<i class="em em-expressionless"></i></p>
                                    </div>
                                </li>`
                $("#chat").append(mensajeIzq);
            }
            
        }
    }
return{
    cargarChats:cargarChats,
    ubicarChat:ubicarChat
}
})();