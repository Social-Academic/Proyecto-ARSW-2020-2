chat = (function (){
    var nombreAmigo = "";
    var numeroChat;
    var connectAndSubscribe = function (callback) {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
    
        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/enviarMensaje.'+numeroChat, function (message) {
                console.log(message.body);
               var theObject=JSON.parse(message.body);
               if(theObject[0][3] === localStorage.getItem("id")){
                   theObject[0][2] = "izquierda";
               }
               callback(theObject);
            });
        });
    }

    
    function getMensaje(){
        mensaje = $("#campoMensaje").val();
    }

    function enviarMensaje(mensaje){
        fechaCompleta = new Date();
        fecha = fechaCompleta.getDate()+"/"+(fechaCompleta.getMonth()+1)+"/"+fechaCompleta.getFullYear()+" "+fechaCompleta.getHours()+":"+fechaCompleta.getMinutes();
        datos = [[fecha, mensaje, "izquierda"]];
        stompClient.send('/app/enviarMensaje.'+numeroChat, {}, JSON.stringify({mensaje: mensaje, propietario: localStorage.getItem("id"), idChat: numeroChat}));
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
        nombreAmigo = chat[1]+" "+chat[2];
        chat = `<li class="active">
                    <a onclick="chat.ubicarChat(${chat[0]})" data-toggle="tab">
                        <div class="contact">
                            <img src="images/ecdbe23f-5164-480d-affe-02f751f1c8ff_WhatsApp%20Image%202019-06-21%20at%203.51.09%20PM.jpeg" alt="" class="profile-photo-sm pull-left"/>
                            <div class="msg-preview">
                                <h6>${chat[1]}" "${chat[2]}</h6>
                            </div>
                        </div>
                    </a>
                </li>`
        $("#chats").append(chat);
    }

    function ubicarChat (idChat){
        numeroChat = idChat;
        connectAndSubscribe(pintarMensajes);
        apiclient.obtenerMensajes(localStorage.getItem("id"), idChat, localStorage.getItem("Authorization"), pintarMensajes);            
    }

    function pintarMensajes(info){
        for(var i =0; i < info.length; i++){
            if(info[i][2] == "derecha"){
                mensajeDer =    `<li class="right">
                                <img src="images/ecdbe23f-5164-480d-affe-02f751f1c8ff_WhatsApp%20Image%202019-06-21%20at%203.51.09%20PM.jpeg" alt="" class="profile-photo-sm pull-right" />
                                <div class="chat-item">
                                    <div class="chat-item-header">
                                        <h5>${nombreAmigo}</h5>
                                        <small class="text-muted">${info[i][0]}</small>
                                    </div>
                                    <p>${info[i][1]}</p>
                                </div>
                            </li>`
                $("#chat").append(mensajeDer);
            }else if (info[i][2]== "izquierda"){
                mensajeIzq =    `<li class="left">
                                    <img src="images/ecdbe23f-5164-480d-affe-02f751f1c8ff_WhatsApp%20Image%202019-06-21%20at%203.51.09%20PM.jpeg" alt="" class="profile-photo-sm pull-left" />
                                    <div class="chat-item">
                                        <div class="chat-item-header">
                                            <h5>${localStorage.getItem("nombreUsuario")}</h5>
                                            <small class="text-muted">${info[i][0]}</small>
                                        </div>
                                        <p>${info[i][1]}</p>
                                    </div>
                                </li>`
                $("#chat").append(mensajeIzq);
            }
            
        }
    }
return{
    cargarChats:cargarChats,
    ubicarChat:ubicarChat,
    getMensaje:getMensaje,
    enviarMensaje:function(info){
        enviarMensaje(info);
    } 
}
})();