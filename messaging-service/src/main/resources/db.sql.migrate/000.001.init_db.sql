CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS DIALOGUE_WATCHER;
DROP TABLE IF EXISTS MESSAGE_WATCHER;
DROP TABLE IF EXISTS ACCOUNT CASCADE;
DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS DIALOGUE CASCADE;
DROP TABLE IF EXISTS MESSAGE CASCADE;

CREATE TABLE USERS
(
    "id"       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "username" VARCHAR(256) UNIQUE NOT NULL,
    "password" VARCHAR(256)        NOT NULL
);

CREATE TABLE ACCOUNT
(
    "id"          UUID PRIMARY KEY,
    "first_name"  varchar(256) NOT NULL,
    "second_name" varchar(256) NOT NULL,
    "user_id"     UUID REFERENCES USERS ("id")
);

CREATE TABLE DIALOGUE
(
    "id"         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name"       VARCHAR(256) UNIQUE NOT NULL,
    "created_by" UUID REFERENCES ACCOUNT ("id")
);

CREATE TABLE MESSAGE
(
    "id"                UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "text"              TEXT NOT NULL,
    "dialogue_id"       UUID NOT NULL,
    "sender_account_id" UUID NOT NULL,
    "parent_message_id" UUID,
    CONSTRAINT fk_message_dialogue_id FOREIGN KEY ("dialogue_id") REFERENCES DIALOGUE ("id"),
    CONSTRAINT fk_message_parent_message_id FOREIGN KEY ("parent_message_id") REFERENCES message ("id"),
    CONSTRAINT fk_message_account_id FOREIGN KEY ("sender_account_id") REFERENCES message ("id")
);

CREATE TABLE MESSAGE_WATCHER
(
    "target_id"  UUID REFERENCES message ("id"),
    "watcher_id" UUID REFERENCES account ("id"),
    "deleted"    BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT pk_message_watcher PRIMARY KEY ("target_id", "watcher_id")
);

CREATE TABLE DIALOGUE_WATCHER
(
    "target_id"  UUID REFERENCES dialogue ("id"),
    "watcher_id" UUID references account ("id"),
    "deleted"    BOOL NOT NULL DEFAULT FALSE,
    PRIMARY KEY ("target_id", "watcher_id")
);