CREATE TABLE responses (
                           id TEXT PRIMARY KEY,
                           room_id TEXT NOT NULL REFERENCES rooms(id) ON DELETE CASCADE,
                           activity_type TEXT NOT NULL,
                           budget TEXT NOT NULL,
                           radius_miles INTEGER,
                           notes TEXT,
                           created_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_responses_room_id ON responses(room_id);
