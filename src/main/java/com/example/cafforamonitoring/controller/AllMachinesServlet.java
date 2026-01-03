package com.example.cafforamonitoring.controller;

import java.io.*;
import java.util.List;
import com.example.cafforamonitoring.entity.Machine;
import com.example.cafforamonitoring.service.MachineService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//servlet che restituisce la lista dei distributori con gli stati aggiornati
@WebServlet("/api/machines")
public class AllMachinesServlet extends HttpServlet{
    private final MachineService service = new MachineService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Machine> machines = service.getMachineData();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.print("[");
        for (int i = 0; i < machines.size(); i++){
            Machine m = machines.get(i);

            out.print("{");
            out.print("\"codice\": \""+m.getCodice()+"\",");
            out.print("\"lat\": \""+m.getLat()+"\",");
            out.print("\"lon\": \""+m.getLon()+"\",");
            out.print("\"stato\": \""+m.getStato()+"\",");
            out.print("\"heartbeat\": \""+m.getHeartbeat()+"\"");
            out.print("}");

            if (i < machines.size()-1) out.print(",");
        }
        out.print("]");
    }

    @Override
    public void destroy() { super.destroy(); }
}
