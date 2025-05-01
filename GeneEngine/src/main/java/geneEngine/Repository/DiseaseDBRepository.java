package geneEngine.Repository;

import geneEngine.Disease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DiseaseDBRepository implements DiseaseRepository{
    private final JdbcUtils dbUtils;

    public DiseaseDBRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public Disease findById(String string) {
        return null;
    }

    @Override
    public Iterable<Disease> findAll() {
        return null;
    }

    @Override
    public void save(Disease entity) {
        Connection con = dbUtils.getLocalConnection();
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO Diseases(id,name,parent) values (?,?,?)")) {
            ps.setString(1, entity.getId());
            ps.setString(2, entity.getName());
            if (entity.getParent() != null) {
                ps.setString(3,entity.getParent());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Disease entity) {

    }

    @Override
    public void delete(String string) {

    }
}
