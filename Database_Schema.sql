
USE CSCI5308_18_PRODUCTION ;
-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`BasePasswordPolicyConfig`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS BasePasswordPolicyConfig (
  `POLICY_NAME` VARCHAR(255) NOT NULL,
  `IS_ENABLED` TINYINT(1) NULL DEFAULT NULL,
  `POLICY_VALUE` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`POLICY_NAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`HistoryPasswordPolicyConfig`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HistoryPasswordPolicyConfig (
  `POLICY_NAME` VARCHAR(255) NOT NULL,
  `IS_ENABLED` TINYINT(1) NULL DEFAULT NULL,
  `POLICY_VALUE` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`POLICY_NAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
  `bannerid` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `emailid` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`bannerid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS course(
  `courseid` INT(11) NOT NULL,
  `coursename` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`courseid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS survey (
  `surveyid` INT(11) NOT NULL AUTO_INCREMENT,
  `courseid` INT(11) NULL DEFAULT NULL,
  `state` TINYINT(4) NULL DEFAULT NULL,
  `groupsize` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`surveyid`),
  INDEX `survey_ibfk_1` (`courseid` ASC),
  CONSTRAINT `survey_ibfk_1`
    FOREIGN KEY (`courseid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`course` (`courseid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`groupingoptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS groupingoptions (
  `logicid` INT(11) NOT NULL AUTO_INCREMENT,
  `logicname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`logicid`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`questiontype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS questiontype (
  `questiontypeid` INT(11) NOT NULL AUTO_INCREMENT,
  `questiontypename` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`questiontypeid`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS questions (
  `questionid` INT(11) NOT NULL AUTO_INCREMENT,
  `bannerid` VARCHAR(45) NULL DEFAULT NULL,
  `questiontypeid` INT(11) NULL DEFAULT NULL,
  `qtitle` VARCHAR(255) NULL DEFAULT NULL,
  `question` TINYTEXT NULL DEFAULT NULL,
  `datecreated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`questionid`),
  INDEX `qtitleid` (`qtitle` ASC),
  INDEX `questiontypeid` (`questiontypeid` ASC),
  INDEX `bannerid` (`bannerid` ASC),
  CONSTRAINT `questions_ibfk_1`
    FOREIGN KEY (`questiontypeid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`questiontype` (`questiontypeid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `questions_ibfk_2`
    FOREIGN KEY (`bannerid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`user` (`bannerid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`survey_questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS survey_questions (
  `surveyquestionid` INT(11) NOT NULL AUTO_INCREMENT,
  `surveyid` INT(11) NULL DEFAULT NULL,
  `questionid` INT(11) NULL DEFAULT NULL,
  `datequestionadded` DATETIME NULL DEFAULT NULL,
  `logicid` INT(11) NULL DEFAULT NULL,
  `logicvalue` INT(11) NULL DEFAULT NULL,
  `priority` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`surveyquestionid`),
  INDEX `survey_questions_ibfk_3` (`questionid` ASC),
  INDEX `survey_questions_ibfk_1` (`surveyid` ASC),
  INDEX `survey_questions_ibfk_2` (`logicid` ASC),
  CONSTRAINT `survey_questions_ibfk_1`
    FOREIGN KEY (`surveyid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`survey` (`surveyid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `survey_questions_ibfk_2`
    FOREIGN KEY (`logicid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`groupingoptions` (`logicid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `survey_questions_ibfk_3`
    FOREIGN KEY (`questionid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`questions` (`questionid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 300
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS answers (
  `answerid` INT(11) NOT NULL AUTO_INCREMENT,
  `bannerid` VARCHAR(45) NULL DEFAULT NULL,
  `answer` VARCHAR(255) NULL DEFAULT NULL,
  `surveyquestionid` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`answerid`),
  INDEX `bannerid` (`bannerid` ASC),
  INDEX `answers_ibfk_2` (`surveyquestionid` ASC),
  CONSTRAINT `answers_ibfk_1`
    FOREIGN KEY (`bannerid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`user` (`bannerid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `answers_ibfk_2`
    FOREIGN KEY (`surveyquestionid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`survey_questions` (`surveyquestionid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 56
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS role (
  `roleid` INT(11) NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`roleid`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`courserole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS courserole (
  `courseroleid` INT(11) NOT NULL AUTO_INCREMENT,
  `roleid` INT(11) NULL DEFAULT NULL,
  `courseid` INT(11) NULL DEFAULT NULL,
  `bannerid` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`courseroleid`),
  INDEX `roleid` (`roleid` ASC),
  INDEX `courseid` (`courseid` ASC),
  INDEX `bannerid` (`bannerid` ASC),
  CONSTRAINT `courserole_ibfk_4`
    FOREIGN KEY (`roleid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`role` (`roleid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `courserole_ibfk_5`
    FOREIGN KEY (`courseid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`course` (`courseid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `courserole_ibfk_6`
    FOREIGN KEY (`bannerid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`user` (`bannerid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 127
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`groupformationresults`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS groupformationresults (
  `groupid` INT(11) NOT NULL AUTO_INCREMENT,
  `surveyid` INT(11) NULL DEFAULT NULL,
  `groupname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`groupid`),
  INDEX `surveyid` (`surveyid` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`groupmembers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS groupmembers (
  `groupmemberid` INT(11) NOT NULL AUTO_INCREMENT,
  `groupid` INT(11) NULL DEFAULT NULL,
  `bannerid` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`groupmemberid`),
  INDEX `groupmembers_ibfk_2` (`groupid` ASC),
  INDEX `groupmembers_ibfk_1` (`bannerid` ASC),
  CONSTRAINT `groupmembers_ibfk_1`
    FOREIGN KEY (`bannerid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`user` (`bannerid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `groupmembers_ibfk_2`
    FOREIGN KEY (`groupid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`groupformationresults` (`groupid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS options (
  `optionid` INT(11) NOT NULL AUTO_INCREMENT,
  `questionid` INT(11) NULL DEFAULT NULL,
  `optiontext` VARCHAR(45) NULL DEFAULT NULL,
  `optionlinenumber` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`optionid`),
  INDEX `questionid` (`questionid` ASC),
  CONSTRAINT `options_ibfk_1`
    FOREIGN KEY (`questionid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`questions` (`questionid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 72
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`passwords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS passwords (
  `passwordid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `bannerid` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `createddate` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`passwordid`),
  INDEX `passwords_ibfk_1` (`bannerid` ASC),
  CONSTRAINT `passwords_ibfk_1`
    FOREIGN KEY (`bannerid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`user` (`bannerid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `CSCI5308_18_PRODUCTION`.`systemrole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS systemrole (
  `systemroleid` INT(11) NOT NULL AUTO_INCREMENT,
  `roleid` INT(11) NULL DEFAULT NULL,
  `bannerid` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`systemroleid`),
  INDEX `systemrole_ibfk_2` (`bannerid` ASC),
  INDEX `roleid` (`roleid` ASC),
  CONSTRAINT `systemrole_ibfk_2`
    FOREIGN KEY (`bannerid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`user` (`bannerid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `systemrole_ibfk_3`
    FOREIGN KEY (`roleid`)
    REFERENCES `CSCI5308_18_PRODUCTION`.`role` (`roleid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 74
DEFAULT CHARACTER SET = latin1;
