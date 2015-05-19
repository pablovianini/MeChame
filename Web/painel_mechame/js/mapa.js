/*------------------------------------------------------------------------------------------
            String do formulário                                                             */
			
var strForm = '<form name="frmMeChame" action="#" method="post">' +
            '<p>' +
                '<label for="txtTitulo">T�tulo:</label>' +
                   '<input type="text" id="txtTitulo">' +
            '</p>' +
            
            '<p>' +
                '<label for="txtDistancia">Dist�ncia(m):</label>' +
                    '<input type="text" id="txtDistancia">' +
            '<p>' +
                '<input type="button" onclick="registraMeChame();" value="Salvar">' +
            '</p>' +
            
        '</form>';

/*------------------------------------------------------------------------------------------
            Variáveis globais                                                                */
        
var map;
var marker;
var infoWindow;
var geocoder;

/*------------------------------------------------------------------------------------------
            Função que instância o mapa                                                      */

function initialize() { 
    var latlng = new google.maps.LatLng(-23.5295767, -46.6659365);

    var options = {
        zoom: 18,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        scrollwheel: false,
        disableDefaultUI: true     
    };
        map = new google.maps.Map(document.getElementById("map_canvas") , options);
        
        geocoder = new google.maps.Geocoder();
        
        google.maps.event.addListener(map, 'click', function(event)    {
            
            
            /*Declara as variáveis que vão receber os valores de latitude e longitude
             * após clicar em um determinado ponto do mapa, elas serão usadas 
             * para passar essas informações para o ponteiro*/
            var latitude = event.latLng.lat().toFixed(6);
            var longitude = event.latLng.lng().toFixed(6);
            
            /*Chama a função de criação do ponteiro no mapa, que recebe os valores de latitude
             * e longitude que foram pegos nas variáveis acima*/
            criaPonteiro(latitude, longitude);
            
            
            /*Chama a função que irá pegar os valroes de latitude e longitude do ponto clicado no mapa, e que depois 
             * será passo para o ponteiro*/
            pegaCoordenadas(latitude, longitude);

            
     });
 }
 
 google.maps.event.addDomListener(window, 'load', initialize);
 
 /*------------------------------------------------------------------------------------------
            Função que cria o ponteiro no mapa                                                */

function criaPonteiro(latitude, longitude){
    marker = new google.maps.Marker({
        position: new google.maps.LatLng(latitude, longitude),
        map: map,
        title: 'Clique para editar',
        draggable: true
  });
    
    infoWindow = new google.maps.InfoWindow({
        content: strForm
    });
    
    google.maps.event.addListener(marker, 'click', function() {
        infoWindow.open(map, marker);
    });
    
    google.maps.event.addListener(marker, 'dragend', function() {
        
    marker.position = marker.getPosition();
    
    var latitude = marker.position.lat().toFixed(6);
    var longitude = marker.position.lng().toFixed(6);
    
    
    
    pegaCoordenadas(latitude, longitude);
    
    });
}

/*------------------------------------------------------------------------------------------
            Função que pega as coordenadas do ponteiro inserido no mapa                      */

function pegaCoordenadas(latitude, longitude) {
   // Referência ao elemento HTML (input) com o id 'lat'
   var coords_lat = document.getElementById('latitude');

   // Actualiza o valor do input 'lat'
   coords_lat.value = latitude;

   // Referência ao elemento HTML (input) com o id 'lng'
   var coords_lng = document.getElementById('longitude');

   // Actualiza o valor do input 'lng'
   coords_lng.value = longitude;
}

/*------------------------------------------------------------------------------------------
            Função que abre a InfoWindow do ponteiro                                         */

function abrirInfoWindows(map, marker){
    infoWindow.open(map,marker);
}