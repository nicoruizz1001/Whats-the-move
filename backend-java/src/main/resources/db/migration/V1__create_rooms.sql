CREATE TABLE rooms (
                       id TEXT PRIMARY KEY,
                       title TEXT NOT NULL,
                       anchor_lat DOUBLE PRECISION NOT NULL,
                       anchor_lon DOUBLE PRECISION NOT NULL,
                       default_radius_miles INTEGER NOT NULL,
                       created_at TIMESTAMPTZ NOT NULL
);
