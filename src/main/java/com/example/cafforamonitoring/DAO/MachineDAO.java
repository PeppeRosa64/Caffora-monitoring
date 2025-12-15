package com.example.cafforamonitoring.DAO;

import com.example.cafforamonitoring.entity.Machine;
import com.example.cafforamonitoring.utility.ConnectionDBMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MachineDAO {
    public void insertMachine(Machine machine){
        String insert = "INSERT INTO machines (codice, lat, lon, stato, heartbeat) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, machine.getCodice());
            ps.setFloat(2, machine.getLat());
            ps.setFloat(3, machine.getLon());
            ps.setString(4, machine.getStato());
            ps.setTimestamp(5, machine.getHeartbeat());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Impossibile aggiungere distributore", e);
        }
    }

    public void deleteMachine(String codice){
        String delete = "DELETE FROM machines WHERE codice = ?";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setString(1, codice);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Impossibile eliminare distributore", e);
        }
    }

    public void changeStatus(String codice, String nuovoStato){
        String update = "UPDATE machines SET stato = ? WHERE codice = ?";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, nuovoStato);
            ps.setString(2, codice);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Impossibile aggiornare stato", e);
        }
    }

    public void updateHeartbeat(String codice, Timestamp nuovoHeartbeat){
        String update = "UPDATE machines SET heartbeat = ? WHERE codice = ?";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setTimestamp(1, nuovoHeartbeat);
            ps.setString(2, codice);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Impossibile aggiornare heartbeat", e);
        }
    }
}
