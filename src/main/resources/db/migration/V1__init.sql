CREATE TABLE IF NOT EXISTS users (
        id SERIAL PRIMARY KEY,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    cell_number VARCHAR(10),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    is_driver boolean DEFAULT FALSE,
    identification VARCHAR(10) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL,
    role VARCHAR(10) NOT NULL
    );

CREATE TABLE IF NOT EXISTS driver (
        id SERIAL PRIMARY KEY,
    driver_licence VARCHAR(20) NOT NULL,
    user_id INT NOT NULL REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS vehicle (
        id SERIAL PRIMARY KEY,
    plate VARCHAR(10) NOT NULL,
    color VARCHAR(10) NOT NULL,
    model VARCHAR(10) NOT NULL,
    driver_id INT NOT NULL REFERENCES driver(id)
    );

CREATE TABLE IF NOT EXISTS route (
        id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    start_date DATE NOT NULL,
    description VARCHAR(400) NOT NULL,
    driver_id INT NOT NULL REFERENCES driver(id)
    );

CREATE TABLE IF NOT EXISTS stop (
        id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(400) NOT NULL
    );

CREATE TABLE IF NOT EXISTS route_stop (
        id SERIAL PRIMARY KEY,
    position INT NOT NULL DEFAULT 0,
    arrive_hour DATE NOT NULL,
    stop_id INT NOT NULL REFERENCES stop(id),
    route_id INT NOT NULL REFERENCES route(id)
    );