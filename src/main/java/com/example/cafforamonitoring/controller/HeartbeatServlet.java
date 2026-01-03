package com.example.cafforamonitoring.controller;

import java.io.*;
import com.example.cafforamonitoring.service.MachineService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//servlet per l'aggiornamento dell'heartbeat
@WebServlet("/heartbeat")
public class HeartbeatServlet extends HttpServlet{
    private final MachineService service = new MachineService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String codice = request.getParameter("codice");

        if (codice != null && !codice.isEmpty()) {
            service.updateHeartbeat(codice);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Heartbeat received");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing machine code");
        }
    }

    @Override
    public void destroy() { super.destroy(); }
}
