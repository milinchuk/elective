package model.service;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import config.connection.factory.ConnectionFactoryImpl;
import model.dao.factory.DAOFactory;
import model.dao.factory.DAOFactoryImpl;
import model.dao.interfaces.ProgressDAO;
import model.entity.Progress;
import model.service.interfaces.ProgressService;

import java.util.List;

/**
 * Implementation of ProgressService
 *
 * @author Anastasia Milinchuk
 */
public class ProgressServiceImpl implements ProgressService {
    /**
     * Factory that generate new connections
     */
    private ConnectionFactory connectionFactory;

    /**
     * Factory that generate DAOs
     */
    private DAOFactory daoFactory;

    private ProgressServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class LazyHolder{
        private static final ProgressServiceImpl INSTANCE = new ProgressServiceImpl(ConnectionFactoryImpl.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static ProgressServiceImpl getInstance(){
        return LazyHolder.INSTANCE;
    }

    @Override
    public void create(Progress progress) {
       try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
           ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
           connection.beginTransaction();
           Progress existProgress = progressDAO.findOne(progress.getStudent().getId(), progress.getCourse().getId());
           if (existProgress == null) {
               progressDAO.create(progress);
           }
           connection.commit();
           connection.close();
       }
    }

    @Override
    public void delete(Integer progressId) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
            connection.beginTransaction();
            progressDAO.delete(progressId);
            connection.commit();
            connection.close();
        }
    }

    @Override
    public void update(Progress progress) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
            connection.beginTransaction();
            progressDAO.update(progress);
            connection.commit();
            connection.close();
        }
    }

    @Override
    public Progress findOne(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
            Progress progress = progressDAO.findOne(id);
            connection.close();
            return progress;
        }
    }

    @Override
    public List<Progress> findByUser(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
            List<Progress> progresses = progressDAO.findByUser(id);
            connection.close();
            return progresses;
        }
    }

    @Override
    public List<Progress> findByCourse(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
            List<Progress> progresses = progressDAO.findByCourse(id);
            connection.close();
            return progresses;
        }
    }
}
