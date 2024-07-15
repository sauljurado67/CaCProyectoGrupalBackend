package edu.codoacodo.application.services;

import edu.codoacodo.application.adapters.IRepository;
import edu.codoacodo.domain.models.Usuario;
import edu.codoacodo.infrastucture.database.MySQLRepositoryImpl;

public class UsuarioService implements IRepository {

    private final IRepository repository = new MySQLRepositoryImpl();


    @Override
    public void saveUser(Usuario user) {

        repository.saveUser(user);
    }

    @Override
    public Usuario findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteUser(id);
    }

    @Override
    public void updateUser(int id, String username) {
        repository.updateUser(id, username);
    }

    @Override
    public boolean updateUse(Usuario id) {
        repository.updateUse(id);
        return true;
    }


}
