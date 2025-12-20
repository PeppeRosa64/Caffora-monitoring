package com.example.cafforamonitoring.service;

import com.example.cafforamonitoring.DAO.MachineDAO;
import com.example.cafforamonitoring.entity.Machine;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class MachineService {
    private final MachineDAO machineDAO = new MachineDAO();

    public List<Machine> getMachineData(){
        List<Machine> machines = machineDAO.getAllMachines();

        for (Machine m: machines){
            if(m.getStato().equals("Attivo")){
                Instant heartbeat = m.getHeartbeat();

                if(heartbeat == null || Duration.between(heartbeat, Instant.now()).toMinutes() >= 3){
                    m.setStato("Guasto");
                    machineDAO.changeStatus(m.getCodice(), m.getStato());
                }
            }
        }

        return machines;
    }

    public void updateHeartbeat(String codice){
        if (codice != null && !codice.isEmpty()) {
            machineDAO.updateHeartbeat(codice, Timestamp.from(Instant.now()));
            machineDAO.changeStatus(codice, "Attivo");
        }
    }

    public void toggleManutenzione(String codice){
        if (codice != null && !codice.isEmpty()) {
            String nuovoStato = machineDAO.getStatoByCodice(codice).equals("Manutenzione") ? "Attivo" : "Manutenzione";
            machineDAO.changeStatus(codice, nuovoStato);
        }
    }

    public void addMachine(Machine machine){
        if (machine.getCodice() == null || machine.getCodice().isEmpty()) {
            throw new IllegalArgumentException("Il codice distributore è obbligatorio");
        }
        if (machine.getStato() == null) {
            machine.setStato("ATTIVO");
        }

        machineDAO.addMachine(machine);
    }

    public void deleteMachine(String codice){
        if (codice != null && !codice.isEmpty()) {
            machineDAO.deleteMachine(codice);
        }
    }
}
