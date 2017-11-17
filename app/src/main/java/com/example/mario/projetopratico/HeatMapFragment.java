package com.example.mario.projetopratico;


import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

import static com.example.mario.projetopratico.MainActivity.fab;

public class HeatMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private HashMap<Integer, Marker> marksbkp = new HashMap<Integer, Marker>();
    private HashMap<Integer, Marker> marks = new HashMap<Integer, Marker>();
    private int pk = 4;
    FloatingActionButton fab;
    private int ativo = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_heat_map);
       // SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.frame_mapa);
        //mapFragment.getMapAsync(this);
        getMapAsync(this);

    }

    private void popularMapa(int resource) throws JSONException {
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);

            LatLng latLng = new LatLng(object.getDouble("lat"),object.getDouble("lng")) ;
            //double lng = object.getDouble("lng");
            int id = object.getInt("id");
            String description = object.getString("description");
            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(description).snippet(object.getString("id")));
            marks.put(id,marker);

        }
        //return list;
    }


    public void mudarFiltro(){
        if(ativo == 0){
            ativo++;
            marksbkp = marks;
            ativarHeatMap();
        }else{
            ativo--;
            ativarMarkerMap();
        }
    }

    private void removerFoco(final int chave) {
        AlertDialog.Builder decisao = new AlertDialog.Builder(getContext());
        decisao.setMessage("Deseja mesmo remover este foco de dengue?");
        decisao.setTitle("REMOÇÃO DE FOCO");
        decisao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {

                    Marker marker = marks.get(chave);
                    marker.remove();
                    marks.remove(chave);
                    Toast.makeText(getContext(), "Ponto de foco removido com sucesso!", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Problema ao deletar", Toast.LENGTH_LONG).show();
                }


            }
        });
        decisao.show();
    }

    // Método que preenche com valores estáticos.
    private void addHeatMap() {
      //  List<LatLng> list = null;

        // Get the data: latitude/longitude positions of police stations.
        try {
            popularMapa(R.raw.focos_de_dengue);
        } catch (JSONException e) {
            Toast.makeText(getContext(), "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        }

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        //HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder().build();
        // Add a tile overlay to the map, using the heat map tile provider.
        //TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
      try{

            mMap = googleMap;
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(Boolean.TRUE);
            mMap.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
                @Override
                public void onInfoWindowLongClick(Marker marker) {
                    mudarFiltro();
                }
            });
            mMap.setOnMapLongClickListener(this);


            mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mudarFiltro();
                    return false;
                }
            });
            mMap.setOnMapClickListener(this);
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    removerFoco(Integer.parseInt( marker.getSnippet()));
                }
            });

            LatLng gps = new LatLng(-2.490882, -44.250214);

            float zoomLevel = 11.0f;
            //mMap.addMarker(new MarkerOptions().position(gps).title("Marcador em São Luis"));
            //mMap.addMarker(new MarkerOptions().position(new LatLng(-2.553771, -44.254005)).title("Hue"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gps,zoomLevel));
            addHeatMap();
            //ativarHeatMap();
      }catch (SecurityException ex){

      }
    }



    @Override
    public void onMapClick(LatLng latLng) {
        String description;


    }




    void ativarHeatMap(){

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        TileProvider mProvider;
        TileOverlay mOverlay;
        List<LatLng> list = new ArrayList<LatLng>();

        for(int i = 0; i< marks.size();i++){
            if(marks.get(i) != null)
                list.add(marks.get(i).getPosition());
        }
        mMap.clear();
        mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.

        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }

    void ativarMarkerMap(){
        mMap.clear();
        for (int i = 0; i < marks.size(); i++) {

            if(marks.get(i) != null)
                mMap.addMarker(new MarkerOptions().position(marks.get(i).getPosition()).title(marks.get(i).getTitle()).snippet(marks.get(i).getSnippet()));
            }
    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        pk++;
        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Foco numero: "+pk).snippet(""+pk));
        marks.put(pk,marker);


    }
}
