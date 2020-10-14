verificaciones = (function (){
    function fechas( ){
        var fechaIni= $("#desdeInicioTrabajo").val().split("-");
        var anoInicio =  fechaIni[0];
        var mesInicio = fechaIni[1];
        var diaInicio = fechaIni[2];
        var fechaFin = $("#desdeFinTrabajo").val().split("-");
        var anoFin = fechaFin[0];
        var mesFin = fechaFin[1];
        var diaFin = fechaFin[2];
        var fecha1 = new Date(anoInicio, mesInicio, diaInicio);
        var fecha2 = new Date(anoFin, mesFin, diaFin);
    }
})();