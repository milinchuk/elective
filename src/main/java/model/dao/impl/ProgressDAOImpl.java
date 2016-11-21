package model.dao.impl;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import model.dao.impl.utils.ProgressPickUtil;
import model.dao.impl.utils.QueryResource;
import model.dao.interfaces.ProgressDAO;
import model.entity.Course;
import model.entity.Progress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by click on 11/10/2016.
 */
public class ProgressDAOImpl implements ProgressDAO {
    private Connection connection;
    private ProgressPickUtil progressPickUtil;
    private QueryResource resource;

    private String QUERIES_URL = "progress_query.properties";

    public ProgressDAOImpl(Connection connection) {
        this.connection = connection;
        this.progressPickUtil = new ProgressPickUtil();
        this.resource = new QueryResource(QUERIES_URL);
    }

    @Override
    public void create(Progress progress) {

    }

    @Override
    public void delete(String courseCode, String userEmail) {

    }

    @Override
    public void update(Progress progress) {

    }

    @Override
    public Progress findOne(String courseCode, String userEmail) {
        return null;
    }

    @Override
    public List<Progress> findByUser(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_STUDENT));
            statement.setInt(1, id);
            List<Progress> progresses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                progresses.add(progressPickUtil.pick(resultSet));
            }
            return progresses;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Progress> findByCourse(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_COURSE));
            statement.setInt(1, id);
            List<Progress> progresses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                progresses.add(progressPickUtil.pick(resultSet));
            }
            return progresses;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
