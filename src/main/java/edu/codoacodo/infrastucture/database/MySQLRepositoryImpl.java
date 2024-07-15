package edu.codoacodo.infrastucture.database;

import edu.codoacodo.application.adapters.IRepository;
import edu.codoacodo.domain.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLRepositoryImpl implements IRepository {

    private Connection conexion;

    public MySQLRepositoryImpl() {
        this.conexion = DataBaseConnection.getConnection();
    }


    @Override
    public void saveUser(Usuario user) {

        String sql = "INSERT INTO users (username, password, email) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setString(1, user.getUsername() );
            preparador.setString(2, user.getPassword() );
            preparador.setString(3, user.getEmail() );

            preparador.executeUpdate();
            preparador.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Usuario findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setString(1, username);

            ResultSet tablaVirtual = preparador.executeQuery();

            if(tablaVirtual.next()){
                Usuario usuario = new Usuario();
                usuario.setId(tablaVirtual.getInt("id"));
                usuario.setUsername(tablaVirtual.getString("username"));
                usuario.setPassword(tablaVirtual.getString("password"));
                usuario.setEmail(tablaVirtual.getString("email"));
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setInt(1, id);

            preparador.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(int id, String username) {
        String sql = "UPDATE users SET username = ? WHERE id = ?";

        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setString(1, username);
            preparador.setInt(2, id);


            preparador.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean updateUse(Usuario usuario) {
        String sql = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";

        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setString(1, usuario.getUsername());
            preparador.setString(2, usuario.getPassword());
            preparador.setString(3, usuario.getEmail());
            preparador.setInt(4, usuario.getId());

            int rowsUpdated = preparador.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Esto es opcional, para logging
            return false;
            //throw new RuntimeException(e);
        }
    }

}
