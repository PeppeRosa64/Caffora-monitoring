package com.example.cafforamonitoring.controller;

import java.io.*;
import java.util.List;
import com.example.cafforamonitoring.entity.Machine;
import com.example.cafforamonitoring.service.MachineService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/api/machines")
public class AllMachinesServlet extends HttpServlet{
    private final MachineService service = new MachineService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Machine> machines = service.getMachineData();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("[");
        for (Machine m : machines){
            out.println("\t{");
            out.println("\t\tcodice: "+m.getCodice());
            out.println("\t\tlat: "+m.getLat());
            out.println("\t\tlon: "+m.getLon());
            out.println("\t\tstato: "+m.getStato());
            if(m.getHeartbeat() != null){
                out.println("\t\theartbeat: "+m.getHeartbeat());
            }else{
                out.println("\t\theartbeat: ");
            }
            out.println("\t}");
        }
        out.println("]");
    }

    @Override
    public void destroy() { super.destroy(); }
}
