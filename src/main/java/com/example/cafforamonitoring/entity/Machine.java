package com.example.cafforamonitoring.entity;

import java.time.Instant;

//entità del distributore
public class Machine {
    private String codice;
    private float lat;
    private float lon;
    private String stato;
    private Instant heartbeat;

    public Machine() { }

    public Machine(String codice, float lat, float lon, String stato, Instant heartbeat){
        this.codice = codice;
        this.lat = lat;
        this.lon = lon;
        this.stato = stato;
        this.heartbeat = heartbeat;
    }

    public String getCodice() { return codice; }
    public float getLat() { return lat; }
    public float getLon() { return lon; }
    public String getStato() { return stato; }
    public Instant getHeartbeat() { return heartbeat; }

    public void setCodice(String codice) { this.codice = codice; }
    public void setLat(float lat) { this.lat = lat; }
    public void setLon(float lon) { this.lon = lon; }
    public void setStato(String stato) { this.stato = stato; }
    public void setHeartbeat(Instant heartbeat) { this.heartbeat = heartbeat; }
}