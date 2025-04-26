package geneEngine.Repository;

import geneEngine.Gene;
import geneEngine.JSONUtils;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GeneDBRepository implements GeneRepository{

    private final JdbcUtils dbUtils;

    public GeneDBRepository(
            @Value("${jdbc.url}") String url) {
        this.dbUtils = new JdbcUtils(url);
    }

    @Override
    public Gene findById(String s) {
        Connection con = dbUtils.getConnection();
        try ( PreparedStatement ps = con.prepareStatement("select * from Genes where name=?")) {
            ps.setString(1, s);
            try ( ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst())
                    return null;
                return JSONUtils.ResultSetToGene(rs);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Iterable<Gene> findAll() {
        return null;
    }

    @Override
    public void save(Gene entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement ps = con.prepareStatement(
                "insert into Genes (" +
                        "summary,organism," +
                        "maplocation,chromosome," +
                        "otherdesignations,description," +
                        "otheraliases,nomenclaturename," +
                        "uid,chrsort,geneticsource," +
                        "name,nomenclaturesymbol," +
                        "geneweight,chrstart,status,diseases) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")){
            ps.setString(1,entity.getSummary());
            ps.setString(2,entity.getOrganism());
            ps.setString(3,entity.getMaplocation());
            ps.setString(4,entity.getChromosome());
            ps.setString(5,entity.getOtherdesignations());
            ps.setString(6,entity.getDescription());
            ps.setString(7,entity.getOtheraliases());
            ps.setString(8,entity.getNomenclaturename());
            ps.setString(9,entity.getUid());
            ps.setString(10,entity.getChrsort());
            ps.setString(11,entity.getGeneticsource());
            ps.setString(12,entity.getNume());
            ps.setString(13,entity.getNomenclaturesymbol());
            ps.setInt(14,entity.getGeneweight());
            ps.setInt(15,entity.getChrstart());
            ps.setString(16,entity.getStatus());
            ps.setString(17,entity.getDiseases());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Gene entity) {

    }

    @Override
    public void delete(String s) {

    }
}
