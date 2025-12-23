package com.example.cafforamonitoring.controller;

import java.io.*;

import com.example.cafforamonitoring.entity.Machine;
import com.example.cafforamonitoring.service.MachineService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/machineManagement")
public class ManageMachinesServlet extends HttpServlet{
    private final MachineService service = new MachineService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codice = request.getParameter("codice");
        String action = request.getParameter("action");

        if (codice != null && !codice.isEmpty()) {
            switch (action) {
                case "add" -> {
                    float lat = Float.parseFloat(request.getParameter("lat"));
                    float lon = Float.parseFloat(request.getParameter("lon"));

                    Machine machine = new Machine(codice, lat, lon, "Attivo", null);
                    service.addMachine(machine);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Machine added successfully");
                }
                case "delete" -> {
                    service.deleteMachine(codice);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Machine deleted successfully");
                }
                case "toggleManutenzione" -> {
                    service.toggleManutenzione(codice);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Machine status changed successfully");
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing machine code");
        }
    }

    @Override
    public void destroy() { super.destroy(); }
}
