package geneEngine.Repository;

import geneEngine.Disease;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class DiseaseDBRepository implements DiseaseRepository{
    private final JdbcUtils dbUtils;

    public DiseaseDBRepository(
            @Value("${jdbc.url}") String url) {
        this.dbUtils = new JdbcUtils(url);
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

    @Override
    public Iterable<Disease> findAllById(String id) {
        List<Disease> diseases = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Diseases WHERE parent=?")){
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    diseases.add(new Disease(rs.getString("Name"),rs.getString("id"),rs.getString("parent")));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return diseases;
    }
}
