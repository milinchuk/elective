package model.service;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import config.connection.factory.StandartConnectionFactory;
import model.dao.factory.DAOFactory;
import model.dao.factory.DAOFactoryImpl;
import model.dao.interfaces.ProgressDAO;
import model.entity.Progress;
import model.service.interfaces.ProgressService;

import java.util.List;

/**
 * Created by click on 11/10/2016.
 */
public class ProgressServiceImpl implements ProgressService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    private ProgressServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class LazyHolder{
        private static final ProgressServiceImpl INSTANCE = new ProgressServiceImpl(StandartConnectionFactory.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static ProgressServiceImpl getInstance(){
        return LazyHolder.INSTANCE;
    }

    @Override
    public void create(Progress progress) {
        // add cheking
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
        Progress existProgress = progressDAO.findOne(progress.getId());
        if(existProgress == null) {
            progressDAO.create(progress);
        }
        connection.close();
    }

    @Override
    public void delete(Integer progressId) {
        // add checking
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
        progressDAO.delete(progressId);
        connection.close();
    }

    @Override
    public void update(Progress progress) {
        // add check
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
        progressDAO.update(progress);
        connection.close();
    }

    @Override
    public Progress findOne(Integer id) {
        // add check
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
        Progress progress = progressDAO.findOne(id);
        connection.close();
        return progress;
    }

    @Override
    public List<Progress> findByUser(Integer id) {
        // add check
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
        List<Progress> progresses = progressDAO.findByUser(id);
        connection.close();
        return progresses;
    }

    @Override
    public List<Progress> findByCourse(Integer id) {
        // add check
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
        List<Progress> progresses = progressDAO.findByCourse(id);
        connection.close();
        return progresses;
    }
}
