--liquibase formatted sql

--changeset mathysjt:init_change_number_1 failOnError:true tag:create_table_test1
create table test1 (
id int primary key,
name varchar(255)
);

--changeset mathysjt:init_change_number_2 failOnError:true tag:create_table_test2
create table test2 (
id int primary key,
name varchar(255)
);
--rollback drop table test2

--changeset mathysjt:changeRollback2-create failOnError:true tag:create_table_changeRollback2
create table changeRollback2( 
id int
);
--rollback drop table changeRollback2

--changeset mathysjt:multiRollbackTest failOnError:true tag:rollback_multiRollbackTest
create table multiRollback1(
id int
);
create table multiRollback2(
id int
);
create table multiRollback3(
id int
);
--rollback drop table 
--rollback multiRollback1;
--rollback drop table 
--rollback multiRollback2;
--rollback drop table 
--rollback multiRollback3;


