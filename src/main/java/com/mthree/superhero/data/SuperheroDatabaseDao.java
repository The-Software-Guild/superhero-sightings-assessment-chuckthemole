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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        final String sql = "INSERT INTO location(longitude, latitude) VALUES(?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setDouble(1, location.getLongitude());
            statement.setDouble(2, location.getLatitude());
            return statement;

        }, keyHolder);

        location.setId(keyHolder.getKey().intValue());

        return location;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        final String sql = "INSERT INTO organizationHeroVillain(isForHero, organizationName) VALUES(?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setBoolean(1, organization.getIsForHero());
            statement.setString(2, organization.getName());
            return statement;

        }, keyHolder);

        organization.setId(keyHolder.getKey().intValue());

        return organization;
    }

    @Override
    public Power createPower(Power power) {
        final String sql = "INSERT INTO power(powerName, powerDescription) VALUES(?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, power.getName());
            statement.setString(2, power.getDescription());
            return statement;

        }, keyHolder);

        power.setId(keyHolder.getKey().intValue());

        return power;
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        final String sql = "INSERT INTO sighting(sightingDate, heroVillain_id, location_id) VALUES(?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setDate(1, Date.valueOf(sighting.getDate()));
            statement.setInt(2, sighting.getHeroVillain().getId());
            statement.setInt(3, sighting.getLocation().getId());
            return statement;

        }, keyHolder);

        sighting.setId(keyHolder.getKey().intValue());

        return sighting;
    }

    @Override
    public HeroVillain editHeroVillain(HeroVillain heroVillain, int id) {
        final String sql = "UPDATE heroVillain SET heroVillainName = ?, isHero = ? WHERE heroVillain_id = ?";

        jdbcTemplate.update(sql, heroVillain.getName(), heroVillain.getIsHero(), id);
        return heroVillain; 
    }

    @Override
    public Location editLocation(Location location, int id) {
        final String sql = "UPDATE location SET latitude = ?, longitude = ? WHERE location_id = ?";

        jdbcTemplate.update(sql, location.getLatitude(), location.getLongitude(), id);
        return location; 
    }

    @Override
    public Organization editOrganization(Organization organization, int id) {
        final String sql = "UPDATE organizationHeroVillain SET organizationName = ?, isForHero = ? WHERE organization_id = ?";

        jdbcTemplate.update(sql, organization.getName(), organization.getIsForHero(), id);
        return organization;
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
    public boolean deleteHeroVillain(int id) {
        // delete heroOrganization heroPower and sighting first
        final String sql = "DELETE FROM heroVillain WHERE heroVillain_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean deleteLocation(int id) {
        final String sql = "DELETE FROM location WHERE location_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean deleteOrganization(int id) {
        final String sql = "DELETE FROM organizationHeroVillain WHERE organization_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean deletePower(int id) {
        final String sql = "DELETE FROM power WHERE power_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean deleteSighting(int id) {
        final String sql = "DELETE FROM sighting WHERE sighting_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public HeroVillain getHeroVillain(int id) {
        final String sql = 
                "SELECT * FROM heroVillain WHERE heroVillain_id = ?;";
        HeroVillain heroVillain = jdbcTemplate.queryForObject(sql, new HeroVillainMapper(), id);
        
        String sqlPower = "SELECT power_id FROM heroVillainPower WHERE heroVillain_id = ?;";
        List<Integer> ids = jdbcTemplate.queryForList(sqlPower, Integer.class, heroVillain.getId());

        List<Power> powers = new ArrayList<>();
        ids.forEach(powerId -> {
            powers.add(getPower(powerId));
        });
        heroVillain.setPowers(powers);
        
        return heroVillain;
    }

    @Override
    public Location getLocation(int id) {
        final String sql = 
                "SELECT * FROM location WHERE location_id = ?;";
        return jdbcTemplate.queryForObject(sql, new LocationMapper(), id);
    }

    @Override
    public Organization getOrganization(int id) {
        final String sql = 
                "SELECT * FROM organizationHeroVillain WHERE organization_id = ?;";
        Organization organization = jdbcTemplate.queryForObject(sql, new OrganizationMapper(), id);
        
        final String sqlHeroVillain = "SELECT heroVillain_id FROM heroVillainOrganization WHERE organization_id = ?;";
        List<Integer> ids = jdbcTemplate.queryForList(sqlHeroVillain, Integer.class, organization.getId());

        List<HeroVillain> herosAndVillains = new ArrayList<>();
        ids.forEach(heroVillainId -> {
            herosAndVillains.add(getHeroVillain(heroVillainId));
        });
        organization.setHerosAndVillains(herosAndVillains);
        
        return organization;
    }

    @Override
    public Power getPower(int id) {
        final String sql = 
                "SELECT * FROM power WHERE power_id = ?;";
        return jdbcTemplate.queryForObject(sql, new PowerMapper(), id);
    }

    @Override
    public Sighting getSighting(int id) {
        final String sql = 
                "SELECT * FROM sighting WHERE sighting_id = ?;";
        Sighting sighting = jdbcTemplate.queryForObject(sql, new SightingMapper(), id);
        
        String sqlHeroVillain = "SELECT heroVillain_id FROM sighting WHERE sighting_id = ?;";
        int heroVillainId = jdbcTemplate.queryForObject(sqlHeroVillain, Integer.class);
        sighting.setHeroVillain(getHeroVillain(heroVillainId));

        String sqlLocation = "SELECT location_id FROM sighting WHERE sighting_id = ?;";
        int locationId = jdbcTemplate.queryForObject(sqlLocation, Integer.class);
        sighting.setLocation(getLocation(locationId));
        
        return sighting;
    }

    @Override
    public List<HeroVillain> getAllHerosAndVillains() {
        final String sql = "SELECT * FROM heroVillain;";
        List<HeroVillain> herosAndVillains = jdbcTemplate.query(sql, new HeroVillainMapper());
        
        herosAndVillains.forEach(heroVillain -> {
            String sqlPower = "SELECT power_id FROM heroVillainPower WHERE heroVillain_id = ?;";
            List<Integer> ids = jdbcTemplate.queryForList(sqlPower, Integer.class, heroVillain.getId());
            
            List<Power> powers = new ArrayList<>();
            ids.forEach(id -> {
                powers.add(getPower(id));
            });
            heroVillain.setPowers(powers);
        });
        
        return herosAndVillains;
    }
    
    @Override
    public List<Location> getAllLocations() {
        final String sql = "SELECT * FROM location;";
        return jdbcTemplate.query(sql, new LocationMapper());
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        final String sqlOrganization = "SELECT * FROM organizationHeroVillain;";
        List<Organization> organizations = 
                jdbcTemplate.query(sqlOrganization, new OrganizationMapper());
        
        // set heros and villains for organization
        organizations.forEach(organization -> {
            String sqlHeroVillain = "SELECT heroVillain_id FROM heroVillainOrganization WHERE organization_id = ?;";
            List<Integer> ids = jdbcTemplate.queryForList(sqlHeroVillain, Integer.class, organization.getId());
            
            List<HeroVillain> herosAndVillains = new ArrayList<>();
            ids.forEach(id -> {
                herosAndVillains.add(getHeroVillain(id));
            });
            organization.setHerosAndVillains(herosAndVillains);
        });
        
        return organizations;
    }
    
    @Override
    public List<Power> getAllPowers() {
        final String sqlOrganization = "SELECT * FROM power;";
        List<Power> powers = 
                jdbcTemplate.query(sqlOrganization, new PowerMapper()); 
        return powers;
    }
    
    @Override
    public List<Sighting> getAllSightings() {
        final String sqlSighting = "SELECT * FROM sighting;";
        List<Sighting> sightings = 
                jdbcTemplate.query(sqlSighting, new SightingMapper());
        
        // set heros and villains for organization
        sightings.forEach(sighting -> {
            String sqlHeroVillain = "SELECT heroVillain_id FROM sighting WHERE sighting_id = ?;";
            int heroVillainId = jdbcTemplate.queryForObject(sqlHeroVillain, Integer.class);
            sighting.setHeroVillain(getHeroVillain(heroVillainId));
            
            String sqlLocation = "SELECT location_id FROM sighting WHERE sighting_id = ?;";
            int locationId = jdbcTemplate.queryForObject(sqlLocation, Integer.class);
            sighting.setLocation(getLocation(locationId));
        });
        
        return sightings;
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
    
    private static final class LocationMapper 
            implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("location_id"));
            location.setLatitude(rs.getDouble("latitude"));
            location.setLongitude(rs.getDouble("longitude"));
            return location;
        }
    }
    
    private static final class OrganizationMapper 
            implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("organization_id"));
            organization.setIsForHero(rs.getBoolean("isForHero"));
            organization.setName(rs.getString("organizationName"));
            return organization;
        }
    }

    private static final class PowerMapper 
            implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power power = new Power();
            power.setId(rs.getInt("power_id"));
            power.setName(rs.getString("powerName"));
            power.setDescription(rs.getString("powerDescription"));;
            return power;
        }
    }
    
    private static final class SightingMapper 
            implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("sighting_id"));
            sighting.setDate(rs.getDate("sightingDate").toLocalDate());
            return sighting;
        }
    }
}
