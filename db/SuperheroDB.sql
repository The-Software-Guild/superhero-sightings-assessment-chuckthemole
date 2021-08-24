DROP DATABASE IF EXISTS superheroDB;
CREATE DATABASE superheroDB;

USE superheroDB;

CREATE TABLE location (
	location_id INT AUTO_INCREMENT,
    CONSTRAINT pk_location
		PRIMARY KEY (location_id),
        
    longitude DOUBLE PRECISION NOT NULL,
    latitude DOUBLE PRECISION NOT NULL
);

CREATE TABLE organizationHeroVillain (
	organization_id INT AUTO_INCREMENT,
    CONSTRAINT pk_organization
		PRIMARY KEY (organization_id),
	
    isForHero BOOLEAN NOT NULL,
    organizationName VARCHAR(45) NOT NULL
);

CREATE TABLE heroVillain (
	heroVillain_id INT AUTO_INCREMENT,
    CONSTRAINT pk_heroVillain
		PRIMARY KEY (heroVillain_id),
        
	isHero BOOLEAN NOT NULL,
    heroVillainName VARCHAR(45) NOT NULL
);

CREATE TABLE power (
	power_id INT AUTO_INCREMENT,
    CONSTRAINT pk_power
		PRIMARY KEY (power_id),
	
    powerName VARCHAR(45) NOT NULL, 
    powerDescription VARCHAR(45) NOT NULL
);

CREATE TABLE heroVillainPower (
	heroVillainPower_id INT AUTO_INCREMENT,
    CONSTRAINT pk_heroVillainPower
		PRIMARY KEY (heroVillainPower_id),
        
	heroVillain_id INT NOT NULL,
    CONSTRAINT fk_heroVillainPower
    	FOREIGN KEY (heroVillain_id)
    	REFERENCES heroVillain(heroVillain_id),
	power_id INT NOT NULL,
    CONSTRAINT fk_power
    	FOREIGN KEY (power_id)
    	REFERENCES power(power_id)   
);

CREATE TABLE heroVillainOrganization (
	heroVillainOrganization_id INT AUTO_INCREMENT,
    CONSTRAINT pk_heroVillainOrganization
		PRIMARY KEY (heroVillainOrganization_id),
        
	heroVillain_id INT NOT NULL,
    CONSTRAINT fk_heroVillainOrganization
    	FOREIGN KEY (heroVillain_id)
    	REFERENCES heroVillain(heroVillain_id),
	organization_id INT NOT NULL,
    CONSTRAINT fk_organization
    	FOREIGN KEY (organization_id)
    	REFERENCES organizationHeroVillain(organization_id)   
);

CREATE TABLE sighting (
	sighting_id INT AUTO_INCREMENT,
    CONSTRAINT pk_sighting
		PRIMARY KEY (sighting_id),
	
    sightingDate DATE NOT NULL,
        
	location_id INT NOT NULL,
    CONSTRAINT fk_location
    	FOREIGN KEY (location_id)
    	REFERENCES location(location_id),
	heroVillain_id INT NOT NULL,
    CONSTRAINT fk_heroVillainSighting
    	FOREIGN KEY (heroVillain_id)
    	REFERENCES heroVillain(heroVillain_id)
);