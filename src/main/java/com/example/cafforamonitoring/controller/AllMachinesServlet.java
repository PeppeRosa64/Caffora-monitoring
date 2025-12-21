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

        response.setContentType("application/xml");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.println("<distributori>");
        for (Machine m : machines){
            out.println("\t<distributore>");
            out.println("\t\t<codice>"+m.getCodice()+"</codice>");
            out.println("\t\t<lat>"+m.getLat()+"</lat>");
            out.println("\t\t<lon>"+m.getLon()+"</lon>");
            out.println("\t\t<stato>"+m.getStato()+"</stato>");
            if(m.getHeartbeat() != null){
                out.println("\t\t<heartbeat>"+m.getHeartbeat()+"</heartbeat>");
            }else{
                out.println("\t\t<heartbeat></heartbeat>");
            }
            out.println("\t</distributore>");
        }
        out.println("</distributori>");
    }

    @Override
    public void destroy() { super.destroy(); }
}
