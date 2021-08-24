/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.data;

import com.mthree.superhero.models.HeroVillain;
import com.mthree.superhero.models.Location;
import com.mthree.superhero.models.Organization;
import com.mthree.superhero.models.Power;
import com.mthree.superhero.models.Sighting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Chuck
 */

@Repository
@Profile("database")
public class SuperheroDatabaseDao implements SuperheroDao {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public SuperheroDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public HeroVillain createHeroVillain(HeroVillain heroVillain) {
        final String sql = "INSERT INTO heroVillain(isHero, heroVillainName) VALUES(?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setBoolean(1, heroVillain.getIsHero());
            statement.setString(2, heroVillain.getName());
            return statement;

        }, keyHolder);

        heroVillain.setId(keyHolder.getKey().intValue());

        return heroVillain;
    }

    @Override
    public Location createLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization createOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Power createPower(Power power) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HeroVillain editHeroVillain(HeroVillain heroVillain, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location editLocation(Location location, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization editOrganization(Organization organization, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Power editPower(Power power, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting editSighting(Sighting sighting, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HeroVillain deleteHeroVillain(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location deleteLocation(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization deleteOrganization(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Power deletePower(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting deleteSighting(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HeroVillain getHeroVillain(int id) {
        final String sql = 
                "SELECT * FROM heroVillain WHERE heroVillain_id = ?;";
        HeroVillain heroVillain = 
                jdbcTemplate.queryForObject(sql, new HeroVillainMapper(), id);
        return heroVillain;
    }

    @Override
    public Location getLocation(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization getOrganization(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Power getPower(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting getSighting(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HeroVillain> getAllHerosAndVillains() {
        final String sql = "SELECT * FROM heroVillain;";
        return jdbcTemplate.query(sql, new HeroVillainMapper());
    }
    
    private static final class HeroVillainMapper 
            implements RowMapper<HeroVillain> {
        @Override
        public HeroVillain mapRow(ResultSet rs, int i) throws SQLException {
            HeroVillain heroVillain = new HeroVillain();
            heroVillain.setId(rs.getInt("heroVillain_id"));
            heroVillain.setIsHero(rs.getBoolean("isHero"));
            heroVillain.setName(rs.getString("heroVillainName"));
            return heroVillain;
        }
    }
}
