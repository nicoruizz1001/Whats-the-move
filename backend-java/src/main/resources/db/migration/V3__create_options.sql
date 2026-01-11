CREATE TABLE options (
                         id TEXT PRIMARY KEY,
                         room_id TEXT NOT NULL REFERENCES rooms(id) ON DELETE CASCADE,
                         name TEXT NOT NULL,
                         address TEXT,
                         lat DOUBLE PRECISION NOT NULL,
                         lon DOUBLE PRECISION NOT NULL,
                         reason TEXT,
                         created_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_options_room_id ON options(room_id);
