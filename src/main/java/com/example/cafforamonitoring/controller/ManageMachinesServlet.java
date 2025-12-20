package com.example.cafforamonitoring.controller;

import java.io.*;

import com.example.cafforamonitoring.entity.Machine;
import com.example.cafforamonitoring.service.MachineService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/adminManagement")
public class ManageMachinesServlet extends HttpServlet{
    private final MachineService service = new MachineService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codice = request.getParameter("codice");
        String action = request.getParameter("action");

        if (codice != null && !codice.isEmpty()) {
            if (action.equals("add")){
                float lat = Float.parseFloat(request.getParameter("lat"));
                float lon = Float.parseFloat(request.getParameter("lon"));

                Machine machine = new Machine(codice, lat, lon, "Attivo", null);
                service.addMachine(machine);
            } else if (action.equals("delete")){
                service.deleteMachine(codice);
            } else if (action.equals("toggleManutenzione")){
                service.toggleManutenzione(codice);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing machine code");
        }
    }

    @Override
    public void destroy() { super.destroy(); }
}
