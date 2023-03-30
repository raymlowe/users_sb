--drop database app_auth;

CREATE DATABASE app_auth
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
   	template template0;
 
--maybe not set locales?
CREATE DATABASE app_auth
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
   	template template0;

--Once the database is created, you need to connect to it
   
--CREATE TABLE public.employees
--(
--    emp_no integer NOT NULL,
--    birth_date date NOT NULL,
--    first_name character varying(14) COLLATE pg_catalog."default" NOT NULL,
--    last_name character varying(16) COLLATE pg_catalog."default" NOT NULL,
--    hire_date date NOT NULL,
--    --gender gender_t,
--    CONSTRAINT employees_pkey PRIMARY KEY (emp_no)
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.employees
--    OWNER to postgres;
--    
--   
--CREATE SEQUENCE facility_id_seq
--    AS INT
--    START WITH 1
--    INCREMENT BY 1;
--   
--CREATE TABLE public.facility
--(
--    id bigint NOT NULL DEFAULT nextval('facility_id_seq'::regclass),
--    contractorname character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    idnumber character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    facilityname character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    address1 character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    address2 character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    city character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    province character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    postal character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    cntfrsname character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    cntlastname character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    "position" character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    phone character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    CONSTRAINT facility_pkey PRIMARY KEY (id)
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.facility
--    OWNER to postgres;
--    
--   
--CREATE SEQUENCE phone_id_seq
--    AS INT
--    START WITH 1
--    INCREMENT BY 1;
--   
--   CREATE TABLE public.phone
--(
--    id bigint NOT NULL DEFAULT nextval('phone_id_seq'::regclass),
--    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
--    review character varying(20) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
--    CONSTRAINT phone_pkey PRIMARY KEY (id)
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.phone
--    OWNER to postgres;
--    
--
--   CREATE SEQUENCE task_id_seq
--    AS INT
--    START WITH 1
--    INCREMENT BY 1;
--   
--CREATE TABLE public.task
--(
--    id bigint NOT NULL DEFAULT nextval('task_id_seq'::regclass),
--    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    taskdesc character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    priority character varying(50) COLLATE pg_catalog."default" NOT NULL,
--    CONSTRAINT task_pkey PRIMARY KEY (id)
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.task
--    OWNER to postgres;
--   
--   
--   CREATE SEQUENCE hibernate_sequence
--    AS INT
--    START WITH 1
--    INCREMENT BY 1;
--   
--CREATE TABLE public.tasks
--(
--    id integer NOT NULL DEFAULT nextval('hibernate_sequence'::regclass),
--    "user" character varying(100) COLLATE pg_catalog."default",
--    taskdesc character varying(250) COLLATE pg_catalog."default",
--    priority character varying(50) COLLATE pg_catalog."default"
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.tasks
--    OWNER to postgres;
--   
--   
--   CREATE SEQUENCE emp_tbl_id_seq
--    AS INT
--    START WITH 1
--    INCREMENT BY 1;
--   
--CREATE TABLE public.emp_tbl
--(
--    id integer NOT NULL DEFAULT nextval('emp_tbl_id_seq'::regclass),
--    name character varying(45) COLLATE pg_catalog."default" NOT NULL,
--    email character varying(45) COLLATE pg_catalog."default" NOT NULL,
--    address character varying(45) COLLATE pg_catalog."default" NOT NULL,
--    telephone character varying(45) COLLATE pg_catalog."default" NOT NULL,
--    CONSTRAINT emp_tbl_pkey PRIMARY KEY (id)
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.emp_tbl
--    OWNER to postgres;