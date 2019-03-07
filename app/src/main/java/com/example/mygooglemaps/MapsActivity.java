package com.example.mygooglemaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // User allows app to take their location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }
        //user doesn't allow and their current location is not shown
        else{
            mMap.setMyLocationEnabled(true);
            //mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
        }


        // Castle coordinates
        LatLng Alnwick = new LatLng(55.41575, -1.70607);
        mMap.addMarker(new MarkerOptions().position(Alnwick).title("Alnwick Castle"));
        LatLng Bamburgh = new LatLng(55.608, -1.709);
        mMap.addMarker(new MarkerOptions().position(Bamburgh).title("Bamburgh Castle"));
        LatLng Warkworth = new LatLng(55.3447, -1.6105);
        mMap.addMarker(new MarkerOptions().position(Warkworth).title("Warkworth Castle"));
        LatLng Lindisfarne = new LatLng(55.669, -1.785);
        mMap.addMarker(new MarkerOptions().position(Lindisfarne).title("Lindisfarne Castle"));
        LatLng Tynemouth_Castle_Priory = new LatLng(55.0177, -1.4179);
        mMap.addMarker(new MarkerOptions().position(Tynemouth_Castle_Priory).title("Tynemouth Castle & Priory"));
        LatLng Dunstanburgh = new LatLng(55.4894, -1.5950);
        mMap.addMarker(new MarkerOptions().position(Dunstanburgh).title("Dunstanburgh Castle"));
        LatLng Chillingham = new LatLng(55.5259, -1.9038);
        mMap.addMarker(new MarkerOptions().position(Chillingham).title("Chillingham Castle"));
        LatLng Berwick = new LatLng(55.7736, -2.0125);
        mMap.addMarker(new MarkerOptions().position(Berwick).title("Berwick Castle"));
        LatLng Prudhoe = new LatLng(54.9649, -1.8582);
        mMap.addMarker(new MarkerOptions().position(Prudhoe).title("Prudhoe Castle"));
        LatLng Edlingham = new LatLng(55.3767, -1.8185);
        mMap.addMarker(new MarkerOptions().position(Edlingham).title("Edlingham Castle"));

        //imports json layer which acts as the boundary for the google maps
        try {
            GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.geojson, getApplicationContext());

            GeoJsonPolygonStyle map = layer.getDefaultPolygonStyle();
            map.setFillColor(Color.argb(70,255,195,195));
            map.setStrokeWidth(5);
            map.setStrokeColor(Color.rgb(255,77,77));

            layer.addLayerToMap();

        }catch (IOException ex) {
            Log.e("IOException", ex.getLocalizedMessage());
        } catch (JSONException ex) {
            Log.e("JSONException", ex.getLocalizedMessage());
        }


        //Moves camera to a zoomed out view of Alnick castle which will be the centre of Northumberland
        float zoomLevel = 8.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Alnwick, zoomLevel));
    }
}
