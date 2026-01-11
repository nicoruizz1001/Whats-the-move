CREATE TABLE votes (
                       id TEXT PRIMARY KEY,
                       room_id TEXT NOT NULL REFERENCES rooms(id) ON DELETE CASCADE,
                       option_id TEXT NOT NULL REFERENCES options(id) ON DELETE CASCADE,
                       value INTEGER NOT NULL,
                       created_at TIMESTAMPTZ NOT NULL,
                       CONSTRAINT chk_vote_value CHECK (value IN (1, -1))
);

CREATE INDEX idx_votes_room_id ON votes(room_id);
CREATE INDEX idx_votes_option_id ON votes(option_id);
