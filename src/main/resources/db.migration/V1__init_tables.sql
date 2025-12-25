DROP  TABLE IF EXISTS contact_m;
DROP  TABLE IF EXISTS prefecture_m;
DROP  TABLE IF EXISTS rgon_m;
DROP  TABLE IF EXISTS url_m;

CREATE TABLE rgon_m(
    `rgon_cd`       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'region code',
    `rgon_nm`       VARCHAR(255) NOT NULL COMMENT 'region name',
    `delete_flg`    VARCHAR(1)   NOT NULL COMMENT 'delete flag',
    `rgon_use_typ`  int          NOT NULL COMMENT 'region use type',
    `disp_order`    VARCHAR(2)   NOT NULL COMMENT 'disp order'
);
ALTER TABLE rgon_m ADD INDEX index_rgon_m_1(rgon_nm);

CREATE TABLE prefecture_m(
     `prefecture_cd` BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'prefecture code',
     `rgon_cd`       BIGINT       NOT NULL COMMENT 'region code',
     `prefecture_nm` VARCHAR(255) NOT NULL COMMENT 'prefecture name'
);
ALTER TABLE prefecture_m ADD INDEX index_prefecture_m_1(rgon_cd);

CREATE TABLE contact_m(
      `ref_cd`        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ref code',
      `ref_nm`        VARCHAR(255) NOT NULL COMMENT 'ref name',
      `ref_kn`        VARCHAR(255) NOT NULL COMMENT 'ref name kana',
      `tel_no`        VARCHAR(45)  NOT NULL COMMENT 'tel number',
      `prefecture_cd` BIGINT       NOT NULL COMMENT 'prefecture code'
);
ALTER TABLE contact_m ADD INDEX index_contact_m_1(ref_nm);
ALTER TABLE contact_m ADD INDEX index_contact_m_2(ref_kn);
ALTER TABLE contact_m ADD INDEX index_contact_m_3(prefecture_cd);

CREATE TABLE url_m(
      `id`            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
      `url`           LONGTEXT     NOT NULL COMMENT 'HTML of screen',
      `screen_cd`     INT          NOT NULL COMMENT 'screen code',
      `screen_nm`     VARCHAR(255) NOT NULL COMMENT 'screen name'
);
ALTER TABLE url_m ADD INDEX index_url_m_1(screen_cd);


ALTER TABLE  prefecture_m ADD CONSTRAINT fk_prefecture_region FOREIGN KEY (rgon_cd) REFERENCES rgon_m(rgon_cd);
ALTER TABLE  contact_m ADD CONSTRAINT fk_contact_prefecture FOREIGN KEY (prefecture_cd) REFERENCES prefecture_m(prefecture_cd);