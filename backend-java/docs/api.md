# EQ Backend API Contract (v0)

Base path: `/api`  
All request and response bodies are JSON.

---

## Conventions

- IDs are strings (UUID style or prefixed IDs like `room_123`)
- Timestamps are ISO-8601 strings (UTC), e.g. `2026-01-07T21:15:00Z`
- Budget enum: `$` | `$$` | `$$$`
- Vote value enum: `1` | `-1`
- Latitude range: -90..90
- Longitude range: -180..180
- Radius miles range: 1..10 (default 3 unless specified)

---

## Error Format

All errors return JSON in this shape:

```json
{
  "error": {
    "code": "ROOM_NOT_FOUND",
    "message": "Room not found.",
    "details": [
      { "field": "roomId", "issue": "No room exists with id=room_123" }
    ]
  }
}
```
Status code rules:
- 400: invalid input (bad enum, missing fields, out of range)
- 404: room or option not found
- 409: conflict (reserved for later; e.g., duplicate vote)
- 500: unexpected server error

---
## Rooms 
### POST '/api/rooms'

Request:
```json
{
"title": "Friday Night Plans",
"anchorLat": 38.0336,
"anchorLon": -78.5080,
"defaultRadiusMiles": 3
}
```
Validation:
- title: required, 1–80 characters 
- anchorLat: required, -90..90 
- anchorLon: required, -180..180 
- defaultRadiusMiles: optional, 1..10 (default 3)

Response — 201:
```json
{
  "room": {
    "id": "room_123",
    "title": "Friday Night Plans",
    "anchorLat": 38.0336,
    "anchorLon": -78.5080,
    "defaultRadiusMiles": 3,
    "createdAt": "2026-01-07T21:15:00Z"
  }
}
```

###  GET /api/rooms/{roomId}
````json
{
  "room": {
    "id": "room_123",
    "title": "Friday Night Plans",
    "anchorLat": 38.0336,
    "anchorLon": -78.5080,
    "defaultRadiusMiles": 3,
    "createdAt": "2026-01-07T21:15:00Z"
  },
  "responses": [
    {
      "id": "resp_456",
      "roomId": "room_123",
      "activityType": "FOOD",
      "budget": "$$",
      "radiusMiles": 4,
      "notes": "Prefer casual, not too loud",
      "createdAt": "2026-01-07T21:18:00Z"
    }
  ],
  "options": [
    {
      "id": "opt_1",
      "roomId": "room_123",
      "name": "Belle",
      "address": "XYZ St, Charlottesville, VA",
      "lat": 38.0,
      "lon": -78.5,
      "reason": "Matches $$ food and is nearby",
      "createdAt": "2026-01-07T21:25:00Z",
      "votes": {
        "up": 3,
        "down": 1,
        "score": 2
      }
    }
  ],
  "winner": {
    "optionId": "opt_1",
    "score": 2
  }
}
````
- votes are computed from stored vote records
- winner is null if no options exist

## Responses
### POST /api/rooms/{roomId}/responses
Submit a user preference to a room
Request:
````json
{
  "activityType": "FOOD",
  "budget": "$$",
  "radiusMiles": 4,
  "notes": "Prefer casual, not too loud"
}
````
#### Validation
- room must exist (404)
- activityType: enum (FOOD | COFFEE | BARS | OUTDOORS | STUDY | OTHER)
- budget: $ | $$ | $$$
- radiusMiles: optional, 1..10 (default: room default)
- notes: optional, max 200 characters
#### Response - 201:
````json
{
  "response": {
    "id": "resp_456",
    "roomId": "room_123",
    "activityType": "FOOD",
    "budget": "$$",
    "radiusMiles": 4,
    "notes": "Prefer casual, not too loud",
    "createdAt": "2026-01-07T21:18:00Z"
  }
}
````
## Recomendations
### POST /api/rooms/{roomId}/recommendations
Generate and store recommendation options for a room.
#### Behavior:
- Java gathers room anchor and all responses
- Java calls Python recommendation service
- Returned options are stored under the room

#### Request:
````json
{}
````
#### Validation
- room must exist (404)
- at least one response must exist (400 NO_RESPONSES)
#### Response - 201:
````json
{
  "summary": "Most people want $$ food within 4 miles.",
  "options": [
    {
      "id": "opt_1",
      "roomId": "room_123",
      "name": "Belle",
      "address": "XYZ St, Charlottesville, VA",
      "lat": 38.0,
      "lon": -78.5,
      "reason": "Matches $$ food and is nearby",
      "createdAt": "2026-01-07T21:25:00Z"
    }
  ]
}
````
## Voting
### POST /api/rooms/{roomId}/votes 
Vote on a recommendation option
#### Request: 
````json
{
  "optionId": "opt_1",
  "value": 1
}
````
#### Validation
- room must exist (404)
- option must exist in the room (404)
- value must be 1 or -1 (400)
#### Response - 201:
````json
{
  "vote": {
    "id": "vote_999",
    "roomId": "room_123",
    "optionId": "opt_1",
    "value": 1,
    "createdAt": "2026-01-07T21:30:00Z"
  }
}

````




 