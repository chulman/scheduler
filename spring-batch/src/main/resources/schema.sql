DROP TABLE IF EXISTS T_BATCH;
CREATE TABLE T_BATCH (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  USER_NAME VARCHAR(255) NOT NULL,
  ADDRESS VARCHAR(255) NOT NULL,
  PHONE VARCHAR(255) NOT NULL,
  COUNTRY VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);
