package com.mapa.mechame.mechame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,LocationListener{

    private GoogleMap mMap;

    double latitude;
    double longitude;
    double latitudeInicio;
    double longitudeInicio;
    private AlertDialog alerta;
    private GoogleApiClient mClient;
    private LocationRequest mLocationRequest;
    Marker marcadorUsuario;
    LatLng novoLocal;
    String buscarLocal;
    SearchView txtLocal;
    ListView listaLembrete;
    Lembrete lembreteSelecionado ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);
        //Criando o Cliente se Localização do Google
        conectarClienteLocalizacao();


        ImageButton btnLembretes = (ImageButton) findViewById(R.id.btnLembretes);
        ImageButton btnConfig = (ImageButton) findViewById(R.id.btnConfig);
        ImageView btnLocalAtual = (ImageView) findViewById(R.id.btnBuscaLocal);

        //Cria o campo pesquisa e atribui a ele o método se Query Listener !
        txtLocal = (SearchView) findViewById(R.id.txtBuscaLocal);
        txtLocal.onActionViewExpanded();
        txtLocal.setOnQueryTextListener(new SearchFiltro());

        //Caso o mapa não esteja correto
        setUpMapIfNeeded();

        //Carrega os Lembretes já cadastrados
        carregaLembretes();

        //Click do MAPA
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            public void onMapClick(LatLng latLng){
                latitude=latLng.latitude;
                longitude=latLng.longitude;
                criarLembrete();
            }});

        //Click no MARCADOR
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            public boolean onMarkerClick(Marker marker){
                String tituloMarcadorClicado;
                String cidadeMarcadoClicado;
                Localizador localizador = new Localizador();
                LatLng lat = marker.getPosition();
                List<Address> addresses = localizador.mostraLocal(MapsActivity.this,lat.latitude, lat.longitude);
                tituloMarcadorClicado = addresses.get(0).getAddressLine(0);
                cidadeMarcadoClicado = addresses.get(0).getAddressLine(1) + " "+addresses.get(0).getAddressLine(2);
                marker.setTitle(tituloMarcadorClicado);
                marker.setSnippet(cidadeMarcadoClicado );
            return false;
            }});

        //========================== Ações dos botões ===================================

        //Botão de localização atual
        btnLocalAtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(novoLocal!= null) {
                 localAtual(novoLocal);
                }

            }
        });//Fim btnLocalAtual

        //Click do botão lembrete - Mostra a Lista de Lembrets e Configura ações da Lista
        btnLembretes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayAdapter<Lembrete> adapter = populaLista();

               // açao de click da lista
                listaLembrete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        lembreteSelecionado = adapter.getItem(position);
                        listaLembrete.showContextMenu();

                    }
                });//Fim da ação da Lista
            }
        });//Fim btnLembretes


        //CLick do botão configuração
        btnConfig.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                LayoutInflater li = getLayoutInflater();
                RelativeLayout parentLayout = (RelativeLayout)findViewById(R.id.config);
                final View view;
                view =  li.inflate(R.layout.configuracoes,parentLayout, true);
                AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                builder.setTitle("Configurações");
                builder.setView(view);
                TextView txtNome = (TextView) findViewById(R.id.txtConfigNomeUsuario);
                TextView txtEMail = (TextView) findViewById(R.id.txtConfigEmailUsuario);

                UsuarioDAO dao = new UsuarioDAO(MapsActivity.this);
                Usuario usuario = dao.mostrarUsuario();
                txtNome.setText(usuario.getNome());
                txtEMail.setText(usuario.getEmail());

                builder.setNegativeButton("Voltar",null);
                builder.setPositiveButton("Sair do Me chame",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alerta = builder.create();
                alerta.show();

            }});//Sim btnConfig
    }//Fim do OnCreate

    private ArrayAdapter<Lembrete> populaLista() {
        LembreteDAO dao = new LembreteDAO(this);
        final List<Lembrete> lembretes = dao.listaLembretes();
        int layout = android.R.layout.simple_list_item_1;
        final ArrayAdapter<Lembrete> adapter = new ArrayAdapter<Lembrete>(this, layout, lembretes);
        listaLembrete = new ListView(this);
        registerForContextMenu(listaLembrete);
        listaLembrete.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lista de Lembretes");
        builder.setView(listaLembrete);
        builder.setNegativeButton("Voltar", null);
        alerta = builder.create();
        alerta.show();
        listaLembrete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listaLembrete.showContextMenu();
            }
        });

        return adapter;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem irParaLembrete = menu.add("Centralizar no Local");
        irParaLembrete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                localSelecionado(new LatLng(lembreteSelecionado.getLatitude(),lembreteSelecionado.getLongitude()));
                alerta.dismiss();
                return false;
            }
        });
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                alerta.dismiss();
                editaLembrete(lembreteSelecionado);

                return false;
            }
        });
        MenuItem excluir = menu.add("Excluir");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LembreteDAO dao = new LembreteDAO(MapsActivity.this);
                dao.excluir(lembreteSelecionado);
                mMap.clear();
                localInicio(new LatLng(latitudeInicio,longitudeInicio));

                carregaLembretes();
                alerta.dismiss();
                Toast.makeText(MapsActivity.this,"Lembrete "+lembreteSelecionado.getTitulo()+"\n Excluido com sucesso",Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        super.onCreateContextMenu(menu, v, menuInfo);
        txtLocal.clearFocus();
    }

    //===================================== Começo dos Métodos ============================


    // Metodo para mostrar o mapa na tela !!!
    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

        }

    }

    //Conecta a API de Localização
    private void conectarClienteLocalizacao() {
        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        mClient.connect();
    }

    //Criar Lembrete
    private void criarLembrete(){
        LayoutInflater li = getLayoutInflater();
        RelativeLayout parentLayout = (RelativeLayout)findViewById(R.id.criar_lembrete);
        final View view;
        view =  li.inflate(R.layout.criar_lembrete,parentLayout, true);
        //Criar Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("Criar Lembrete");
        builder.setView(view);
        builder.setNegativeButton("Cancelar",null);
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tituloLembrete = (TextView) view.findViewById(R.id.txtDescricao);
                TextView diametro = (TextView) view.findViewById(R.id.txtDiametro);
                LembreteDAO dao = new LembreteDAO(MapsActivity.this);
                Lembrete lembrete = new Lembrete();

                if(validacao(tituloLembrete.getText().toString(), diametro.toString()))
                {
                    int raio = Integer.parseInt(diametro.getText().toString());

                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));


                    criarGeofence(tituloLembrete.getText().toString(), latitude, longitude, raio);
                    lembrete.setTitulo(tituloLembrete.getText().toString());
                    lembrete.setRaio(raio);
                    lembrete.setLatitude(latitude);
                    lembrete.setLongitude(longitude);
                    dao.inserir(lembrete);
                   // dao.lembreteToJson(tituloLembrete.getText().toString(), raio, true, latitude, longitude);
                    Toast.makeText(MapsActivity.this, "Lembrete criado com Sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alerta = builder.create();
        alerta.show();
    }




    private void editaLembrete(final Lembrete lembrete){
        LayoutInflater li = getLayoutInflater();
        RelativeLayout parentLayout = (RelativeLayout)findViewById(R.id.criar_lembrete);
        final View view;
        view =  li.inflate(R.layout.criar_lembrete,parentLayout, true);
        final TextView tituloLembrete = (TextView) view.findViewById(R.id.txtDescricao);
        final TextView diametro = (TextView) view.findViewById(R.id.txtDiametro);
        tituloLembrete.setText(lembrete.getTitulo());
        diametro.setText(String.valueOf(lembrete.getRaio()));
        //Criar Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("Alterar Lembrete");
        builder.setView(view);
        builder.setNegativeButton("Cancelar",null);
        builder.setPositiveButton("Alterar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              LembreteDAO dao = new LembreteDAO(MapsActivity.this);
                lembrete.setTitulo(tituloLembrete.getText().toString());
                lembrete.setRaio(Integer.parseInt(diametro.getText().toString()));
                dao.editarLembrete(lembrete);
                mMap.clear();
                localInicio(new LatLng(latitudeInicio,longitudeInicio));
                carregaLembretes();
                Toast.makeText(MapsActivity.this, "Lembrete editado com Sucesso", Toast.LENGTH_SHORT).show();
                alerta.dismiss();

            }
        });

        alerta = builder.create();
        alerta.show();
    }


    //Carrega os marcadores dos lembretes já criados !!
    private void carregaLembretes(){
        LembreteDAO dao = new LembreteDAO(this);
        List<Lembrete> lembretes = dao.listaLembretes();
        for(Lembrete lembrete : lembretes){
            mMap.addMarker(new MarkerOptions().position(new LatLng(lembrete.getLatitude(), lembrete.getLongitude())));
            criarGeofence(lembrete.getTitulo(),lembrete.getLatitude(),lembrete.getLongitude(),lembrete.getRaio());
        }



    }


    private boolean validacao(String titulo ,String raio){
        String mensagem = "";

        if(titulo.equals("")) {
            mensagem += "\nDigite o Título";
        }

        if(raio.equals(""))
            mensagem+="\nDigite o Raio";


        if (!mensagem.equals("")) {
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            builder.setTitle("Atenção !");
            builder.setNegativeButton("Fechar",null);
            builder.setMessage(mensagem);
            alerta = builder.create();
            alerta.show();
        }

        return mensagem.equals("");
    }


   //Metodo para centralizar o mapa no local inicial
    private void localInicio(LatLng local){
        GoogleMap mapa = mMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
        Localizador localizador = new Localizador();
        List<Address> addresses = localizador.mostraLocal(this, local.latitude, local.longitude);
        String tituloLocalInicio = addresses.get(0).getAddressLine(0);
        String cidadeLocalInicio= addresses.get(0).getAddressLine(1) + " "+addresses.get(0).getAddressLine(2);
        //Adicionando o marcador
        marcadorUsuario = mMap.addMarker(new MarkerOptions().title(tituloLocalInicio).snippet(cidadeLocalInicio)
                                        .position(new LatLng(local.latitude, local.longitude))
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador)));


        //criarGeofence("LocalAtual",local.latitude,local.longitude);


    }

    //Mostra a localização atual do usuario
    private void localAtual(LatLng latLng){
        Localizador localizador = new Localizador();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        List<Address> addresses = localizador.mostraLocal(this,latLng.latitude,latLng.longitude);
        String tituloLocalAtual = addresses.get(0).getAddressLine(0);
        String cidadeLocalAtual= addresses.get(0).getAddressLine(1) + " "+addresses.get(0).getAddressLine(2);
        marcadorUsuario.setTitle(tituloLocalAtual);
        marcadorUsuario.setSnippet(cidadeLocalAtual);
        marcadorUsuario.setPosition(latLng);


        //criarGeofence("LocalAtual",latLng.latitude,latLng.longitude);

    }

    //Move a camera para o local onde o usuario procurou na Pesquisa ou clicou na sua lista
    private void localSelecionado(LatLng local){
        GoogleMap mapa = mMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));

    }


    //método que verifica se houve mudança de local
    private void mudancaLocal(){
        //Testar com a dstancia ao invés do Set Interval !!!
        mLocationRequest = new LocationRequest();
        //mLocationRequest.setSmallestDisplacement(10);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, mLocationRequest,MapsActivity.this);


    }

    //criação da geofence
    public void criarGeofence(String idGeo, double latitude, double longitude, int diametro){
        Geofence.Builder builder = new Geofence.Builder();
        builder.setRequestId(idGeo);
        builder.setCircularRegion(latitude, longitude, diametro);
        builder.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER);
        builder.setExpirationDuration(Geofence.NEVER_EXPIRE);
        builder.build();
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(diametro)
                .strokeColor(Color.rgb(211, 211, 211))
                .fillColor(Color.rgb(211, 211, 211)));
        Log.i("GEOFENCE", " --> "+idGeo+" " + latitude + " " + longitude+" "+diametro);

    }


   //Conexão do Serviço de Localização do Google
    @Override
    public void onConnected(Bundle bundle) {
        Location local = LocationServices.FusedLocationApi.getLastLocation(mClient);
        if(local !=null){
            latitudeInicio=local.getLatitude();
            longitudeInicio=local.getLongitude();
          localInicio(new LatLng(local.getLatitude(), local.getLongitude()));
        }

        mudancaLocal();



    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    //Métode de muddar a localização
    @Override
    public void onLocationChanged(Location location) {
        Log.i("Log","LATITUDE: "+location.getLatitude()+" "+location.getLongitude());
        Log.i("Log","Tempo: "+DateFormat.getTimeInstance().format(new Date()));
        novoLocal = new LatLng(location.getLatitude(),location.getLongitude());
       //localAtual(novoLocal);



        }

    //Inner Class que implementa o set text do pesquisar
    private class SearchFiltro implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            buscarLocal = txtLocal.getQuery().toString();
            if(buscarLocal != null) {

                Localizador localizar = new Localizador();
                LatLng local;
                local = localizar.getCoordenada(buscarLocal, MapsActivity.this);
                localSelecionado(local);
                txtLocal.clearFocus();
            }else{
                Toast.makeText(MapsActivity.this,"Digite o lugar",Toast.LENGTH_SHORT);
            }

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {


            return false;

        }
    }
}



