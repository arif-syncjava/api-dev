DROP TABLE IF EXISTS pendrives;
CREATE TABLE pendrives (
     primary_id BIGSERIAL PRIMARY KEY,
     id VARCHAR NOT NULL,
     brand VARCHAR NOT NULL,
     storage VARCHAR NOT NULL,
     price VARCHAR NOT NULL
);