package com.example.cafforamonitoring.DAO;

import com.example.cafforamonitoring.entity.Machine;
import com.example.cafforamonitoring.utility.ConnectionDBMonitor;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MachineDAO {
    public void addMachine(Machine machine){
        String add = "INSERT INTO machines (codice, lat, lon, stato) VALUES (?, ?, ?, ?)";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(add)){
            ps.setString(1, machine.getCodice());
            ps.setFloat(2, machine.getLat());
            ps.setFloat(3, machine.getLon());
            ps.setString(4, machine.getStato());

            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Impossibile aggiungere distributore", e);
        }
    }

    public void deleteMachine(String codice){
        String delete = "DELETE FROM machines WHERE codice = ?";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(delete)){
            ps.setString(1, codice);

            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Impossibile eliminare distributore", e);
        }
    }

    public void changeStatus(String codice, String nuovoStato){
        String update = "UPDATE machines SET stato = ? WHERE codice = ?";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(update)){
            ps.setString(1, nuovoStato);
            ps.setString(2, codice);

            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Impossibile aggiornare stato", e);
        }
    }

    public void updateHeartbeat(String codice, Timestamp nuovoHeartbeat){
        String update = "UPDATE machines SET heartbeat = ? WHERE codice = ?";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(update)){
            ps.setTimestamp(1, nuovoHeartbeat);
            ps.setString(2, codice);

            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Impossibile aggiornare heartbeat", e);
        }
    }

    public List<Machine> getAllMachines(){
        List<Machine> machines = new LinkedList<>();
        String getAll = "SELECT * FROM machines";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(getAll)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Machine machine = new Machine(rs.getString("codice"), rs.getFloat("lat"), rs.getFloat("lon"),
                        rs.getString("stato"), rs.getTimestamp("heartbeat").toInstant());
                machines.add(machine);
            }
        }catch (SQLException e){
            throw new RuntimeException("Impossibile recuperare distributori", e);
        }

        return machines;
    }

    public String getStatoByCodice(String codice){
        String getStato = "SELECT stato FROM machines WHERE codice = ?";

        try(Connection conn = ConnectionDBMonitor.getConnection(); PreparedStatement ps = conn.prepareStatement(getStato)){
            ps.setString(1, codice);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("stato");
            }
        }catch (SQLException e){
            throw new RuntimeException("Impossibile recuperare stato", e);
        }

        return null;
    }
}
