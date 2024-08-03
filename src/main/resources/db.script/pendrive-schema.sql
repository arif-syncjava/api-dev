DROP TABLE IF EXISTS pendrives;
CREATE TABLE pendrives (
     key BIGSERIAL PRIMARY KEY,
     product_id VARCHAR NOT NULL,
     brand VARCHAR NOT NULL,
     storage VARCHAR NOT NULL,
     price VARCHAR NOT NULL
);