package edu.codoacodo.infrastucture.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.codoacodo.application.services.UsuarioService;
import edu.codoacodo.domain.models.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsuarioController extends HttpServlet {
    private ObjectMapper mapper;
    private UsuarioService service;

    public UsuarioController() {
        this.mapper = new ObjectMapper();
        this.service = new UsuarioService();
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // configura los encabezados CORS
        configureCorsHeaders(resp);
    }

    private void configureCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*"); // aca colocan la direccion de donde viene la peticion
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "content-type, authorization");
    }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            configureCorsHeaders(resp);
            Usuario usuario = mapper.readValue(req.getInputStream(), Usuario.class);
            service.saveUser(usuario);
            resp.setStatus(200);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            configureCorsHeaders(resp);
            String username = req.getParameter("username");

            if (username != null) {
                Usuario usuario = service.findByUsername(username);
                if (usuario != null) {
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    resp.getWriter().write(mapper.writeValueAsString(usuario));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("usuario no encontrado");
                }
            }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            configureCorsHeaders(resp);
            String idString = req.getParameter("id");

            if (idString != null && !idString.isEmpty()) {
                int id = Integer.parseInt(idString);
                service.deleteUser(id);
                resp.setStatus(200);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("ID DE USUARIO INVALIDO");
            }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            configureCorsHeaders(resp);
            String idString = req.getParameter("id");
            System.out.println(idString);
            if (idString != null && !idString.isEmpty()) {
                int id = Integer.parseInt(idString);
                Usuario usuario = mapper.readValue(req.getInputStream(), Usuario.class);
                usuario.setId(id); // Set the ID to the user object to ensure it updates the correct user
                boolean updated = service.updateUse(usuario);

                if (updated) {
                    resp.setStatus(200);
                    resp.getWriter().write("Usuario actualizado con éxito");
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("Usuario no encontrado");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("ID DE USUARIO INVALIDO");
            }
    }

}



