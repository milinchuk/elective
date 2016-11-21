package model.service;

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
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connectionFactory.getMySqlConnection());
        progressDAO.create(progress);
    }

    @Override
    public void delete(String courseCode, String userEmail) {
        // add checking
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connectionFactory.getMySqlConnection());
        progressDAO.delete(courseCode, userEmail);
    }

    @Override
    public void update(Progress progress) {
        // add check
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connectionFactory.getMySqlConnection());
        progressDAO.update(progress);
    }

    @Override
    public Progress findOne(String courseCode, String userEmail) {
        // add check
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connectionFactory.getMySqlConnection());
        return progressDAO.findOne(courseCode, userEmail);
    }

    @Override
    public List<Progress> findByUser(Integer id) {
        // add check
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connectionFactory.getMySqlConnection());
        return progressDAO.findByUser(id);
    }

    @Override
    public List<Progress> findByCourse(Integer id) {
        // add check
        ProgressDAO progressDAO = daoFactory.getProgressDAO(connectionFactory.getMySqlConnection());
        return progressDAO.findByCourse(id);
    }
}
