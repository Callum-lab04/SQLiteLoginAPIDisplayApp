package com.example.sqlitelogindatabaseapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;

        public class IpAPIScreen extends AppCompatActivity {
            TextView txtView_API;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ip_apiscreen);

                txtView_API = findViewById(R.id.txtView_API);

                IPGeolocationAPI api = new IPGeolocationAPI("");

                GeolocationParams geoParams = new GeolocationParams();
                geoParams.setIPAddress("19.180.160.106");
                geoParams.setFields("geo,time_zone,currency");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Geolocation geolocation = api.getGeolocation(geoParams);

                            if (geolocation.getStatus() == 200) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String result = "Country: " + geolocation.getCountryName() +
                                                "\nCountry Capital: " + geolocation.getCountryCapital() +
                                                "\nContinent Name: " + geolocation.getContinentName() +
                                                "\nCurrency: " + geolocation.getCurrency().getName() +
                                                "\nCurrent Time: " + geolocation.getTimezone().getCurrentTime() +
                                                "\nOrganisation: " + geolocation.getOrganization() +
                                                "\nCity: " + geolocation.getCity() +
                                                "\nDistrict: " + geolocation.getDistrict() +
                                                "\nTimezone: " + geolocation.getTimezone() +
                                                "\nLatitude: " + geolocation.getLatitude() +
                                                "\nLongitude: " + geolocation.getLongitude() +
                                                "\nZip Code: " + geolocation.getZipCode() +
                                                "\nIP Address: " + geolocation.getIPAddress() +
                                                "\nLanguage Spoken: " + geolocation.getLanguages() +
                                                "\nISP: " + geolocation.getISP();

                                        txtView_API.setText(result);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(IpAPIScreen.this,
                                                "Error: " + geolocation.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(IpAPIScreen.this,
                                            "Failed to fetch data: " + e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        }
